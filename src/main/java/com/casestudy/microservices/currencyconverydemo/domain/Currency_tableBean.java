package com.casestudy.microservices.currencyconverydemo.domain;

import java.math.BigDecimal;

public class Currency_tableBean {
	
	
	String country_code;
	double currency;
	BigDecimal convertedAmt;
	String port;
	
	
	public Currency_tableBean(String country_code, double currency, BigDecimal convertedAmt,String port) {
		super();
		this.country_code = country_code;
		this.currency = currency;
		this.convertedAmt = convertedAmt;
		this.port = port;
	}
	public Currency_tableBean() {
		super();
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public double getCurrency() {
		return currency;
	}
	public void setCurrency(double currency) {
		this.currency = currency;
	}
	public BigDecimal getConvertedAmt() {
		return convertedAmt;
	}
	public void setConvertedAmt(BigDecimal convertedAmt) {
		this.convertedAmt = convertedAmt;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}  
	
	
	
	

}
