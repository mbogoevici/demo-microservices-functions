package com.redhat.rhte.demo.creditservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@SpringBootApplication
@RestController
public class CreditServiceApplication {

	@GetMapping("/credit/{id}")
	@ResponseBody CreditRating getCreditRating(@PathVariable("id") String applicantId, @RequestHeader Map<String,String> headers) {
		System.out.println(applicantId);
		System.out.println(headers);
		if (applicantId.startsWith("A"))
			return new CreditRating(applicantId, 900);
		if (applicantId.startsWith("B"))
			return new CreditRating(applicantId, 800);
		if (applicantId.startsWith("C"))
			return new CreditRating(applicantId, 700);
		if (applicantId.startsWith("D"))
			return new CreditRating(applicantId, 600);
		if (applicantId.startsWith("E"))
			return new CreditRating(applicantId, 500);
		if (applicantId.startsWith("F"))
			return new CreditRating(applicantId, 400);
		return new CreditRating(applicantId, 300);
	}

	public static void main(String[] args) {
		SpringApplication.run(CreditServiceApplication.class, args);
	}
}
