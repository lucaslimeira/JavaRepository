package br.com.cinq.spring.data.sample.service;

import java.util.List;

import br.com.cinq.spring.data.sample.dao.CountryDAO;
import br.com.cinq.spring.data.sample.entity.Country;

public class CountryDataService {
	private static CountryDAO countryDao;
	private static CountryDataService countryInstance = new CountryDataService();
	
	public static CountryDataService getInstance() {
		countryDao = new CountryDAO();
        return countryInstance;
    }
	
	public List<Country> getAllCountries() {
		List<Country> countryList = countryDao.getAllCountries();
		return countryList;
	}
	
	public Country getCountryByName(String name) {
		Country country = countryDao.getCountryByName(name);
		return country;
	}
	
}
