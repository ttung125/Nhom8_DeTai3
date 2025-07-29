/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.action;

import com.mycompany.quanlydoituongdacbiet.entity.ExamBlock;
import com.mycompany.quanlydoituongdacbiet.entity.ExamBlockXML;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Lớp ManagerExamBlock để quản lý danh sách khối thi
 * Thực hiện các chức năng CRUD và lưu trữ XML
 * 
 * @author PC
 */
public class ManagerExamBlock {
    
    private static final String EXAM_BLOCK_FILE_NAME = "data/ExamBlocks.xml";
    private List<ExamBlock> listExamBlocks;

    /**
     * Constructor
     */
    public ManagerExamBlock() {
        this.listExamBlocks = new ArrayList<>();
        loadFromXML();
        
        // Nếu file trống, tạo dữ liệu mặc định
        if (listExamBlocks.isEmpty()) {
            initializeDefaultExamBlocks();
        }
    }

    /**
     * Getter cho danh sách khối thi
     * 
     * @return danh sách khối thi
     */
    public List<ExamBlock> getListExamBlocks() {
        return listExamBlocks;
    }

    /**
     * Setter cho danh sách khối thi
     * 
     * @param listExamBlocks danh sách khối thi mới
     */
    public void setListExamBlocks(List<ExamBlock> listExamBlocks) {
        this.listExamBlocks = listExamBlocks;
    }

    /**
     * Thêm khối thi mới
     * 
     * @param examBlock khối thi cần thêm
     * @return true nếu thêm thành công, false nếu mã khối đã tồn tại
     */
    public boolean add(ExamBlock examBlock) {
        // Kiểm tra trùng mã khối
        if (isExamBlockCodeExists(examBlock.getBlockCode())) {
            return false;
        }
        
        listExamBlocks.add(examBlock);
        saveToXML();
        return true;
    }

    /**
     * Cập nhật thông tin khối thi
     * 
     * @param examBlock khối thi cần cập nhật
     * @return true nếu cập nhật thành công, false nếu không tìm thấy
     */
    public boolean edit(ExamBlock examBlock) {
        int index = getExamBlockIndexByCode(examBlock.getBlockCode());
        if (index >= 0) {
            listExamBlocks.set(index, examBlock);
            saveToXML();
            return true;
        }
        return false;
    }

    /**
     * Xóa khối thi theo mã
     * 
     * @param blockCode mã khối thi cần xóa
     * @return true nếu xóa thành công, false nếu không tìm thấy
     */
    public boolean delete(String blockCode) {
        int index = getExamBlockIndexByCode(blockCode);
        if (index >= 0) {
            listExamBlocks.remove(index);
            saveToXML();
            return true;
        }
        return false;
    }

    /**
     * Tìm khối thi theo mã
     * 
     * @param blockCode mã khối thi
     * @return khối thi nếu tìm thấy, null nếu không tìm thấy
     */
    public ExamBlock findByCode(String blockCode) {
        return listExamBlocks.stream()
                .filter(e -> e.getBlockCode().equals(blockCode))
                .findFirst()
                .orElse(null);
    }

