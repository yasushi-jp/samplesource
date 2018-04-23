package knowledgebank.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int min;
    @Override
    public void initialize(Password constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //NULLの場合はチェックしない
        if (value == null) return true;

        //指定文字数以下はNG
        if (value.length() < min) return false;
        
        //英数記号をすべて含まなければNG
        if (!value.matches("^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*).*"
                + "[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]+.*$")) return false;

        return true;
    }
    
}
