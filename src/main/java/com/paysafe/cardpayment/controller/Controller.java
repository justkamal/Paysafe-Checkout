package com.paysafe.cardpayment.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

	@PostMapping("/paymentRequest")
	public String getPaymentReuqest(@RequestBody String userInfo) {
		// RestTemplate paysafePayment = new RestTemplate();
		JSONObject json = new JSONObject(userInfo);
		System.out.println(json.get("token"));
		return "";
	}

	@PostMapping("/getToken")
	public String getToken(@RequestBody String payload) {
		JSONObject json = new JSONObject(payload);
		System.out.println(payload);
		System.out.println(json.get("email"));
		return "";
	}

}
