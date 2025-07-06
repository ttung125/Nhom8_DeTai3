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
 * Lớp ExamScoreXML để bao bọc danh sách điểm thi cho việc chuyển đổi XML
 * 
 * @author PC
 */
@XmlRootElement(name = "examScores")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExamScoreXML {
    
    @XmlElement(name = "examScore")
    private List<ExamScore> examScores;

    /**
     * Constructor mặc định
     */
    public ExamScoreXML() {
    }

    /**
     * Constructor với danh sách điểm thi
     * 
     * @param examScores danh sách điểm thi
     */
    public ExamScoreXML(List<ExamScore> examScores) {
        this.examScores = examScores;
    }

    /**
     * Getter cho danh sách điểm thi
     * 
     * @return danh sách điểm thi
     */
    public List<ExamScore> getExamScores() {
        return examScores;
    }

    /**
     * Setter cho danh sách điểm thi
     * 
     * @param examScores danh sách điểm thi
     */
    public void setExamScores(List<ExamScore> examScores) {
        this.examScores = examScores;
    }

    @Override
    public String toString() {
        return "ExamScoreXML{" +
                "examScores=" + examScores +
                '}';
    }
}
