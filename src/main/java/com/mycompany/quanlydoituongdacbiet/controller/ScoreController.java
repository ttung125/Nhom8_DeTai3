/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.controller;

import com.mycompany.quanlydoituongdacbiet.action.ManagerExamScore;
import com.mycompany.quanlydoituongdacbiet.entity.ExamScore;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import com.mycompany.quanlydoituongdacbiet.view.ScoreViewSimple;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controller cho ScoreViewSimple
 * 
 * @author PC
 */
public class ScoreController {
    
    private ScoreViewSimple scoreView;
    private ManagerExamScore managerExamScore;
    private MainView mainView;
    
    public ScoreController(ScoreViewSimple view) {
        this.scoreView = view;
        this.managerExamScore = new ManagerExamScore();
        
        // Add listeners
        view.addAddExamScoreListener(new AddExamScoreListener());
        view.addEditExamScoreListener(new EditExamScoreListener());
        view.addDeleteExamScoreListener(new DeleteExamScoreListener());
        view.addClearListener(new ClearListener());
        view.addSearchListener(new SearchListener());
        view.addSearchScoreListener(new SearchScoreListener());
        view.addSortListener(new SortListener());
        view.addBackListener(new BackListener());
        view.addTableSelectionListener(new TableSelectionListener());
    }
    
    public void showScoreView() {
        List<ExamScore> scores = managerExamScore.getListExamScores();
        scoreView.showListExamScores(scores);
        updateStatistics();
        scoreView.setVisible(true);
    }
    
    private void updateStatistics() {
        List<ExamScore> scores = managerExamScore.getListExamScores();
        int total = managerExamScore.getTotalExamScores();
        long passed = managerExamScore.getPassedCount();
        long failed = managerExamScore.getFailedCount();
        double average = managerExamScore.getAverageScore();
        scoreView.showStatistics(total, passed, failed, average);
    }
    
    class AddExamScoreListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ExamScore score = scoreView.getExamScoreInfo();
            if (score != null) {
                if (managerExamScore.add(score)) {
                    scoreView.showListExamScores(managerExamScore.getListExamScores());
                    updateStatistics();
                    scoreView.clearForm();
                    scoreView.showMessage("Thêm điểm thi thành công!");
                } else {
                    scoreView.showMessage("Điểm thi cho thí sinh và môn này đã tồn tại!");
                }
            }
        }
    }
    
    class EditExamScoreListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedStudentId = scoreView.getSelectedStudentId();
            String selectedSubjectCode = scoreView.getSelectedSubjectCode();
            
            if (selectedStudentId == null || selectedSubjectCode == null) {
                scoreView.showMessage("Vui lòng chọn điểm thi cần sửa!");
                return;
            }
            
            ExamScore score = scoreView.getExamScoreInfo();
            if (score != null) {
                // Keep the same student ID and subject code
                score.setStudentId(selectedStudentId);
                score.setSubjectCode(selectedSubjectCode);
                
                if (managerExamScore.edit(score)) {
                    scoreView.showListExamScores(managerExamScore.getListExamScores());
                    updateStatistics();
                    scoreView.clearForm();
                    scoreView.showMessage("Cập nhật điểm thi thành công!");
                } else {
                    scoreView.showMessage("Không tìm thấy điểm thi!");
                }
            }
        }
    }
    
    class DeleteExamScoreListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedStudentId = scoreView.getSelectedStudentId();
            String selectedSubjectCode = scoreView.getSelectedSubjectCode();
            
            if (selectedStudentId == null || selectedSubjectCode == null) {
                scoreView.showMessage("Vui lòng chọn điểm thi cần xóa!");
                return;
            }
            
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                scoreView, 
                "Bạn có chắc chắn muốn xóa điểm thi này?",
                "Xác nhận xóa",
                javax.swing.JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                if (managerExamScore.delete(selectedStudentId, selectedSubjectCode)) {
                    scoreView.showListExamScores(managerExamScore.getListExamScores());
                    updateStatistics();
                    scoreView.clearForm();
                    scoreView.showMessage("Xóa điểm thi thành công!");
                } else {
                    scoreView.showMessage("Không tìm thấy điểm thi!");
                }
            }
        }
    }
    
    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            scoreView.clearForm();
            scoreView.showListExamScores(managerExamScore.getListExamScores());
            scoreView.showMessage("Đã làm mới form!");
        }
    }
    
    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentId = scoreView.getSearchStudentId();
            String subject = scoreView.getSearchSubject();
            
            List<ExamScore> searchResult;
            
            if (!studentId.isEmpty() && !subject.isEmpty()) {
                // Search by both student ID and subject
                ExamScore score = managerExamScore.findByStudentAndSubject(studentId, subject);
                searchResult = (score != null) ? List.of(score) : List.of();
            } else if (!studentId.isEmpty()) {
                // Search by student ID only
                searchResult = managerExamScore.findByStudent(studentId);
            } else if (!subject.isEmpty()) {
                // Search by subject only
                searchResult = managerExamScore.findBySubject(subject);
            } else {
                // Show all
                searchResult = managerExamScore.getListExamScores();
            }
            
            scoreView.showListExamScores(searchResult);
            scoreView.showMessage("Tìm thấy " + searchResult.size() + " bài thi");
        }
    }
    
    class SearchScoreListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double minScore = scoreView.getMinScore();
            double maxScore = scoreView.getMaxScore();
            
            List<ExamScore> searchResult = managerExamScore.searchByScoreRange(minScore, maxScore);
            scoreView.showListExamScores(searchResult);
            scoreView.showMessage("Tìm thấy " + searchResult.size() + " bài thi từ " + minScore + " đến " + maxScore + " điểm");
        }
    }
    
    class SortListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            managerExamScore.sortByScore(false); // Sort descending (highest first)
            scoreView.showListExamScores(managerExamScore.getListExamScores());
            scoreView.showMessage("Đã sắp xếp theo điểm số (cao đến thấp)!");
        }
    }
    
    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainView = new MainView();
            MainController mainController = new MainController(mainView);
            mainController.showMainView();
            scoreView.setVisible(false);
        }
    }
    
    class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String selectedStudentId = scoreView.getSelectedStudentId();
                String selectedSubjectCode = scoreView.getSelectedSubjectCode();
                
                if (selectedStudentId != null && selectedSubjectCode != null) {
                    ExamScore score = managerExamScore.findByStudentAndSubject(selectedStudentId, selectedSubjectCode);
                    if (score != null) {
                        scoreView.showExamScoreInfo(score);
                    }
                }
            }
        }
    }
}
