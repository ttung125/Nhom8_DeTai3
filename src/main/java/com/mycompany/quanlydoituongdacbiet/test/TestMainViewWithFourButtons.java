package com.mycompany.quanlydoituongdacbiet.test;

import com.mycompany.quanlydoituongdacbiet.controller.MainController;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import javax.swing.SwingUtilities;

/**
 * Test MainView với 4 nút mới
 */
public class TestMainViewWithFourButtons {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Create MainView with 4 buttons
                MainView mainView = new MainView();
                MainController mainController = new MainController(mainView);
                
                // Show the main view
                mainController.showMainView();
                
                System.out.println("🎉 MainView với 4 nút đã được khởi tạo!");
                System.out.println("- Quản lý thí sinh");
                System.out.println("- Quản lý điểm thi");
                System.out.println("- Quản lý môn thi");
                System.out.println("- Quản lý khối thi");
                
            } catch (Exception e) {
                System.err.println("Lỗi khi khởi động MainView: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
