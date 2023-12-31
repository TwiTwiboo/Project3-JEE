package triphub.viewModel;
// TODO voir plus de cohrence entre subservice view model et les entités
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import triphub.entity.product.Price;
import triphub.entity.product.service.Service;
import triphub.entity.product.service.ServiceType;
import triphub.entity.subservices.AccommodationType;
import triphub.entity.subservices.TransportationType;
import triphub.entity.util.Address;
import triphub.entity.util.CurrencyType;
import triphub.entity.util.Picture;
import triphub.entity.util.PictureType;

public class SubServicesViewModel implements Serializable {

	private static final long serialVersionUID = 1L;
	


	private Long id;
	
	private String name;

	private String description;

	
	// Service attributes
	private boolean availability;
	private Date availableFrom;
	private Date availableTill;

	
	// enumerations for Accommodation type, Transportation type, Currency type (price) and Service type
	private AccommodationType accommodationType;
	private TransportationType transportationType;
	
	private CurrencyType currencyType;
	private ServiceType serviceType;
	

	// Address attributes
	private Address address = new Address();
	private Address departure  = new Address();
	private Address arrival = new Address();
	private Price price= new Price();
	private Service service = new Service();

	// price attributes
	private BigDecimal amount;
	private String currency;

	// Image attributes
	private Picture picture;
	private int size;
	private int weight;
	private String link;
	private PictureType type;
	private String altText;

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccommodationType getAccommodationType() {
		return accommodationType;
	}

	public void setAccommodationType(AccommodationType accommodationType) {
		this.accommodationType = accommodationType;
	}

	public TransportationType getTransportationType() {
		return transportationType;
	}

	public void setTransportationType(TransportationType transportationType) {
		this.transportationType = transportationType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public PictureType getType() {
		return type;
	}

	public void setType(PictureType type) {
		this.type = type;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}


	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Date getAvailableTill() {
		return availableTill;
	}

	public void setAvailableTill(Date availableTill) {
		this.availableTill = availableTill;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}


	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Address getDeparture() {
		return departure;
	}

	public void setDeparture(Address departure) {
		this.departure = departure;
	}

	public Address getArrival() {
		return arrival;
	}

	public void setArrival(Address arrival) {
		this.arrival = arrival;

	}
	

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "SubServicesViewModel [id=" + id + ", name=" + name + ", availability=" + availability
				+ ", availableFrom=" + availableFrom + ", availableTill=" + availableTill + ", accommodationType="
				+ accommodationType + ", transportationType=" + transportationType + ", currencyType=" + currencyType
				+ ", serviceType=" + serviceType + ", description=" + description + ", address=" + address
				+ ", departure=" + departure + ", arrival=" + arrival + ", price=" + price + ", service=" + service
				+ ", amount=" + amount + ", currency=" + currency + ", size=" + size + ", weight=" + weight + ", link="
				+ link + ", type=" + type + ", altText=" + altText + "]";
	}

	

}
