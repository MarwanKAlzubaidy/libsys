package edu.kfupm.libsys.UI;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import edu.kfupm.libsys.entities.Author;
import edu.kfupm.libsys.entities.Book;
import edu.kfupm.libsys.entities.services.AuthorService;
import edu.kfupm.libsys.entities.services.BookService;
import org.vaadin.gatanaso.MultiselectComboBox;

import javax.annotation.security.RolesAllowed;


//Publisher is not Added
@RolesAllowed("ROLE_ADMIN")
@Route(value = "/book/new", layout = MainLayout.class)
public class BookForm extends FormLayout {
    Book book = new Book();
    AuthorService authorService;
    BookService bookService;
    // PublisherService;

    TextField title = new TextField("Title");
    TextField ISBN = new TextField("ISBN");
    TextField edition = new TextField("Edition");
    NumberField price = new NumberField("Price");
    IntegerField shelf_num = new IntegerField("shelf number");
    DatePicker publishDate = new DatePicker("Publish_Date");
    MultiselectComboBox<Author> authors = new MultiselectComboBox<Author>("Authors");
    Button save = new Button("Save");
    Button back = new Button("Back");
    Binder<Book> binder = new BeanValidationBinder<>(Book.class);

    public BookForm(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
        authors.setRequired(true);
        binder.bindInstanceFields(this);
        configerButton();
        authors.setItems(authorService.findAllAuthor());

        add(title, ISBN, edition, price, shelf_num, publishDate, authors);
        HorizontalLayout hl = new HorizontalLayout(save, back);
        add(hl);


    }


    private void validateAndSave() {
        try {
            binder.writeBean(book);
            bookService.saveBook(book);
            back();
        } catch (ValidationException e) {



        } catch (Exception e) {
            Notification notification = Notification.show("BOOK has the same ISBN ");
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addThemeVariants();
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        }
    }

    public void configerButton() {
        save.addClickListener(buttonClickEvent -> validateAndSave());
        back.addClickListener(buttonClickEvent -> back());


    }

    private void back() {
        UI.getCurrent().navigate("");
    }


}
