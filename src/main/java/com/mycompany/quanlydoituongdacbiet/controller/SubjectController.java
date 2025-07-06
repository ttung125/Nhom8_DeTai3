/*
 * Controller cho quản lý môn thi
 */
package com.mycompany.quanlydoituongdacbiet.controller;

import com.mycompany.quanlydoituongdacbiet.action.ManagerSubject;
import com.mycompany.quanlydoituongdacbiet.entity.Subject;
import com.mycompany.quanlydoituongdacbiet.view.SubjectViewSimple;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controller để điều khiển các hành động trong SubjectViewSimple
 * 
 * @author PC
 */
public class SubjectController {
    
    private SubjectViewSimple view;
    private ManagerSubject manager;
    
    public SubjectController(SubjectViewSimple view) {
        this.view = view;
        this.manager = new ManagerSubject();
        
        // Khởi tạo event listeners
        initEventListeners();
        
        // Hiển thị dữ liệu ban đầu
        refreshSubjectList();
        updateStatistics();
    }
    
    private void initEventListeners() {
        view.addAddSubjectListener(new AddSubjectListener());
        view.addEditSubjectListener(new EditSubjectListener());
        view.addDeleteSubjectListener(new DeleteSubjectListener());
        view.addClearListener(new ClearListener());
        view.addSearchListener(new SearchListener());
        view.addSearchTimeListener(new SearchTimeListener());
        view.addSortListener(new SortListener());
        view.addBackListener(new BackListener());
        view.addTableSelectionListener(new TableSelectionListener());
    }
    
    private void refreshSubjectList() {
        List<Subject> subjects = manager.getListSubjects();
        view.showListSubjects(subjects);
    }
    
    private void updateStatistics() {
        List<Subject> subjects = manager.getListSubjects();
        int total = subjects.size();
        double avgTime = subjects.stream()
                .mapToInt(Subject::getDuration)
                .average()
                .orElse(0.0);
        
        view.showStatistics(total, avgTime);
    }
    
    /**
     * Hiển thị SubjectView
     */
    public void showSubjectView() {
        view.showSubjectView();
    }
    
    // Event Listeners
    
    class AddSubjectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Subject subject = view.getSubjectInfo();
            if (subject != null) {
                try {
                    // Auto-generate ID
                    String newId = "MH" + String.format("%03d", manager.getListSubjects().size() + 1);
                    subject.setSubjectCode(newId);
                    
                    boolean success = manager.addSubject(subject);
                    if (success) {
                        view.showMessage("Thêm môn thi thành công!");
                        view.clearForm();
                        refreshSubjectList();
                        updateStatistics();
                    } else {
                        view.showMessage("Môn thi đã tồn tại!");
                    }
                } catch (Exception ex) {
                    view.showMessage("Lỗi khi thêm môn thi: " + ex.getMessage());
                }
            }
        }
    }
    
    class EditSubjectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedCode = view.getSelectedSubjectCode();
            if (selectedCode == null) {
                view.showMessage("Vui lòng chọn môn thi cần sửa!");
                return;
            }
            
            Subject subject = view.getSubjectInfo();
            if (subject != null) {
                try {
                    subject.setSubjectCode(selectedCode); // Keep original code
                    boolean success = manager.editSubject(subject);
                    if (success) {
                        view.showMessage("Cập nhật môn thi thành công!");
                        view.clearForm();
                        refreshSubjectList();
                        updateStatistics();
                    } else {
                        view.showMessage("Không tìm thấy môn thi để cập nhật!");
                    }
                } catch (Exception ex) {
                    view.showMessage("Lỗi khi cập nhật môn thi: " + ex.getMessage());
                }
            }
        }
    }
    
    class DeleteSubjectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedCode = view.getSelectedSubjectCode();
            if (selectedCode == null) {
                view.showMessage("Vui lòng chọn môn thi cần xóa!");
                return;
            }
            
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                view, 
                "Bạn có chắc chắn muốn xóa môn thi này?",
                "Xác nhận xóa",
                javax.swing.JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                try {
                    boolean success = manager.deleteSubject(selectedCode);
                    if (success) {
                        view.showMessage("Xóa môn thi thành công!");
                        view.clearForm();
                        refreshSubjectList();
                        updateStatistics();
                    } else {
                        view.showMessage("Không tìm thấy môn thi để xóa!");
                    }
                } catch (Exception ex) {
                    view.showMessage("Lỗi khi xóa môn thi: " + ex.getMessage());
                }
            }
        }
    }
    
    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearForm();
            refreshSubjectList();
            view.showMessage("Đã làm mới form!");
        }
    }
    
    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchName = view.getSearchName();
            if (searchName.isEmpty()) {
                refreshSubjectList();
                view.showMessage("Hiển thị tất cả môn thi!");
                return;
            }
            
            try {
                List<Subject> results = manager.searchSubjectsByName(searchName);
                view.showListSubjects(results);
                view.showMessage("Tìm thấy " + results.size() + " môn thi!");
            } catch (Exception ex) {
                view.showMessage("Lỗi khi tìm kiếm: " + ex.getMessage());
            }
        }
    }
    
    class SearchTimeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int minTime = view.getMinTime();
            int maxTime = view.getMaxTime();
            
            if (minTime > maxTime) {
                view.showMessage("Thời gian từ phải nhỏ hơn thời gian đến!");
                return;
            }
            
            try {
                List<Subject> results = manager.searchSubjectsByTimeRange(minTime, maxTime);
                view.showListSubjects(results);
                view.showMessage("Tìm thấy " + results.size() + " môn thi!");
            } catch (Exception ex) {
                view.showMessage("Lỗi khi tìm kiếm: " + ex.getMessage());
            }
        }
    }
    
    class SortListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Subject> sortedList = manager.sortSubjectsByName();
                view.showListSubjects(sortedList);
                view.showMessage("Đã sắp xếp môn thi theo tên!");
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
                String selectedCode = view.getSelectedSubjectCode();
                if (selectedCode != null) {
                    Subject subject = manager.findSubjectByCode(selectedCode);
                    if (subject != null) {
                        view.showSubjectInfo(subject);
                    }
                }
            }
        }
    }
}
