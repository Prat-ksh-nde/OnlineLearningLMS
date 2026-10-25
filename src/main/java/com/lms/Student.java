package com.lms;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student {
    private final String studentId, firstName, lastName, email;
    private final Set<String> enrolledCourseIds;

    public Student(String studentId, String firstName, String lastName, String email) {
        this.studentId = Objects.requireNonNull(studentId);
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
        this.enrolledCourseIds = new HashSet<>();
    }

    public String getStudentId() { return studentId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public Set<String> getEnrolledCourseIds() { return new HashSet<>(enrolledCourseIds); }

    public boolean enrollInCourse(String courseId) { return enrolledCourseIds.add(courseId); }
    public boolean isEnrolledInCourse(String courseId) { return enrolledCourseIds.contains(courseId); }
    public int getEnrollmentCount() { return enrolledCourseIds.size(); }

    @Override
    public String toString() { return "Student{studentId='" + studentId + "', name=" + firstName + " " + lastName + '}'; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() { return studentId.hashCode(); }
}
