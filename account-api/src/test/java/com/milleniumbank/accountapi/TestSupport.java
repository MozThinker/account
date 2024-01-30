package com.milleniumbank.accountapi;

import com.milleniumbank.accountapi.dto.CreateAccountRequestDto;
import com.milleniumbank.accountapi.model.Customer;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

public class TestSupport {

    public static final String CUSTOMER_API_ENDPOINT = "/v1/customer/";
    public static final String ACCOUNT_API_ENDPOINT = "/v1/account/";

    public final Long CUSTOMER_ID = 1L;
    public final String CUSTOMER_NAME = "customer-name";
    public final String CUSTOMER_SURNAME = "customer-surname";
    public final String CUSTOMER_EMAIL = "customer-email";
    public final String CUSTOMER_PHONE = "0031612345678";
    public final Long ACCOUNT_ID = 1L;

    public Instant getCurrentInstant() {
        String instantExpected = "2021-06-15T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), Clock.systemDefaultZone().getZone());

        return Instant.now(clock);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
    }

    public Customer generateCustomer() {
        return new Customer("customer-name", "customer-surname", "customer-email","0031612345678");
    }

    public CreateAccountRequestDto generateCreateAccountRequest() {
        return generateCreateAccountRequest(null,100);
    }
    public CreateAccountRequestDto generateCreateAccountRequest(int initialCredit) {
        return generateCreateAccountRequest(CUSTOMER_ID, initialCredit);
    }


    public CreateAccountRequestDto generateCreateAccountRequest(Long customerId, int initialCredit) {
        return new CreateAccountRequestDto(customerId, new BigDecimal(initialCredit));
    }
}
