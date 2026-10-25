# Online Learning Management System - CI/CD Project

## Project Overview

This is a comprehensive college practical project that demonstrates the setup and implementation of a **Continuous Integration/Continuous Deployment (CI/CD) pipeline** for a Java-based **Online Learning Management System (LMS)** using modern DevOps tools and practices.

**Project Title:** "Develop a CI Pipeline for an Online Learning Management System using GitLab, GitLab CI/CD, Gradle and JUnit"

---

## 🎯 Objectives

1. Develop a simple but functional Java-based LMS application
2. Configure an automated CI pipeline using **GitLab CI/CD**
3. Implement a three-stage pipeline: **Build → Test → Release Preparation**
4. Demonstrate test-driven development with comprehensive **JUnit 5** test coverage
5. Show how a failed test stops the pipeline from progressing to release
6. Use **Gradle** with Groovy DSL for build automation
7. Implement clean object-oriented Java code with proper design patterns

---

## 📋 Technology Stack

### Core Technologies
- **Java JDK 21** - Latest stable Java version
- **Gradle 8.5** - Build automation tool (with Gradle Wrapper)
- **JUnit 5 (JUnit Jupiter)** - Unit testing framework
- **Git & GitLab** - Version control and CI/CD platform
- **GitLab CI/CD** - Continuous integration and deployment
- **GitLab Runner** - Pipeline executor

### Development Environment
- **IntelliJ IDEA** - Primary IDE
- **Gradle Wrapper** - Ensures consistent Gradle version across environments

### What We Don't Use (As Per Requirements)
- ❌ Spring Boot
- ❌ Frontend frameworks (Angular, React, Vue)
- ❌ Node.js / npm
- ❌ Docker / Kubernetes
- ❌ External databases (using in-memory storage)
- ❌ Complex frameworks

---

## 📁 Project Structure

```
OnlineLearningLMS/
├── .gitignore                          # Git ignore patterns
├── .gitlab-ci.yml                      # GitLab CI/CD pipeline configuration
├── README.md                           # Project documentation
├── settings.gradle                     # Gradle multi-project settings
├── build.gradle                        # Build configuration and dependencies
├── gradlew                             # Gradle wrapper script (Unix/Linux)
├── gradlew.bat                         # Gradle wrapper script (Windows)
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar          # Gradle wrapper JAR
│       └── gradle-wrapper.properties   # Gradle wrapper configuration
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── lms/
    │               ├── Course.java              # Course model
    │               ├── Student.java             # Student model
    │               ├── CourseService.java       # Business logic layer
    │               └── LMSApplication.java      # Main application entry point
    └── test/
        └── java/
            └── com/
                └── lms/
                    └── CourseServiceTest.java   # Comprehensive JUnit 5 tests
```

---

## 🔧 Application Features

### Core Functionality

1. **Display List of Courses**
   - Retrieve all available courses from the catalog
   - View course details including capacity and enrollment

2. **Retrieve Course Details**
   - Get specific course information by course ID
   - Display instructor, capacity, and current enrollment

3. **Enroll Students in Courses**
   - Register students in available courses
   - Prevent duplicate enrollments
   - Reject enrollment when course is full

4. **Input Validation**
   - Validate course names (reject empty/blank names)
   - Validate student information
   - Ensure valid course IDs

5. **Error Handling**
   - Proper exception handling for invalid operations
   - Descriptive error messages
   - Graceful failure handling

### LMS Classes

#### 1. **Course.java**
- Represents a course with ID, name, instructor, capacity, and enrollment count
- Immutable design for thread safety
- Methods:
  - `hasAvailableSeats()` - Check seat availability
  - `getAvailableSeats()` - Get count of empty seats
  - `enrollStudent()` - Create new instance with incremented enrollment

