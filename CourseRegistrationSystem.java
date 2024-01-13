import java.util.*;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<String> registeredStudents;

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

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public void registerStudent(String studentID) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentID);
            System.out.println("Registration successful for course " + courseCode);
        } else {
            System.out.println("Course " + courseCode + " is full. Cannot register.");
        }
    }

    public void removeStudent(String studentID) {
        if (registeredStudents.contains(studentID)) {
            registeredStudents.remove(studentID);
            System.out.println("Successfully dropped course " + courseCode);
        } else {
            System.out.println("Student " + studentID + " is not registered for course " + courseCode);
        }
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Schedule: " + schedule);
        System.out.println("Registered Students: " + registeredStudents.size() + "/" + capacity);
        System.out.println();
    }
}

class Student {
    private String studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
        System.out.println("Course " + courseCode + " registered successfully for student " + studentID);
    }

    public void dropCourse(String courseCode) {
        if (registeredCourses.contains(courseCode)) {
            registeredCourses.remove(courseCode);
            System.out.println("Successfully dropped course " + courseCode + " for student " + studentID);
        } else {
            System.out.println("Student " + studentID + " is not registered for course " + courseCode);
        }
    }

    public void displayStudentDetails() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Registered Courses: " + registeredCourses.size());
        System.out.println();
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        // Sample courses
        courses.add(new Course("CSE101", "Introduction to Computer Science", "Fundamentals of programming", 30, "MWF 10:00 AM - 11:00 AM"));
        courses.add(new Course("MAT201", "Calculus I", "Limits, derivatives, integrals", 25, "TTH 1:00 PM - 2:30 PM"));
        courses.add(new Course("ENG301", "Advanced Writing", "Academic and professional writing skills", 20, "MWF 2:00 PM - 3:00 PM"));

        // Sample students
        students.add(new Student("S001", "John Doe"));
        students.add(new Student("S002", "Jane Smith"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Course Registration System");
            System.out.println("1. Display Course Listings");
            System.out.println("2. Display Student Information");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCourseList(courses);
                    break;
                case 2:
                    displayStudentInformation(students);
                    break;
                case 3:
                    registerForCourse(students, courses, scanner);
                    break;
                case 4:
                    dropCourse(students, courses, scanner);
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayCourseList(List<Course> courses) {
        System.out.println("Course Listings:");
        for (Course course : courses) {
            course.displayCourseDetails();
        }
    }

    private static void displayStudentInformation(List<Student> students) {
        System.out.println("Student Information:");
        for (Student student : students) {
            student.displayStudentDetails();
        }
    }

    private static void registerForCourse(List<Student> students, List<Course> courses, Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.next();

        Student student = findStudent(students, studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code to register: ");
        String courseCode = scanner.next();

        Course course = findCourse(courses, courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.registerCourse(courseCode);
        course.registerStudent(studentID);
    }

    private static void dropCourse(List<Student> students, List<Course> courses, Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.next();

        Student student = findStudent(students, studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.next();

        Course course = findCourse(courses, courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.dropCourse(courseCode);
        course.removeStudent(studentID);
    }

    private static Student findStudent(List<Student> students, String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourse(List<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
