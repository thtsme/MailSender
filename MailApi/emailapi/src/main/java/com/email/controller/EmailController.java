package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "hello";
	}
	
	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	//api to send email
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		boolean flag = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
		if(flag) {
			return ResponseEntity.ok(new EmailResponse("Email is sent successfully..."));
		}
		System.out.println(request);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("email not sent...."));
	}

}