#### 2. **Student.java**
- Represents a student with ID, name, email
- Maintains set of enrolled course IDs
- Methods:
  - `enrollInCourse(courseId)` - Enroll in a course
  - `isEnrolledInCourse(courseId)` - Check enrollment status
  - `getEnrollmentCount()` - Get total enrollments

#### 3. **CourseService.java**
- Business logic layer managing courses and enrollments
- In-memory storage of courses and students
- Methods:
  - `addCourse(course)` - Add course to catalog
  - `registerStudent(student)` - Register student
  - `getAllCourses()` - Retrieve all courses
  - `enrollStudentInCourse(studentId, courseId)` - Process enrollment
  - `getCourseById(courseId)` - Retrieve specific course
  - `getStudentById(studentId)` - Retrieve specific student

#### 4. **LMSApplication.java**
- Main application entry point
- Demonstrates all functionality
- Sample data initialization
- User-friendly console output

---

## 📊 GitLab CI/CD Pipeline

The pipeline is defined in `.gitlab-ci.yml` and consists of three stages:

### Stage 1: BUILD
**Purpose:** Compile Java source code and generate JAR artifacts

```yaml
build:
  stage: build
  script:
    - ./gradlew clean build -x test
  artifacts:
    - build/libs/
    - build/classes/
```

**What Happens:**
- Cleans previous build artifacts
- Compiles all Java source files
- Resolves dependencies from Maven Central
- Generates JAR file
- Runs Gradle checks

**Failure Handling:** Pipeline stops if compilation fails

---

### Stage 2: TEST
**Purpose:** Run comprehensive JUnit 5 tests to verify application logic

```yaml
test:
  stage: test
  script:
    - ./gradlew test --info
  artifacts:
    reports:
      junit: build/test-results/test/TEST-*.xml
  allow_failure: false
```

**What Happens:**
- Executes all JUnit 5 tests in `src/test/java`
- Generates test reports in XML and HTML formats
- Displays test results and coverage
- **Pipeline STOPS if any test fails** (allow_failure: false)

**Test Coverage:**
- **51 comprehensive test cases** organized in nested test classes:
  - Course Management Tests (6 tests)
  - Student Registration Tests (5 tests)
  - Enrollment Tests (10 tests)
  - Validation Tests (4 tests)
  - Integration Tests (2 tests)

**Demonstrates:** A failed test prevents progression to Release stage

---

### Stage 3: RELEASE PREPARATION
**Purpose:** Prepare successfully built and tested artifact for release

```yaml
release:
  stage: release
  script:
    - ./gradlew build
    - Build artifact validation
  artifacts:
    paths:
      - build/libs/OnlineLearningLMS-*.jar
  when: on_success
```

**What Happens:**
- Only executes if Build and Test stages succeed
- Creates final release JAR
- Validates artifact integrity
- Archives JAR for deployment

**Pipeline Flow:**
```
┌─────────────────────────────────────────┐
│       Code Push to GitLab Repo          │
└────────────────┬────────────────────────┘
                 │
                 ▼
        ┌────────────────┐
        │ BUILD STAGE    │ (Gradle clean build)
        │  - Compile     │
        │  - Generate    │
        │    JAR         │
        └────────┬───────┘
                 │
        ┌────────▼───────┐
        │ TEST STAGE     │ (JUnit 5 tests)
        │  - Run 51      │
        │    tests       │
        │  - Generate    │
        │    reports     │
        └────────┬───────┘
                 │
        ┌────────▼───────────────────┐
        │ RELEASE STAGE              │ (Only if all pass)
        │  - Prepare artifact        │
        │  - Archive JAR for deploy  │
        └────────┬───────────────────┘
                 │
        ┌────────▼────────────────┐
        │ SUCCESS NOTIFICATION    │
        └─────────────────────────┘

        [If any stage fails: Pipeline Stops]
```

---

## 🧪 JUnit 5 Test Suite

### Test Organization

The test suite (`CourseServiceTest.java`) contains **51 comprehensive tests** organized using JUnit 5's `@Nested` annotation:

