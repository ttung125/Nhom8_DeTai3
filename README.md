# Hệ thống Quản lý Điểm thi Đại học

## Mô tả
Hệ thống quản lý điểm thi đại học được phát triển bằng Java Swing, chuyển đổi từ hệ thống quản lý dân cư ban đầu. Hệ thống cung cấp các tính năng quản lý thông tin thí sinh, điểm thi, môn thi, khối thi cùng các chức năng tìm kiếm và thống kê chi tiết.

## Tính năng chính

### 1. Quản lý Thí sinh
- ✅ Thêm, sửa, xóa thông tin thí sinh
- ✅ Mã thí sinh tự động tăng dần 
- ✅ Chọn ngày sinh bằng Calendar picker
- ✅ Dropdown list cho giới tính và khối thi
- ✅ Hiển thị dữ liệu dạng bảng (JTable)
- ✅ Validation đầy đủ và xử lý lỗi
- ✅ Lưu trữ dữ liệu định dạng XML

### 2. Quản lý Điểm thi
- ✅ Nhập điểm thi cho từng thí sinh và môn
- ✅ Chọn ngày thi bằng Calendar
- ✅ Dropdown cho môn thi, ca thi, trạng thái
- ✅ Format điểm số dạng tiền tệ (1,000.00)
- ✅ Kiểm tra trùng lặp (thí sinh + môn)
- ✅ Hiển thị kết quả dạng bảng

### 3. Quản lý Môn thi
- ✅ Thêm, sửa, xóa thông tin môn thi
- ✅ Mã môn tự động tăng dần (MH001, MH002...)
- ✅ Quản lý thời gian thi và điểm tối đa
- ✅ Tìm kiếm theo tên môn và khoảng thời gian
- ✅ Thống kê thời gian thi trung bình
- ✅ Sắp xếp theo tên môn

### 4. Quản lý Khối thi
- ✅ Thêm, sửa, xóa thông tin khối thi
- ✅ Mã khối tự động tăng dần (KB001, KB002...)
- ✅ Chọn môn thi từ danh sách có sẵn
- ✅ Giao diện drag-and-drop chọn môn
- ✅ Tìm kiếm theo tên khối và số lượng môn
- ✅ Thống kê số môn trung bình

### 5. Tìm kiếm nâng cao
- ✅ **Tìm kiếm gần đúng theo tên**: Nhập "A" → tìm "Nguyễn Văn A", "Trần Thị A"...
- ✅ **Tìm kiếm theo khoảng tuổi**: Từ X đến Y tuổi
- ✅ **Tìm kiếm theo khoảng điểm**: Từ X đến Y điểm
- ✅ Tìm theo mã thí sinh, môn thi, phòng thi

### 6. Thống kê chi tiết
- ✅ **Thí sinh**: Tổng số, nam/nữ, tuổi lớn nhất/nhỏ nhất
- ✅ **Điểm thi**: Tổng bài thi, đạt/không đạt, điểm cao nhất/thấp nhất/trung bình
- ✅ Hiển thị số liệu dạng dấu phẩy (1,000)

### 7. Tính năng bổ sung
- ✅ Sắp xếp theo tên, mã, điểm số
- ✅ ID tự tăng và duy nhất
- ✅ Xử lý lỗi nhập sai, trùng dữ liệu
- ✅ Giao diện giữ nguyên style ban đầu
- ✅ Background: login (neon.png), views (blue-blazed-background.jpg)

## Yêu cầu hệ thống
- **Java**: 8 hoặc cao hơn
- **Maven**: 3.6 trở lên
- **IDE**: NetBeans, IntelliJ IDEA, Eclipse

## Cài đặt và chạy

### 1. Clone project
```bash
git clone <repository-url>
cd Nhom8_DeTai3
```

### 2. Build project
```bash
mvn clean compile
```

### 3. Chạy ứng dụng
```bash
mvn exec:java -Dexec.mainClass="com.mycompany.quanlydoituongdacbiet.QuanLyDoiTuong.QuanLyDoiTuongDacBiet"
```

