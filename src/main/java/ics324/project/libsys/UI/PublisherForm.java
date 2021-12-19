package ics324.project.libsys.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.Publisher;
import ics324.project.libsys.entities.services.PublisherService;

import javax.annotation.security.RolesAllowed;

@Route(value = "publisher/add", layout = MainLayout.class)
@RolesAllowed("ROLE_ADMIN")
public class PublisherForm extends FormLayout {
    private Publisher publisher;
    TextField name = new TextField("name");
    TextField location = new TextField("location");
    TextField phone = new TextField("phone number");
    Binder<Publisher> binder = new BeanValidationBinder<>(Publisher.class);
    Button save=new Button("Save");
    PublisherService publisherService;

    public PublisherForm(PublisherService publisherService) {
        binder.bindInstanceFields(this);

        this.publisherService = publisherService;
        save.addClickListener(buttonClickEvent -> save());
        add(name,location,phone,save);


    }

    private void save() {
        try {
            publisher=new Publisher();
            binder.writeBean(publisher);
            publisherService.save(publisher);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
}
