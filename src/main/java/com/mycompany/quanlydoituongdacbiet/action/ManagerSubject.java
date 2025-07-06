/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.action;

import com.mycompany.quanlydoituongdacbiet.entity.Subject;
import com.mycompany.quanlydoituongdacbiet.entity.SubjectXML;
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
 * Lớp ManagerSubject để quản lý danh sách môn thi
 * Thực hiện các chức năng CRUD và lưu trữ XML
 * 
 * @author PC
 */
public class ManagerSubject {
    
    private static final String SUBJECT_FILE_NAME = "data/Subjects.xml";
    private List<Subject> listSubjects;

    /**
     * Constructor
     */
    public ManagerSubject() {
        this.listSubjects = new ArrayList<>();
        loadFromXML();
        
        // Nếu file trống, tạo dữ liệu mặc định
        if (listSubjects.isEmpty()) {
            initializeDefaultSubjects();
        }
    }

    /**
     * Getter cho danh sách môn thi
     * 
     * @return danh sách môn thi
     */
    public List<Subject> getListSubjects() {
        return listSubjects;
    }

    /**
     * Setter cho danh sách môn thi
     * 
     * @param listSubjects danh sách môn thi mới
     */
    public void setListSubjects(List<Subject> listSubjects) {
        this.listSubjects = listSubjects;
    }

    /**
     * Thêm môn thi mới
     * 
     * @param subject môn thi cần thêm
     * @return true nếu thêm thành công, false nếu mã môn đã tồn tại
     */
    public boolean add(Subject subject) {
        // Kiểm tra trùng mã môn
        if (isSubjectCodeExists(subject.getSubjectCode())) {
            return false;
        }
        
        listSubjects.add(subject);
        saveToXML();
        return true;
    }

    /**
     * Cập nhật thông tin môn thi
     * 
     * @param subject môn thi cần cập nhật
     * @return true nếu cập nhật thành công, false nếu không tìm thấy
     */
    public boolean edit(Subject subject) {
        int index = getSubjectIndexByCode(subject.getSubjectCode());
        if (index >= 0) {
            listSubjects.set(index, subject);
            saveToXML();
            return true;
        }
        return false;
    }

    /**
     * Xóa môn thi theo mã
     * 
     * @param subjectCode mã môn thi cần xóa
     * @return true nếu xóa thành công, false nếu không tìm thấy
     */
    public boolean delete(String subjectCode) {
        int index = getSubjectIndexByCode(subjectCode);
        if (index >= 0) {
            listSubjects.remove(index);
            saveToXML();
            return true;
        }
        return false;
    }

    /**
     * Tìm môn thi theo mã
     * 
     * @param subjectCode mã môn thi
     * @return môn thi nếu tìm thấy, null nếu không tìm thấy
     */
    public Subject findByCode(String subjectCode) {
        return listSubjects.stream()
                .filter(s -> s.getSubjectCode().equals(subjectCode))
                .findFirst()
                .orElse(null);
    }

