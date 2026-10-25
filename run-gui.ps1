# Online Learning Management System - GUI Launch Script

Write-Host "`n========================================" -ForegroundColor Green
Write-Host "  LMS GUI Application Launcher" -ForegroundColor Green
Write-Host "========================================`n" -ForegroundColor Green

# Compile if needed
if (-not (Test-Path "build\classes\java\main\com\lms\LMSFrame.class")) {
    Write-Host "Compiling..."
    javac -d build\classes\java\main -sourcepath src\main\java src\main\java\com\lms\*.java
}

# Run GUI
Write-Host "Starting GUI...`n"
java -cp build\classes\java\main com.lms.LMSFrame

Write-Host "`nGUI closed."
