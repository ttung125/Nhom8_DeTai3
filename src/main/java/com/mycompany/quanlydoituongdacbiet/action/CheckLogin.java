package com.mycompany.quanlydoituongdacbiet.action;

import com.mycompany.quanlydoituongdacbiet.entity.User;
import java.util.ArrayList;
import java.util.List;

public class CheckLogin {

    private List<User> users;

    public CheckLogin() {
        // Khởi tạo danh sách tài khoản test
        users = new ArrayList<>();
        users.add(new User("admin", "admin", "admin"));
        users.add(new User("sv001", "123", "user"));
        users.add(new User("sv002", "456", "user"));
        users.add(new User("sv003", "789", "user"));
    }

    /**
     * Kiểm tra tài khoản và mật khẩu.
     * Nếu hợp lệ trả về User (có role), ngược lại trả về null.
     */
    public User checkUser(User inputUser) {
        if (inputUser == null) return null;

        for (User u : users) {
            if (u.getUserName().equals(inputUser.getUserName()) &&
                u.getPassword().equals(inputUser.getPassword())) {
                return u; // Đăng nhập thành công, trả về User với role
            }
        }

        return null; // Sai tài khoản hoặc mật khẩu
    }
}
