package triphub.entity.product.service;


import java.util.Date;

import java.io.Serializable;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import triphub.entity.product.Price;

import triphub.viewModel.SubServicesViewModel;

@Entity
public class Service implements Serializable{
	private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

	@Enumerated(EnumType.STRING)
	private ServiceType type;

	@OneToOne(cascade = CascadeType.ALL)
	private Price price;

	private boolean availability;
	

	@JoinColumn(name = "fromDate_id")
	@Temporal(TemporalType.DATE)
	private Date availableFrom;



	

	@JoinColumn(name = "tillDate_id")
	@Temporal(TemporalType.DATE)
	private Date availableTill;

	public static Service createServiceFromViewModel(SubServicesViewModel form) {
		Service service = new Service();
		service.setPrice(form.getPrice());
		service.setAvailability(form.isAvailability());

		service.setAvailableFrom(form.getAvailableFrom());
		service.setAvailableTill(form.getAvailableTill());

		return service;
	}

	public void updateServiceFromViewModel(SubServicesViewModel form) {
		this.setPrice(form.getPrice());
		this.setAvailability(form.isAvailability());
		this.setAvailableFrom(form.getAvailableFrom());
		this.setAvailableTill(form.getAvailableTill());
	}

	public void initServiceViewModel(SubServicesViewModel form) {
	    form.setId(this.getId());
	    this.getPrice().initPriceViewModel(form);
	    form.setAvailability(this.isAvailability());
	    form.setAvailableFrom(this.getAvailableFrom());
	    form.setAvailableTill(this.getAvailableTill());
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ServiceType getType() {
		return type;
	}

	public void setType(ServiceType type) {
		this.type = type;
	}

}
