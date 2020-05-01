package mbdlabs.presentation.validator;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.Test;

import java.time.LocalDate;

import static io.vavr.API.println;

public class ValidationTest {


    /**
     * One way of validating a Customer object.
     * If invalid there is no way to tell what is invalid
     */
    @Test
    public void test_static_validator() {
        Customer customer = new Customer(
                "Fred",
                "fredATinvisiableman.com",
                LocalDate.of(1946, 9, 5)
        );

        boolean valid = CustomerValidator.isValid(customer);
        println(valid);
    }

    /**
     * Validate using combinator pattern.
     * Can see what broke validation.
     * Short circuits so you will only know the first thing that is invalid.
     */
    @Test
    public void test_validation_with_combinator_pattern() {
        Customer customer = new Customer(
                "Fred",
                "fredATinvisiableman.com",
                LocalDate.of(1946, 9, 5)
        );

        CustomerValidatorCombinator.ValidationResult result = CustomerValidatorCombinator.isNameValid()
                .and(CustomerValidatorCombinator.isEmailValid())
                .and(CustomerValidatorCombinator.isAdult())
                .apply(customer);

        println(result);
    }


    /**
     * Validate using Vavr Validation.
     * Does not short circuit so you can see all the fields that are invalid.
     */
    @Test
    public void test_validation_with_vavr() {
        Validation<Seq<String>, Customer> customers = CustomerValidatorVavr.validateCustomer(
                "Fred",
                "fredATinvisiableman.com",
                LocalDate.of(1946, 9, 5));

        println(customers);

        customers = CustomerValidatorVavr.validateCustomer(
                "Fred",
                "fred@invisiableman.com",
                LocalDate.of(1946, 9, 5));
        println(customers);
    }
}