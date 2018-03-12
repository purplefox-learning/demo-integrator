package com.ycm.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Repository
public class CountryDao {
    private class CountryList extends ParameterizedTypeReference<List<Country>> {}

    private final String resourceUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public CountryDao(RestTemplate restTemplate, @Value("${edmi.provider.url}") String providerUrl) {
        this.restTemplate = restTemplate;
        this.resourceUrl = providerUrl + "/countries";
    }

    public List<Country> getAllCountries() {
        log.info("[Integrator] CoutryDao is about to getAllCountries()...");
        return restTemplate.exchange(resourceUrl, HttpMethod.GET, null, new CountryList()).getBody();
    }

    public Country getCountry(String countryCode) {
        log.info("[Integrator] CoutryDao is about to getCountry()...");
        String url = resourceUrl + "/" + countryCode;
        return restTemplate.getForObject(url, Country.class);
    }
}
