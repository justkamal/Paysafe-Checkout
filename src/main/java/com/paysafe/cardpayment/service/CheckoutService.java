package com.paysafe.cardpayment.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paysafe.cardpayment.dto.CreateCustomerRequestDTO;
import com.paysafe.cardpayment.dto.CreateCustomerResponseDTO;
import com.paysafe.cardpayment.dto.CustomerTokenRequestDTO;
import com.paysafe.cardpayment.dto.CustomerTokenResponseDTO;
import com.paysafe.cardpayment.dto.PaymentRequestDTO;
import com.paysafe.cardpayment.dto.PaymentResponseDTO;
import com.paysafe.cardpayment.entity.Customer;
import com.paysafe.cardpayment.repository.CustomerRepository;

@Service
@CrossOrigin
public class CheckoutService {

	@Autowired
	private CustomerRepository customerRepository;
	private RestTemplate restTemplate;
	private String baseUrl;
	private String APIKeyId;
	private String APIKeyPassword;
	private final HttpHeaders headers;

	public CheckoutService() {
		this.restTemplate = new RestTemplate();
		baseUrl = "https://api.test.paysafe.com/paymenthub/v1";
		APIKeyId = <Enter API Private Key>;
		APIKeyPassword = <Enter API Private Password>;

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// set basic authorization with api key and its value
		headers.setBasicAuth(APIKeyId, APIKeyPassword);
	}

	public CustomerTokenResponseDTO creatSingleUserCustomerToken(CreateCustomerRequestDTO createCustomerRequestDTO) {
		Customer customer = null;
		
		for(Customer existingCustomer : customerRepository.findAll()) {
			if(existingCustomer.getEmail().equalsIgnoreCase(createCustomerRequestDTO.getEmail()))
				customer = existingCustomer;
		}
		
		System.out.println(customer);
		
		// check if user is previously registered
		if (customer == null) {
			// create a new user
			System.out.println("Creating new customer");
			customer = createCustomer(createCustomerRequestDTO);
			// check if user is created or not
			if (customer == null) {
				System.out.println("Unable to create customer");
				return null;
			}
		}

		// get API url in the string
		String url = baseUrl + "/customers/" + customer.getPaysafeId() + "/singleusecustomertokens";

		// create new http header and set content type to application/json
		// create request object
		CustomerTokenRequestDTO customerTokenRequestDTO = new CustomerTokenRequestDTO();
		customerTokenRequestDTO.setMerchantRefNum(String.valueOf(new Random().nextInt()));
		customerTokenRequestDTO.setPaymentTypes(Arrays.asList("CARD"));

		// convert request object in to json object
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(customerTokenRequestDTO);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// build the request
		HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

		// send POST request
		ResponseEntity<CustomerTokenResponseDTO> responseEntity = restTemplate.postForEntity(url, entity,
				CustomerTokenResponseDTO.class);

		if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
			CustomerTokenResponseDTO customerTokenResponseDTO = responseEntity.getBody();
			customerTokenResponseDTO.setMerchantRefNum(customerTokenRequestDTO.getMerchantRefNum());
			System.out.println("Customer Token" + "\n" + responseEntity.getBody());
			return responseEntity.getBody();
		}

		return null;
	}

	public Customer createCustomer(CreateCustomerRequestDTO createCustomerRequestDTO) {

		// get API url in the string
		String url = baseUrl + "/customers";

		// create a new map for the body of the request and put all the values received
		// from the user in the map
		// convert request object in to json object
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(createCustomerRequestDTO);
		} catch (JsonMappingException | JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(jsonString);

		// build the request
		HttpEntity<String> entity = new HttpEntity<String>(jsonString, headers);

		// send POST request
		ResponseEntity<CreateCustomerResponseDTO> responseEntity = restTemplate.postForEntity(url, entity,
				CreateCustomerResponseDTO.class);

		// check if user is successfully created
		if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
			// get the response
			CreateCustomerResponseDTO response = responseEntity.getBody();

			System.out.println("Newly created customer token" + "\n" + responseEntity.getBody());
			// create new record for the customer in local database and set it's attributes
			// values
			Customer newCustomer = new Customer();
			newCustomer.setPaysafeId(response.getId());
			newCustomer.setEmail(response.getEmail());

			customerRepository.save(newCustomer);
			return newCustomer;
		} else {
			System.out.println("failed user creation");
			return null;
		}

	}

	public PaymentResponseDTO makePayment(PaymentRequestDTO makePaymentRequestDTO) {

		System.out.println("Attempting payment request ... ");

		// create an url for the payment api
		String url = baseUrl + "/payments";

		// convert request object into json object
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(makePaymentRequestDTO);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// build the request
		HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

		// send POST request
		ResponseEntity<PaymentResponseDTO> responseEntity = restTemplate.postForEntity(url, entity,
				PaymentResponseDTO.class);

		if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
			// throw an exception
			System.out.println("Couldn't complete payment");
			return null;
		}
		System.out.println(responseEntity.toString());
		
		return responseEntity.getBody();
	}
}
