package triphub.managedBeans.products;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import triphub.entity.product.service.Service;
import triphub.entity.product.service.ServiceType;
import triphub.entity.subservices.Accommodation;
import triphub.entity.subservices.AccommodationType;
import triphub.entity.subservices.Restaurant;
import triphub.entity.subservices.Transportation;
import triphub.entity.subservices.TransportationType;
import triphub.services.ServiceService;
import triphub.viewModel.SubServicesViewModel;

/**
 * Managed Bean responsible for handling Service-related actions.
 */
@Named("serviceBean")
@RequestScoped
public class ServiceBean implements Serializable {

	@Inject
	private ServiceService serviceService;

	@Inject
	private SubServicesViewModel servicevm;

	private static final long serialVersionUID = 1L;

	private ServiceType selectedServiceType;
	private List<Service> allServices;

	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	private ServiceType accommodationType;
	private ServiceType transportationType;
	private ServiceType restaurantType;

	private List<Restaurant> restaurantList;
	private String searchRestaurantName;
	private String searchRestaurantCity;
	private String searchRestaurantCountry;

	private List<Accommodation> accommodationList;
	private AccommodationType selectedAccommodationType;
	private String searchAccommodationName;
	private String searchAccommodationCity;
	private String searchAccommodationCountry;

	private List<Transportation> transportationList;
	private TransportationType selectedTransportationType;
	private String searchTransportationName;
	private String searchDepartureCity;
	private String searchDepartureCountry;
	private String searchArrivalCity;
	private String searchArrivalCountry;

	public ServiceBean() {
	}

	public ServiceBean(ServiceService serviceService, SubServicesViewModel servicevm, List<Service> allServices) {
		super();
		this.serviceService = serviceService;
		this.servicevm = servicevm;
		this.setAllServices(allServices);
	}

	/**
	 * Initializes the properties of the ServiceBean.
	 */
	@PostConstruct
	public void init() {
		selectedServiceType = null;
		selectedAccommodationType = null;
		selectedTransportationType = null;
		accommodationList = new ArrayList<>();
		transportationList = new ArrayList<>();
		restaurantList = new ArrayList<>();
	}

	/**
	 * Filters services based on selected service type.
	 * 
	 * @return List of filtered services.
	 */
	public List<Service> getFilteredServices() {
		List<Service> filteredServices = new ArrayList<>();
		if (selectedServiceType == null) {
			filteredServices = allServices;
		} else {
			for (Service service : allServices) {
				if (service.getType() == selectedServiceType) {
					filteredServices.add(service);
				}
			}
		}
		return filteredServices;
	}

	/**
	 * Executes advanced search for accommodations based on user-defined criteria.
	 */
	public void advancedSearchAccommodation() {
		accommodationList = serviceService.advancedSearchAccommodations(searchAccommodationCity,
				searchAccommodationCountry, minPrice, maxPrice, searchAccommodationName, selectedAccommodationType);
	}

	/**
	 * Executes advanced search for transportations based on user-defined criteria.
	 */
	public void advancedSearchTransportation() {
		transportationList = serviceService.advancedSearchTransportations(searchDepartureCity, searchDepartureCountry,
				searchArrivalCity, searchArrivalCountry, minPrice, maxPrice, searchTransportationName,
				selectedTransportationType);
	}

	/**
	 * Executes advanced search for restaurants based on user-defined criteria.
	 */
	public void advancedSearchRestaurant() {
		restaurantList = serviceService.advancedSearchRestaurants(searchRestaurantName, searchRestaurantCity,
				searchRestaurantCountry, minPrice, maxPrice);
	}

	public List<Service> getAllServices() {
		return allServices;
	}

	public void setAllServices(List<Service> allServices) {
		this.allServices = allServices;
	}

	public ServiceType[] getAllServiceTypes() {
		return ServiceType.values();
	}

	public SubServicesViewModel getServicevm() {
		return servicevm;
	}

