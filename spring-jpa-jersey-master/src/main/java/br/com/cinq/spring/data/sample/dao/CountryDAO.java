package br.com.cinq.spring.data.sample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.cinq.spring.data.sample.entity.Country;

public class CountryDAO {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbcities");
	EntityManager manager;
	
	private static final long serialVersionUID = 1L;
	
	public CountryDAO() {
		manager = factory.createEntityManager();
    }
 

	public List<Country> getAllCountries() {
		Query query = manager.createQuery("SELECT c FROM Country c") ;
		List<Country> countries = query.getResultList() ;
		return countries;
	}
	
	public Country getCountryByName(String name) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(Country.class);
		Root from = query.from(Country.class);

		Predicate predicate = builder.and();

		predicate = builder.and(predicate, builder.equal(from.get("name"), name));
		
		TypedQuery typedQuery = manager.createQuery(query.select(from).where(predicate).orderBy(builder.asc(from.get("name"))));
		return typedQuery.getResultList().size() > 0 ? (Country) typedQuery.getSingleResult() : null;	
	}		
	
}
