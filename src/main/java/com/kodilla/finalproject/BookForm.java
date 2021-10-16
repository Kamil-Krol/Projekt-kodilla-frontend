package com.kodilla.finalproject;

import com.kodilla.finalproject.domain.Book;
import com.kodilla.finalproject.service.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class BookForm extends FormLayout {

    private TextField title = new TextField("Title");
    private TextField author = new TextField("Author");
    private TextField publicationYear = new TextField("Publication year");
    private ComboBox<BookType> type = new ComboBox<>("Book type");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<Book> binder = new Binder<Book>(Book.class);
    private MainView mainView;
    private BookService service = BookService.getInstance();

    public BookForm() {
        type.setItems(BookType.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(title, author, publicationYear, type, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public BookForm(MainView mainView) {
        this.mainView = mainView;
    }

    private void save() {
        Book book = binder.getBean();
        service.save(book);
        mainView.refresh();
        service.setBook(null);
    }

    private void delete() {
        Book book = binder.getBean();
        service.delete(book);
        mainView.refresh();
        service.setBook(null);
    }
}
