package net.helloworld.validator;

import net.helloworld.model.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.                 l
 * User: ayeshaf
 * Date: 23/11/13
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class StudentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Student student = (Student) target;

        Date dob = student.getDateOfBirth();

        ValidationUtils.rejectIfEmpty(errors, "firstName", "student.mandatory.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "student.mandatory.empty");

        if (dob == null) {
            errors.rejectValue("dateOfBirth", "student.dob.empty");
        }

    }
}
