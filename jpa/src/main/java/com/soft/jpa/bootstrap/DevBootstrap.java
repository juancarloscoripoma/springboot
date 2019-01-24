package com.soft.jpa.bootstrap;

import com.soft.jpa.model.Author;
import com.soft.jpa.model.Book;
import com.soft.jpa.model.Publisher;
import com.soft.jpa.repository.AuthorRepository;
import com.soft.jpa.repository.BookRepository;
import com.soft.jpa.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by jcarlos on 1/23/2019.
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Publisher publisher = new Publisher();
        publisher.setName("foo");
        publisher.setAddress("petro");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book book = new Book("Domain driven Design", "1234", publisher);
        eric.getBookSet().add(book);
        book.getAuthorSet().add(eric);

        authorRepository.save(eric);
        bookRepository.save(book);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "234444", publisher);
        rod.getBookSet().add(noEJB);
        noEJB.getAuthorSet().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
