/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.action;

import com.mycompany.quanlydoituongdacbiet.entity.ExamScore;
import com.mycompany.quanlydoituongdacbiet.entity.ExamScoreXML;
import java.io.File;
import java.text.DecimalFormat;
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
 * Lớp ManagerExamScore để quản lý danh sách điểm thi
 * Thực hiện các chức năng CRUD và lưu trữ XML
 * 
 * @author PC
 */
public class ManagerExamScore {
    
    private static final String EXAM_SCORE_FILE_NAME = "data/ExamScores.xml";
    private List<ExamScore> listExamScores;
    private DecimalFormat decimalFormat;

    /**
     * Constructor
     */
    public ManagerExamScore() {
        this.listExamScores = new ArrayList<>();
        this.decimalFormat = new DecimalFormat("#,##0.00");
        loadFromXML();
    }

    /**
     * Getter cho danh sách điểm thi
     * 
     * @return danh sách điểm thi
     */
    public List<ExamScore> getListExamScores() {
        return listExamScores;
    }

    /**
     * Setter cho danh sách điểm thi
     * 
     * @param listExamScores danh sách điểm thi mới
     */
    public void setListExamScores(List<ExamScore> listExamScores) {
        this.listExamScores = listExamScores;
    }

    /**
     * Thêm điểm thi mới
     * 
     * @param examScore điểm thi cần thêm
     * @return true nếu thêm thành công, false nếu đã tồn tại
     */
    public boolean add(ExamScore examScore) {
        // Kiểm tra trùng (mã thí sinh + mã môn)
        if (isExamScoreExists(examScore.getStudentId(), examScore.getSubjectCode())) {
            return false;
        }
        
        listExamScores.add(examScore);
        saveToXML();
        return true;
    }

    /**
     * Cập nhật thông tin điểm thi
     * 
     * @param examScore điểm thi cần cập nhật
     * @return true nếu cập nhật thành công, false nếu không tìm thấy
     */
    public boolean edit(ExamScore examScore) {
        int index = getExamScoreIndex(examScore.getStudentId(), examScore.getSubjectCode());
        if (index >= 0) {
            listExamScores.set(index, examScore);
            saveToXML();
            return true;
        }
        return false;
    }

    /**
     * Xóa điểm thi
     * 
     * @param studentId mã thí sinh
     * @param subjectCode mã môn thi
     * @return true nếu xóa thành công, false nếu không tìm thấy
     */
    public boolean delete(String studentId, String subjectCode) {
        int index = getExamScoreIndex(studentId, subjectCode);
        if (index >= 0) {
            listExamScores.remove(index);
            saveToXML();
            return true;
        }
        return false;
    }

    /**
     * Tìm điểm thi theo mã thí sinh và mã môn
     * 
     * @param studentId mã thí sinh
     * @param subjectCode mã môn thi
     * @return điểm thi nếu tìm thấy, null nếu không tìm thấy
     */
    public ExamScore findByStudentAndSubject(String studentId, String subjectCode) {
        return listExamScores.stream()
                .filter(e -> e.getStudentId().equals(studentId) && 
                           e.getSubjectCode().equals(subjectCode))
                .findFirst()
                .orElse(null);
    }

