package com.lms;

public class Course {
    private final String courseId;
    private final String courseName;
    private final String instructor;
    private final int maxCapacity;
    private final int enrolledCount;

    public Course(String courseId, String courseName, String instructor, int maxCapacity, int enrolledCount) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
        this.enrolledCount = enrolledCount;
    }

    public Course(String courseId, String courseName, String instructor, int maxCapacity) {
        this(courseId, courseName, instructor, maxCapacity, 0);
    }

    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getInstructor() { return instructor; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getEnrolledCount() { return enrolledCount; }

    public boolean hasAvailableSeats() { return enrolledCount < maxCapacity; }
    public int getAvailableSeats() { return Math.max(0, maxCapacity - enrolledCount); }

    public Course enrollStudent() {
        if (!hasAvailableSeats()) throw new IllegalStateException("No available seats in course: " + courseName);
        return new Course(courseId, courseName, instructor, maxCapacity, enrolledCount + 1);
    }

    @Override
    public String toString() {
        return "Course{courseId='" + courseId + "', courseName='" + courseName + "', instructor='" + instructor + "', maxCapacity=" + maxCapacity + ", enrolledCount=" + enrolledCount + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return courseId.equals(course.courseId);
    }

    @Override
    public int hashCode() { return courseId.hashCode(); }
}
