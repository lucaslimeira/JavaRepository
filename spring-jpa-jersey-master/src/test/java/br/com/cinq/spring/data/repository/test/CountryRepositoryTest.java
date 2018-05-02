package br.com.cinq.spring.data.repository.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.service.CountryDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
@IntegrationTest("server.port=9000")
@ActiveProfiles("unit")
public class CountryRepositoryTest {

	private CountryDataService countryDS = CountryDataService.getInstance();

    @Test
    public void testGelAllCountries() {

        Assert.assertNotNull(countryDS);
        
        List<Country> countries = countryDS.getAllCountries();

        Assert.assertTrue(countries.size() > 0);
    }

    @Test
    public void testFindOneCountry() {

        Assert.assertNotNull(countryDS);

        Country country = countryDS.getCountryByName("Brazil");

        Assert.assertNotNull(country);
    }

}