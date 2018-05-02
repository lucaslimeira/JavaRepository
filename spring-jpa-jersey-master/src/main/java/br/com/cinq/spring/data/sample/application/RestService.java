package br.com.cinq.spring.data.sample.application;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.service.CityDataService;
import br.com.cinq.spring.data.sample.service.CountryDataService;

//http://localhost:8080/spring-jpa-jersey/rest/cityservice/getCities/France

@Path("/cityservice")
public class RestService {
	private CityDataService cityDataService = CityDataService.getInstance();
	private CountryDataService countryDataService = CountryDataService.getInstance();
	
	@GET
	//@Path("/getCities/{country}")
	@Path("/getCities")
	@Produces(MediaType.APPLICATION_JSON)
	public List<City> getCities(@QueryParam("country") String countryParam) {
		String err = "";
		if (!"".equals(countryParam)) {
			Country country = countryDataService.getCountryByName(countryParam);	
			if (country != null) {
				return cityDataService.getCitiesByCountry(country);	
			} else {
				err = "No country has been found by the name '" + countryParam +"'";
			}
		} else {
			err = "Country must be informed";
		}
		throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(err).build());
	}
}
