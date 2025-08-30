package rare.prod.Student_Grade_Validator;


import jakarta.validation.Validator;
import rare.prod.Student_Grade_Validator.entity.Student;
import rare.prod.Student_Grade_Validator.validation.StudentValidator;

public class MainApp {

    public static void main(String[] args) {
        Validator validator = new StudentValidator().getValidator();
        System.out.println("=== Student Grade Validator Demo ===\n");

        // Test 1: Valid student
        System.out.println("1. Testing VALID student:");
        Student validStudent = createValidStudent();
        StudentValidator.validateAndPrint(validator, validStudent);

        // Test 2: Invalid student ID
        System.out.println("\n2. Testing INVALID student ID:");
        Student invalidIdStudent = createValidStudent();
        invalidIdStudent.setStudentId("INVALID-ID");
        StudentValidator.validateAndPrint(validator, invalidIdStudent);

        // Test 3: Invalid GPA
        System.out.println("\n3. Testing INVALID GPA:");
        Student invalidGpaStudent = createValidStudent();
        invalidGpaStudent.setGpa(5.0); // Too high
        StudentValidator.validateAndPrint(validator, invalidGpaStudent);

        // Test 4: Multiple validation errors
        System.out.println("\n4. Testing MULTIPLE errors:");
        Student multiErrorStudent = new Student();
        multiErrorStudent.setStudentId("BAD");
        multiErrorStudent.setFirstname("");
        multiErrorStudent.setAge(15); // Too young
        multiErrorStudent.setEmail("not-an-email"); // Invalid format
        multiErrorStudent.setGpa(4.555); // Too many decimals
        StudentValidator.validateAndPrint(validator, multiErrorStudent);

        // Test 5: Custom business validation
        System.out.println("\n5. Testing business rules:");
        Student businessStudent = createValidStudent();
        businessStudent.addSubjectGrade("Math", 85.0);
        businessStudent.addSubjectGrade("Science", 92.0);
        businessStudent.addSubjectGrade("English", 78.0);

        System.out.println("Average grade: " + businessStudent.calculateAverageGrade());
        System.out.println("Is passing: " + businessStudent.isPassing());
        StudentValidator.validateAndPrint(validator, businessStudent);
    }

    private static Student createValidStudent() {
        Student student = new Student(
                "STU-2024-0001",
                "John",
                "Doe",
                20,
                "john.doe@student.edu",
                3.75
        );

        student.addSubjectGrade("Math", 88.0);
        student.addSubjectGrade("Science", 91.0);

        return student;
    }

}
