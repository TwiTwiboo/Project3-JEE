package triphub.dao.user;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import triphub.entity.user.Customer;
import triphub.entity.user.User;
import triphub.entity.util.Address;
import triphub.entity.util.FinanceInfo;
import triphub.viewModel.UserViewModel;

@Stateless
public class UserDAO {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private CustomerDAO customerDAO;

	@Inject
	private SuperAdminDAO superAdminDAO;

	@Inject
	private ProviderDAO providerDAO;

	@Inject
	private OrganizerDAO organizerDAO;

//	public UserDAO(EntityManager em) {
//		this.em = em;
//	}

	public UserDAO() {
	}

	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	public User readUser(Long id) {
		return em.find(User.class, id);
	}

	public UserViewModel initUser(Long userId) {
		// Use the CustomerDAO to get the Customer with the same ID as the User
		Customer customer = customerDAO.findCustomerByUserId(userId);
		if (customer == null) {
			System.out.println("Pas de CustoemrID");
			return null;
		}

		User user = customer.getUser();
		if (user == null) {
			System.out.println("Pas de UserID");
			return null;
		}

		UserViewModel userViewModel = new UserViewModel();
		userViewModel.setCustomerId(customer.getId());
		userViewModel.setUserId(user.getId());
		userViewModel.setFirstName(user.getFirstName());
		userViewModel.setLastName(user.getLastName());
		userViewModel.setEmail(user.getEmail());
		userViewModel.setPhoneNum(user.getPhoneNum());

		Address address = user.getAddress();
		if (address != null) {
			userViewModel.setNum(address.getNum());
			userViewModel.setStreet(address.getStreet());
			userViewModel.setCity(address.getCity());
			userViewModel.setState(address.getState());
			userViewModel.setCountry(address.getCountry());
			userViewModel.setZipCode(address.getZipCode());
		}

		FinanceInfo finance = user.getFinance();
		if (finance != null) {
			userViewModel.setCCNumber(finance.getCCNumber());
			userViewModel.setExpirationDate(finance.getExpirationDate());
		}

//        // Use the CustomerDAO to get the Customer with the same ID as the User;
//        if (customer != null) {
//            // Add the Customer details to the UserViewModel
////            userViewModel.setProfilePicture(customer.getPicture().getLink());
//            userViewModel.setCustomerId(customer.getId());
//        }

		userViewModel.setUserId(user.getId());

		return userViewModel;
	}

	public User findByEmailUser(String email) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
		query.setParameter("email", email);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User findByUserId(Long userId) {
		try {
			return em.find(User.class, userId);
		} catch (NoResultException e) {
			return null;
		}
	}

	public void updateUser(User user) {
		em.merge(user);
	}

	public void refreshUser(User user) {
		em.refresh(user);
	}

	public List<User> advancedSearch( String firstName,String lastName, String city, String street, String zipCode,
			String country, String email, String phoneNum, String CCNumber) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);

		List<Predicate> predicates = new ArrayList<>();

		if (firstName != null && !firstName.isEmpty()) {
			predicates.add(cb.like(root.get("firstName"), "%" + firstName + "%"));
		}

		if (lastName != null && !lastName.isEmpty()) {
			predicates.add(cb.like(root.get("lastName"), "%" + lastName + "%"));
		}

		if (city != null && !city.isEmpty()) {
			predicates.add(cb.like(root.get("address").get("city"), "%" + city + "%"));
		}

		if (street != null && !street.isEmpty()) {
			predicates.add(cb.like(root.get("address").get("street"), "%" + street + "%"));
		}

		if (zipCode != null && !zipCode.isEmpty()) {
			predicates.add(cb.like(root.get("address").get("zipCode"), "%" + zipCode + "%"));
		}

		if (country != null && !country.isEmpty()) {
			predicates.add(cb.like(root.get("address").get("country"), "%" + country + "%"));
		}

		if (email != null && !email.isEmpty()) {
			predicates.add(cb.like(root.get("email"), "%" + email + "%"));
		}

		if (phoneNum != null && !phoneNum.isEmpty()) {
			predicates.add(cb.like(root.get("phoneNum"), "%" + phoneNum + "%"));
		}

		if (CCNumber != null && !CCNumber.isEmpty()) {
			predicates.add(cb.like(root.get("ccNumber"), "%" + CCNumber + "%"));
		}

		query.where(cb.and(predicates.toArray(new Predicate[0])));

		return em.createQuery(query).getResultList();
	}

}
