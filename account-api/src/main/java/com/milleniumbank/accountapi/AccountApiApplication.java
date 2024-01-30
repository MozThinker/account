package com.milleniumbank.accountapi;

import com.milleniumbank.accountapi.model.Customer;
import com.milleniumbank.accountapi.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@EnableCaching
@SpringBootApplication
@OpenAPIDefinition
public class AccountApiApplication implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(AccountApiApplication.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = customerRepository.save(new Customer("Edson", "Mutombene", "edson.mutombene@gmail.com","03232521"));
        Customer customer2 = customerRepository.save(new Customer("Vania", "Mutombene", "vania.mutombene@gmail.com","03232522"));
        Customer customer3 = customerRepository.save(new Customer("Tulipa", "Mutombene", "tulipa.mutombene@gmail.com","03232523"));
        System.out.println(customer);
        System.out.println(customer2);
        System.out.println(customer3);
    }
}