    /**
     * Tìm tất cả điểm thi của một thí sinh
     * 
     * @param studentId mã thí sinh
     * @return danh sách điểm thi của thí sinh
     */
    public List<ExamScore> findByStudent(String studentId) {
        return listExamScores.stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    /**
     * Tìm tất cả điểm thi của một môn
     * 
     * @param subjectCode mã môn thi
     * @return danh sách điểm thi của môn
     */
    public List<ExamScore> findBySubject(String subjectCode) {
        return listExamScores.stream()
                .filter(e -> e.getSubjectCode().equals(subjectCode))
                .collect(Collectors.toList());
    }

    /**
     * Tìm kiếm điểm thi theo khoảng điểm
     * 
     * @param minScore điểm tối thiểu
     * @param maxScore điểm tối đa
     * @return danh sách điểm thi trong khoảng điểm
     */
    public List<ExamScore> searchByScoreRange(double minScore, double maxScore) {
        return listExamScores.stream()
                .filter(e -> e.getScore() >= minScore && e.getScore() <= maxScore)
                .collect(Collectors.toList());
    }

    /**
     * Tìm kiếm điểm thi theo phòng thi
     * 
     * @param examRoom phòng thi
     * @return danh sách điểm thi trong phòng
     */
    public List<ExamScore> findByExamRoom(String examRoom) {
        return listExamScores.stream()
                .filter(e -> e.getExamRoom().toLowerCase().contains(examRoom.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Sắp xếp danh sách điểm thi theo điểm số
     * 
     * @param ascending true nếu sắp xếp tăng dần, false nếu giảm dần
     */
    public void sortByScore(boolean ascending) {
        Comparator<ExamScore> comparator = Comparator.comparing(ExamScore::getScore);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        Collections.sort(listExamScores, comparator);
        saveToXML();
    }

    /**
     * Sắp xếp danh sách điểm thi theo mã thí sinh
     * 
     * @param ascending true nếu sắp xếp tăng dần, false nếu giảm dần
     */
    public void sortByStudentId(boolean ascending) {
        Comparator<ExamScore> comparator = Comparator.comparing(ExamScore::getStudentId);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        Collections.sort(listExamScores, comparator);
        saveToXML();
    }

    /**
     * Thống kê tổng số bài thi
     * 
     * @return tổng số bài thi
     */
    public int getTotalExamScores() {
        return listExamScores.size();
    }

    /**
     * Thống kê điểm cao nhất
     * 
     * @return điểm cao nhất, -1 nếu danh sách rỗng
     */
    public double getMaxScore() {
        return listExamScores.stream()
                .mapToDouble(ExamScore::getScore)
                .max()
                .orElse(-1);
    }

    /**
     * Thống kê điểm thấp nhất
     * 
     * @return điểm thấp nhất, -1 nếu danh sách rỗng
     */
    public double getMinScore() {
        return listExamScores.stream()
                .mapToDouble(ExamScore::getScore)
                .min()
                .orElse(-1);
    }

    /**
     * Thống kê điểm trung bình
     * 
     * @return điểm trung bình, -1 nếu danh sách rỗng
     */
    public double getAverageScore() {
        return listExamScores.stream()
                .mapToDouble(ExamScore::getScore)
                .average()
                .orElse(-1);
    }

    /**
     * Thống kê số bài đạt điểm từ 5.0 trở lên
     * 
     * @return số bài đạt
     */
    public long getPassedCount() {
        return listExamScores.stream()
                .filter(e -> e.getScore() >= 5.0)
                .count();
    }

    /**
     * Thống kê số bài dưới 5.0 điểm
     * 
     * @return số bài không đạt
     */
    public long getFailedCount() {
        return listExamScores.stream()
                .filter(e -> e.getScore() < 5.0)
                .count();
    }

    /**
     * Format điểm số dạng tiền tệ
     * 
     * @param score điểm số
     * @return chuỗi điểm đã format
     */
    public String formatScore(double score) {
        return decimalFormat.format(score);
    }

    /**
     * Kiểm tra điểm thi đã tồn tại chưa
     * 
     * @param studentId mã thí sinh
     * @param subjectCode mã môn thi
     * @return true nếu đã tồn tại, false nếu chưa tồn tại
     */
    private boolean isExamScoreExists(String studentId, String subjectCode) {
        return listExamScores.stream()
                .anyMatch(e -> e.getStudentId().equals(studentId) && 
                             e.getSubjectCode().equals(subjectCode));
    }

    /**
     * Lấy chỉ số của điểm thi trong danh sách
     * 
     * @param studentId mã thí sinh
     * @param subjectCode mã môn thi
     * @return chỉ số trong danh sách, -1 nếu không tìm thấy
     */
    private int getExamScoreIndex(String studentId, String subjectCode) {
        for (int i = 0; i < listExamScores.size(); i++) {
            ExamScore score = listExamScores.get(i);
            if (score.getStudentId().equals(studentId) && 
                score.getSubjectCode().equals(subjectCode)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Lưu danh sách điểm thi vào file XML
     */
    private void saveToXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(ExamScoreXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new ExamScoreXML(listExamScores), new File(EXAM_SCORE_FILE_NAME));
        } catch (JAXBException e) {
            System.err.println("Lỗi khi lưu file XML: " + e.getMessage());
        }
    }

    /**
     * Tải danh sách điểm thi từ file XML
     */
    private void loadFromXML() {
        try {
            File file = new File(EXAM_SCORE_FILE_NAME);
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(ExamScoreXML.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                ExamScoreXML examScoreXML = (ExamScoreXML) unmarshaller.unmarshal(file);
                
                if (examScoreXML.getExamScores() != null) {
                    this.listExamScores = examScoreXML.getExamScores();
                }
            }
        } catch (JAXBException e) {
            System.err.println("Lỗi khi đọc file XML: " + e.getMessage());
        }
    }
}
