/*
 * Entity Student - Thí sinh
 */
package com.mycompany.quanlydoituongdacbiet.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Class đại diện cho thí sinh trong hệ thống quản lý điểm thi đại học
 */
@XmlRootElement(name = "Student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    private static int idCounter = 1;
    
    private int id;                     // ID tự tăng
    private String studentCode;         // Số báo danh (unique)
    private String fullName;            // Họ và tên
    private Date birthDate;             // Ngày sinh (Calendar)
    private String gender;              // Giới tính (Dropdown)
    private String examBlock;           // Khối thi (Dropdown: A, B, C, D...)
    private String address;             // Địa chỉ
    private String phoneNumber;         // Số điện thoại
    private String email;               // Email
    private String idCard;              // CMND/CCCD

    // Constructor
    public Student() {
        this.id = idCounter++;
    }

    public Student(String studentCode, String fullName, Date birthDate, String gender, 
                   String examBlock, String address, String phoneNumber, String email, String idCard) {
        this();
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.examBlock = examBlock;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCard = idCard;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExamBlock() {
        return examBlock;
    }

    public void setExamBlock(String examBlock) {
        this.examBlock = examBlock;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    // Alias methods để tương thích với code cũ
    public String getStudentId() {
        return this.studentCode;
    }
    
    public void setStudentId(String studentId) {
        this.studentCode = studentId;
    }
    
    // Tính tuổi từ ngày sinh
    public int getAge() {
        if (birthDate == null) return 0;
        
        Date currentDate = new Date();
        long ageInMillis = currentDate.getTime() - birthDate.getTime();
        return (int) (ageInMillis / (365.25 * 24 * 60 * 60 * 1000));
    }
    
    // Static methods cho dropdown options
    public static String[] getGenderOptions() {
        return new String[]{"Nam", "Nữ"};
    }
    
    public static String[] getExamBlockOptions() {
        return new String[]{"Khối A", "Khối A1", "Khối B", "Khối C", "Khối D"};
    }

    // Utility methods
    public String getBirthDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return birthDate != null ? sdf.format(birthDate) : "";
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentCode='" + studentCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + getBirthDateString() +
                ", gender='" + gender + '\'' +
                ", examBlock='" + examBlock + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    // Validation method
    public boolean isValid() {
        return studentCode != null && !studentCode.trim().isEmpty() &&
               fullName != null && !fullName.trim().isEmpty() &&
               birthDate != null &&
               gender != null && !gender.trim().isEmpty() &&
               examBlock != null && !examBlock.trim().isEmpty();
    }
}
