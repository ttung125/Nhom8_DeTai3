/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.controller;

import com.mycompany.quanlydoituongdacbiet.view.LoginView;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import com.mycompany.quanlydoituongdacbiet.view.StudentViewSimple;
import com.mycompany.quanlydoituongdacbiet.view.ScoreViewSimple;
import com.mycompany.quanlydoituongdacbiet.view.SubjectViewSimple;
import com.mycompany.quanlydoituongdacbiet.view.ExamBlockViewSimple;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainController cho hệ thống quản lý điểm thi đại học
 * 
 * @author PC
 */
public class MainController 
{
    private LoginView loginView;
    private StudentViewSimple studentView;
    private ScoreViewSimple scoreView;
    private SubjectViewSimple subjectView;
    private ExamBlockViewSimple examBlockView;
    private MainView mainView;
    
    public MainController(MainView view)
    {
        this.mainView = view;
        System.out.println("Đang thiết lập listeners cho MainView...");
        
        // Listeners cho hệ thống mới
        view.addChooseScoreManagementListener(new ChooseScoreManagementListener());
        view.addChooseStudentListener(new ChooseStudentListener());
        view.addChooseSubjectsListener(new ChooseSubjectsListener());
        view.addChooseExamBlocksListener(new ChooseExamBlocksListener());
        view.addLogoutListener(new LogoutListener());

        
        // Giữ lại để tương thích ngược với hệ thống cũ
        view.addChooseSpecialPersonListener(new ChooseSpecialPersonListener());
        view.addChooseResidentsListener(new ChooseResidentListener());
        
        System.out.println("Đã thiết lập xong listeners.");
    }
    
    public void showMainView() 
    {
        mainView.setVisible(true);
    }
    
    /**
     * Listener cho nút quản lý điểm thi (hệ thống mới)
     */
    class ChooseScoreManagementListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("🎯 Chọn quản lý điểm thi...");
            scoreView = new ScoreViewSimple();
            ScoreController scoreController = new ScoreController(scoreView);
            scoreController.showScoreView();
            mainView.setVisible(false);
            System.out.println("✅ Đã chuyển sang ScoreView!");
        }
    }
    
    /**
     * Listener cho nút quản lý thí sinh (hệ thống mới)
     */
    class ChooseStudentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("🎯 Chọn quản lý thí sinh...");
            studentView = new StudentViewSimple();
            StudentController studentController = new StudentController(studentView);
            studentController.showStudentView();
            mainView.setVisible(false);
            System.out.println("✅ Đã chuyển sang StudentView!");
        }
    }
    
    /**
     * Listener cho nút quản lý môn thi
     */
    class ChooseSubjectsListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("🎯 Chọn quản lý môn thi...");
            subjectView = new SubjectViewSimple();
            SubjectController subjectController = new SubjectController(subjectView);
            subjectController.showSubjectView();
            mainView.setVisible(false);
            System.out.println("✅ Đã chuyển sang SubjectView!");
        }
    }
    
    /**
     * Listener cho nút quản lý khối thi
     */
    class ChooseExamBlocksListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("🎯 Chọn quản lý khối thi...");
            examBlockView = new ExamBlockViewSimple();
            ExamBlockController examBlockController = new ExamBlockController(examBlockView);
            examBlockController.showExamBlockView();
            mainView.setVisible(false);
            System.out.println("✅ Đã chuyển sang ExamBlockView!");
        }
    }
    
    /**
     * Listener cho nút quản lý đối tượng đặc biệt (hệ thống cũ - để tương thích ngược)
     */
    class ChooseSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            // Trong hệ thống mới, nút này sẽ chuyển đến quản lý điểm thi
            System.out.println("🎯 Chọn quản lý điểm thi (từ nút cũ)...");
            scoreView = new ScoreViewSimple();
            ScoreController scoreController = new ScoreController(scoreView);
            scoreController.showScoreView();
            mainView.setVisible(false);
            System.out.println("✅ Đã chuyển sang ScoreView!");
        }
    }
    
    /**
     * Listener cho nút quản lý dân cư (hệ thống cũ - để tương thích ngược)
     */
    class ChooseResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            // Trong hệ thống mới, nút này sẽ chuyển đến quản lý thí sinh
            System.out.println("🎯 Chọn quản lý thí sinh (từ nút cũ)...");
            studentView = new StudentViewSimple();
            StudentController studentController = new StudentController(studentView);
            studentController.showStudentView();
            mainView.setVisible(false);
            System.out.println("✅ Đã chuyển sang StudentView!");
        }
    }
    
    /**
     * Method để mở view quản lý môn thi
     */
    public void openSubjectView() {
        System.out.println("🎯 Mở quản lý môn thi...");
        subjectView = new SubjectViewSimple();
        SubjectController subjectController = new SubjectController(subjectView);
        subjectView.setVisible(true);
        mainView.setVisible(false);
        System.out.println("✅ Đã mở SubjectView!");
    }
    
    /**
     * Method để mở view quản lý khối thi
     */
    public void openExamBlockView() {
        System.out.println("🎯 Mở quản lý khối thi...");
        examBlockView = new ExamBlockViewSimple();
        ExamBlockController examBlockController = new ExamBlockController(examBlockView);
        examBlockView.setVisible(true);
        mainView.setVisible(false);
        System.out.println("✅ Đã mở ExamBlockView!");
    }
    
    /**
     * Method để mở view quản lý thí sinh
     */
    public void openStudentView() {
        System.out.println("🎯 Mở quản lý thí sinh...");
        studentView = new StudentViewSimple();
        StudentController studentController = new StudentController(studentView);
        studentView.setVisible(true);
        mainView.setVisible(false);
        System.out.println("✅ Đã mở StudentView!");
    }
    
    /**
     * Method để mở view quản lý điểm thi
     */
    public void openScoreView() {
        System.out.println("🎯 Mở quản lý điểm thi...");
        scoreView = new ScoreViewSimple();
        ScoreController scoreController = new ScoreController(scoreView);
        scoreView.setVisible(true);
        mainView.setVisible(false);
        System.out.println("✅ Đã mở ScoreView!");
    }


    class LogoutListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("🔒 Đăng xuất khỏi hệ thống...");
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
        loginController.showLoginView();
        mainView.setVisible(false);
        System.out.println("✅ Quay về màn hình đăng nhập!");
    }
}

}
