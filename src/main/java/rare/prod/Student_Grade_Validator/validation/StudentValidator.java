package rare.prod.Student_Grade_Validator.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import rare.prod.Student_Grade_Validator.entity.Student;

import java.util.Set;

public class StudentValidator {

    private Validator validator;

    public StudentValidator() {
        validator = Validation.byDefaultProvider().configure().buildValidatorFactory().getValidator();
    }

    public Validator getValidator() {
        return this.validator;
    }

    public static void validateAndPrint(Validator validator, Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        if (violations.isEmpty()) {
            System.out.println("✅ Student is VALID: " + student);
        } else {
            System.out.println("❌ Student has " + violations.size() + " validation error(s):");
            for (ConstraintViolation<Student> violation : violations) {
                System.out.println("   - " + violation.getPropertyPath() + ": " + violation.getMessage());
            }
            System.out.println("   Student: " + student);
        }
    }
}
