package ics324.project.libsys.UI;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.services.CustomerService;
import ics324.project.libsys.entities.user.Customer;
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
    TextField filterText = new TextField();
    Checkbox enable = new Checkbox("Enabled");

    CustomerService service;


    public CustomerView(CustomerService service) {
        this.service = service;

        setSizeFull();
        configureGrid();

        add(getToolbar(), getContnet());
        updateList();
    }

    private void updateList() {

        grid.setItems(service.findAllCustomer());
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
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        enable.addValueChangeListener(checkboxBooleanComponentValueChangeEvent -> custEnb());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, enable);
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
