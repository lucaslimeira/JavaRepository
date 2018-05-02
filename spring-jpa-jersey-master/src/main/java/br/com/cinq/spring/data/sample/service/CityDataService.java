package br.com.cinq.spring.data.sample.service;

import java.util.List;

import br.com.cinq.spring.data.sample.dao.CityDAO;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

public class CityDataService {
	private static CityDAO cityDao;
	private static CityDataService cityInstance = new CityDataService();
	
	public static CityDataService getInstance() {
		cityDao = new CityDAO();
        return cityInstance;
    }
	
	public List<City> getCitiesByCountry(Country country) {
		List<City> cityList = cityDao.getCitiesByCountry(country);
		return cityList;
	}


}