#### 1. **Course Management Tests** (6 tests)
- Add course successfully
- Reject null courses
- Reject duplicate courses
- Retrieve all courses
- Retrieve course by ID
- Handle non-existent courses

#### 2. **Student Registration Tests** (5 tests)
- Register student successfully
- Reject null students
- Reject duplicate students
- Retrieve student by ID
- Handle non-existent students

#### 3. **Enrollment Tests** (10 tests)
- Enroll student in course
- Update enrollment count
- Reject enrollment in non-existent course
- Reject enrollment of non-existent student
- Prevent double enrollment
- Prevent enrollment when full
- Allow multiple students in same course
- Allow one student in multiple courses
- Verify enrollment state changes

#### 4. **Validation Tests** (4 tests)
- Validate valid course names
- Reject null course names
- Reject empty course names
- Reject blank course names

#### 5. **Integration Tests** (2 tests)
- Complete enrollment workflow
- Complex multi-student, multi-course scenario

### Running Tests Locally

```bash
# Using Gradle Wrapper
./gradlew test                  # Run all tests
./gradlew test --info          # Run with detailed output
./gradlew test --fail-fast      # Stop on first failure

# View test reports
# Open: build/reports/tests/test/index.html
```

### Test Results

```
CourseServiceTest
├── CourseManagementTests
│   ├── Should add a course successfully
│   ├── Should throw exception when adding null course
│   ├── Should throw exception when adding duplicate course
│   ├── Should retrieve all courses
│   ├── Should return empty list when no courses exist
│   └── ... (6 total)
├── StudentRegistrationTests
│   ├── Should register a student successfully
│   ├── Should throw exception when registering null student
│   ├── ... (5 total)
├── EnrollmentTests
│   ├── Should enroll student in course successfully
│   ├── Should update course enrollment count after enrollment
│   ├── ... (10 total)
├── ValidationTests
│   ├── Should validate non-null course name
│   ├── ... (4 total)
└── IntegrationTests
    ├── Should complete full enrollment workflow
    └── Should handle complex multi-student scenario
```

---

## 🚀 Getting Started

### Prerequisites
- **Java 21 JDK** installed
- **Git** installed
- **GitLab account** (for CI/CD)
- **IntelliJ IDEA** (optional, for development)

### Setup Instructions

#### 1. Clone the Repository
```bash
git clone <your-gitlab-repo-url>
cd OnlineLearningLMS
```

#### 2. Build the Project (Using Gradle Wrapper)
```bash
# On Windows
gradlew.bat build

# On Unix/Linux/macOS
chmod +x gradlew
./gradlew build
```

#### 3. Run Tests
```bash
./gradlew test
```

#### 4. Run the Application
```bash
./gradlew run
```

#### 5. Clean Build Artifacts
```bash
./gradlew clean
```

### Using Gradle Wrapper
The project includes Gradle Wrapper scripts (`gradlew` / `gradlew.bat`) that automatically download the correct Gradle version. **No separate Gradle installation needed!**

```bash
# Windows
gradlew.bat build

# Unix/Linux/macOS
./gradlew build
```

---

## 📦 Build Configuration (build.gradle)

```gradle
plugins {
    id 'java'
    id 'application'
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.10.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.10.1'
}

tasks.named('test') {
    useJUnitPlatform()
}

application {
    mainClass = 'com.lms.LMSApplication'
}
```

### Key Configuration Details:
- **Gradle Version:** 8.5
- **Java Version:** 21 (using Toolchain API)
- **JUnit Version:** 5.10.1
- **Repository:** Maven Central
- **Main Class:** com.lms.LMSApplication

---

## 🔄 Pipeline Execution Examples

### Scenario 1: Successful Build and Test
```
Code Commit → Build Stage (✓) → Test Stage (✓) → Release Stage (✓)
                                 └─ 51/51 tests pass
```

