import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Course class
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean isFull() {
        return registeredStudents.size() >= capacity;
    }

    public void addStudent(Student student) {
        if (!isFull()) {
            registeredStudents.add(student);
        }
    }

    public void removeStudent(Student student) {
        registeredStudents.remove(student);
    }

    public int availableSlots() {
        return capacity - registeredStudents.size();
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode +
               "\nTitle: " + title +
               "\nDescription: " + description +
               "\nSchedule: " + schedule +
               "\nAvailable Slots: " + availableSlots() + "\n";
    }
}

// Student class
class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course) && !course.isFull()) {
            registeredCourses.add(course);
            course.addStudent(this);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Registered Courses: ").append(registeredCourses.isEmpty() ? "None" : registeredCourses).append("\n");
        return sb.toString();
    }
}

// CourseRegistrationSystem class with password feature
class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;
    private final String ADMIN_PASSWORD = "codesoft123"; // Set a default admin password

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void listAvailableCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);
        if (student != null && course != null && !course.isFull()) {
            student.registerCourse(course);
            System.out.println(student.getName() + " successfully registered for " + course.getTitle());
        } else {
            System.out.println("Registration failed. Either student or course not found, or course is full.");
        }
    }

    public void removeStudentFromCourse(String studentId, String courseCode) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);
        if (student != null && course != null) {
            student.dropCourse(course);
            System.out.println(student.getName() + " successfully dropped " + course.getTitle());
        } else {
            System.out.println("Drop failed. Either student or course not found.");
        }
    }

    public void showStudentDetails(String studentId) {
        Student student = findStudentById(studentId);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    public void addNewStudent(String studentId, String name, List<String> courseCodes) {
        if (findStudentById(studentId) == null) {
            Student newStudent = new Student(studentId, name);
            addStudent(newStudent);

            // Register the student for the specified courses
            for (String code : courseCodes) {
                Course course = findCourseByCode(code);
                if (course != null && !course.isFull()) {
                    newStudent.registerCourse(course);
                } else {
                    System.out.println("Course with code " + code + " is either not found or full.");
                }
            }
            System.out.println("Student " + name + " added successfully with courses.");
        } else {
            System.out.println("Student ID already exists.");
        }
    }

    public void showAllStudentDetails(String password) {
        if (ADMIN_PASSWORD.equals(password)) {
            for (Student student : students) {
                System.out.println(student);
            }
        } else {
            System.out.println("Incorrect password.");
        }
    }
}

// Main class with updated user interface
public class Task5{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Sample data
        system.addCourse(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 2, "Mon-Wed-Fri 10-11 AM"));
        system.addCourse(new Course("CS102", "Data Structures", "Study of data structures", 2, "Tue-Thu 2-3 PM"));
        system.addStudent(new Student("S001", "Alice"));
        system.addStudent(new Student("S002", "Bob"));

        while (true) {
            System.out.println("Course Registration System");
            System.out.println("1. List available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. Show student details");
            System.out.println("5. Add new student");
            System.out.println("6. Show all student details (admin)");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.listAvailableCourses();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    system.registerStudentForCourse(studentId, courseCode);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    courseCode = scanner.nextLine();
                    system.removeStudentFromCourse(studentId, courseCode);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    system.showStudentDetails(studentId);
                    break;
                case 5:
                    System.out.print("Enter new student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter new student name: ");
                    String name = scanner.nextLine();

                    // Enter course codes for the new student
                    List<String> courseCodes = new ArrayList<>();
                    System.out.println("Enter course codes (type 'done' when finished):");
                    while (true) {
                        String code = scanner.nextLine();
                        if (code.equalsIgnoreCase("done")) {
                            break;
                        }
                        courseCodes.add(code);
                    }

                    system.addNewStudent(studentId, name, courseCodes);
                    break;
                case 6:
                    System.out.print("Enter admin password: ");
                    String password = scanner.nextLine();
                    system.showAllStudentDetails(password);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
