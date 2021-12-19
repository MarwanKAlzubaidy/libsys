package ics324.project.libsys.UI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.Library;
import ics324.project.libsys.entities.services.LibraryService;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_ADMIN")
@Route(value = "/libraryAdd", layout = MainLayout.class)
public class LibraryForm extends FormLayout {
    Library library = new Library();
    LibraryService libraryService;

    ComboBox<Library> libraryComboBox = new ComboBox<>("Library");

    TextField name = new TextField("Library Name");
    TextField location = new TextField("Location");

    Binder<Library> binder = new BeanValidationBinder<>(Library.class);

    Button save = new Button("Save");
    Button back = new Button("Back");


    public LibraryForm(LibraryService libraryService) {
        this.libraryService = libraryService;
        binder.bindInstanceFields(this);
        configerButton();
        add(name, location);
        HorizontalLayout hl = new HorizontalLayout(save, back);
        add(hl);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(library);
            if (libraryComboBox.getValue() != null)
                library.setId(libraryComboBox.getValue().getId());
            libraryService.saveLibrary(library);
            back();
        } catch (ValidationException e) {


        } catch (Exception e) {
            Notification notification = Notification.show("");
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addThemeVariants();
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        }
    }

    public void configerButton() {
        libraryComboBox.setItems(libraryService.getAllLibraries());
        libraryComboBox.addValueChangeListener(comboBoxBookComponentValueChangeEvent -> selectLibrary());
        save.addClickListener(buttonClickEvent -> validateAndSave());
        back.addClickListener(buttonClickEvent -> back());



    }

    private void selectLibrary() {
        if (libraryComboBox.getValue() != null)

            binder.readBean(libraryComboBox.getValue());

        else

            binder.readBean(new Library());

    }


    private void back() {
        UI.getCurrent().navigate("libraryList");
    }


}

