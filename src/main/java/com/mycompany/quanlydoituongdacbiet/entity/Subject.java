/*
 * Entity Subject - Môn thi
 */
package com.mycompany.quanlydoituongdacbiet.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class đại diện cho môn thi trong hệ thống quản lý điểm thi đại học
 */
@XmlRootElement(name = "Subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {
    private static int idCounter = 1;
    
    private int id;                 // ID tự tăng
    private String subjectCode;     // Mã môn (unique) - VD: TOAN, LY, HOA
    private String subjectName;     // Tên môn - VD: Toán học, Vật lý, Hóa học
    private int duration;           // Thời gian thi (phút)
    private double maxScore;        // Điểm tối đa
    private String description;     // Mô tả

    // Predefined subjects for dropdown
    public static final String[] SUBJECT_CODES = {
        "TOAN", "LY", "HOA", "SINH", "VAN", "ANH", "SU", "DIA", "GDCD"
    };
    
    public static final String[] SUBJECT_NAMES = {
        "Toán học", "Vật lý", "Hóa học", "Sinh học", "Ngữ văn", 
        "Tiếng Anh", "Lịch sử", "Địa lý", "Giáo dục công dân"
    };

    // Constructor
    public Subject() {
        this.id = idCounter++;
        this.maxScore = 10.0; // Mặc định thang điểm 10
        this.duration = 180;  // Mặc định 180 phút
    }

    public Subject(String subjectCode, String subjectName, int duration, double maxScore, String description) {
        this();
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.duration = duration;
        this.maxScore = maxScore;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Alias method để tương thích với code cũ
    public int getExamDuration() {
        return this.duration;
    }
    
    public void setExamDuration(int examDuration) {
        this.duration = examDuration;
    }

    // Utility methods
    public String getDisplayText() {
        return subjectCode + " - " + subjectName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", duration=" + duration +
                ", maxScore=" + maxScore +
                '}';
    }

    // Validation method
    public boolean isValid() {
        return subjectCode != null && !subjectCode.trim().isEmpty() &&
               subjectName != null && !subjectName.trim().isEmpty() &&
               duration > 0 && maxScore > 0;
    }

    // Static method to get subject name by code
    public static String getSubjectNameByCode(String code) {
        for (int i = 0; i < SUBJECT_CODES.length; i++) {
            if (SUBJECT_CODES[i].equals(code)) {
                return SUBJECT_NAMES[i];
            }
        }
        return code;
    }
}