    /**
     * Tìm kiếm môn thi theo tên (gần đúng)
     * 
     * @param name tên môn cần tìm
     * @return danh sách môn thi có tên chứa từ khóa
     */
    public List<Subject> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>(listSubjects);
        }
        
        String keyword = name.toLowerCase().trim();
        return listSubjects.stream()
                .filter(s -> s.getSubjectName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Tìm kiếm môn thi theo khoảng thời gian thi
     * 
     * @param minDuration thời gian tối thiểu (phút)
     * @param maxDuration thời gian tối đa (phút)
     * @return danh sách môn thi trong khoảng thời gian
     */
    public List<Subject> searchByDurationRange(int minDuration, int maxDuration) {
        return listSubjects.stream()
                .filter(s -> s.getDuration() >= minDuration && s.getDuration() <= maxDuration)
                .collect(Collectors.toList());
    }

    /**
     * Sắp xếp danh sách môn thi theo tên
     * 
     * @param ascending true nếu sắp xếp tăng dần, false nếu giảm dần
     */
    public void sortByName(boolean ascending) {
        Comparator<Subject> comparator = Comparator.comparing(Subject::getSubjectName);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        Collections.sort(listSubjects, comparator);
        saveToXML();
    }

    /**
     * Sắp xếp danh sách môn thi theo mã
     * 
     * @param ascending true nếu sắp xếp tăng dần, false nếu giảm dần
     */
    public void sortByCode(boolean ascending) {
        Comparator<Subject> comparator = Comparator.comparing(Subject::getSubjectCode);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        Collections.sort(listSubjects, comparator);
        saveToXML();
    }

    /**
     * Thống kê tổng số môn thi
     * 
     * @return tổng số môn thi
     */
    public int getTotalSubjects() {
        return listSubjects.size();
    }

    /**
     * Thống kê thời gian thi dài nhất
     * 
     * @return thời gian thi dài nhất (phút), -1 nếu danh sách rỗng
     */
    public int getMaxDuration() {
        return listSubjects.stream()
                .mapToInt(Subject::getDuration)
                .max()
                .orElse(-1);
    }

    /**
     * Thống kê thời gian thi ngắn nhất
     * 
     * @return thời gian thi ngắn nhất (phút), -1 nếu danh sách rỗng
     */
    public int getMinDuration() {
        return listSubjects.stream()
                .mapToInt(Subject::getDuration)
                .min()
                .orElse(-1);
    }

    /**
     * Thống kê điểm tối đa cao nhất
     * 
     * @return điểm tối đa cao nhất, -1 nếu danh sách rỗng
     */
    public double getMaxScore() {
        return listSubjects.stream()
                .mapToDouble(Subject::getMaxScore)
                .max()
                .orElse(-1);
    }

    /**
     * Thống kê điểm tối đa thấp nhất
     * 
     * @return điểm tối đa thấp nhất, -1 nếu danh sách rỗng
     */
    public double getMinScore() {
        return listSubjects.stream()
                .mapToDouble(Subject::getMaxScore)
                .min()
                .orElse(-1);
    }

    /**
     * Kiểm tra mã môn thi đã tồn tại chưa
     * 
     * @param subjectCode mã môn thi cần kiểm tra
     * @return true nếu đã tồn tại, false nếu chưa tồn tại
     */
    private boolean isSubjectCodeExists(String subjectCode) {
        return listSubjects.stream()
                .anyMatch(s -> s.getSubjectCode().equals(subjectCode));
    }

    /**
     * Lấy chỉ số của môn thi trong danh sách theo mã
     * 
     * @param subjectCode mã môn thi
     * @return chỉ số trong danh sách, -1 nếu không tìm thấy
     */
    private int getSubjectIndexByCode(String subjectCode) {
        for (int i = 0; i < listSubjects.size(); i++) {
            if (listSubjects.get(i).getSubjectCode().equals(subjectCode)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Khởi tạo dữ liệu môn thi mặc định
     */
    private void initializeDefaultSubjects() {
        listSubjects.add(new Subject("TOAN", "Toán học", 180, 10.0, "Môn Toán trong kỳ thi THPT"));
        listSubjects.add(new Subject("LY", "Vật lý", 180, 10.0, "Môn Vật lý trong kỳ thi THPT"));
        listSubjects.add(new Subject("HOA", "Hóa học", 180, 10.0, "Môn Hóa học trong kỳ thi THPT"));
        listSubjects.add(new Subject("SINH", "Sinh học", 180, 10.0, "Môn Sinh học trong kỳ thi THPT"));
        listSubjects.add(new Subject("VAN", "Ngữ văn", 180, 10.0, "Môn Ngữ văn trong kỳ thi THPT"));
        listSubjects.add(new Subject("SU", "Lịch sử", 180, 10.0, "Môn Lịch sử trong kỳ thi THPT"));
        listSubjects.add(new Subject("DIA", "Địa lý", 180, 10.0, "Môn Địa lý trong kỳ thi THPT"));
        listSubjects.add(new Subject("GDCD", "Giáo dục công dân", 180, 10.0, "Môn GDCD trong kỳ thi THPT"));
        listSubjects.add(new Subject("TA", "Tiếng Anh", 180, 10.0, "Môn Tiếng Anh trong kỳ thi THPT"));
        saveToXML();
    }

    /**
     * Lưu danh sách môn thi vào file XML
     */
    private void saveToXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(SubjectXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new SubjectXML(listSubjects), new File(SUBJECT_FILE_NAME));
        } catch (JAXBException e) {
            System.err.println("Lỗi khi lưu file XML: " + e.getMessage());
        }
    }

    /**
     * Tải danh sách môn thi từ file XML
     */
    private void loadFromXML() {
        try {
            File file = new File(SUBJECT_FILE_NAME);
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(SubjectXML.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                SubjectXML subjectXML = (SubjectXML) unmarshaller.unmarshal(file);
                
                if (subjectXML.getSubjects() != null) {
                    this.listSubjects = subjectXML.getSubjects();
                }
            }
        } catch (JAXBException e) {
            System.err.println("Lỗi khi đọc file XML: " + e.getMessage());
        }
    }

    /**
     * Sắp xếp danh sách môn thi theo tên
     * 
     * @return danh sách môn thi đã sắp xếp
     */
    public List<Subject> sortByName() {
        sortByName(true);
        return new ArrayList<>(listSubjects);
    }

    /**
     * Sắp xếp danh sách môn thi theo mã
     * 
     * @return danh sách môn thi đã sắp xếp
     */
    public List<Subject> sortByCode() {
        sortByCode(true);
        return new ArrayList<>(listSubjects);
    }
    
    // ===== ALIAS METHODS FOR CONTROLLER COMPATIBILITY =====
    
    /**
     * Alias method for add() to maintain compatibility with SubjectController
     */
    public boolean addSubject(Subject subject) {
        return add(subject);
    }
    
    /**
     * Alias method for edit/update operation to maintain compatibility
     */
    public boolean editSubject(Subject subject) {
        // Find and update existing subject
        for (int i = 0; i < listSubjects.size(); i++) {
            if (listSubjects.get(i).getSubjectCode().equals(subject.getSubjectCode())) {
                listSubjects.set(i, subject);
                saveToXML();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Alias method for delete by code to maintain compatibility
     */
    public boolean deleteSubject(String subjectCode) {
        return delete(subjectCode);
    }
    
    /**
     * Alias method for findByCode() to maintain compatibility
     */
    public Subject findSubjectByCode(String subjectCode) {
        return findByCode(subjectCode);
    }
    
    /**
     * Alias method for searchByName() to maintain compatibility
     */
    public List<Subject> searchSubjectsByName(String name) {
        return searchByName(name);
    }
    
    /**
     * Alias method for searchByDurationRange() to maintain compatibility
     */
    public List<Subject> searchSubjectsByTimeRange(int minTime, int maxTime) {
        return searchByDurationRange(minTime, maxTime);
    }
    
    /**
     * Alias method for sortByName() to maintain compatibility
     */
    public List<Subject> sortSubjectsByName() {
        return sortByName();
    }
}
