package ics324.project.libsys.UI;

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
import ics324.project.libsys.entities.services.EmployeeService;
import ics324.project.libsys.entities.user.Employee;

@AnonymousAllowed
@Route(value = "employee/reg", layout = MainLayout.class)
public class EmployeeForm extends FormLayout {
    Employee employee = new Employee();
    EmployeeService service;
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField userName = new TextField("Username");

    PasswordField password = new PasswordField("Password");
    EmailField email = new EmailField("email");
    Binder<Employee> binder = new BeanValidationBinder<>(Employee.class);

    Button save = new Button("register");
    Button back = new Button("Back");
    Button delete = new Button("delete by Username");

    public EmployeeForm(EmployeeService service) {
        this.service = service;
        binder.bindInstanceFields(this);
        configerButtons();
        add(firstName, lastName, userName, password, email, save, back,delete);

    }

    private void configerButtons() {
        save.addClickListener(buttonClickEvent -> validateAndSave());
        back.addClickListener(buttonClickEvent -> back());
        delete.addClickListener(buttonClickEvent -> delete());
    }

    private void validateAndSave() {
        try {
            binder.writeBean(employee);
            service.saveEmployee(employee);
            back();
        } catch (ValidationException e) {
            e.printStackTrace();


        } catch (Exception e) {
            Notification notification = Notification.show("DATA integrity ERRor: possible dublicate uniqe value");
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addThemeVariants();
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        }
    }

    private void back() {
        getUI().ifPresent(ui -> ui.navigate("employee"));

    }
    private void delete(){
    Employee employee= service.findByuserName(userName.getValue());

    if(employee == null)
    {Notification notification = Notification.show("NOT found");

    }
    else {
        Notification notification = Notification.show("Employee deleted");
        service.delete(employee);
    }

    }
}