	public void setServicevm(SubServicesViewModel servicevm) {
		this.servicevm = servicevm;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public ServiceType getSelectedServiceType() {
		return selectedServiceType;
	}

	public void setSelectedServiceType(ServiceType selectedServiceType) {
		this.selectedServiceType = selectedServiceType;
	}

	public ServiceType getAccommodationType() {
		return accommodationType;
	}

	public void setAccommodationType(ServiceType accommodationType) {
		this.accommodationType = accommodationType;
	}

	public ServiceType getTransportationType() {
		return transportationType;
	}

	public void setTransportationType(ServiceType transportationType) {
		this.transportationType = transportationType;
	}

	public ServiceType getRestaurantType() {
		return restaurantType;
	}

	public void setRestaurantType(ServiceType restaurantType) {
		this.restaurantType = restaurantType;
	}

	public List<Accommodation> getAccommodationList() {
		return accommodationList;
	}

	public void setAccommodationList(List<Accommodation> accommodationList) {
		this.accommodationList = accommodationList;
	}

	public List<Transportation> getTransportationList() {
		return transportationList;
	}

	public void setTransportationList(List<Transportation> transportationList) {
		this.transportationList = transportationList;
	}

	public List<Restaurant> getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List<Restaurant> restaurantList) {
		this.restaurantList = restaurantList;
	}

	public AccommodationType getSelectedAccommodationType() {
		return selectedAccommodationType;
	}

	public void setSelectedAccommodationType(AccommodationType selectedAccommodationType) {
		this.selectedAccommodationType = selectedAccommodationType;
	}

	public String getSearchAccommodationName() {
		return searchAccommodationName;
	}

	public void setSearchAccommodationName(String searchAccommodationName) {
		this.searchAccommodationName = searchAccommodationName;
	}

	public String getSearchAccommodationCity() {
		return searchAccommodationCity;
	}

	public void setSearchAccommodationCity(String searchAccommodationCity) {
		this.searchAccommodationCity = searchAccommodationCity;
	}

	public String getSearchAccommodationCountry() {
		return searchAccommodationCountry;
	}

	public void setSearchAccommodationCountry(String searchAccommodationCountry) {
		this.searchAccommodationCountry = searchAccommodationCountry;
	}

	public String getSearchRestaurantName() {
		return searchRestaurantName;
	}

	public void setSearchRestaurantName(String searchRestaurantName) {
		this.searchRestaurantName = searchRestaurantName;
	}

	public String getSearchRestaurantCity() {
		return searchRestaurantCity;
	}

	public void setSearchRestaurantCity(String searchRestaurantCity) {
		this.searchRestaurantCity = searchRestaurantCity;
	}

	public String getSearchRestaurantCountry() {
		return searchRestaurantCountry;
	}

	public void setSearchRestaurantCountry(String searchRestaurantCountry) {
		this.searchRestaurantCountry = searchRestaurantCountry;
	}

	public TransportationType getSelectedTransportationType() {
		return selectedTransportationType;
	}

	public void setSelectedTransportationType(TransportationType selectedTransportationType) {
		this.selectedTransportationType = selectedTransportationType;
	}

	public String getSearchTransportationName() {
		return searchTransportationName;
	}

	public void setSearchTransportationName(String searchTransportationName) {
		this.searchTransportationName = searchTransportationName;
	}

	public String getSearchDepartureCity() {
		return searchDepartureCity;
	}

	public void setSearchDepartureCity(String searchDepartureCity) {
		this.searchDepartureCity = searchDepartureCity;
	}

	public String getSearchDepartureCountry() {
		return searchDepartureCountry;
	}

	public void setSearchDepartureCountry(String searchDepartureCountry) {
		this.searchDepartureCountry = searchDepartureCountry;
	}

	public String getSearchArrivalCity() {
		return searchArrivalCity;
	}

	public void setSearchArrivalCity(String searchArrivalCity) {
		this.searchArrivalCity = searchArrivalCity;
	}

	public String getSearchArrivalCountry() {
		return searchArrivalCountry;
	}

	public void setSearchArrivalCountry(String searchArrivalCountry) {
		this.searchArrivalCountry = searchArrivalCountry;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

}
