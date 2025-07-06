@echo off
title He thong Quan ly Diem thi Dai hoc

echo ================================
echo   HE THONG QUAN LY DIEM THI DAI HOC
echo ================================
echo.

REM Kiem tra Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java khong duoc cai dat hoac khong co trong PATH!
    echo Vui long cai dat Java JDK 8 hoac cao hon.
    pause
    exit /b 1
)

echo [INFO] Dang build project...
call mvn clean compile

if %errorlevel% neq 0 (
    echo [ERROR] Build that bai!
    pause
    exit /b 1
)

echo [INFO] Dang khoi dong ung dung...
echo.

REM Chay ung dung
call mvn exec:java -Dexec.mainClass="com.mycompany.quanlydoituongdacbiet.QuanLyDoiTuong.QuanLyDoiTuongDacBiet"

if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Ung dung khong khoi dong duoc!
    echo Kiem tra lai cau hinh va thu lai.
)

echo.
echo [INFO] Cam on ban da su dung he thong!
pause
