@echo off
title Build JAR - He thong Quan ly Diem thi Dai hoc

echo ================================
echo   BUILD JAR EXECUTABLE
echo ================================
echo.

REM Kiem tra Maven
call mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Maven khong duoc cai dat hoac khong co trong PATH!
    echo Vui long cai dat Apache Maven.
    pause
    exit /b 1
)

echo [INFO] Dang clean va build project...
call mvn clean package -DskipTests

if %errorlevel% neq 0 (
    echo [ERROR] Build that bai!
    pause
    exit /b 1
)

echo.
echo [SUCCESS] Build thanh cong!
echo [INFO] File JAR da duoc tao tai: target\QuanLyDoiTuongDacBiet-1.0-SNAPSHOT.jar

echo.
echo De chay ung dung, su dung lenh:
echo java -jar target\QuanLyDoiTuongDacBiet-1.0-SNAPSHOT.jar

echo.
pause
