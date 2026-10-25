package com.lms;

public class LMSApplication {
    private final CourseService courseService = new CourseService();

    public void initializeSampleData() {
        courseService.addCourse(new Course("CS101", "Java Programming", "Dr. Smith", 30));
        courseService.addCourse(new Course("CS102", "Python Programming", "Dr. Jones", 25));
        courseService.addCourse(new Course("CS103", "Data Structures", "Prof. Brown", 20));
        courseService.registerStudent(new Student("STU001", "Alice", "Johnson", "alice@univ.edu"));
        courseService.registerStudent(new Student("STU002", "Bob", "Smith", "bob@univ.edu"));
        courseService.registerStudent(new Student("STU003", "Charlie", "Brown", "charlie@univ.edu"));
    }

    public static void main(String[] args) {
        System.out.println("\n======== ONLINE LEARNING MANAGEMENT SYSTEM ========\n");

        LMSApplication lms = new LMSApplication();
        lms.initializeSampleData();
        System.out.println("[INFO] System initialized with 3 courses and 3 students.\n");

        System.out.println("[ACTION] Displaying all courses:");
        lms.courseService.displayAllCourses();

        System.out.println("[ACTION] Getting details for course CS101:");
        lms.courseService.getCourseById("CS101").ifPresent(c -> System.out.println("  " + c + "\n"));

        System.out.println("[ACTION] Enrolling student STU001 in CS101:");
        try {
            lms.courseService.enrollStudentInCourse("STU001", "CS101");
            System.out.println("  SUCCESS: Student enrolled!\n");
        } catch (Exception e) {
            System.out.println("  ERROR: " + e.getMessage() + "\n");
        }

        System.out.println("[ACTION] Attempting to enroll in non-existent course CS999:");
        try {
            lms.courseService.enrollStudentInCourse("STU002", "CS999");
        } catch (Exception e) {
            System.out.println("  ERROR: " + e.getMessage() + "\n");
        }

        System.out.println("[ACTION] Final course listing:");
        lms.courseService.displayAllCourses();
        System.out.println("===================================================\n");
    }
}
