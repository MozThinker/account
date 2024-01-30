package com.milleniumbank.accountapi.controller;

import com.milleniumbank.accountapi.dto.CustomerDto;
import com.milleniumbank.accountapi.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    static final String BASE_URL = "/api/customer";

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "This endpoint is to retrieve details of a Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Return Customer details from DB",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Customer doesn't exist",
                    content = @Content)
    })
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> customerDetails(@PathVariable @Valid Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerDetailsById(customerId));
    }
}
