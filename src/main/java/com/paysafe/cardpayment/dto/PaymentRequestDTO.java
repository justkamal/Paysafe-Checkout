package com.paysafe.cardpayment.dto;

public class PaymentRequestDTO {
	
	private String merchantRefNum;
    private Integer amount;
    private String paymentHandleToken;
    private String currencyCode;
    private String description;
    private String id;
    
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

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
    @Override
    public String toString() {
        return "MakePaymentRequestDTO{" +
        		", id=" + id +
                ", amount=" + amount +
                ", merchantRefNum=" + merchantRefNum +
                ", paymentHandleToken='" + paymentHandleToken + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}