### 4. Hoặc chạy từ JAR file
```bash
mvn package
java -jar target/QuanLyDoiTuongDacBiet-1.0-SNAPSHOT.jar
```

## Tài khoản đăng nhập

### Tài khoản mặc định:
- **Username**: `admin`
- **Password**: `admin`

### Tài khoản bổ sung:
- **Username**: `user`
- **Password**: `123456`

- **Username**: `teacher`
- **Password**: `password`

## Cấu trúc dự án

```
src/main/java/com/mycompany/quanlydoituongdacbiet/
├── QuanLyDoiTuong/
│   └── QuanLyDoiTuongDacBiet.java     # Main class
├── entity/                            # Các lớp thực thể
│   ├── Student.java                   # Thông tin thí sinh
│   ├── Subject.java                   # Môn thi  
│   ├── ExamBlock.java                 # Khối thi
│   ├── ExamScore.java                 # Điểm thi
│   ├── StudentXML.java                # XML wrapper
│   └── ...
├── action/                            # Logic xử lý
│   ├── ManagerStudent.java            # Quản lý thí sinh
│   ├── ManagerExamScore.java          # Quản lý điểm thi
│   ├── ManagerSubject.java            # Quản lý môn thi
│   └── ...
├── view/                              # Giao diện người dùng
│   ├── LoginView.java                 # Màn hình đăng nhập
│   ├── MainView.java                  # Màn hình chính
│   ├── StudentViewSimple.java         # Quản lý thí sinh
│   ├── ScoreViewSimple.java           # Quản lý điểm thi
│   ├── SubjectViewSimple.java         # Quản lý môn thi
│   ├── ExamBlockViewSimple.java       # Quản lý khối thi
│   └── *.png, *.jpg                   # Resource ảnh
├── controller/                        # Điều khiển
│   ├── LoginController.java           # Controller đăng nhập
│   ├── MainController.java            # Controller chính
│   ├── StudentController.java         # Controller thí sinh
│   ├── ScoreController.java           # Controller điểm thi
│   ├── SubjectController.java         # Controller môn thi
│   └── ExamBlockController.java       # Controller khối thi
data/                                  # Dữ liệu XML
├── Students.xml                       # Danh sách thí sinh
├── Subjects.xml                       # Danh sách môn thi
├── ExamBlocks.xml                     # Danh sách khối thi
└── ExamScores.xml                     # Điểm thi của thí sinh
```

## Cơ sở dữ liệu XML

Hệ thống sử dụng XML để lưu trữ dữ liệu với cấu trúc như sau:

### 1. Students.xml - Thông tin thí sinh
```xml
<Students>
    <Student>
        <studentId>TS001</studentId>
        <fullName>Nguyễn Văn An</fullName>
        <birthDate>2005-03-15T00:00:00+07:00</birthDate>
        <gender>Nam</gender>
        <address>123 Đường ABC, Quận 1, TP.HCM</address>
        <phoneNumber>0901234567</phoneNumber>
        <email>nguyenvanan@email.com</email>
        <examBlock>Khối A</examBlock>
    </Student>
    ...
</Students>
```

### 2. Subjects.xml - Môn thi
```xml
<Subjects>
    <Subject>
        <subjectId>MH001</subjectId>
        <subjectName>Toán</subjectName>
        <subjectCode>TOAN</subjectCode>
        <examTime>180</examTime>
        <maxScore>10.0</maxScore>
        <description>Môn Toán trong kỳ thi THPT Quốc gia</description>
    </Subject>
    ...
</Subjects>
```

### 3. ExamBlocks.xml - Khối thi
```xml
<ExamBlocks>
    <ExamBlock>
        <blockId>KB001</blockId>
        <blockName>Khối A</blockName>
        <blockCode>A</blockCode>
        <subjects>
            <subject>Toán</subject>
            <subject>Vật Lý</subject>
            <subject>Hóa Học</subject>
        </subjects>
        <description>Khối A - Khối tự nhiên gồm Toán, Lý, Hóa</description>
        <totalSubjects>3</totalSubjects>
    </ExamBlock>
    ...
</ExamBlocks>
```

