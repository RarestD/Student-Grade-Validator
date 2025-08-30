package rare.prod.Student_Grade_Validator.entity;

import jakarta.validation.constraints.*;
import rare.prod.Student_Grade_Validator.validation.ValidGPA;
import rare.prod.Student_Grade_Validator.validation.ValidStudent;

import java.util.ArrayList;
import java.util.List;

public class Student {

    @ValidStudent()
    private String studentId;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name must be 2-30 characters")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "First name can only contain letters and spaces")
    private String firstname;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 30, message = "Last name must be 2-30 characters")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Last name can only contain letters and spaces")
    private String lastname;

    @NotNull(message = "Age is required")
    @Min(value = 16, message = "Student must be at least 16 years old")
    @Max(value = 100, message = "Age cannot exceed 100")
    private Integer age;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must valid")
    private String email;

    @ValidGPA(message = "GPA must be between 0.0 and 4.0")
    private Double gpa;

    @NotEmpty(message = "At least one subject is required")
    @Size(max = 10, message = "Cannot have more than 10 subjects")
    private List<String> subjects;

    @NotEmpty(message = "At least one grade is required")
    private List<
            @Min(value = 0, message = "Grade cannot be negative")
            @Max(value = 100, message = "Grade cannot exceed 100") Double> grades;

    public Student() {
        subjects = new ArrayList<>();
        grades = new ArrayList<>();
    }

    public Student(String studentId, String firstname, String lastname, Integer age, String email, Double gpa) {
        this();
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.gpa = gpa;
    }

    public void addSubjectGrade(String subject, Double grade) {
        subjects.add(subject);
        grades.add(grade);
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        return grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }

    public boolean isPassing(){
        return gpa != null && gpa > 2.0;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", gpa=" + gpa +
                ", subjects=" + subjects +
                ", grades=" + grades +
                '}';
    }
}
