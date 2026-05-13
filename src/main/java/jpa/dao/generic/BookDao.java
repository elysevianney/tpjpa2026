package jpa.dao.generic;

import jpa.domain.Book;
import jpa.domain.Magazine;

public class BookDao extends AbstractJpaDao<Long, Book>{
    public BookDao() {
        super(Book.class);
    }
}
