package mbdlabs.presentation.validator;

import java.time.LocalDate;
import java.time.Period;


/**
 * One way to validate customer object...
 * It's not clear to see what fields are being validated and if a field is invalid, for what reason?
 * What if you only want to validate one field and not all of them?
 */
public class CustomerValidator {

    private static boolean isNameValid(String name) {
        return name != null && !name.isEmpty();
    }

    private static boolean isEmailValid(String email) {
        return email != null && !email.isEmpty() && email.contains("@");
    }

    private static boolean isAdult(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears() >= 16;
    }

    public static boolean isValid(Customer customer) {
        return isNameValid(customer.getName()) &&
                isEmailValid(customer.getEmail()) &&
                isAdult(customer.getDob());

    }

}
