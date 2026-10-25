# 🖥️ GUI VERSION - ONLINE LEARNING MANAGEMENT SYSTEM

## ✅ GUI Successfully Created & Compiled!

A professional **Swing-based GUI** has been created for the LMS. No external dependencies needed!

---

## 🚀 HOW TO RUN THE GUI

### **Option 1: Direct Run (Fastest)**

```bash
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS
java -cp build\classes\java\main com.lms.LMSFrame
```

### **Option 2: From PowerShell**

```powershell
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS
java -cp build\classes\java\main com.lms.LMSFrame
```

### **Option 3: Using Gradle**

```bash
cd C:\Codebeamer\cb\DevOps\OnlineLearningLMS
gradlew.bat run
```

---

## 📋 GUI FEATURES

### **Three Main Tabs:**

#### 1️⃣ **Courses Tab**
- ✅ View all courses in a table
- ✅ See Course ID, Name, Instructor, Capacity, and Enrolled Count
- ✅ **Add Course** button - Open dialog to create new course
- ✅ **Refresh** button - Update course list
- Real-time updates when students enroll

#### 2️⃣ **Students Tab**
- ✅ View all registered students
- ✅ See Student ID, First/Last Name, Email, Enrollment Count
- ✅ **Register Student** button - Create new student account
- ✅ **Refresh** button - Update student list

#### 3️⃣ **Enrollments Tab**
- ✅ View all student-course enrollments
- ✅ See Student ID, Name, Course ID, Course Name
- ✅ **Enroll Student** button - Register student in course
- ✅ **Refresh** button - Update enrollment list
- Automatic validation (prevents double enrollment, full courses, etc.)

### **Menu Bar:**
- **File → Exit** - Close application
- **Help → About** - Application information

---

## 🎨 GUI SCREENSHOTS DESCRIPTION

### Main Window
```
┌─────────────────────────────────────────────────────────────────────┐
│ Online Learning Management System                         [_][□][X] │
├─────────────────────────────────────────────────────────────────────┤
│ File  Help                                                           │
├─────────────────────────────────────────────────────────────────────┤
│ │ Courses  │ Students  │ Enrollments  │                            │
├─────────────────────────────────────────────────────────────────────┤
│ [Add Course]  [Refresh]                                            │
├──────────┬──────────────┬──────────┬───────────┬─────────────────┤
│Course ID │Course Name   │Instructor│Max Cap   │Enrolled         │
├──────────┼──────────────┼──────────┼───────────┼─────────────────┤
│CS101     │Java Prog     │Dr. Smith │30        │0                │
│CS102     │Python Prog   │Dr. Jones │25        │0                │
│CS103     │Data Struct   │Prof Bown │20        │0                │
└──────────┴──────────────┴──────────┴───────────┴─────────────────┘
```

### Add Course Dialog
```
┌────────────────────────────────────┐
│ Add New Course                 [X] │
├────────────────────────────────────┤
│ Course ID:        [____________]   │
│ Course Name:      [____________]   │
│ Instructor:       [____________]   │
│ Max Capacity:     [____________]   │
│                   [Add Course]     │
└────────────────────────────────────┘
```

### Register Student Dialog
```
┌────────────────────────────────────┐
│ Register New Student           [X] │
├────────────────────────────────────┤
│ Student ID:       [____________]   │
│ First Name:       [____________]   │
│ Last Name:        [____________]   │
│ Email:            [____________]   │
│                   [Register]       │
└────────────────────────────────────┘
```

### Enroll Student Dialog
```
┌────────────────────────────────────┐
│ Enroll Student in Course       [X] │
├────────────────────────────────────┤
│ Student ID:       [____________]   │
│ Course ID:        [____________]   │
│                   [Enroll]         │
└────────────────────────────────────┘
```

---

## 🎯 FEATURES DEMONSTRATED

✅ **Add Course**
- Fill in: CS101, Java Programming, Dr. Smith, 30
- Click "Add Course"
- Appears in Courses table immediately

✅ **Register Student**
- Fill in: STU001, Alice, Johnson, alice@univ.edu
- Click "Register"
- Student added and appears in Students table

✅ **Enroll Student**
- Student ID: STU001
- Course ID: CS101
- Click "Enroll"
- Enrollment succeeds, course enrollment count increases from 0 → 1