### 4. ExamScores.xml - Điểm thi
```xml
<ExamScores>
    <ExamScore>
        <scoreId>D001</scoreId>
        <studentId>TS001</studentId>
        <subjectId>MH001</subjectId>
        <score>8.5</score>
        <examDate>2024-06-28T08:00:00+07:00</examDate>
        <examRoom>P101</examRoom>
        <notes>Thi đợt 1</notes>
    </ExamScore>
    ...
</ExamScores>
```

## Hướng dẫn sử dụng

### 1. Đăng nhập
- Khởi động ứng dụng
- Nhập username/password
- Click "Đăng nhập"

### 2. Màn hình chính
- **Quản lý thí sinh**: Thêm, sửa, xóa thông tin thí sinh
- **Quản lý điểm thi**: Nhập và quản lý điểm thi
- **Quản lý môn thi**: Thêm, sửa, xóa thông tin môn thi
- **Quản lý khối thi**: Thêm, sửa, xóa thông tin khối thi

### 3. Quản lý thí sinh
- **Thêm**: Điền form → Click "Thêm"
- **Sửa**: Chọn từ bảng → Sửa form → Click "Sửa"  
- **Xóa**: Chọn từ bảng → Click "Xóa"
- **Tìm kiếm**: Nhập tên hoặc khoảng tuổi → Click "Tìm"

### 4. Quản lý điểm thi
- **Thêm điểm**: Chọn thí sinh, môn → Nhập điểm → Click "Thêm"
- **Tìm kiếm**: Theo mã thí sinh, môn, khoảng điểm
- **Thống kê**: Xem số liệu tổng hợp ở cuối màn hình

### 5. Quản lý môn thi
- **Thêm**: Điền form thông tin môn thi → Click "Thêm"
- **Sửa**: Chọn môn thi từ danh sách → Sửa thông tin → Click "Sửa"
- **Xóa**: Chọn môn thi → Click "Xóa"
- **Tìm kiếm**: Nhập tên môn hoặc khoảng thời gian thi → Click "Tìm"

### 6. Quản lý khối thi
- **Thêm**: Điền form thông tin khối thi → Click "Thêm"
- **Sửa**: Chọn khối thi từ danh sách → Sửa thông tin → Click "Sửa"
- **Xóa**: Chọn khối thi → Click "Xóa"
- **Tìm kiếm**: Nhập tên khối hoặc số lượng môn → Click "Tìm"

## Xử lý lỗi
- ✅ Validation dữ liệu đầu vào
- ✅ Kiểm tra trùng lặp ID/mã
- ✅ Xử lý lỗi file XML
- ✅ Thông báo lỗi rõ ràng cho người dùng

## Đóng gói ứng dụng

### Tạo JAR executable:
```bash
mvn clean package
```

### File JAR được tạo tại:
```
target/QuanLyDoiTuongDacBiet-1.0-SNAPSHOT.jar
```

### Chạy JAR:
```bash
java -jar target/QuanLyDoiTuongDacBiet-1.0-SNAPSHOT.jar
```

## Lưu ý
- Đảm bảo có file ảnh background trong thư mục view
- File XML sẽ tự động tạo khi chạy lần đầu
- Backup dữ liệu XML thường xuyên
- Hệ thống hỗ trợ Unicode (tiếng Việt)

## Liên hệ hỗ trợ
- **Nhóm phát triển**: Nhóm 8
- **Đề tài**: Số 3 - Hệ thống quản lý điểm thi đại học
- **Phiên bản**: 1.0-SNAPSHOT

---
**Ghi chú**: Hệ thống được chuyển đổi từ "Quản lý dân cư" sang "Quản lý điểm thi đại học" với đầy đủ tính năng mở rộng theo yêu cầu.
