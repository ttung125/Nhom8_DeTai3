/*
 * Test class để chạy và kiểm tra các view mới
 */
package com.mycompany.quanlydoituongdacbiet.test;

import com.mycompany.quanlydoituongdacbiet.controller.SubjectController;
import com.mycompany.quanlydoituongdacbiet.controller.ExamBlockController;
import com.mycompany.quanlydoituongdacbiet.view.SubjectViewSimple;
import com.mycompany.quanlydoituongdacbiet.view.ExamBlockViewSimple;

/**
 * Test class để kiểm tra SubjectView và ExamBlockView
 * 
 * @author PC
 */
public class TestNewViews {
    
    public static void main(String[] args) {
        // Set Look and Feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TestNewViews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        // Test SubjectView
        System.out.println("🧪 Testing SubjectView...");
        testSubjectView();
        
        // Test ExamBlockView (có thể uncomment để test)
        // System.out.println("🧪 Testing ExamBlockView...");
        // testExamBlockView();
    }
    
    public static void testSubjectView() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            SubjectViewSimple subjectView = new SubjectViewSimple();
            SubjectController controller = new SubjectController(subjectView);
            subjectView.setVisible(true);
            System.out.println("✅ SubjectView đã được mở!");
        });
    }
    
    public static void testExamBlockView() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ExamBlockViewSimple examBlockView = new ExamBlockViewSimple();
            ExamBlockController controller = new ExamBlockController(examBlockView);
            examBlockView.setVisible(true);
            System.out.println("✅ ExamBlockView đã được mở!");
        });
    }
}