    /**
     * Tìm kiếm khối thi theo tên (gần đúng)
     * 
     * @param name tên khối cần tìm
     * @return danh sách khối thi có tên chứa từ khóa
     */
    public List<ExamBlock> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>(listExamBlocks);
        }
        
        String keyword = name.toLowerCase().trim();
        return listExamBlocks.stream()
                .filter(e -> e.getBlockName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Tìm kiếm khối thi theo số lượng môn
     * 
     * @param minSubjects số môn tối thiểu
     * @param maxSubjects số môn tối đa
     * @return danh sách khối thi trong khoảng số môn
     */
    public List<ExamBlock> searchBySubjectCountRange(int minSubjects, int maxSubjects) {
        return listExamBlocks.stream()
                .filter(e -> e.getSubjectCodes().size() >= minSubjects && 
                           e.getSubjectCodes().size() <= maxSubjects)
                .collect(Collectors.toList());
    }

    /**
     * Sắp xếp danh sách khối thi theo tên
     * 
     * @param ascending true nếu sắp xếp tăng dần, false nếu giảm dần
     */
    public void sortByName(boolean ascending) {
        Comparator<ExamBlock> comparator = Comparator.comparing(ExamBlock::getBlockName);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        Collections.sort(listExamBlocks, comparator);
        saveToXML();
    }

    /**
     * Sắp xếp danh sách khối thi theo mã
     * 
     * @param ascending true nếu sắp xếp tăng dần, false nếu giảm dần
     */
    public void sortByCode(boolean ascending) {
        Comparator<ExamBlock> comparator = Comparator.comparing(ExamBlock::getBlockCode);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        Collections.sort(listExamBlocks, comparator);
        saveToXML();
    }

    /**
     * Thống kê tổng số khối thi
     * 
     * @return tổng số khối thi
     */
    public int getTotalExamBlocks() {
        return listExamBlocks.size();
    }

    /**
     * Thống kê số môn nhiều nhất trong các khối
     * 
     * @return số môn nhiều nhất, -1 nếu danh sách rỗng
     */
    public int getMaxSubjectCount() {
        return listExamBlocks.stream()
                .mapToInt(e -> e.getSubjectCodes().size())
                .max()
                .orElse(-1);
    }

    /**
     * Thống kê số môn ít nhất trong các khối
     * 
     * @return số môn ít nhất, -1 nếu danh sách rỗng
     */
    public int getMinSubjectCount() {
        return listExamBlocks.stream()
                .mapToInt(e -> e.getSubjectCodes().size())
                .min()
                .orElse(-1);
    }

    /**
     * Kiểm tra mã khối thi đã tồn tại chưa
     * 
     * @param blockCode mã khối thi cần kiểm tra
     * @return true nếu đã tồn tại, false nếu chưa tồn tại
     */
    private boolean isExamBlockCodeExists(String blockCode) {
        return listExamBlocks.stream()
                .anyMatch(e -> e.getBlockCode().equals(blockCode));
    }

    /**
     * Lấy chỉ số của khối thi trong danh sách theo mã
     * 
     * @param blockCode mã khối thi
     * @return chỉ số trong danh sách, -1 nếu không tìm thấy
     */
    private int getExamBlockIndexByCode(String blockCode) {
        for (int i = 0; i < listExamBlocks.size(); i++) {
            if (listExamBlocks.get(i).getBlockCode().equals(blockCode)) {
                return i;
            }
        }
        return -1;
    }
    

    /**
     * Khởi tạo dữ liệu khối thi mặc định
     */
    private void initializeDefaultExamBlocks() {
        // Khối A
        List<String> blockASubjects = new ArrayList<>();
        blockASubjects.add("TOAN");
        blockASubjects.add("LY");
        blockASubjects.add("HOA");
        listExamBlocks.add(new ExamBlock("A", "Khối A", blockASubjects, "Khối tự nhiên gồm Toán, Lý, Hóa"));
        
        // Khối B
        List<String> blockBSubjects = new ArrayList<>();
        blockBSubjects.add("TOAN");
        blockBSubjects.add("HOA");
        blockBSubjects.add("SINH");
        listExamBlocks.add(new ExamBlock("B", "Khối B", blockBSubjects, "Khối gồm Toán, Hóa, Sinh"));
        
        // Khối C
        List<String> blockCSubjects = new ArrayList<>();
        blockCSubjects.add("VAN");
        blockCSubjects.add("SU");
        blockCSubjects.add("DIA");
        listExamBlocks.add(new ExamBlock("C", "Khối C", blockCSubjects, "Khối xã hội gồm Văn, Sử, Địa"));
        
        // Khối D
        List<String> blockDSubjects = new ArrayList<>();
        blockDSubjects.add("TOAN");
        blockDSubjects.add("VAN");
        blockDSubjects.add("TA");
        listExamBlocks.add(new ExamBlock("D", "Khối D", blockDSubjects, "Khối gồm Toán, Văn, Tiếng Anh"));
        
        saveToXML();
    }

    /**
     * Lưu danh sách khối thi vào file XML
     */
    private void saveToXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(ExamBlockXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new ExamBlockXML(listExamBlocks), new File(EXAM_BLOCK_FILE_NAME));
        } catch (JAXBException e) {
            System.err.println("Lỗi khi lưu file XML: " + e.getMessage());
        }
    }

    /**
     * Tải danh sách khối thi từ file XML
     */
    private void loadFromXML() {
        try {
            File file = new File(EXAM_BLOCK_FILE_NAME);
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(ExamBlockXML.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                ExamBlockXML examBlockXML = (ExamBlockXML) unmarshaller.unmarshal(file);
                
                if (examBlockXML.getExamBlocks() != null) {
                    this.listExamBlocks = examBlockXML.getExamBlocks();
                }
            }
        } catch (JAXBException e) {
            System.err.println("Lỗi khi đọc file XML: " + e.getMessage());
        }
    }
    
    // ===== ALIAS METHODS FOR CONTROLLER COMPATIBILITY =====
    
    /**
     * Alias method for add() to maintain compatibility with ExamBlockController
     */
    public boolean addExamBlock(ExamBlock examBlock) {
        return add(examBlock);
    }
    
    /**
     * Alias method for edit/update operation to maintain compatibility
     */
    public boolean editExamBlock(ExamBlock examBlock) {
        // Find and update existing exam block
        for (int i = 0; i < listExamBlocks.size(); i++) {
            if (listExamBlocks.get(i).getBlockCode().equals(examBlock.getBlockCode())) {
                listExamBlocks.set(i, examBlock);
                saveToXML();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Alias method for delete by code to maintain compatibility
     */
    public boolean deleteExamBlock(String blockCode) {
        return delete(blockCode);
    }
    
    /**
     * Alias method for findByCode() to maintain compatibility
     */
    public ExamBlock findExamBlockByCode(String blockCode) {
        return findByCode(blockCode);
    }
    
    /**
     * Alias method for searchByName() to maintain compatibility
     */
    public List<ExamBlock> searchExamBlocksByName(String name) {
        return searchByName(name);
    }
    
    /**
     * Alias method for searchBySubjectCountRange() to maintain compatibility
     */
    public List<ExamBlock> searchExamBlocksBySubjectCount(int minSubjects, int maxSubjects) {
        return searchBySubjectCountRange(minSubjects, maxSubjects);
    }
    
    /**
     * Alias method for sortByName() to maintain compatibility
     */
    public List<ExamBlock> sortExamBlocksByName() {
        sortByName(true); // Sắp xếp tăng dần
        return new ArrayList<>(listExamBlocks);
    }

    public List<String> getExamBlockNames() {
        List<String> names = new ArrayList<>();
        for (ExamBlock block : listExamBlocks) {
            names.add(block.getBlockName()); // hoặc getCode() nếu bạn dùng mã khối
        }
        return names;
    }
}
