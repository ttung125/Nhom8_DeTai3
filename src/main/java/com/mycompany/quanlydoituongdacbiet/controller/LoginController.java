/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.controller;

import com.mycompany.quanlydoituongdacbiet.action.CheckLogin;
import com.mycompany.quanlydoituongdacbiet.entity.User;
import com.mycompany.quanlydoituongdacbiet.view.LoginView;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author PC
 */
public class LoginController 
{
    private CheckLogin checkLogin;
    private LoginView loginView;
    private MainView mainView;
    
    public LoginController(LoginView view) 
    {
        this.loginView = view;
        this.checkLogin = new CheckLogin();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() 
    {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("🔍 Bắt đầu xử lý đăng nhập...");
            
            User user = loginView.getUser();
            System.out.println("📝 User nhập: " + user.getUserName() + " / " + user.getPassword());
            
            if (checkLogin.checkUser(user)) 
            {
                // nếu đăng nhập thành công, mở màn hình chính quản lý điểm thi đại học
                System.out.println("🎉 Đăng nhập thành công! Chuyển đến MainView...");
                
                try {
                    mainView = new MainView();
                    System.out.println("✅ Đã tạo MainView thành công!");
                    
                    MainController mainController = new MainController(mainView);
                    System.out.println("✅ Đã tạo MainController thành công!");
                    
                    mainController.showMainView();
                    System.out.println("✅ Đã gọi showMainView() thành công!");
                    
                    loginView.setVisible(false);
                    System.out.println("✅ Đã ẩn LoginView thành công!");
                    
                    System.out.println("🚀 Hoàn thành chuyển đổi sang MainView!");
                    
                } catch (Exception ex) {
                    System.err.println("❌ Lỗi khi chuyển đến MainView: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } 
            else 
            {
                System.out.println("❌ Đăng nhập thất bại!");
                loginView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        }
    }
}
