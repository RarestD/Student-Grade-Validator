package rare.prod.Student_Grade_Validator.validation;


import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {ValidGPA.ValidGPAValidator.class}
)
@Documented
public @interface ValidGPA {

    double min() default 0.00;

    double max() default 4.00;

    String message() default "GPA must be between 0.0 and 4.0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class ValidGPAValidator implements ConstraintValidator<ValidGPA, Double> {

        private double min;
        private double max;

        @Override
        public void initialize(ValidGPA constraintAnnotation) {
            this.min = constraintAnnotation.min();
            this.max = constraintAnnotation.max();
        }

        @Override
        public boolean isValid(Double gpa, ConstraintValidatorContext context) {
            if (gpa == null) return false; // gpa is null returning invalid

            // not required the minimum and maximum amount of GPA returning invalid
            if (gpa < min || gpa > max) {
                return false;
            }

            String gpaStr = String.valueOf(gpa);
            int decimalIndex = gpaStr.indexOf('.');
            if (decimalIndex != -1 && gpaStr.length() - decimalIndex - 1 > 2) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                                "GPA can have at most 2 decimal places")
                        .addConstraintViolation();
                return false;
            }

            return true;
        }
    }

}
