package com.casestudy.microservices.currencyconverydemo.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.casestudy.microservices.currencyconverydemo.domain.Currency_tableBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@RestController

public class CurrencyConvertController  {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CurrencyDemoProxy currencyDemoProxy ;
	
	@Autowired
	private org.springframework.core.env.Environment environment;
	
	@HystrixCommand(fallbackMethod = "fallbackGetConvertedCurrency")
	 @GetMapping("/currency-convert-call/country-code/{country_code}/currency/{currency}") 
	 public  Currency_tableBean  getConvertedCurrency(@PathVariable String country_code,@PathVariable BigDecimal currency)   {
			 Map<String,String> variable = new HashMap<>(); 
			 variable.put("country_code", country_code); 
			 ResponseEntity<Currency_tableBean> forEntity = new
					 RestTemplate().getForEntity("http://localhost:8100/currency-conversion-factor/country-code/{country_code}",
							 Currency_tableBean.class,variable);
			 Currency_tableBean response =forEntity.getBody(); 
			 String portval = response.getPort();
			 logger.info("currency-convert-call" +response );
			 return new Currency_tableBean(response.getCountry_code(), response.getCurrency(), currency.multiply(new BigDecimal(response.getCurrency())), portval);
       
	}
	public  Currency_tableBean  fallbackGetConvertedCurrency(@PathVariable String country_code,@PathVariable BigDecimal currency)   {
		double curr =1;
		 return new Currency_tableBean(country_code, curr, currency.multiply(new BigDecimal(curr)), "0");
  
}

	 
	@HystrixCommand(fallbackMethod = "fallbackgetConvertedCurrencyFeign")
	 @GetMapping("/currency-convert-call-Feign/country/{country_code}/quantity/{quantity}") 
	 public  Currency_tableBean  getConvertedCurrencyFeign(@PathVariable String country_code,@PathVariable BigDecimal quantity) {
		 Currency_tableBean conversionFactor = currencyDemoProxy.getConversionFactor(country_code);
		 String portval = conversionFactor.getPort();
		 logger.info("currency-convert-call" +conversionFactor );
		return new Currency_tableBean(conversionFactor.getCountry_code(), conversionFactor.getCurrency(), quantity.multiply(new BigDecimal(conversionFactor.getCurrency())), portval);
				 
	 }
	 public  Currency_tableBean  fallbackgetConvertedCurrencyFeign(@PathVariable String country_code,@PathVariable BigDecimal quantity) {
		 double curr =1;
		 return new Currency_tableBean(country_code, curr, quantity.multiply(new BigDecimal(curr)), "0");
				 
	 }

	

}
