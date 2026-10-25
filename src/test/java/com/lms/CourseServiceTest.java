@DisplayName("Should handle complex multi-student, multi-course scenario")
void testComplexScenario() {
    // Add multiple courses
    courseService.addCourse(new Course("CS101", "Java", "Dr. Smith", 30));
    courseService.addCourse(new Course("CS102", "Python", "Dr. Jones", 25));
    courseService.addCourse(new Course("CS103", "C++", "Prof. Brown", 20));

    // Register multiple students
    courseService.registerStudent(new Student("STU001", "Pratik", "Shinde", "pratik@university.edu"));
    courseService.registerStudent(new Student("STU002", "Vaishnav", "kedar" +
            "", "vaishnav@university.edu"));
    courseService.registerStudent(new Student("STU003", "Manodnya", "Medhe", "manodnya@university.edu"));

    // Complex enrollment pattern
    courseService.enrollStudentInCourse("STU001", "CS101");
    courseService.enrollStudentInCourse("STU001", "CS102");
    courseService.enrollStudentInCourse("STU002", "CS101");
    courseService.enrollStudentInCourse("STU002", "CS103");
    courseService.enrollStudentInCourse("STU003", "CS102");

    // Verify state
    assertEquals(3, courseService.getTotalCourses());
    assertEquals(3, courseService.getTotalStudents());
    assertEquals(2, courseService.getCourseById("CS101").get().getEnrolledCount());
    assertEquals(2, courseService.getCourseById("CS102").get().getEnrolledCount());
    assertEquals(1, courseService.getCourseById("CS103").get().getEnrolledCount());
}
    }
            }
