package triphub.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;

import triphub.dao.services.TransportationDAO;
import triphub.entity.product.TourPackage;
import triphub.entity.product.service.ServiceInterface;
import triphub.entity.subservices.Accommodation;
import triphub.entity.subservices.Restaurant;
import triphub.entity.subservices.Transportation;
import triphub.entity.subservices.TransportationType;
import triphub.helpers.FacesMessageUtil;

import triphub.viewModel.SubServicesViewModel;
import triphub.viewModel.TourPackageFormViewModel;

/**
 * This class represents a service that handles transportation-related
 * operations. It implements the ServiceInterface and is intended to be used in
 * a CDI (Contexts and Dependency Injection) context. The service interacts with
 * a TransportationDAO to perform CRUD operations on transportation entities.
 */
@ApplicationScoped
public class TransportationService implements ServiceInterface {

	@Inject
	@Default
	private TransportationDAO transportationDAO;

	public TransportationService() {
	}

	public TransportationService(TransportationDAO transportationDAO) {
		this.transportationDAO = transportationDAO;
	}

	/**
	 * Finds and returns a list of transportation entities based on the specified
	 * transportation type.
	 *
	 * @param transportationType The type of transportation to search for.
	 * @return A list of transportation entities matching the specified type.
	 */
	public List<Transportation> findByType(TransportationType transportationType) {
		return transportationDAO.findByType(transportationType);
	}

	/**
	 * Creates a new transportation entity using the provided ViewModel and user
	 * information.
	 *
	 * @param transportationvm The ViewModel containing transportation details.
	 * @param userId           The ID of the user creating the transportation.
	 * @param userType         The type of user creating the transportation.
	 * @return The created Transportation entity.
	 */
	@Transactional
	@Override
	public Transportation create(SubServicesViewModel transportationvm, Long userId, String userType) {

		try {
			return transportationDAO.create(transportationvm, userId, userType); // Call create() method of DAO
		} catch (Exception e) {
			// Handle any unexpected exceptions that might occur during the create process
			FacesMessageUtil.addErrorMessage("Failed to create transportation. An unexpected error occurred.");
		}
		return null;
	}

	/**
	 * Retrieves a transportation entity by its ID.
	 *
	 * @param id The ID of the transportation entity to retrieve.
	 * @return The retrieved Transportation entity, or null if not found.
	 */
	@Override
	public Transportation read(Long id) {
		return transportationDAO.read(id);
	}

	/**
	 * Updates a transportation entity using the provided ViewModel.
	 *
	 * @param transportationvm The ViewModel containing updated transportation
	 *                         details.
	 */
	public void update(SubServicesViewModel transportationvm) {
		try {
			transportationDAO.update(transportationvm);
		} catch (IllegalArgumentException e) {
			// Handle the case when the transportation with the provided ID was not found in
			// the DAO
			FacesMessageUtil.addErrorMessage("Failed to update transportation: " + e.getMessage());
		} catch (Exception e) {
			// Handle any other unexpected exceptions that might occur during the update
			// process
			FacesMessageUtil.addErrorMessage("Failed to update transportation. An unexpected error occurred.");
		}
	}

	/**
	 * Deletes a transportation entity using the provided ViewModel.
	 *
	 * @param transportationvm The ViewModel containing transportation details to
	 *                         delete.
	 */
	@Override
	public void delete(SubServicesViewModel transportationvm) {
		transportationDAO.delete(transportationvm);
	}

	/**
	 * Initializes and returns a ViewModel for a specific transportation entity.
	 *
	 * @param id The ID of the transportation entity for which to create the
	 *           ViewModel.
	 * @return The initialized ViewModel, or null if the transportation entity is
	 *         not found.
	 */
	@Override
	public SubServicesViewModel initSubService(Long id) {
		Transportation transportation = transportationDAO.findById(id);
		if (transportation == null) {
			return null;
		}
		return transportation.initTransportationViewModel();
	}

	// getters/setters

	public Transportation getTransportationById(Long id) {
		return transportationDAO.read(id);
	}

	@Override
	public List<Transportation> getAll() {
		return transportationDAO.getAll();
	}

	@Override
	public Transportation findByName(String name) {
		return transportationDAO.findByName(name);
	}

	@Override
	public Transportation findById(Long id) {
		return transportationDAO.findById(id);
	}

	public List<Transportation> getTransportationForOrganizer(Long organizerId) {
		return transportationDAO.getTransportationForOrganizer(organizerId);
	}

	public List<Transportation> getTransportationForProvider(Long providerId) {
		return transportationDAO.getTransportationForProvider(providerId);
	}

}
