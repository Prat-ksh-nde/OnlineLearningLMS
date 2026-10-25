package com.lms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CourseService {
    private final List<Course> courseList = new ArrayList<>();
    private final List<Student> studentList = new ArrayList<>();

    public void addCourse(Course course) {
        Objects.requireNonNull(course);
        if (courseList.stream().anyMatch(c -> c.getCourseId().equals(course.getCourseId()))) {
            throw new IllegalArgumentException("Course " + course.getCourseId() + " already exists");
        }
        courseList.add(course);
    }

    public void registerStudent(Student student) {
        Objects.requireNonNull(student);
        if (studentList.stream().anyMatch(s -> s.getStudentId().equals(student.getStudentId()))) {
            throw new IllegalArgumentException("Student " + student.getStudentId() + " already exists");
        }
        studentList.add(student);
    }

    public List<Course> getAllCourses() { 
        return Collections.unmodifiableList(courseList); 
    }

    public List<Student> getAllStudents() { 
        return Collections.unmodifiableList(studentList); 
    }

    public Optional<Course> getCourseById(String courseId) {
        Objects.requireNonNull(courseId);
        return courseList.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst();
    }

    public Optional<Student> getStudentById(String studentId) {
        Objects.requireNonNull(studentId);
        return studentList.stream().filter(s -> s.getStudentId().equals(studentId)).findFirst();
    }

    public boolean enrollStudentInCourse(String studentId, String courseId) {
        Student student = getStudentById(studentId).orElseThrow(() -> new IllegalArgumentException("Student " + studentId + " not found"));
        Course course = getCourseById(courseId).orElseThrow(() -> new IllegalArgumentException("Course " + courseId + " not found"));

        if (student.isEnrolledInCourse(courseId)) {
            throw new IllegalStateException("Student already enrolled in " + courseId);
        }
        if (!course.hasAvailableSeats()) {
            throw new IllegalStateException("Course " + courseId + " is full");
        }

        Course updatedCourse = course.enrollStudent();
        courseList.set(courseList.indexOf(course), updatedCourse);
        student.enrollInCourse(courseId);
        return true;
    }

    public int getTotalCourses() { return courseList.size(); }
    public int getTotalStudents() { return studentList.size(); }
    public static boolean isValidCourseName(String courseName) { return courseName != null && !courseName.trim().isEmpty(); }

    public void displayAllCourses() {
        System.out.println("\n========== AVAILABLE COURSES ==========");
        courseList.forEach(System.out::println);
        System.out.println("=======================================\n");
    }
}
