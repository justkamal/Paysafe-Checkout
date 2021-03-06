package com.paysafe.cardpayment.dto;

import java.util.List;

import com.paysafe.cardpayment.model.Address;
import com.paysafe.cardpayment.model.PaymentHandle;


public class CustomerTokenResponseDTO {
	
	private String id;
	private String merchantRefNum;
	private String profileId;
	private Integer timeToLiveSeconds;
	private String status;
	private String singleUseCustomerToken;
	private List<String> paymentTypes;
	private String locale;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phone;
	private String customerId;
	private String nationality;
	private List<Address> addresses;
	private List<PaymentHandle> paymentHandles;

    public String getId() {
        return id;
    }
        
    public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setId(String id) {
        this.id = id;
    }

    public String getMerchantRefNum() {
        return merchantRefNum;
    }

    public void setMerchantRefNum(String merchantRefNum) {
        this.merchantRefNum = merchantRefNum;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Integer getTimeToLiveSeconds() {
        return timeToLiveSeconds;
    }

    public void setTimeToLiveSeconds(Integer timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSingleUseCustomerToken() {
        return singleUseCustomerToken;
    }

    public void setSingleUseCustomerToken(String singleUseCustomerToken) {
        this.singleUseCustomerToken = singleUseCustomerToken;
    }

    public List<String> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<String> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<PaymentHandle> getPaymentHandles() {
        return paymentHandles;
    }

    public void setPaymentHandles(List<PaymentHandle> paymentHandles) {
        this.paymentHandles = paymentHandles;
    }

    @Override
    public String toString() {
        return "CustomerTokenResponseDTO{" +
                "id='" + id + '\'' +
                ", merchantRefNum='" + merchantRefNum + '\'' +
                ", profileId='" + profileId + '\'' +
                ", timeToLiveSeconds=" + timeToLiveSeconds +
                ", status='" + status + '\'' +
                ", singleUseCustomerToken='" + singleUseCustomerToken + '\'' +
                ", paymentTypes=" + paymentTypes +
                ", locale='" + locale + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", customerId='" + customerId + '\'' +
                ", nationality='" + nationality + '\'' +
                ", addresses=" + addresses +
                ", paymentHandles=" + paymentHandles +
                '}';
    }
}
