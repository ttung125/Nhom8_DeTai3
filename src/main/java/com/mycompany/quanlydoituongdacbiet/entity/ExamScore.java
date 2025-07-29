/*
 * Entity ExamScore - Điểm thi
 */
package com.mycompany.quanlydoituongdacbiet.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Class đại diện cho điểm thi trong hệ thống quản lý điểm thi đại học
 */
@XmlRootElement(name = "ExamScore")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExamScore {

    private static int idCounter = 1;

    private int id;                     // ID tự tăng
    private String studentCode;         // Số báo danh thí sinh
    private String subjectCode;         // Mã môn thi
    private double score;               // Điểm số (0-10)
    private Date examDate;               // Ngày thi          
    private String status;              // Trạng thái (Có mặt/Vắng mặt/Vi phạm)
    private String notes;               // Ghi chú
    private String fullName;             // Họ và tên thí sinh
    private String examBlock;
    // Score status constants
    public static final String STATUS_PRESENT = "Có mặt";
    public static final String STATUS_ABSENT = "Vắng mặt";
    public static final String STATUS_VIOLATION = "Vi phạm";

    public static final String[] EXAM_SESSIONS = {"Sáng", "Chiều"};
    public static final String[] SCORE_STATUS = {STATUS_PRESENT, STATUS_ABSENT, STATUS_VIOLATION};

    // Constructor
    public ExamScore() {
        this.id = idCounter++;
        this.score = 0.0;
        this.status = STATUS_PRESENT;
        this.examDate = new Date();
    }

   public ExamScore(String studentCode, String fullName, String examBlock,
                 String subjectCode, double score, Date examDate, String status) {
    this(); 
    this.studentCode = studentCode;
    this.fullName = fullName;
    this.examBlock = examBlock;
    this.subjectCode = subjectCode;
    this.score = score;
    this.examDate = examDate;
    this.status = status;
}


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getExamBlock() {
        return examBlock;
    }

    public void setExamBlock(String examBlock) {
        this.examBlock = examBlock;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        // Validate score range
        if (score >= 0 && score <= 10) {
            this.score = score;
        } else {
            throw new IllegalArgumentException("Điểm phải trong khoảng 0-10");
        }
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Alias methods để tương thích với code cũ
    public String getStudentId() {
        return this.studentCode;
    }

    public void setStudentId(String studentId) {
        this.studentCode = studentId;
    }

    // Static methods cho dropdown options
    public static String[] getSubjectOptions() {
        return new String[]{"Toán", "Vật Lý", "Hóa Học", "Sinh Học", "Ngữ Văn", "Tiếng Anh", "Lịch Sử", "Địa Lý", "GDCD"};
    }

    public static String[] getExamSessionOptions() {
        return EXAM_SESSIONS;
    }

    public static String[] getStatusOptions() {
        return SCORE_STATUS;
    }

    // Utility methods
    public String getFormattedScore() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(score);
    }

    public String getScoreGrade() {
        if (score >= 9.0) {
            return "Xuất sắc";
        } else if (score >= 8.0) {
            return "Giỏi";
        } else if (score >= 6.5) {
            return "Khá";
        } else if (score >= 5.0) {
            return "Trung bình";
        } else if (score >= 3.5) {
            return "Yếu";
        } else {
            return "Kém";
        }
    }

    public boolean isPassed() {
        return score >= 5.0 && STATUS_PRESENT.equals(status);
    }

    @Override
    public String toString() {
        return "ExamScore{" +
            "id=" + id +
            ", studentCode='" + studentCode + '\'' +
            ", fullName='" + fullName + '\'' +
            ", examBlock='" + examBlock + '\'' +
            ", subjectCode='" + subjectCode + '\'' +
            ", score=" + score +
            ", status='" + status + '\'' +
            ", examDate=" + examDate +
            '}';
}


    // Validation method
   public boolean isValid() {
    return studentCode != null && !studentCode.trim().isEmpty()
            && fullName != null && !fullName.trim().isEmpty()
            && examBlock != null && !examBlock.trim().isEmpty()
            && subjectCode != null && !subjectCode.trim().isEmpty()
            && score >= 0 && score <= 10
            && status != null && !status.trim().isEmpty()
            && examDate != null;
}

    // Calculate weighted score for university admission
    public double getWeightedScore(double weight) {
        return score * weight;
    }

    // Compare scores
    public boolean isHigherThan(ExamScore other) {
        return this.score > other.score;
    }

    public boolean isEqualTo(ExamScore other) {
        return Math.abs(this.score - other.score) < 0.01; // Compare with tolerance
    }

}
