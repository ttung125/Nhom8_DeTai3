/*
 * Entity ExamBlock - Khối thi
 */
package com.mycompany.quanlydoituongdacbiet.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class đại diện cho khối thi trong hệ thống quản lý điểm thi đại học
 */
@XmlRootElement(name = "ExamBlock")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExamBlock {
    private static int idCounter = 1;
    
    private int id;                     // ID tự tăng
    private String blockCode;           // Mã khối (unique) - VD: A, B, C, D
    private String blockName;           // Tên khối - VD: Khối A, Khối B
    private List<String> subjects;      // Danh sách môn thi trong khối
    private String description;         // Mô tả khối thi
    private boolean isActive;           // Trạng thái hoạt động

    // Predefined exam blocks for dropdown
    public static final String[] BLOCK_CODES = {"A", "B", "C", "D", "A1", "D1"};
    
    public static final String[] BLOCK_NAMES = {
        "Khối A (Toán - Lý - Hóa)",
        "Khối B (Toán - Sinh - Hóa)", 
        "Khối C (Văn - Sử - Địa)",
        "Khối D (Toán - Văn - Anh)",
        "Khối A1 (Toán - Lý - Anh)",
        "Khối D1 (Toán - Văn - Lý)"
    };

    // Constructor
    public ExamBlock() {
        this.id = idCounter++;
        this.subjects = new ArrayList<>();
        this.isActive = true;
    }

    public ExamBlock(String blockCode, String blockName, List<String> subjects, String description) {
        this();
        this.blockCode = blockCode;
        this.blockName = blockName;
        this.subjects = subjects != null ? new ArrayList<>(subjects) : new ArrayList<>();
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects != null ? new ArrayList<>(subjects) : new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Alias method để tương thích với code cũ
    public List<String> getSubjectCodes() {
        return this.subjects;
    }
    
    public void setSubjectCodes(List<String> subjectCodes) {
        this.subjects = subjectCodes != null ? new ArrayList<>(subjectCodes) : new ArrayList<>();
    }
    
    public int getTotalSubjects() {
        return subjects != null ? subjects.size() : 0;
    }

    // Utility methods
    public void addSubject(String subjectCode) {
        if (!subjects.contains(subjectCode)) {
            subjects.add(subjectCode);
        }
    }

    public void removeSubject(String subjectCode) {
        subjects.remove(subjectCode);
    }

    public String getSubjectsString() {
        return String.join(", ", subjects);
    }

    public String getDisplayText() {
        return blockCode + " - " + blockName;
    }

    @Override
    public String toString() {
        return "ExamBlock{" +
                "id=" + id +
                ", blockCode='" + blockCode + '\'' +
                ", blockName='" + blockName + '\'' +
                ", subjects=" + subjects +
                ", isActive=" + isActive +
                '}';
    }

    // Validation method
    public boolean isValid() {
        return blockCode != null && !blockCode.trim().isEmpty() &&
               blockName != null && !blockName.trim().isEmpty() &&
               subjects != null && !subjects.isEmpty();
    }

    // Static method to get predefined exam blocks
    public static List<ExamBlock> getPredefinedBlocks() {
        List<ExamBlock> blocks = new ArrayList<>();
        
        blocks.add(new ExamBlock("A", "Khối A (Toán - Lý - Hóa)", 
                   Arrays.asList("TOAN", "LY", "HOA"), "Khối tự nhiên truyền thống"));
        blocks.add(new ExamBlock("B", "Khối B (Toán - Sinh - Hóa)", 
                   Arrays.asList("TOAN", "SINH", "HOA"), "Khối tự nhiên sinh học"));
        blocks.add(new ExamBlock("C", "Khối C (Văn - Sử - Địa)", 
                   Arrays.asList("VAN", "SU", "DIA"), "Khối xã hội truyền thống"));
        blocks.add(new ExamBlock("D", "Khối D (Toán - Văn - Anh)", 
                   Arrays.asList("TOAN", "VAN", "ANH"), "Khối ngoại ngữ"));
        blocks.add(new ExamBlock("A1", "Khối A1 (Toán - Lý - Anh)", 
                   Arrays.asList("TOAN", "LY", "ANH"), "Khối tự nhiên ngoại ngữ"));
        blocks.add(new ExamBlock("D1", "Khối D1 (Toán - Văn - Lý)", 
                   Arrays.asList("TOAN", "VAN", "LY"), "Khối kết hợp"));
                   
        return blocks;
    }

    // Get block name by code
    public static String getBlockNameByCode(String code) {
        for (int i = 0; i < BLOCK_CODES.length; i++) {
            if (BLOCK_CODES[i].equals(code)) {
                return BLOCK_NAMES[i];
            }
        }
        return code;
    }
}
