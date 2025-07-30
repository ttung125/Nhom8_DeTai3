# 🎓 Hệ thống Quản lý Điểm thi Đại học

## 📖 Mô tả
Hệ thống quản lý điểm thi đại học được phát triển bằng **Java Swing** với kiến trúc **MVC Pattern**, chuyển đổi từ hệ thống quản lý dân cư ban đầu. Hệ thống cung cấp đầy đủ các tính năng quản lý thông tin thí sinh, điểm thi, môn thi, khối thi cùng các chức năng tìm kiếm nâng cao, thống kê chi tiết và báo cáo trực quan.

### ✨ Đặc điểm nổi bật:
- 🖥️ **Giao diện đẹp mắt**: UI hiện đại với background tùy chỉnh
- 📊 **Biểu đồ trực quan**: Tích hợp Java Swing Custom Chart
- 💾 **Lưu trữ XML**: Dữ liệu được lưu trữ dạng XML với JAXB
- 🔍 **Tìm kiếm thông minh**: Hỗ trợ tìm kiếm gần đúng và theo khoảng
- 📈 **Thống kê chi tiết**: Báo cáo toàn diện với số liệu thực tế
- ⚡ **Hiệu suất cao**: Xử lý nhanh với Maven build system

## 🚀 Tính năng chính

### 👥 1. Quản lý Thí sinh
- ✅ **CRUD đầy đủ**: Thêm, sửa, xóa, xem thông tin thí sinh
- ✅ **Mã tự động**: Số báo danh tự động tăng dần (230122, 234375...)
- ✅ **Calendar picker**: Chọn ngày sinh trực quan bằng JDateChooser
- ✅ **Dropdown thông minh**: Giới tính và khối thi từ danh sách có sẵn
- ✅ **Bảng hiển thị**: JTable với sorting và selection
- ✅ **Validation mạnh**: Kiểm tra đầy đủ email, số điện thoại, ngày sinh
- ✅ **XML Storage**: Lưu trữ an toàn với encoding UTF-8

### 📊 2. Quản lý Điểm thi
- ✅ **Nhập điểm linh hoạt**: Hỗ trợ nhiều môn thi cho mỗi thí sinh
- ✅ **Thông tin đầy đủ**: Ngày thi, trạng thái (Có mặt/Vắng mặt/Vi phạm)
- ✅ **Format chuyên nghiệp**: Điểm số hiển thị dạng decimal (8.50, 9.25)
- ✅ **Kiểm tra trùng lặp**: Ngăn nhập trùng điểm cho cùng thí sinh + môn
- ✅ **Tự động tính toán**: Thống kê đạt/không đạt, điểm trung bình
- ✅ **Tìm kiếm nâng cao**: Theo khoảng điểm, môn thi, trạng thái

### 📚 3. Quản lý Môn thi
- ✅ **Thông tin chi tiết**: Mã môn, tên môn, thời gian thi, điểm tối đa
- ✅ **Mã tự động**: MH001, MH002... với prefix "MH"
- ✅ **Môn thi chuẩn**: Toán, Lý, Hóa, Sinh, Văn, Anh, Sử, Địa, GDCD
- ✅ **Tìm kiếm thông minh**: Theo tên môn và khoảng thời gian
- ✅ **Thống kê**: Thời gian thi trung bình, số môn theo khối

### 🎯 4. Quản lý Khối thi
- ✅ **Khối thi chuẩn**: A (Toán-Lý-Hóa), B (Toán-Sinh-Hóa), C (Văn-Sử-Địa)...
- ✅ **Drag & Drop**: Giao diện chọn môn thi trực quan
- ✅ **Validation logic**: Kiểm tra số môn hợp lệ cho từng khối
- ✅ **Thống kê khối**: Số thí sinh theo khối, số môn trung bình

### 🔍 5. Tìm kiếm & Lọc nâng cao
- ✅ **Tìm kiếm gần đúng**: Nhập "An" → tìm "Nguyễn Văn An", "Lê Thành An"
- ✅ **Tìm theo khoảng tuổi**: Từ 18 đến 20 tuổi
- ✅ **Tìm theo khoảng điểm**: Từ 7.0 đến 9.0 điểm
- ✅ **Tìm theo môn**: Lọc điểm theo môn thi cụ thể
- ✅ **Tìm theo khối**: Lọc thí sinh theo khối thi
- ✅ **Tìm theo trạng thái**: Có mặt, vắng mặt, vi phạm

### 📈 6. Thống kê & Báo cáo
- ✅ **Thống kê thí sinh**: 
  - Tổng số thí sinh đăng ký
  - Phân bố nam/nữ (%)
  - Độ tuổi trung bình, min, max
  - Phân bố theo khối thi
- ✅ **Thống kê điểm thi**:
  - Tổng số bài thi đã chấm
  - Tỷ lệ đạt/không đạt (điểm ≥ 5.0)
  - Điểm cao nhất, thấp nhất, trung bình
  - Phân bố điểm theo môn
- ✅ **Thống kê môn thi**:
  - Thời gian thi trung bình
  - Độ khó theo điểm trung bình
  - Số thí sinh đăng ký theo môn
- ✅ **Format số đẹp**: 1,000 thí sinh, 8.50 điểm

### 🎨 7. Giao diện & UX
- ✅ **Theme nhất quán**: Background xanh dương chuyên nghiệp
- ✅ **Font tiêu chuẩn**: Times New Roman cho độ rõ nét
- ✅ **Layout responsive**: Tự động điều chỉnh theo kích thước
- ✅ **Icon trực quan**: Buttons với màu sắc phân biệt chức năng
- ✅ **Thông báo rõ ràng**: Success/Error messages chi tiết
- ✅ **Navigation dễ dàng**: Back/Forward giữa các màn hình

### ⚙️ 8. Tính năng kỹ thuật
- ✅ **Auto-increment ID**: Đảm bảo tính duy nhất
- ✅ **Data validation**: Kiểm tra tính hợp lệ đầu vào
- ✅ **Error handling**: Xử lý exception toàn diện
- ✅ **XML backup**: Tự động sao lưu dữ liệu
- ✅ **Unicode support**: Hỗ trợ tiếng Việt đầy đủ
- ✅ **Cross-platform**: Chạy trên Windows, Linux, macOS

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

### Tài khoản admin:
- **Username**: `admin`
- **Password**: `admin`

### Tài khoản thí sinh:
- **Username**: `sv001`
- **Password**: `123`

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
