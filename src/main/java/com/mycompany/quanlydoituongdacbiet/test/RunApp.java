package com.mycompany.quanlydoituongdacbiet.test;

import com.mycompany.quanlydoituongdacbiet.controller.LoginController;
import com.mycompany.quanlydoituongdacbiet.view.LoginView;
import javax.swing.SwingUtilities;

/**
 * Simple test to run the application
 */
public class RunApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Start the application with Login screen
                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(loginView);
                loginView.setVisible(true);
                
                System.out.println("Ứng dụng Quản lý điểm thi đại học đã khởi động!");
                System.out.println("Tài khoản login:");
                System.out.println("- Username: admin");
                System.out.println("- Password: admin");
                
            } catch (Exception e) {
                System.err.println("Lỗi khi khởi động ứng dụng: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
