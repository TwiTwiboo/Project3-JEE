package triphub.managedBeans.registration;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import triphub.entity.user.SuperAdmin;
import triphub.helpers.FacesMessageUtil;
import triphub.helpers.ImageHelper;
import triphub.helpers.RegistrationException;
import triphub.services.UserService;
import triphub.viewModel.UserViewModel;

/**
 * Managed bean responsible for handling SuperAdmin related operations including
 * registration, update, and delete actions.
 */
@Named("superAdminBean")
@RequestScoped
public class SuperAdminBean implements Serializable {

	@Inject
	private UserService userService;

	private static final long serialVersionUID = 1L;

	private UserViewModel userViewModel = new UserViewModel();

	private Part profilePicture;

	private List<SuperAdmin> allSuperAdmins;

	public SuperAdminBean() {
	}

	/**
	 * Registers a new SuperAdmin. Validates the data, creates the SuperAdmin if
	 * validation passes and redirects to login on success.
	 *
	 * @throws IOException if there's an error during redirection.
	 */
	public void register() throws IOException {
		if (!userViewModel.getPassword().equals(userViewModel.getConfirmPassword())) {
			FacesMessageUtil.addErrorMessage("Passwords do not match!");
			return;
		}

		try {
			SuperAdmin email = userService.findByEmailSuperAdmin(userViewModel.getEmail());
			if (email != null) {
				throw new RegistrationException("This email is already used");
			}

			SuperAdmin newSuperAdmin = userService.createSuperAdmin(userViewModel);

			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
			session.setAttribute("superAdminId", newSuperAdmin.getId());

			initFormData(newSuperAdmin.getId());

			userViewModel.clear();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "SuperAdmin created successfully!", null));

			// Redirection to login.xhtml
			context.getExternalContext().redirect("/triphub/views/loginAndAccount/login.xhtml");

		} catch (RegistrationException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed: " + e.getMessage(), null));
		}
	}

	/**
	 * Initializes the form data for a specified SuperAdmin.
	 *
	 * @param superAdminId The ID of the SuperAdmin.
	 */
	public void initFormData(Long superAdminId) {
		UserViewModel temp = userService.initSuperAdmin(superAdminId);
		if (temp != null) {
			this.userViewModel = temp;
		} else {
			FacesMessageUtil.addErrorMessage("Initialization failed: SuperAdmin does not exist");
		}
	}

	/**
	 * Post-construct method to initialize the necessary data and attributes.
	 */
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		Long superAdminId = (Long) session.getAttribute("superAdminId");

		if (superAdminId != null) {
			initFormData(superAdminId);
		}

		allSuperAdmins = userService.getAllSuperAdmins();
	}

	/**
	 * Updates the SuperAdmin's details including the profile picture.
	 */
	public void updateSuperAdmin() {
		try {
			String profilePicName = ImageHelper.processProfilePicture(profilePicture);
			if (profilePicName != null) {
				userViewModel.setProfilePicture(profilePicName);
			}
			userViewModel = userService.updateSuperAdminWithImage(userViewModel);
		} catch (Exception e) {
			FacesMessageUtil.addErrorMessage("Update failed: " + e.getMessage());
		}
	}

	/**
	 * Deletes the specified SuperAdmin and redirects the user to the home page.
	 */
	public void deleteSuperAdmin() {
		try {
			userService.deleteSuperAdmin(userViewModel.getSuperAdminId());
			FacesMessageUtil.addSuccessMessage("SuperAdmin deleted successfully!");

			FacesContext context = FacesContext.getCurrentInstance();

			context.getExternalContext().redirect("/triphub/views/home.xhtml");

		} catch (Exception e) {
			FacesMessageUtil.addErrorMessage("Delete failed: " + e.getMessage());
		}
	}

	public String modifySuperAdmin(SuperAdmin superAdmin) {
		userViewModel = superAdmin.initSuperAdminViewModel();

		return "modifySuperAdmin?faces-redirect=true";
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserViewModel getUserViewModel() {
		return userViewModel;
	}

	public void setUserViewModel(UserViewModel userViewModel) {
		this.userViewModel = userViewModel;
	}

	public Part getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(Part profilePicture) {
		this.profilePicture = profilePicture;
	}

	public List<SuperAdmin> getAllSuperAdmins() {
		return allSuperAdmins;
	}

	public void setAllSuperAdmins(List<SuperAdmin> allSuperAdmins) {
		this.allSuperAdmins = allSuperAdmins;
	}

}
