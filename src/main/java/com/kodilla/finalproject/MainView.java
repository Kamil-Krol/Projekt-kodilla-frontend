package com.kodilla.finalproject;

import com.kodilla.finalproject.domain.Book;
import com.kodilla.finalproject.service.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private BookForm form = new BookForm(this);
    private BookService bookService = BookService.getInstance();
    private Grid<Book> grid = new Grid<>(Book.class);
    private TextField filter = new TextField();
    private Button addNewBook = new Button("Add new book");

    public MainView() {
        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("title", "author", "publicationYear", "type");

        addNewBook.addClickListener(e -> {
            grid.asSingleSelect().clear();
            bookService.setBook(new Book());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBook);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        bookService.setBook(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> bookService.setBook(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(bookService.getBooks());
    }

    private void update() {
        grid.setItems(bookService.findByTitle(filter.getValue()));
    }

}
