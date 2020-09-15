package com.example.CarpathiansBlog.validators;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                //Rules:
                // Password length should be in between 8 and 16 characters
                new LengthRule(8, 16),
                // No whitespace allowed
                new WhitespaceRule(),
                // At least one Upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // At least one Lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                // At least one digit
                new CharacterRule(EnglishCharacterData.Digit, 1),
                // At least one special character
                new CharacterRule(EnglishCharacterData.Special, 1)
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}
