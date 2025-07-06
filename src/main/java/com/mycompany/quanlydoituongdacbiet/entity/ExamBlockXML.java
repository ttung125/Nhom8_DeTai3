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
 * Lớp ExamBlockXML để bao bọc danh sách khối thi cho việc chuyển đổi XML
 * 
 * @author PC
 */
@XmlRootElement(name = "examBlocks")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExamBlockXML {
    
    @XmlElement(name = "examBlock")
    private List<ExamBlock> examBlocks;

    /**
     * Constructor mặc định
     */
    public ExamBlockXML() {
    }

    /**
     * Constructor với danh sách khối thi
     * 
     * @param examBlocks danh sách khối thi
     */
    public ExamBlockXML(List<ExamBlock> examBlocks) {
        this.examBlocks = examBlocks;
    }

    /**
     * Getter cho danh sách khối thi
     * 
     * @return danh sách khối thi
     */
    public List<ExamBlock> getExamBlocks() {
        return examBlocks;
    }

    /**
     * Setter cho danh sách khối thi
     * 
     * @param examBlocks danh sách khối thi
     */
    public void setExamBlocks(List<ExamBlock> examBlocks) {
        this.examBlocks = examBlocks;
    }

    @Override
    public String toString() {
        return "ExamBlockXML{" +
                "examBlocks=" + examBlocks +
                '}';
    }
}
