package mbdlabs.presentation.validator;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import java.time.LocalDate;
import java.time.Period;

public class CustomerValidatorVavr {

    public static Validation<Seq<String>, Customer> validateCustomer(String name, String email, LocalDate dob) {
        return Validation.combine(
                validateName(name),
                validateEmail(email),
                validateDob(dob)).ap(Customer::new);
    }

    private static Validation<String, String> validateName(String name) {
        return name != null && !name.isEmpty()
                ? Validation.valid(name)
                : Validation.invalid(String.format("Name invalid: %s", name));
    }

    private static Validation<String, String> validateEmail(String email) {
        return email != null && !email.isEmpty() && email.contains("@")
                ? Validation.valid(email)
                : Validation.invalid(String.format("Email invalid: %s", email));
    }

    private static Validation<String, LocalDate> validateDob(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears() >= 16
                ? Validation.valid(dob)
                : Validation.invalid(String.format("dob invalid: %s", dob));
    }
}
