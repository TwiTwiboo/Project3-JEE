package triphub.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import triphub.entity.user.Customer;
import triphub.entity.user.Provider;
import triphub.entity.user.User;
import triphub.helpers.RegistrationException;
@Stateless
public class ProviderDAO {
	@PersistenceUnit
	private EntityManager em;
	   public ProviderDAO() {
	    }
	public ProviderDAO(EntityManager em) {
		this.em = em;
	}

	public Provider create(Provider provider) {
		em.persist(provider);
		return provider;
	}

	public Provider read(Long id) {
		return em.find(Provider.class, id);
	}

    public Provider findByEmail(String email) throws RegistrationException {
        TypedQuery<Provider> query = em.createQuery("SELECT c FROM Provider c WHERE c.user.email = :email", Provider.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new RegistrationException("Provider with email " + email + " not found.");
        }
    }
	
	public Provider findByUser(User user) {
	    try {
	        TypedQuery<Provider> query = em.createQuery("SELECT c FROM Provider c WHERE c.user = :user", Provider.class);
	        query.setParameter("user", user);
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
}
