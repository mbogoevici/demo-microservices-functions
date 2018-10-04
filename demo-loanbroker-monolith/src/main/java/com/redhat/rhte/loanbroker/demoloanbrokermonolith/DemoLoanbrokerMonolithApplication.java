package com.redhat.rhte.loanbroker.demoloanbrokermonolith;

import com.redhat.rhte.loanbroker.demoloanbrokermonolith.lending.BankRequest;
import com.redhat.rhte.loanbroker.demoloanbrokermonolith.lending.BankService;
import com.redhat.rhte.loanbroker.demoloanbrokermonolith.rating.CreditRating;
import com.redhat.rhte.loanbroker.demoloanbrokermonolith.rating.CreditRatingService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoLoanbrokerMonolithApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoLoanbrokerMonolithApplication.class, args);
    }

    @Bean
    public CreditRatingService creditRatingService() {
        return new CreditRatingService();
    }

    @Bean
    public BankService lendingService() {
        return new BankService();
    }

    @Bean
    public RouteBuilder buildRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                restConfiguration("servlet").bindingMode(RestBindingMode.auto);

                rest("/loans")
                        .post("/new").type(CreditRequest.class)
                        .route().id("NewLoan").setHeader(Exchange.HTTP_RESPONSE_CODE).constant(200)
                        .choice()
                        .when(exchange -> exchange.getIn().getBody(CreditRequest.class).getAmount() <= 100).to("direct:low-value")
                        .when(exchange -> exchange.getIn().getBody(CreditRequest.class).getAmount() >= 1000).to("direct:high-value")
                        .otherwise().to("direct:medium-value");

                from("direct:low-value")
                        // auto-approve loans for small amounts
                        .process(exchange -> {
                            CreditRequest creditRequest = exchange.getIn().getBody(CreditRequest.class);
                            exchange.getOut().setBody(new CreditResponse(creditRequest.getApplicantId(),
                                    CreditResponse.Status.APPROVED,
                                    new BigDecimal(5.5),
                                    creditRequest.getAmount()
                            ));
                        });

                from("direct:medium-value")
                        .setHeader("originalRequest").simple("${in.body}")
                        .setBody().simple("${in.body.applicantId}")
                        .to("creditRatingService")
                        .choice()
                        .when(exchange -> exchange.getIn().getBody(CreditRating.class).getScore() >= 700)
                        .process(exchange -> {
                            CreditRequest creditRequest = exchange.getIn().getHeader("originalRequest", CreditRequest.class);
                            exchange.getOut().copyFrom(exchange.getIn());
                            exchange.getOut().setBody(new CreditResponse(creditRequest.getApplicantId(),
                                    CreditResponse.Status.APPROVED,
                                    new BigDecimal(5.5),
                                    creditRequest.getAmount()
                            ));
                        })
                        .when(exchange -> exchange.getIn().getBody(CreditRating.class).getScore() < 500)
                        .process(exchange -> {
                            CreditRequest creditRequest = exchange.getIn().getHeader("originalRequest", CreditRequest.class);
                            exchange.getOut().copyFrom(exchange.getIn());
                            exchange.getOut().setBody(new CreditResponse(creditRequest.getApplicantId(),
                                    CreditResponse.Status.DECLINED,
                                    new BigDecimal(0),
                                    0
                            ));
                        })
                        .otherwise()
                        .process(exchange -> {
                            CreditRequest originalRequest = exchange.getIn().getHeader("originalRequest", CreditRequest.class);
                            exchange.getOut().setBody(new BankRequest(originalRequest, exchange.getIn().getBody(CreditRating.class)));
                        })
                        .to("lendingService");


                from("direct:high-value")
                        .setHeader("originalRequest").simple("${in.body}")
                        .setBody().simple("${in.body.applicantId}")
                        .to("creditRatingService")
                        .process(exchange -> {
                            CreditRequest originalRequest = exchange.getIn().getHeader("originalRequest", CreditRequest.class);
                            exchange.getOut().setBody(new BankRequest(originalRequest, exchange.getIn().getBody(CreditRating.class)));
                        })
                        .to("lendingService");

            }
        };
    }
}
