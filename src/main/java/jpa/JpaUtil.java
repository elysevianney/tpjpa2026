package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("dev"); // <-- même nom que persistence.xml

    public static EntityManager em() {
        return emf.createEntityManager();
    }
}
