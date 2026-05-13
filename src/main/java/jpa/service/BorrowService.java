package jpa.service;

import jpa.dao.generic.BorrowDao;
import jpa.domain.Borrow;
import jpa.domain.BorrowStatus;
import jpa.domain.Element;
import jpa.domain.User;

import java.time.LocalDate;
import java.util.List;

public class BorrowService {

    private BorrowDao borrowDao = new BorrowDao();

    public List<Borrow> findAll() {
        return borrowDao.findAll();
    }

    public Borrow findById(Long id) {
        return borrowDao.findOne(id);
    }

    public List<Borrow> findByUserId(Long userId) {
        return borrowDao.findByUserId(userId);
    }

    public Long createBorrow(User user, Element item) {
        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setItem(item);

        borrow.setStatus(BorrowStatus.IN_PROGRESS);
        borrowDao.save(borrow);
        return borrow.getId();
    }

    public void updateBorrow(Long id, LocalDate borrowDate, LocalDate dueDate,  BorrowStatus status) {
        Borrow borrow = borrowDao.findOne(id);
        if (borrow != null) {
            if (borrowDate != null) {
                borrow.setBorrowDate(borrowDate);
            }
            if (dueDate != null) {
                borrow.setDueDate(dueDate);
            }
            if (status != null) {
                borrow.setStatus(status);
                if (status == BorrowStatus.RETURNED) {
                    borrow.setReturnDate(LocalDate.now());
                }
            }
            borrowDao.update(borrow);
        }
    }

    public void deleteBorrow(Long id) {
        borrowDao.deleteById(id);
    }
}
