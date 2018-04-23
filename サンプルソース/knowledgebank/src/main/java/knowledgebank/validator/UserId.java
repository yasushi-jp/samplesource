package knowledgebank.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Documented  //アノテーションの情報をJavadocに反映
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER }) //どの要素に定義可能か
@Retention(RUNTIME)  //アノテーションで付加された情報がどの段階まで保持されるか
@Constraint(validatedBy = {})
@Size(max = 255)
@Pattern(regexp = "^[0-9a-zA-Z_\\.\\-]*$")
public @interface UserId {

    String message() default "{knowledgebank.validator.UserId.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        UserId[] value();
    }
}
