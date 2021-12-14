package ics324.project.libsys.UI;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import ics324.project.libsys.entities.Author;

public class AuthorForm extends FormLayout {
    private Author author;
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    DatePicker b_date = new DatePicker("Birth date");
    Binder<Author> binder = new BeanValidationBinder<>(Author.class);
    Button save = new Button("Save");
    Button delete = new Button("delete");
    Button close = new Button("cancel");

    public AuthorForm() {
        binder.bindInstanceFields(this);

        add(firstName, lastName, b_date, createButtonsLayout());


    }

    public void setAuthor(Author Author) {
        this.author = Author;
        binder.readBean(author);


    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, author)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(author);
            fireEvent(new SaveEvent(this, author));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events

    public static abstract class AuthorFormEvent extends ComponentEvent<AuthorForm> {
        private Author author;

        protected AuthorFormEvent(AuthorForm source, Author author) {
            super(source, false);
            this.author = author;
        }

        public Author getAuthor() {
            return author;
        }
    }

    public static class SaveEvent extends AuthorFormEvent {
        SaveEvent(AuthorForm source, Author author) {
            super(source, author);
        }
    }

    public static class DeleteEvent extends AuthorFormEvent {
        DeleteEvent(AuthorForm source, Author author) {
            super(source, author);
        }

    }

    public static class CloseEvent extends AuthorFormEvent {
        CloseEvent(AuthorForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
