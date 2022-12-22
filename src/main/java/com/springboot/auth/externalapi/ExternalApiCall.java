package com.springboot.auth.externalapi;

import com.springboot.auth.taxpayer.entity.TaxPayer;
import org.springframework.web.client.RestTemplate;

public class ExternalApiCall {
    public static Object getRemoveObject(String uri){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, TaxPayer.class);
    }
}
