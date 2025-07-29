/*
 * XML Wrapper for Student List
 */
package com.mycompany.quanlydoituongdacbiet.entity;

import java.io.File;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@XmlRootElement(name = "Students")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentXML {

    @XmlElement(name = "Student")
    private List<Student> students;

    public StudentXML() {
        this.students = new ArrayList<>();
    }

    public StudentXML(List<Student> students) {
        this.students = students != null ? students : new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students != null ? students : new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    public int getSize() {
        return students != null ? students.size() : 0;
    }
    public Student findByStudentCode(String code) {
    if (students == null || code == null) return null;
    return students.stream()
        .filter(s -> code.equalsIgnoreCase(s.getStudentCode()))
        .findFirst()
        .orElse(null);
    }


    /**
     * Load student data from XML file
     */
    public static StudentXML loadStudentXML(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(StudentXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (StudentXML) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace(); // Giúp dễ debug nếu có lỗi
            return null;
        }
    }

    /**
     * Check if a given student ID exists in the list
     */
    public boolean containsStudentCode(String studentCode) {
        if (studentCode == null || students == null) return false;
        return students.stream().anyMatch(s -> studentCode.equalsIgnoreCase(s.getStudentCode()));
    }

}
