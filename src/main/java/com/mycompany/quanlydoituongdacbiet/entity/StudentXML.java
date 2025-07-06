/*
 * XML Wrapper for Student List
 */
package com.mycompany.quanlydoituongdacbiet.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.ArrayList;

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
}
