package rare.prod.Student_Grade_Validator.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.regex.Pattern;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {ValidStudent.ValidStudentValidator.class}
)
@Documented
public @interface ValidStudent {

    String message() default "Invalid student ID format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ValidStudentValidator implements ConstraintValidator<ValidStudent, String> {

        // Format: STU-YYYY-NNNN (e.g., STU-2024-0001)
        private static final Pattern STUDENT_ID_PATTERN = Pattern.compile("^STU-\\d{4}-\\d{4}$");

        @Override
        public boolean isValid(String studentId, ConstraintValidatorContext context) {
            if (studentId == null) {return false;} // checking if the value is null or not if null we return invalid

            if (!STUDENT_ID_PATTERN.matcher(studentId).matches()) {
                return false; // Pattern not match to a valid Student ID Pattern returning invalid
            }

            // Additional validation: year should be reasonable
            String yearPart = studentId.substring(4, 8);
            int year = Integer.parseInt(yearPart);
            int currentYear = java.time.Year.now().getValue();

            if (year < 2000 || year > currentYear + 1) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                                "Year in student ID should be between 2000 and " + (currentYear + 1))
                        .addConstraintViolation();
                return false;
            }

            return true;
        }
    }

}
