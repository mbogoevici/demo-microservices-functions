package com.redhat.rhte.demo.bankservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@SpringBootApplication
@RestController
public class BankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankServiceApplication.class, args);
	}

	@PostMapping("/rate-calculator")
	public CreditResponse calculateCredit(@RequestBody  BankRequest bankRequest) throws Exception {
		System.out.println(new ObjectMapper().writeValueAsString(bankRequest));
		return new CreditResponse(bankRequest.getCreditRequest().getApplicantId(), CreditResponse.Status.APPROVED,
				new BigDecimal(6.0),
				bankRequest.getCreditRequest().getAmount() / 2);
	}
}
