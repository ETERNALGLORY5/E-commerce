package huffle.puff.wand.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)

public @interface ImageNameValid {

	// error message
	String message() default "Invalid Image Name";

	//represent group of constraints
	Class<?>[] groups() default { };

	// additional info of annotation
	Class<? extends Payload>[] payload() default { };

}

