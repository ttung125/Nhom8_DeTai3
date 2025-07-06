/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.controller;

import com.mycompany.quanlydoituongdacbiet.action.ManagerStudent;
import com.mycompany.quanlydoituongdacbiet.entity.Student;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import com.mycompany.quanlydoituongdacbiet.view.StudentViewSimple;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controller cho StudentViewSimple
 * 
 * @author PC
 */
public class StudentController {
    
    private StudentViewSimple studentView;
    private ManagerStudent managerStudent;
    private MainView mainView;
    
    public StudentController(StudentViewSimple view) {
        this.studentView = view;
        this.managerStudent = new ManagerStudent();
        
        // Add listeners
        view.addAddStudentListener(new AddStudentListener());
        view.addEditStudentListener(new EditStudentListener());
        view.addDeleteStudentListener(new DeleteStudentListener());
        view.addClearListener(new ClearListener());
        view.addSearchListener(new SearchListener());
        view.addSearchAgeListener(new SearchAgeListener());
        view.addSortListener(new SortListener());
        view.addBackListener(new BackListener());
        view.addTableSelectionListener(new TableSelectionListener());
    }
    
    public void showStudentView() {
        List<Student> students = managerStudent.getListStudents();
        studentView.showListStudents(students);
        updateStatistics();
        studentView.setVisible(true);
    }
    
    private void updateStatistics() {
        List<Student> students = managerStudent.getListStudents();
        int total = managerStudent.getTotalStudents();
        long male = managerStudent.getMaleStudents();
        long female = managerStudent.getFemaleStudents();
        studentView.showStatistics(total, male, female);
    }
    
    class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                if (managerStudent.add(student)) {
                    studentView.showListStudents(managerStudent.getListStudents());
                    updateStatistics();
                    studentView.clearForm();
                    studentView.showMessage("Thêm thí sinh thành công!");
                } else {
                    studentView.showMessage("Mã thí sinh đã tồn tại!");
                }
            }
        }
    }
    
    class EditStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = studentView.getSelectedStudentId();
            if (selectedId == null) {
                studentView.showMessage("Vui lòng chọn thí sinh cần sửa!");
                return;
            }
            
            Student student = studentView.getStudentInfo();
            if (student != null) {
                student.setStudentId(selectedId); // Keep the same ID
                if (managerStudent.edit(student)) {
                    studentView.showListStudents(managerStudent.getListStudents());
                    updateStatistics();
                    studentView.clearForm();
                    studentView.showMessage("Cập nhật thí sinh thành công!");
                } else {
                    studentView.showMessage("Không tìm thấy thí sinh!");
                }
            }
        }
    }
    
    class DeleteStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = studentView.getSelectedStudentId();
            if (selectedId == null) {
                studentView.showMessage("Vui lòng chọn thí sinh cần xóa!");
                return;
            }
            
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                studentView, 
                "Bạn có chắc chắn muốn xóa thí sinh này?",
                "Xác nhận xóa",
                javax.swing.JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                if (managerStudent.delete(selectedId)) {
                    studentView.showListStudents(managerStudent.getListStudents());
                    updateStatistics();
                    studentView.clearForm();
                    studentView.showMessage("Xóa thí sinh thành công!");
                } else {
                    studentView.showMessage("Không tìm thấy thí sinh!");
                }
            }
        }
    }
    
    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            studentView.clearForm();
            studentView.showListStudents(managerStudent.getListStudents());
            studentView.showMessage("Đã làm mới form!");
        }
    }
    
    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchName = studentView.getSearchName();
            if (searchName.isEmpty()) {
                studentView.showListStudents(managerStudent.getListStudents());
                studentView.showMessage("Hiển thị tất cả thí sinh");
            } else {
                List<Student> searchResult = managerStudent.searchByName(searchName);
                studentView.showListStudents(searchResult);
                studentView.showMessage("Tìm thấy " + searchResult.size() + " thí sinh");
            }
        }
    }
    
    class SearchAgeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int minAge = studentView.getMinAge();
            int maxAge = studentView.getMaxAge();
            
            List<Student> searchResult = managerStudent.searchByAgeRange(minAge, maxAge);
            studentView.showListStudents(searchResult);
            studentView.showMessage("Tìm thấy " + searchResult.size() + " thí sinh từ " + minAge + " đến " + maxAge + " tuổi");
        }
    }
    
    class SortListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            managerStudent.sortByName(true);
            studentView.showListStudents(managerStudent.getListStudents());
            studentView.showMessage("Đã sắp xếp theo tên!");
        }
    }
    
    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainView = new MainView();
            MainController mainController = new MainController(mainView);
            mainController.showMainView();
            studentView.setVisible(false);
        }
    }
    
    class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String selectedId = studentView.getSelectedStudentId();
                if (selectedId != null) {
                    Student student = managerStudent.findById(selectedId);
                    if (student != null) {
                        studentView.showStudentInfo(student);
                    }
                }
            }
        }
    }
}
