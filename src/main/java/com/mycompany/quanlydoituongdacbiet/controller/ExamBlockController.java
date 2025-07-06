/*
 * Controller cho quản lý khối thi
 */
package com.mycompany.quanlydoituongdacbiet.controller;

import com.mycompany.quanlydoituongdacbiet.action.ManagerExamBlock;
import com.mycompany.quanlydoituongdacbiet.entity.ExamBlock;
import com.mycompany.quanlydoituongdacbiet.view.ExamBlockViewSimple;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controller để điều khiển các hành động trong ExamBlockViewSimple
 * 
 * @author PC
 */
public class ExamBlockController {
    
    private ExamBlockViewSimple view;
    private ManagerExamBlock manager;
    
    public ExamBlockController(ExamBlockViewSimple view) {
        this.view = view;
        this.manager = new ManagerExamBlock();
        
        // Khởi tạo event listeners
        initEventListeners();
        
        // Hiển thị dữ liệu ban đầu
        refreshExamBlockList();
        updateStatistics();
    }
    
    private void initEventListeners() {
        view.addAddExamBlockListener(new AddExamBlockListener());
        view.addEditExamBlockListener(new EditExamBlockListener());
        view.addDeleteExamBlockListener(new DeleteExamBlockListener());
        view.addClearListener(new ClearListener());
        view.addSearchListener(new SearchListener());
        view.addSearchSubjectsListener(new SearchSubjectsListener());
        view.addSortListener(new SortListener());
        view.addBackListener(new BackListener());
        view.addTableSelectionListener(new TableSelectionListener());
    }
    
    private void refreshExamBlockList() {
        List<ExamBlock> examBlocks = manager.getListExamBlocks();
        view.showListExamBlocks(examBlocks);
    }
    
    private void updateStatistics() {
        List<ExamBlock> examBlocks = manager.getListExamBlocks();
        int total = examBlocks.size();
        double avgSubjects = examBlocks.stream()
                .mapToInt(ExamBlock::getTotalSubjects)
                .average()
                .orElse(0.0);
        
        view.showStatistics(total, avgSubjects);
    }
    
    /**
     * Hiển thị ExamBlockView
     */
    public void showExamBlockView() {
        view.showExamBlockView();
    }
    
    // Event Listeners
    
    class AddExamBlockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ExamBlock examBlock = view.getExamBlockInfo();
            if (examBlock != null) {
                try {
                    // Auto-generate ID
                    String newId = "KB" + String.format("%03d", manager.getListExamBlocks().size() + 1);
                    examBlock.setBlockCode(newId);
                    
                    boolean success = manager.addExamBlock(examBlock);
                    if (success) {
                        view.showMessage("Thêm khối thi thành công!");
                        view.clearForm();
                        refreshExamBlockList();
                        updateStatistics();
                    } else {
                        view.showMessage("Khối thi đã tồn tại!");
                    }
                } catch (Exception ex) {
                    view.showMessage("Lỗi khi thêm khối thi: " + ex.getMessage());
                }
            }
        }
    }
    
    class EditExamBlockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedCode = view.getSelectedExamBlockCode();
            if (selectedCode == null) {
                view.showMessage("Vui lòng chọn khối thi cần sửa!");
                return;
            }
            
            ExamBlock examBlock = view.getExamBlockInfo();
            if (examBlock != null) {
                try {
                    examBlock.setBlockCode(selectedCode); // Keep original code
                    boolean success = manager.editExamBlock(examBlock);
                    if (success) {
                        view.showMessage("Cập nhật khối thi thành công!");
                        view.clearForm();
                        refreshExamBlockList();
                        updateStatistics();
                    } else {
                        view.showMessage("Không tìm thấy khối thi để cập nhật!");
                    }
                } catch (Exception ex) {
                    view.showMessage("Lỗi khi cập nhật khối thi: " + ex.getMessage());
                }
            }
        }
    }
    
    class DeleteExamBlockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedCode = view.getSelectedExamBlockCode();
            if (selectedCode == null) {
                view.showMessage("Vui lòng chọn khối thi cần xóa!");
                return;
            }
            
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                view, 
                "Bạn có chắc chắn muốn xóa khối thi này?",
                "Xác nhận xóa",
                javax.swing.JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                try {
                    boolean success = manager.deleteExamBlock(selectedCode);
                    if (success) {
                        view.showMessage("Xóa khối thi thành công!");
                        view.clearForm();
                        refreshExamBlockList();
                        updateStatistics();
                    } else {
                        view.showMessage("Không tìm thấy khối thi để xóa!");
                    }
                } catch (Exception ex) {
                    view.showMessage("Lỗi khi xóa khối thi: " + ex.getMessage());
                }
            }
        }
    }
    
    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearForm();
            refreshExamBlockList();
            view.showMessage("Đã làm mới form!");
        }
    }
    
    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchName = view.getSearchName();
            if (searchName.isEmpty()) {
                refreshExamBlockList();
                view.showMessage("Hiển thị tất cả khối thi!");
                return;
            }
            
            try {
                List<ExamBlock> results = manager.searchExamBlocksByName(searchName);
                view.showListExamBlocks(results);
                view.showMessage("Tìm thấy " + results.size() + " khối thi!");
            } catch (Exception ex) {
                view.showMessage("Lỗi khi tìm kiếm: " + ex.getMessage());
            }
        }
    }
    
    class SearchSubjectsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int minSubjects = view.getMinSubjects();
            int maxSubjects = view.getMaxSubjects();
            
            if (minSubjects > maxSubjects) {
                view.showMessage("Số môn từ phải nhỏ hơn số môn đến!");
                return;
            }
            
            try {
                List<ExamBlock> results = manager.searchExamBlocksBySubjectCount(minSubjects, maxSubjects);
                view.showListExamBlocks(results);
                view.showMessage("Tìm thấy " + results.size() + " khối thi!");
            } catch (Exception ex) {
                view.showMessage("Lỗi khi tìm kiếm: " + ex.getMessage());
            }
        }
    }
    
    class SortListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<ExamBlock> sortedList = manager.sortExamBlocksByName();
                view.showListExamBlocks(sortedList);
                view.showMessage("Đã sắp xếp khối thi theo tên!");
            } catch (Exception ex) {
                view.showMessage("Lỗi khi sắp xếp: " + ex.getMessage());
            }
        }
    }
    
    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Quay về MainView
            MainView mainView = new MainView();
            MainController mainController = new MainController(mainView);
            mainController.showMainView();
            view.setVisible(false);
        }
    }
    
    class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String selectedCode = view.getSelectedExamBlockCode();
                if (selectedCode != null) {
                    ExamBlock examBlock = manager.findExamBlockByCode(selectedCode);
                    if (examBlock != null) {
                        view.showExamBlockInfo(examBlock);
                    }
                }
            }
        }
    }
}
