package triphub.main;

import javax.persistence.EntityManager;

import triphub.dao.UserDAO;
import triphub.entity.user.User;

public class main {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        UserDAO userDAO = new UserDAO(em);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNum("1234567890");

        em.getTransaction().begin();
        userDAO.create(user);
        em.getTransaction().commit();

        User foundUser = userDAO.read(user.getId());
        System.out.println("Found user: " + foundUser.getFirstName() + " " + foundUser.getLastName());

        JPAUtil.shutdown();
    }
}

