/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quanlydoituongdacbiet.QuanLyDoiTuong;

import com.mycompany.quanlydoituongdacbiet.controller.LoginController;
import com.mycompany.quanlydoituongdacbiet.view.LoginView;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Hệ thống quản lý điểm thi đại học
 * 
 * @author PC
 */
public class QuanLyDoiTuongDacBiet 
{
    public static void main(String[] args) {
        System.out.println("🚀 Khởi động hệ thống quản lý điểm thi đại học...");
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Khởi tạo LoginView và LoginController
                    LoginView view = new LoginView();
                    LoginController controller = new LoginController(view);
                    
                    // Hiển thị màn hình login
                    controller.showLoginView();
                    System.out.println("✅ Đã hiển thị màn hình đăng nhập!");
                    
                } catch (Exception e) {
                    System.err.println("❌ Lỗi khi khởi động ứng dụng: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}
