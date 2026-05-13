package jpa.dao.generic;

import jakarta.persistence.NoResultException;
import jpa.domain.People;

public class PeopleDao extends AbstractJpaDao<Long, People> {
    public PeopleDao() {
        super(People.class);
    }

    public People findByEmail(String email) {
        try {
            return entityManager
                    .createQuery("select p from People p where p.email = :email", People.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}