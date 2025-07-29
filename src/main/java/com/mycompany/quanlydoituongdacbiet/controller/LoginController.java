package com.mycompany.quanlydoituongdacbiet.controller;

import com.mycompany.quanlydoituongdacbiet.action.CheckLogin;
import com.mycompany.quanlydoituongdacbiet.entity.User;
import com.mycompany.quanlydoituongdacbiet.view.LoginView;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import com.mycompany.quanlydoituongdacbiet.view.UserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller xử lý logic đăng nhập
 */
public class LoginController {
    private CheckLogin checkLogin;
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.checkLogin = new CheckLogin();

        // Đăng ký sự kiện cho nút đăng nhập
        this.loginView.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    /**
     * Lớp xử lý sự kiện đăng nhập
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("🔍 Bắt đầu xử lý đăng nhập...");

            User inputUser = loginView.getUser();
            System.out.println("📝 Thông tin nhập: " + inputUser.getUserName() + " / " + inputUser.getPassword());

            User authenticatedUser = checkLogin.checkUser(inputUser); // Trả về User nếu đúng
            if (authenticatedUser != null) {
                if (authenticatedUser.isAdmin()) {
                    System.out.println("🎉 Đăng nhập thành công (admin) → Chuyển đến MainView...");
                    try {
                        MainView mainView = new MainView();
                        MainController mainController = new MainController(mainView);
                        mainController.showMainView();
                        loginView.setVisible(false);
                    } catch (Exception ex) {
                        System.err.println("❌ Lỗi khi mở MainView: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else if (authenticatedUser.isUser()) {
                    System.out.println("🎉 Đăng nhập thành công (user) → Chuyển đến UserView...");
                    try {
                        UserView userView = new UserView(authenticatedUser);
                        userView.setVisible(true);
                        loginView.setVisible(false);
                    } catch (Exception ex) {
                        System.err.println("❌ Lỗi khi mở UserView: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    loginView.showMessage("❌ Không xác định được vai trò người dùng.");
                }
            } else {
                System.out.println("❌ Đăng nhập thất bại!");
                loginView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        }
    }
}
