/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Lớp SubjectXML để bao bọc danh sách môn thi cho việc chuyển đổi XML
 * 
 * @author PC
 */
@XmlRootElement(name = "subjects")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubjectXML {
    
    @XmlElement(name = "subject")
    private List<Subject> subjects;

    /**
     * Constructor mặc định
     */
    public SubjectXML() {
    }

    /**
     * Constructor với danh sách môn thi
     * 
     * @param subjects danh sách môn thi
     */
    public SubjectXML(List<Subject> subjects) {
        this.subjects = subjects;
    }

    /**
     * Getter cho danh sách môn thi
     * 
     * @return danh sách môn thi
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     * Setter cho danh sách môn thi
     * 
     * @param subjects danh sách môn thi
     */
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "SubjectXML{" +
                "subjects=" + subjects +
                '}';
    }
}
