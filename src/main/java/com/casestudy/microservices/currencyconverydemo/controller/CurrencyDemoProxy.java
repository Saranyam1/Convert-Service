package com.casestudy.microservices.currencyconverydemo.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.casestudy.microservices.currencyconverydemo.domain.Currency_tableBean;

//@FeignClient(name ="currency-demo",url="http://localhost:8100")

@FeignClient(name ="currency-demo")
@RibbonClient(name ="currency-demo")
public interface CurrencyDemoProxy {

	@GetMapping("/currency-conversion-factor/country-code/{country_code}") 
	  public Currency_tableBean getConversionFactor(@PathVariable("country_code") String country_code) ;

}
