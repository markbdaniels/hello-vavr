package mbdlabs.presentation.validator;

import io.vavr.Function1;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Period;

import static mbdlabs.presentation.validator.CustomerValidatorCombinator.*;
import static mbdlabs.presentation.validator.CustomerValidatorCombinator.ValidationResult.*;

/**
 * Validator which uses combinator pattern
 */
public interface CustomerValidatorCombinator extends Function1<Customer, ValidationResult> {

    public enum ValidationResult {
        VALID,
        INVALID_NAME,
        INVALID_EMAIL,
        INVALID_AGE
    }


    static CustomerValidatorCombinator isNameValid() {
        return customer -> StringUtils.isNotEmpty(customer.getName())
                ? VALID : INVALID_NAME;
    }

    static CustomerValidatorCombinator isEmailValid() {
        return customer -> StringUtils.isNotEmpty(customer.getEmail())
                && customer.getEmail().contains("@")
                ? VALID : INVALID_EMAIL;
    }

    static CustomerValidatorCombinator isAdult() {
        return customer -> Period.between(customer.getDob(), LocalDate.now()).getYears() >= 16
                ? VALID : INVALID_AGE;
    }


    default CustomerValidatorCombinator and(CustomerValidatorCombinator other) {
        return customer -> {
            ValidationResult result = this.apply(customer);
            return result.equals(VALID) ? other.apply(customer) : result;
        };
    }





}
