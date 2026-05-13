package jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jpa.domain.Admin;
import jpa.domain.People;
import jpa.utils.PasswordUtil;

public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {

        this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {

			if (findPeopleByEmail(manager, "admin@gmail.com") == null) {
				Admin admin = new Admin(
						"admin",
						"admin",
						PasswordUtil.hash("Azerty123?"),
						"admin@gmail.com"
				);
				manager.persist(admin);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

	private static People findPeopleByEmail(EntityManager manager, String email) {
		try {
			return manager
					.createQuery("select p from People p where p.email = :email", People.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