✅ **Error Handling**
- Try enrolling in non-existent course → Error message
- Try enrolling already enrolled student → Error message
- Try adding duplicate course → Error message
- All handled with user-friendly dialogs

---

## 📊 SAMPLE DATA LOADED

When application starts, it automatically loads:

**Courses:**
- CS101: Java Programming (Dr. Smith, 30 seats)
- CS102: Python Programming (Dr. Jones, 25 seats)
- CS103: Data Structures (Prof. Brown, 20 seats)

**Students:**
- STU001: Alice Johnson
- STU002: Bob Smith
- STU003: Charlie Brown

You can then enroll them in courses via the GUI!

---

## 💻 TECHNICAL DETAILS

### Files Created:
- ✅ `LMSFrame.java` - Main Swing GUI window (450+ lines)
- ✅ Updated `CourseService.java` - Added `getAllStudents()` method
- ✅ Compiled with all dependencies included

### GUI Components Used:
- `JFrame` - Main window
- `JTabbedPane` - Three tabs for different sections
- `JTable` - Display courses and students
- `JDialog` - Add course, register student, enroll student dialogs
- `JButton`, `JTextField`, `JLabel` - Input components
- `JMenuBar`, `JMenu` - Menu bar
- `DefaultTableModel` - Dynamic table data

### No External Dependencies:
- ✅ Uses only Java Swing (built-in)
- ✅ No need to install anything
- ✅ Cross-platform (Windows, Mac, Linux)

---

## 🚀 COMPLETE USAGE WORKFLOW

### 1. Start Application
```bash
java -cp build\classes\java\main com.lms.LMSFrame
```

### 2. GUI Opens with Sample Data
- 3 courses visible
- 3 students registered
- 0 enrollments yet

### 3. Click "Enrollments" Tab
- Click "Enroll Student" button

### 4. Enroll Student
- Enter Student ID: STU001
- Enter Course ID: CS101
- Click "Enroll"
- Success message appears

### 5. Verify in Courses Tab
- Refresh or click "Courses" tab
- CS101 now shows "Enrolled: 1" instead of 0

### 6. Add New Course
- Click "Courses" tab
- Click "Add Course" button
- Fill in details
- Click "Add Course"
- New course appears immediately

---

## 🔧 CUSTOMIZATION

### Change Sample Data
Edit `LMSFrame.java` → `loadSampleData()` method to add different courses/students

### Customize Colors
Add to `initializeUI()` method:
```java
panel.setBackground(new Color(240, 240, 240));
button.setBackground(new Color(0, 120, 215));
```

### Change Window Size
In `initializeUI()`:
```java
setSize(1200, 800);  // Width, Height
```

---

## ⚠️ KNOWN LIMITATIONS

1. **No Persistent Storage** - Data exists only during session
   - Solution: Add file I/O or database (future enhancement)

2. **No Remove/Delete Functions** - Can't remove courses/students
   - Solution: Add remove buttons and dialogs

3. **Enrollments Table** - Shows all enrollments (requires tracking)
   - Solution: Implement enrollment history tracking

These are fine for a college practical project but could be enhanced for production.

---

## 🎓 FOR COLLEGE EXAM

**Show Examiner:**
1. Run GUI application
2. Click through all three tabs
3. Add a new course via dialog
4. Register a new student
5. Enroll student in course
6. Show real-time updates
7. Handle error cases (invalid input)
8. Explain code: `LMSFrame.java` structure

**Talking Points:**
- Swing framework for desktop GUI
- Event-driven programming (ActionListeners)
- MVC pattern (CourseService = Model, LMSFrame = View/Controller)
- User-friendly error messages
- Professional UI design

---

## 📖 FILES

- **LMSFrame.java** - Main GUI (450+ lines)
- **CourseService.java** - Business logic (updated with getAllStudents)
- **Course.java** - Model
- **Student.java** - Model
- **LMSApplication.java** - Console version (alternative)

---

## ✅ READY TO USE!

The GUI is fully functional and ready for demonstration. No additional setup needed.

**Run it now:**
```bash
java -cp build\classes\java\main com.lms.LMSFrame
```

---

**Enjoy your GUI! 🎨**
