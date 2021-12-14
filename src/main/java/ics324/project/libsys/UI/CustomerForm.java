package ics324.project.libsys.UI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import ics324.project.libsys.entities.services.CustomerService;
import ics324.project.libsys.entities.user.Customer;
import org.springframework.security.core.context.SecurityContextHolder;

@AnonymousAllowed

@Route(value = "register", layout = MainLayout.class)
public class CustomerForm extends FormLayout {
    Customer customer = new Customer();
    CustomerService service;
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField userName = new TextField("Username");

    PasswordField password = new PasswordField("Password");
    EmailField email = new EmailField("email");
    Binder<Customer> binder = new BeanValidationBinder<>(Customer.class);

    Button save = new Button("register");
    Button back = new Button("Back");


    public CustomerForm(CustomerService service) {

        this.service = service;
        binder.bindInstanceFields(this);
        configerButtons();
        add(firstName, lastName, userName, password, email, save, back);
        if( SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()!="anonymousUser")
        {   back();
            Notification notification=Notification.show("YOU ARE ALREADY SIGNED IN");

            notification.setPosition(Notification.Position.MIDDLE);

            }
    }

    private void configerButtons() {
        save.addClickListener(buttonClickEvent -> validateAndSave());
        back.addClickListener(buttonClickEvent -> back());

    }

    private void validateAndSave() {
        try {
            binder.writeBean(customer);
            service.saveCustomer(customer);
            back();
        } catch (ValidationException e) {
            e.printStackTrace();


        } catch (Exception e) {
            Notification notification = Notification.show("User has the same USERNAME or EMAIL");
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addThemeVariants();
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        }
    }

    private void back() {
        UI.getCurrent().navigate("/");

    }

}