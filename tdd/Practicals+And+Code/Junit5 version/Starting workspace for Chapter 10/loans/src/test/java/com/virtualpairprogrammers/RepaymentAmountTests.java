package com.virtualpairprogrammers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

class RepaymentAmountTests {
	
	LoanRepository data;
	
	JavaMailSender mailSender;

	RestTemplate restTemplate;
	
	LoanCalculatorController controller;
	
	@Spy
	LoanApplication loanApplication;
	
	@BeforeEach
	public void setup() {
		data = mock(LoanRepository.class);
		mailSender = mock(JavaMailSender.class);
		restTemplate = mock(RestTemplate.class);
		controller = new LoanCalculatorController();
		controller.setData(data);
		controller.setMailSender(mailSender);
		controller.setRestTemplate(restTemplate);
		loanApplication = spy(new LoanApplication());
	}

	@Test
	void test1YearLoanWholePounds() {
		loanApplication.setPrincipal(1200);
		loanApplication.setTermInMonths(12);
		
		// TODO: set the interest rate to 10%
		doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();
			
		controller.processNewLoanApplication(loanApplication);
		
		assertEquals(new BigDecimal(110), loanApplication.getRepayment());
	}
	
	@Test
	public void test2YearLoanWholePounds() {
		loanApplication.setPrincipal(1200);
		loanApplication.setTermInMonths(24);
		
		doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();
		
		controller.processNewLoanApplication(loanApplication);
		
		assertEquals(new BigDecimal(60), loanApplication.getRepayment());
	}
	
	@Test
	public void test5YearLoanWholePounds() {
		loanApplication.setPrincipal(5000);
		loanApplication.setTermInMonths(60);
		
		doReturn(new BigDecimal(6.5)).when(loanApplication).getInterestRate();
		 
		controller.processNewLoanApplication(loanApplication);
		
		assertEquals(new BigDecimal(111), loanApplication.getRepayment());
	}

}
