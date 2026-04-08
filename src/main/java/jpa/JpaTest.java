package jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.domain.Event;
import jpa.domain.User;

import java.util.List;

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

            test.createUsers();
            test.createEvents();
            test.listUsers();
            test.listEvents();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

    private void createUsers() {
        int count = manager
                .createQuery("Select u From User u", User.class)
                .getResultList()
                .size();

        if (count == 0) {
            manager.persist(new User("alice", "alice@mail.com"));
            manager.persist(new User("bob", "bob@mail.com"));
            System.out.println("Users créés");
        }
    }



    private void listUsers() {
        List<User> resultList =
                manager.createQuery("Select u From User u", User.class)
                        .getResultList();

        System.out.println("num of users: " + resultList.size());

        for (User u : resultList) {
            System.out.println("next user: " + u.getUsername() + " - " + u.getEmail());
        }
    }

    private  void createEvents() {
        List<User> users =
                manager.createQuery("Select u From User u", User.class)
                        .getResultList();

        if (users.isEmpty()) {
            System.out.println("Aucun user trouvé");
            return;
        }

        User u = users.get(0); // on prend le premier user

        int count = manager
                .createQuery("Select e From Event e", Event.class)
                .getResultList()
                .size();

        if (count == 0) {
            manager.persist(new Event("Conférence Java","description java", u));
            manager.persist(new Event("Workshop Spring", "description spring", u));
            System.out.println("Events créés");
        }
    }

    private void listEvents() {
        List<Event> resultList =
                manager.createQuery("Select e From Event e", Event.class)
                        .getResultList();

        System.out.println("num of events: " + resultList.size());

        for (Event e : resultList) {
            System.out.println(
                    "event: " + e.getName() +
                            " | user: " + e.getUser().getUsername()
            );
        }
    }








}
