/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.action;

import com.mycompany.quanlydoituongdacbiet.entity.Student;
import com.mycompany.quanlydoituongdacbiet.entity.StudentXML;
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
 * Lớp ManagerStudent để quản lý danh sách thí sinh
 * Thực hiện các chức năng CRUD và lưu trữ XML
 * 
 * @author PC
 */
public class ManagerStudent {
    
    private static final String STUDENT_FILE_NAME = "data/Students.xml";
    private List<Student> listStudents;

    /**
     * Constructor
     */
    public ManagerStudent() {
        this.listStudents = new ArrayList<>();
        loadFromXML();
    }

    /**
     * Getter cho danh sách thí sinh
     * 
     * @return danh sách thí sinh
     */
    public List<Student> getListStudents() {
        return listStudents;
    }

    /**
     * Setter cho danh sách thí sinh
     * 
     * @param listStudents danh sách thí sinh mới
     */
    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }

    /**
     * Thêm thí sinh mới
     * 
     * @param student thí sinh cần thêm
     * @return true nếu thêm thành công, false nếu ID đã tồn tại
     */
    public boolean add(Student student) {
        // Kiểm tra trùng mã thí sinh
        if (isStudentIdExists(student.getStudentId())) {
            return false;
        }
        
        listStudents.add(student);
        saveToXML();
        return true;
    }

    /**
     * Cập nhật thông tin thí sinh
     * 
     * @param student thí sinh cần cập nhật
     * @return true nếu cập nhật thành công, false nếu không tìm thấy
     */
    public boolean edit(Student student) {
        int index = getStudentIndexById(student.getStudentId());
        if (index >= 0) {
            listStudents.set(index, student);
            saveToXML();
            return true;
        }
        return false;
    }

    /**
     * Xóa thí sinh theo mã
     * 
     * @param studentId mã thí sinh cần xóa
     * @return true nếu xóa thành công, false nếu không tìm thấy
     */
    public boolean delete(String studentId) {
        int index = getStudentIndexById(studentId);
        if (index >= 0) {
            listStudents.remove(index);
            saveToXML();
            return true;
        }
        return false;
    }

    /**
     * Tìm thí sinh theo mã
     * 
     * @param studentId mã thí sinh
     * @return thí sinh nếu tìm thấy, null nếu không tìm thấy
     */
    public Student findById(String studentId) {
        return listStudents.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Tìm kiếm thí sinh theo tên (gần đúng)
     * 
     * @param name tên cần tìm
     * @return danh sách thí sinh có tên chứa từ khóa
     */
    public List<Student> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>(listStudents);
        }
        
        String keyword = name.toLowerCase().trim();
        return listStudents.stream()
                .filter(s -> s.getFullName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Tìm kiếm thí sinh theo khoảng tuổi
     * 
     * @param minAge tuổi tối thiểu
     * @param maxAge tuổi tối đa
     * @return danh sách thí sinh trong khoảng tuổi
     */
    public List<Student> searchByAgeRange(int minAge, int maxAge) {
        return listStudents.stream()
                .filter(s -> s.getAge() >= minAge && s.getAge() <= maxAge)
                .collect(Collectors.toList());
    }

    /**
     * Sắp xếp danh sách thí sinh theo tên
     * 
     * @param ascending true nếu sắp xếp tăng dần, false nếu giảm dần
     */
    public void sortByName(boolean ascending) {
        Comparator<Student> comparator = Comparator.comparing(Student::getFullName);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        Collections.sort(listStudents, comparator);
        saveToXML();
    }

    /**
     * Sắp xếp danh sách thí sinh theo mã
     * 
     * @param ascending true nếu sắp xếp tăng dần, false nếu giảm dần
     */
    public void sortById(boolean ascending) {
        Comparator<Student> comparator = Comparator.comparing(Student::getStudentId);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        Collections.sort(listStudents, comparator);
        saveToXML();
    }

    /**
     * Thống kê tổng số thí sinh
     * 
     * @return tổng số thí sinh
     */
    public int getTotalStudents() {
        return listStudents.size();
    }

    /**
     * Thống kê số thí sinh nam
     * 
     * @return số thí sinh nam
     */
    public long getMaleStudents() {
        return listStudents.stream()
                .filter(s -> "Nam".equals(s.getGender()))
                .count();
    }

    /**
     * Thống kê số thí sinh nữ
     * 
     * @return số thí sinh nữ
     */
    public long getFemaleStudents() {
        return listStudents.stream()
                .filter(s -> "Nữ".equals(s.getGender()))
                .count();
    }

    /**
     * Thống kê tuổi lớn nhất
     * 
     * @return tuổi lớn nhất, -1 nếu danh sách rỗng
     */
    public int getMaxAge() {
        return listStudents.stream()
                .mapToInt(Student::getAge)
                .max()
                .orElse(-1);
    }

    /**
     * Thống kê tuổi nhỏ nhất
     * 
     * @return tuổi nhỏ nhất, -1 nếu danh sách rỗng
     */
    public int getMinAge() {
        return listStudents.stream()
                .mapToInt(Student::getAge)
                .min()
                .orElse(-1);
    }

    /**
     * Kiểm tra mã thí sinh đã tồn tại chưa
     * 
     * @param studentId mã thí sinh cần kiểm tra
     * @return true nếu đã tồn tại, false nếu chưa tồn tại
     */
    private boolean isStudentIdExists(String studentId) {
        return listStudents.stream()
                .anyMatch(s -> s.getStudentId().equals(studentId));
    }

    /**
     * Lấy chỉ số của thí sinh trong danh sách theo mã
     * 
     * @param studentId mã thí sinh
     * @return chỉ số trong danh sách, -1 nếu không tìm thấy
     */
    private int getStudentIndexById(String studentId) {
        for (int i = 0; i < listStudents.size(); i++) {
            if (listStudents.get(i).getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Lưu danh sách thí sinh vào file XML
     */
    private void saveToXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(StudentXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new StudentXML(listStudents), new File(STUDENT_FILE_NAME));
        } catch (JAXBException e) {
            System.err.println("Lỗi khi lưu file XML: " + e.getMessage());
        }
    }

    /**
     * Tải danh sách thí sinh từ file XML
     */
    private void loadFromXML() {
        try {
            File file = new File(STUDENT_FILE_NAME);
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(StudentXML.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                StudentXML studentXML = (StudentXML) unmarshaller.unmarshal(file);
                
                if (studentXML.getStudents() != null) {
                    this.listStudents = studentXML.getStudents();
                }
            }
        } catch (JAXBException e) {
            System.err.println("Lỗi khi đọc file XML: " + e.getMessage());
        }
    }
}
