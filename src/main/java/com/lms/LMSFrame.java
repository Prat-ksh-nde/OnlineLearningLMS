package com.lms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LMSFrame extends JFrame {
    private CourseService courseService;
    private JTabbedPane tabbedPane;
    private JTable coursesTable;
    private JTable studentsTable;
    private JTable enrollmentsTable;
    private DefaultTableModel coursesModel;
    private DefaultTableModel studentsModel;
    private DefaultTableModel enrollmentsModel;

    public LMSFrame() {
        this.courseService = new CourseService();
        initializeUI();
        loadSampleData();
        refreshAllTables();
    }

    private void initializeUI() {
        setTitle("Online Learning Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Courses", createCoursesPanel());
        tabbedPane.addTab("Students", createStudentsPanel());
        tabbedPane.addTab("Enrollments", createEnrollmentsPanel());
        
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this, 
            "Online Learning Management System v1.0\n\nManage courses and student enrollments easily."));
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        return menuBar;
    }

    private JPanel createCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel with buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        JButton addCourseBtn = new JButton("Add Course");
        addCourseBtn.addActionListener(e -> showAddCourseDialog());
        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshCoursesTable());
        
        topPanel.add(addCourseBtn);
        topPanel.add(refreshBtn);
        
        // Table
        String[] columns = {"Course ID", "Course Name", "Instructor", "Max Capacity", "Enrolled"};
        coursesModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        coursesTable = new JTable(coursesModel);
        coursesTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(coursesTable);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createStudentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel with buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        JButton addStudentBtn = new JButton("Register Student");
        addStudentBtn.addActionListener(e -> showRegisterStudentDialog());
        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshStudentsTable());
        
        topPanel.add(addStudentBtn);
        topPanel.add(refreshBtn);
        
        // Table
        String[] columns = {"Student ID", "First Name", "Last Name", "Email", "Enrolled Courses"};
        studentsModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        studentsTable = new JTable(studentsModel);
        studentsTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createEnrollmentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel with buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        JButton enrollBtn = new JButton("Enroll Student");
        enrollBtn.addActionListener(e -> showEnrollStudentDialog());
        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshEnrollmentsTable());
        
        topPanel.add(enrollBtn);
        topPanel.add(refreshBtn);
        
        // Table
        String[] columns = {"Student ID", "Student Name", "Course ID", "Course Name", "Enrolled"};
        enrollmentsModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        enrollmentsTable = new JTable(enrollmentsModel);
        enrollmentsTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(enrollmentsTable);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private void showAddCourseDialog() {
        JDialog dialog = new JDialog(this, "Add New Course", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel idLabel = new JLabel("Course ID:");
        JTextField idField = new JTextField();
        
        JLabel nameLabel = new JLabel("Course Name:");
        JTextField nameField = new JTextField();
        
        JLabel instructorLabel = new JLabel("Instructor:");
        JTextField instructorField = new JTextField();
        
        JLabel capacityLabel = new JLabel("Max Capacity:");
        JTextField capacityField = new JTextField();
        
        JButton addBtn = new JButton("Add Course");
        addBtn.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String instructor = instructorField.getText().trim();
                int capacity = Integer.parseInt(capacityField.getText().trim());
                
                if (id.isEmpty() || name.isEmpty() || instructor.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "All fields are required!");
                    return;
                }
                
                courseService.addCourse(new Course(id, name, instructor, capacity));
                JOptionPane.showMessageDialog(dialog, "Course added successfully!");
                refreshCoursesTable();
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Capacity must be a number!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
            }
        });
        
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(instructorLabel);
        panel.add(instructorField);
        panel.add(capacityLabel);
        panel.add(capacityField);
        panel.add(addBtn);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showRegisterStudentDialog() {
        JDialog dialog = new JDialog(this, "Register New Student", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();
        
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        
        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String email = emailField.getText().trim();
                
                if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "All fields are required!");
                    return;
                }
                
                courseService.registerStudent(new Student(id, firstName, lastName, email));
                JOptionPane.showMessageDialog(dialog, "Student registered successfully!");
                refreshStudentsTable();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
            }
        });
        
        panel.add(idLabel);
        panel.add(idField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(registerBtn);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showEnrollStudentDialog() {
        JDialog dialog = new JDialog(this, "Enroll Student in Course", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel studentLabel = new JLabel("Student ID:");
        JTextField studentField = new JTextField();
        
        JLabel courseLabel = new JLabel("Course ID:");
        JTextField courseField = new JTextField();
        
        JButton enrollBtn = new JButton("Enroll");
        enrollBtn.addActionListener(e -> {
            try {
                String studentId = studentField.getText().trim();
                String courseId = courseField.getText().trim();
                
                if (studentId.isEmpty() || courseId.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "All fields are required!");
                    return;
                }
                
                courseService.enrollStudentInCourse(studentId, courseId);
                JOptionPane.showMessageDialog(dialog, "Student enrolled successfully!");
                refreshEnrollmentsTable();
                refreshCoursesTable();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
            }
        });
        
        panel.add(studentLabel);
        panel.add(studentField);
        panel.add(courseLabel);
        panel.add(courseField);
        panel.add(enrollBtn);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void refreshCoursesTable() {
        coursesModel.setRowCount(0);
        List<Course> courses = courseService.getAllCourses();
        for (Course course : courses) {
            coursesModel.addRow(new Object[]{
                course.getCourseId(),
                course.getCourseName(),
                course.getInstructor(),
                course.getMaxCapacity(),
                course.getEnrolledCount()
            });
        }
    }

    private void refreshStudentsTable() {
        studentsModel.setRowCount(0);
        List<Student> students = courseService.getAllStudents();
        for (Student student : students) {
            studentsModel.addRow(new Object[]{
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getEnrollmentCount()
            });
        }
    }

    private void refreshEnrollmentsTable() {
        enrollmentsModel.setRowCount(0);
        List<Student> students = courseService.getAllStudents();
        for (Student student : students) {
            for (String courseId : student.getEnrolledCourseIds()) {
                courseService.getCourseById(courseId).ifPresent(course -> {
                    enrollmentsModel.addRow(new Object[]{
                        student.getStudentId(),
                        student.getFirstName() + " " + student.getLastName(),
                        course.getCourseId(),
                        course.getCourseName(),
                        "Enrolled"
                    });
                });
            }
        }
    }

    private void refreshAllTables() {
        refreshCoursesTable();
        refreshStudentsTable();
        refreshEnrollmentsTable();
    }

    private void loadSampleData() {
        courseService.addCourse(new Course("CS101", "Java Programming", "Dr. Smith", 30));
        courseService.addCourse(new Course("CS102", "Python Programming", "Dr. Jones", 25));
        courseService.addCourse(new Course("CS103", "Data Structures", "Prof. Brown", 20));
        
        courseService.registerStudent(new Student("STU001", "Pratik", "Shinde", "pratik@univ.edu"));
        courseService.registerStudent(new Student("STU002", "Vaishnav", "Kedar", "vaishnav@univ.edu"));
        courseService.registerStudent(new Student("STU003", "Manodnya", "Medhe", "manodnya@univ.edu"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LMSFrame frame = new LMSFrame();
            frame.setVisible(true);
        });
    }
}
