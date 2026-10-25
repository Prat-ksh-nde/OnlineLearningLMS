# QUICK COMMANDS - Copy & Paste

## 🚀 FASTEST WAY TO RUN (Copy & Paste These)

### Windows (Command Prompt or PowerShell)

```batch
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS
javac -d build\classes\java\main -sourcepath src\main\java src\main\java\com\lms\*.java
java -cp build\classes\java\main com.lms.LMSApplication
```

### Mac/Linux

```bash
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS
javac -d build/classes/java/main -sourcepath src/main/java src/main/java/com/lms/*.java
java -cp build/classes/java/main com.lms.LMSApplication
```

---

## 📦 WITH GRADLE

### Windows

```batch
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS
gradlew.bat build
gradlew.bat run
```

### Mac/Linux

```bash
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS
chmod +x gradlew
./gradlew build
./gradlew run
```

---

## 🧪 RUN TESTS ONLY

### Windows
```batch
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS
gradlew.bat test
```

### Mac/Linux
```bash
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS
./gradlew test
```

---

## 🔄 GIT & CI/CD SETUP

### First Time Setup

```bash
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS
git init
git add .
git commit -m "Initial Online LMS with CI/CD"
```

### Push to GitLab (Creates CI/CD Pipeline)

```bash
git remote add origin https://gitlab.com/YOUR_USERNAME/OnlineLearningLMS.git
git push -u origin main
```

Then watch your pipeline run automatically at: `https://gitlab.com/YOUR_USERNAME/OnlineLearningLMS/-/pipelines`

---

## ✅ EXPECTED OUTPUT

```
======== ONLINE LEARNING MANAGEMENT SYSTEM ========

[INFO] System initialized with 3 courses and 3 students.

[ACTION] Displaying all courses:

========== AVAILABLE COURSES ==========
Course{courseId='CS101', courseName='Java Programming', instructor='Dr. Smith', maxCapacity=30, enrolledCount=0}
Course{courseId='CS102', courseName='Python Programming', instructor='Dr. Jones', maxCapacity=25, enrolledCount=0}
Course{courseId='CS103', courseName='Data Structures', instructor='Prof. Brown', maxCapacity=20, enrolledCount=0}
=======================================

[ACTION] Getting details for course CS101:
  Course{courseId='CS101', courseName='Java Programming', instructor='Dr. Smith', maxCapacity=30, enrolledCount=0}

[ACTION] Enrolling student STU001 in CS101:
  SUCCESS: Student enrolled!

[ACTION] Attempting to enroll in non-existent course CS999:
  ERROR: Course CS999 not found

[ACTION] Final course listing:

========== AVAILABLE COURSES ==========
Course{courseId='CS101', courseName='Java Programming', instructor='Dr. Smith', maxCapacity=30, enrolledCount=1}
Course{courseId='CS102', courseName='Python Programming', instructor='Dr. Jones', maxCapacity=25, enrolledCount=0}
Course{courseId='CS103', courseName='Data Structures', instructor='Prof. Brown', maxCapacity=20, enrolledCount=0}
=======================================

===================================================
```

---

## ⚡ ONE-LINER (If You Want to Be Fast)

```bash
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS && javac -d build\classes\java\main -sourcepath src\main\java src\main\java\com\lms\*.java && java -cp build\classes\java\main com.lms.LMSApplication
```

---

**That's it! You're ready to go!** 🎉
