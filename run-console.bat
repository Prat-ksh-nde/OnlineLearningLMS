@echo off
REM Online Learning Management System - Console Launch Script

echo.
echo ========================================
echo  LMS Console Application Launcher
echo ========================================
echo.

cd /d "%~dp0"

REM Compile
echo Compiling...
javac -d build\classes\java\main -sourcepath src\main\java src\main\java\com\lms\*.java

REM Run Console
echo Running Console Version...
java -cp build\classes\java\main com.lms.LMSApplication

echo.
pause
