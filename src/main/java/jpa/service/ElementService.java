package jpa.service;

import jpa.dao.generic.BookDao;
import jpa.dao.generic.ElementDao;
import jpa.dao.generic.MagazineDao;
import jpa.domain.Book;
import jpa.domain.Element;
import jpa.domain.Magazine;
import jpa.dto.BookDto;
import jpa.dto.MagazineDto;

import java.util.List;

public class ElementService {

    private MagazineDao magazineDao = new MagazineDao();
    private BookDao bookDao = new BookDao();
    private ElementDao elementDao = new ElementDao();

    public List<Element> findAll() {
        return elementDao.findAll();
    }

    public Element findById(Long id) {
        return elementDao.findOne(id);
    }
     public Long createMagazine(final MagazineDto magazineDto) {
        Magazine magazine = new Magazine();
        magazine.setTitle(magazineDto.getTitle());
        magazine.setMedia(magazineDto.getMedia());
        magazine.setDatePublication(magazineDto.getDatePublication());

        magazineDao.save(magazine);
        return magazine.getId();
     }

     public Long createBook(final BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setMedia(bookDto.getMedia());
        bookDao.save(book);
        return book.getId();
     }

     public void updateBook(Long id, BookDto bookDto) {
        Book book = bookDao.findOne(id);
        if (book != null) {
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setMedia(bookDto.getMedia());
            bookDao.save(book);
        }
     }

     public void updateMagazine(Long id, MagazineDto magazineDto) {
        Magazine magazine = magazineDao.findOne(id);
        if (magazine != null) {
            magazine.setTitle(magazineDto.getTitle());
            magazine.setMedia(magazineDto.getMedia());
            magazine.setDatePublication(magazineDto.getDatePublication());
            magazineDao.save(magazine);
        }
     }

     public void deleteElement(Long id) {
        Element element = elementDao.findOne(id);
        if (element != null) {
            elementDao.delete(element);
        }
     }

}
