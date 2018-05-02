package br.com.cinq.spring.data.sample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

public class CityDAO {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbcities");
	EntityManager manager;
	
	private static final long serialVersionUID = 1L;
	
	public CityDAO() {
		manager = factory.createEntityManager();
    }
 
	public List<City> getCitiesByCountry(Country country) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(City.class);
		Root from = query.from(City.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(from.get("country_id"), country));
		
		TypedQuery typedQuery = manager.createQuery(query.select(from).where(predicate));
		return (List<City>) typedQuery.getResultList();
		
	}	
	
		
}
