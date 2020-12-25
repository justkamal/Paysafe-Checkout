package com.paysafe.cardpayment.controller;

import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paysafe.cardpayment.dto.CreateCustomerRequestDTO;
import com.paysafe.cardpayment.dto.CustomerTokenResponseDTO;
import com.paysafe.cardpayment.dto.PaymentRequestDTO;
import com.paysafe.cardpayment.dto.PaymentResponseDTO;
import com.paysafe.cardpayment.dto.ResponseDTO;
import com.paysafe.cardpayment.service.CheckoutService;

@RestController
@CrossOrigin
public class Controller {

	@Autowired
	private CheckoutService checkoutService;

	@PostMapping("/getToken")
	public ResponseDTO getSingleUseCustomerToken(@RequestBody String payload) {
		JSONObject reqPayload = new JSONObject(payload);
		
		CreateCustomerRequestDTO createCustomerRequestDTO = new CreateCustomerRequestDTO();
		createCustomerRequestDTO.setMerchantCustomerId(reqPayload.getString("email") + "18995");
		createCustomerRequestDTO.setLocale("en_US");
		createCustomerRequestDTO.setFirstName(reqPayload.getString("firstName"));
		createCustomerRequestDTO.setLastName(reqPayload.getString("lastName"));
		createCustomerRequestDTO.setEmail(reqPayload.getString("email"));
		createCustomerRequestDTO.setPhone(reqPayload.getString("phone"));

		ResponseDTO<CustomerTokenResponseDTO> responseDTO = new ResponseDTO<CustomerTokenResponseDTO>();
		CustomerTokenResponseDTO customerTokenResponseDTO = checkoutService.creatSingleUserCustomerToken(createCustomerRequestDTO);

		if (customerTokenResponseDTO != null) {
			responseDTO.setMessage("CustomerToken Created");
			responseDTO.setData(customerTokenResponseDTO);
		} else {
			responseDTO.setMessage("Couldn't create CustomerToken");
		}

		responseDTO.setStatus(HttpStatus.OK);
		return responseDTO;
	}

	@PostMapping("/requestPayment")
	public ResponseDTO makePayment(@RequestBody String payload) {
		Random randFunc = new Random();
		JSONObject reqPayload = new JSONObject(payload);
		
		System.out.println(payload);
		
		PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
		paymentRequestDTO.setMerchantRefNum(String.valueOf(randFunc.nextInt()));
		paymentRequestDTO.setAmount(Integer.parseInt(reqPayload.get("amount").toString()));
		paymentRequestDTO.setPaymentHandleToken(reqPayload.getString("token"));
		paymentRequestDTO.setCurrencyCode("USD");
		paymentRequestDTO.setDescription("Demo");
		paymentRequestDTO.setId(reqPayload.getString("customerId"));
		
		ResponseDTO<PaymentResponseDTO> responseDTO = new ResponseDTO<PaymentResponseDTO>();
		PaymentResponseDTO paymentResponseDTO = checkoutService.makePayment(paymentRequestDTO);

		if (paymentResponseDTO != null) {
			responseDTO.setData(paymentResponseDTO);
			System.out.println("Payment done!");
		} else {
			//responseDTO.setMessage("Payment Unsuccessfull");
		}
		
		responseDTO.setStatus(HttpStatus.OK);
		System.out.println(responseDTO.getStatus());
		return responseDTO;
	}

}