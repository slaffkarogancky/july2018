package golden.horde.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Documented
@Constraint(validatedBy = ProhibitedValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Prohibited {

	String message() default "содержит запретное слово";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String value() default "Чупакабра";
}
