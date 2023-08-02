package triphub.main;

import java.util.List;

import javax.persistence.EntityManager;

import triphub.dao.CustomerDAO;
import triphub.dao.UserDAO;

import triphub.dao.service.AccommodationDAO;
import triphub.dao.service.AddressDAO;
import triphub.dao.service.RestaurantDAO;
import triphub.entity.product.service.accommodation.Accommodation;
import triphub.entity.product.service.accommodation.AccommodationType;
import triphub.entity.product.service.restaurant.Restaurant;
import triphub.dao.product.DestinationDAO;
import triphub.dao.product.PriceDAO;
import triphub.dao.product.ThemeDAO;
import triphub.dao.product.TourPackageDAO;
import triphub.entity.product.Destination;
import triphub.entity.product.Price;
import triphub.entity.product.Theme;
import triphub.entity.product.TourPackage;

import triphub.entity.user.Customer;
import triphub.entity.user.User;
import triphub.entity.util.Address;
import triphub.services.RestaurantService;
import triphub.viewModel.RestaurantViewModel;

public class main {

	public static void main(String[] args) {

	 EntityManager em = JPAUtil.getEntityManager();
//
//		UserDAO userDAO = new UserDAO(em);		
//	
//		
//		User user = new User();
//		user.setFirstName("John");
//		user.setLastName("Doe");
//		user.setEmail("john.doe@example.com");
//		user.setPhoneNum("1234567890");
//
//		em.getTransaction().begin();
//		userDAO.create(user);
//		em.getTransaction().commit();
//
//		CustomerDAO customerDAO = new CustomerDAO(em);
//
//		Customer customer1 = new Customer();
//		customer1.setUser(user);
//		em.getTransaction().begin();
//		customerDAO.create(customer1);
//		em.getTransaction().commit();
//
//		User foundUser = userDAO.read(user.getId());
//		System.out.println("Found user: " + foundUser.getFirstName() + " " + foundUser.getLastName());
		
		AddressDAO addressDAO = new AddressDAO(em);
		
		Address address1 = new Address();
		address1.setNum("12");
		address1.setStreet("rue de la gare");
		address1.setCity("Amiens");
		address1.setState("Haut de France");
		address1.setCountry("France");
		
		Address address2 = new Address();
		address2.setNum("24");
		address2.setStreet("rue de la gare");
		address2.setCity("Lille");
		address2.setState("Haut de France");
		address2.setCountry("France");
		
		Address address3 = new Address();
		address3.setNum("2");
		address3.setStreet("Piazza Napoleone");
		address3.setCity("Florence");
		address3.setState("Toscane");
		address3.setCountry("Italie");
		
		Address address4 = new Address();
		address4.setNum("1");
		address4.setStreet("Garibaldi");
		address4.setCity("Milan");
		address4.setState("Lombardie");
		address4.setCountry("Italie");


//		AccommodationDAO accommodationDao = new AccommodationDAO(em);
//		
//		
//		Accommodation accommodation1 = new Accommodation();
//		accommodation1.setNameAccommodation("Hotel Ibis");
//		accommodation1.setAddress(address1);
//		accommodation1.setAccommodation(AccommodationType.Hotel);
//		em.getTransaction().begin();
//		accommodationDao.create(accommodation1);
//		em.getTransaction().commit();
//		System.out.println("accommodation create "+ accommodation1.getNameAccommodation());
		
		
		//-------------------------------------- Restaurant ---------------------------------------------
	
		RestaurantDAO restaurantDao = new RestaurantDAO(em);
		RestaurantService restaurantService = new RestaurantService(restaurantDao);
		
		RestaurantViewModel restaurantvm1 = new RestaurantViewModel();
		restaurantvm1.setNameRestaurant("Restau 01");
		restaurantvm1.setAddress(address1);
		restaurantvm1.setDescription("Description 01");
		
		RestaurantViewModel restaurantvm2 = new RestaurantViewModel();
		restaurantvm1.setNameRestaurant("Restau 02");
		restaurantvm2.setAddress(address2);
		restaurantvm2.setDescription("Description 02");
		
		RestaurantViewModel restaurantvm3 = new RestaurantViewModel();
		restaurantvm1.setNameRestaurant("Restau 03");
		restaurantvm2.setAddress(address3);
		restaurantvm2.setDescription("Description 03");
		
		em.getTransaction().begin();		
		
		Restaurant restaurantTest1 = restaurantService.create(restaurantvm1);
		Restaurant restaurantTest2 = restaurantService.create(restaurantvm2);
		Restaurant restaurantTest3 = restaurantService.create(restaurantvm3);
		
		em.getTransaction().commit();
		
		
		//test tourpackage
		DestinationDAO desDao =new DestinationDAO(em);
		ThemeDAO themeDao= new ThemeDAO(em);
		PriceDAO priceDao= new PriceDAO(em);
		TourPackageDAO tpDao= new TourPackageDAO(em);
		
		Destination destination = new Destination();
		destination.setCityName("Paris");
		destination.setState("IDF");
		destination.setCountry("France");
		
		em.getTransaction().begin();
		desDao.create(destination);
		em.getTransaction().commit();
		
		Price price = new Price();
		price.setAmount(1500.0);
		price.setCurrency("USD");
		
		em.getTransaction().begin();
		priceDao.create(price);
		em.getTransaction().commit();
		
		Theme theme = new Theme("InstaSpots");
	
		
		em.getTransaction().begin();
		themeDao.create(theme);
		em.getTransaction().commit();
		
		TourPackage tourPackage = new TourPackage();
		tourPackage.setName("Summer Adventure");
		
		tourPackage.setDestination(destination);
		tourPackage.setTheme(theme);
		tourPackage.setPrice(price);

		em.getTransaction().begin();
		tpDao.createOrUpdate(tourPackage);
		em.getTransaction().commit();
		
        System.out.println("Tourpackage:");
        System.out.println("Name: " + tourPackage.getName());
        System.out.println("Price: " + tourPackage.getPrice().getAmount() + " " + tourPackage.getPrice().getCurrency());
        System.out.println("Destination: " + tourPackage.getDestination().getCityName() + ", " + tourPackage.getDestination().getState() + ", " + tourPackage.getDestination().getCountry());
        System.out.println("Theme: " + tourPackage.getTheme().getName());
        System.out.println("Restau : " + restaurants);
        

		JPAUtil.shutdown();
	}
}
