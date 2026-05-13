package jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jpa.domain.Admin;
import jpa.domain.Book;
import jpa.domain.Element;
import jpa.domain.Magazine;
import jpa.domain.People;
import jpa.utils.PasswordUtil;

import java.time.LocalDate;

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

			addDefaultElements(manager);

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

	private static void addDefaultElements(EntityManager manager) {
		Book[] books = {
				new Book("dune.jpg", "Dune", "Frank Herbert"),
				new Book("fondation.jpg", "Fondation", "Isaac Asimov"),
				new Book("fahrenheit-451.jpg", "Fahrenheit 451", "Ray Bradbury"),
				new Book("1984.jpg", "1984", "George Orwell"),
				new Book("le-petit-prince.jpg", "Le Petit Prince", "Antoine de Saint-Exupery")
		};

		Magazine[] magazines = {
				new Magazine("science-et-vie.jpg", "Science et Vie", LocalDate.of(2026, 1, 10)),
				new Magazine("national-geographic.jpg", "National Geographic", LocalDate.of(2026, 2, 12)),
				new Magazine("geo.jpg", "Geo", LocalDate.of(2026, 3, 8)),
				new Magazine("courrier-international.jpg", "Courrier International", LocalDate.of(2026, 4, 18)),
				new Magazine("le-monde-diplomatique.jpg", "Le Monde diplomatique", LocalDate.of(2026, 5, 1))
		};

		for (Book book : books) {
			persistElementIfMissing(manager, book);
		}

		for (Magazine magazine : magazines) {
			persistElementIfMissing(manager, magazine);
		}
	}

	private static void persistElementIfMissing(EntityManager manager, Element element) {
		if (findElementByTitle(manager, element.getTitle()) == null) {
			manager.persist(element);
		}
	}

	private static Element findElementByTitle(EntityManager manager, String title) {
		try {
			return manager
					.createQuery("select e from Element e where e.title = :title", Element.class)
					.setParameter("title", title)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
