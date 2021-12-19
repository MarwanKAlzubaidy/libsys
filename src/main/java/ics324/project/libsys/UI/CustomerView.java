package ics324.project.libsys.UI;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.services.CustomerService;
import ics324.project.libsys.entities.user.Customer;
import org.hibernate.loader.collection.OneToManyJoinWalker;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Set;

@RolesAllowed("ROLE_ADMIN") //only admin can enter


@Route(value = "Customer", layout = MainLayout.class)
@PageTitle("Customers")
public class CustomerView extends VerticalLayout {
    Grid<Customer> grid = new Grid<>(Customer.class);

    Checkbox enable = new Checkbox("Enabled");
    RadioButtonGroup<String> filterRadioGroup=new RadioButtonGroup<>();

    CustomerService service;


    public CustomerView(CustomerService service) {
        this.service = service;

        setSizeFull();
        configureGrid();

        add(getToolbar(), getContnet());
        updateList();
    }

    private void updateList() {
        if(filterRadioGroup.getValue()==null||filterRadioGroup.getValue().equals("All"))
        grid.setItems(service.findAllCustomer());
        else if(filterRadioGroup.getValue().equals("has more than three book and one more than 120 days"))
            grid.setItems(service.getCust3onelate());
            else if(filterRadioGroup.getValue().equals("Always on Time"))
                grid.setItems(service.custNoFine());
                else if(filterRadioGroup.getValue().equals("new Customers"))
                    grid.setItems(service.getNewCustomer());
    }

    private Component getContnet() {


        HorizontalLayout horizontalLayout = new HorizontalLayout(grid);


        horizontalLayout.setSizeFull();

        return horizontalLayout;
    }

    private void configureGrid() {
        grid.addSelectionListener(selectionEvent -> grid.getSelectedItems().forEach(customer -> enable.setValue(customer.getEnabled())));
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "enabled", "email", "userName","totalPaid","totalNotPaid");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));


    }


    private HorizontalLayout getToolbar() {
        filterRadioGroup.setLabel("choose filter");
        filterRadioGroup.setItems("All","has more than three book and one more than 120 days","Always on Time","new Customers");
        filterRadioGroup.addValueChangeListener(radioButtonGroupStringComponentValueChangeEvent -> updateList());



        enable.addValueChangeListener(checkboxBooleanComponentValueChangeEvent -> custEnb());

        HorizontalLayout toolbar = new HorizontalLayout(filterRadioGroup, enable);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void custEnb() {
        if (!grid.getSelectedItems().isEmpty()) {
            Customer customer = grid.getSelectedItems().iterator().next();
            if (customer.getEnabled() != enable.getValue()) {
                customer.setEnabled(enable.getValue());
                service.save(customer);
                updateList();
            }
        }
    }


}
