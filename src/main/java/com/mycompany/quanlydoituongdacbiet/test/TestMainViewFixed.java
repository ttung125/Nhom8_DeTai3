package com.mycompany.quanlydoituongdacbiet.test;

import com.mycompany.quanlydoituongdacbiet.controller.MainController;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import javax.swing.SwingUtilities;

/**
 * Test xem MainView có tạo được không sau khi sửa lỗi NullPointerException
 */
public class TestMainViewFixed {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("🧪 Test MainView sau khi sửa lỗi...");
                
                // Tạo MainView để xem có lỗi NullPointerException không
                MainView mainView = new MainView();
                System.out.println("✅ MainView tạo thành công!");
                
                // Tạo MainController
                MainController mainController = new MainController(mainView);
                System.out.println("✅ MainController tạo thành công!");
                
                // Hiển thị MainView
                mainController.showMainView();
                System.out.println("✅ MainView hiển thị thành công!");
                
                System.out.println("🎉 Tất cả hoạt động bình thường!");
                
            } catch (Exception e) {
                System.err.println("❌ Lỗi khi test MainView: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