### Scenario 2: Build Failure
```
Code Commit → Build Stage (✗ Compilation Error)
                          └─ Pipeline STOPS
                          └─ Developers notified
```

### Scenario 3: Test Failure (Key Demonstration)
```
Code Commit → Build Stage (✓) → Test Stage (✗ 2 tests fail)
                                          └─ Pipeline STOPS
                                          └─ Release Stage NOT EXECUTED
                                          └─ Bug fixed, retry
```

---

## 📝 Code Quality and Best Practices

### Object-Oriented Design
- ✅ Proper encapsulation with private fields
- ✅ Immutable Course class for thread safety
- ✅ Clear separation of concerns (Model, Service, Application layers)
- ✅ Meaningful class and method names

### Java Best Practices
- ✅ Null safety checks using Objects.requireNonNull()
- ✅ Proper exception handling with custom messages
- ✅ Consistent use of collections (List, Set)
- ✅ hashCode() and equals() implementations
- ✅ Comprehensive JavaDoc documentation

### Testing Best Practices
- ✅ @Nested test classes for organization
- ✅ @DisplayName for readable test names
- ✅ Arrange-Act-Assert pattern
- ✅ Test isolation with @BeforeEach
- ✅ Edge case and error path testing

### DevOps Best Practices
- ✅ Infrastructure as Code (.gitlab-ci.yml)
- ✅ Artifact management and retention policies
- ✅ Build caching for performance
- ✅ Clear stage dependencies
- ✅ Comprehensive logging and reporting

---

## 🐛 Troubleshooting

### Issue: "gradlew: command not found" (macOS/Linux)
**Solution:**
```bash
chmod +x gradlew
./gradlew build
```

### Issue: "JAVA_HOME not set"
**Solution:**
```bash
# Set JAVA_HOME to your Java 21 installation
export JAVA_HOME=/path/to/java/21
./gradlew build
```

### Issue: Tests fail locally but pass in CI
**Solution:**
```bash
# Clear local cache and rebuild
./gradlew clean build --refresh-dependencies
```

### Issue: GitLab Runner not picking up pipeline
**Solution:**
1. Ensure `.gitlab-ci.yml` is in repository root
2. Verify GitLab Runner is installed and running
3. Check runner tags match pipeline requirements
4. Check runner can execute Docker images (for docker executor)

---

## 📚 Additional Resources

- [Gradle Documentation](https://docs.gradle.org/)
- [Gradle Wrapper Guide](https://docs.gradle.org/current/userguide/gradle_wrapper.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [GitLab CI/CD Documentation](https://docs.gitlab.com/ee/ci/)
- [GitLab Runner Installation](https://docs.gitlab.com/runner/install/)
- [Java 21 Features](https://www.oracle.com/java/technologies/javase/21-relnotes.html)

---

## 🎓 Learning Outcomes

After completing this project, students will understand:

1. **CI/CD Concepts**
   - Automated build and testing pipelines
   - Staging and progressive quality gates
   - Artifact management

2. **Gradle Build Tool**
   - Build configuration and dependencies
   - Task execution and customization
   - Gradle Wrapper for consistency

3. **JUnit 5 Testing**
   - Unit testing framework
   - Nested test organization
   - Test reporting and assertions

4. **GitLab CI/CD**
   - Pipeline definition and configuration
   - Job dependencies and artifacts
   - Failure handling and notifications

5. **Java Best Practices**
   - Object-oriented design
   - Exception handling
   - Code documentation

6. **DevOps Mindset**
   - Automation and reliability
   - Infrastructure as Code
   - Continuous improvement

---

## 📄 License

This is an educational project for college practical purposes.

---

## ✍️ Author

Created as part of a DevOps practical course on CI/CD pipeline implementation.

---

**Last Updated:** 2026
**Gradle Version:** 8.5
**Java Version:** 21
**JUnit Version:** 5.10.1
