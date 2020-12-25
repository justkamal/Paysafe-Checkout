package com.paysafe.cardpayment.dto;

public class PaymentResponseDTO {
	
	private String merchantRefNum;
	private Integer amount;
	private String id;
	private String currencyCode;
	private String paymentHandleToken;
	private String description;

	public String getMerchantRefNum() {
		return merchantRefNum;
	}
	public void setMerchantRefNum(String merchantRefNum) {
		this.merchantRefNum = merchantRefNum;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getPaymentHandleToken() {
		return paymentHandleToken;
	}
	public void setPaymentHandleToken(String paymentHandleToken) {
		this.paymentHandleToken = paymentHandleToken;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "PaymentResponseDTO [merchantRefNum=" + merchantRefNum + ", amount=" + amount + ", id=" + id
				+ ", currencyCode=" + currencyCode + ", paymentHandleToken=" + paymentHandleToken + ", description="
				+ description + "]";
	}

}
