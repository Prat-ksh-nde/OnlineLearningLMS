@echo off
REM Online Learning Management System - GUI Launch Script
REM Run this to start the GUI version

echo.
echo ========================================
echo  LMS GUI Application Launcher
echo ========================================
echo.

cd /d "%~dp0"

REM Compile if needed
if not exist "build\classes\java\main\com\lms\LMSFrame.class" (
    echo Compiling...
    javac -d build\classes\java\main -sourcepath src\main\java src\main\java\com\lms\*.java
)

REM Run GUI
echo Starting GUI...
java -cp build\classes\java\main com.lms.LMSFrame

echo GUI closed.
pause
