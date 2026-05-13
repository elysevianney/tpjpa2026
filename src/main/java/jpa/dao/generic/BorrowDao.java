package jpa.dao.generic;

import jpa.domain.Borrow;

import java.util.List;

public class BorrowDao extends AbstractJpaDao<Long, Borrow> {
    public BorrowDao() {
        super(Borrow.class);
    }

    public List<Borrow> findByUserId(Long userId) {
        return entityManager
                .createQuery("select b from Borrow b where b.user.id = :userId", Borrow.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
