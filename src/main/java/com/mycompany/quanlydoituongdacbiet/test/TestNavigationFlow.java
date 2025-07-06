package com.mycompany.quanlydoituongdacbiet.test;

import com.mycompany.quanlydoituongdacbiet.controller.MainController;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import javax.swing.SwingUtilities;

/**
 * Test luồng navigation từ MainView sang các module và quay lại
 * Kiểm tra xem các nút "Quay lại" có hoạt động đúng không
 */
public class TestNavigationFlow {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("🚀 Bắt đầu test luồng navigation...");
                
                // Tạo MainView
                MainView mainView = new MainView();
                MainController mainController = new MainController(mainView);
                
                // Hiển thị MainView
                mainController.showMainView();
                
                System.out.println("✅ MainView đã được hiển thị");
                System.out.println("📌 Hướng dẫn test:");
                System.out.println("1. Nhấn vào từng nút để mở module tương ứng");
                System.out.println("2. Trong mỗi module, nhấn nút 'Quay lại' để quay về MainView");
                System.out.println("3. Kiểm tra xem MainView có hiển thị lại đúng không");
                System.out.println("4. Thử với tất cả 4 module: Thí sinh, Điểm thi, Môn thi, Khối thi");
                
            } catch (Exception e) {
                System.err.println("❌ Lỗi khi test navigation: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
