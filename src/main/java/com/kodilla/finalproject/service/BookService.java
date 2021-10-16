package com.kodilla.finalproject.service;

import com.kodilla.finalproject.BookType;
import com.kodilla.finalproject.domain.Book;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BookService extends FormLayout {

    private Set<Book> books;
    private static BookService bookService;
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<Book> binder = new Binder<Book>(Book.class);

    private BookService() {
        this.books = exampleData();
    }

    public static BookService getInstance() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }

    public Set<Book> getBooks() {
        return new HashSet<>(books);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    private Set<Book> exampleData() {
        Set<Book> books = new HashSet<>();
        books.add(new Book("Cracking the Coding Interview: 189 Programming Questions and Solutions", "Gayle Laakmann McDowell",  "2015", BookType.IT));
        books.add(new Book("Introduction to Algorithms", "The MIT Press", "2009", BookType.IT));
        books.add(new Book("Introduction to the Theory of Computation", "Michael Sipser", "2012", BookType.IT));
        books.add(new Book("Operating System Concepts", "Silberschatz, Galvin, Gagne", "2012", BookType.IT));
        books.add(new Book("The Silent Patient", "Alex Michaelides", "2019", BookType.THRILLER));
        books.add(new Book("The Handmaid's Tale", "Margaret Atwood", "2019", BookType.CLASSIC));
        books.add(new Book("Watch Us Rise", "Renée Watson,  Ellen Hagan", "2019", BookType.POETRY));
        return books;
    }

    public void save(Book book) {
        this.books.add(book);
    }

    public void delete(Book book) {
        this.books.remove(book);
    }

    public void setBook(Book book) {
        binder.setBean(book);

        if (book == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //focus();
        }
    }

    public Set<Book> findByTitle(String title) {
        return books.stream().filter(x -> x.getTitle().contains(title)).collect(Collectors.toSet());
    }
}