@echo off
REM Quick Push to GitHub Script

echo.
echo ========================================
echo  Push LMS Project to GitHub
echo ========================================
echo.

REM Check if git is installed
git --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Git is not installed or not in PATH
    echo Please install Git from: https://git-scm.com/download/win
    pause
    exit /b 1
)

REM Check if git is initialized
if not exist ".git" (
    echo Initializing git repository...
    git init
    echo.
)

REM Configure git
echo Configuring git...
git config --global user.name "Pratik Shinde" >nul 2>&1
git config --global user.email "pratik@example.com" >nul 2>&1

REM Check status
echo.
echo Current git status:
git status
echo.

REM Ask for repository URL
set /p GITHUB_URL="Enter GitHub Repository URL (e.g., https://github.com/USERNAME/OnlineLearningLMS.git): "

if "%GITHUB_URL%"=="" (
    echo ERROR: Repository URL cannot be empty
    pause
    exit /b 1
)

REM Add files
echo.
echo Adding files to git...
git add .

REM Check if there are changes
git status --porcelain >nul 2>&1
if errorlevel 1 (
    echo No changes to commit
    pause
    exit /b 0
)

REM Commit
echo.
echo Creating commit...
git commit -m "Initial commit: Complete LMS with Console and Swing GUI

- Java-based Online Learning Management System
- Console and Swing GUI interfaces
- Gradle build system with Wrapper
- 51+ JUnit 5 tests
- GitLab CI/CD pipeline with 3 stages
- Professional documentation"

REM Add remote
echo.
echo Adding GitHub remote...
git remote remove origin >nul 2>&1
git remote add origin %GITHUB_URL%

REM Rename branch
echo.
echo Setting up main branch...
git branch -M main

REM Push
echo.
echo Pushing to GitHub...
git push -u origin main

if errorlevel 0 (
    echo.
    echo ========================================
    echo  SUCCESS! Project pushed to GitHub!
    echo ========================================
    echo.
    echo View your project at:
    echo %GITHUB_URL%
    echo.
) else (
    echo.
    echo ERROR: Push failed. Check your credentials and URL.
    echo.
)

pause
