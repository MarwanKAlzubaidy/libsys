package edu.kfupm.libsys.UI;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import edu.kfupm.libsys.entities.Author;
import edu.kfupm.libsys.entities.services.AuthorService;
import edu.kfupm.libsys.entities.services.EmployeeService;
import edu.kfupm.libsys.entities.user.Employee;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import javax.annotation.security.RolesAllowed;
import java.util.Arrays;

@RolesAllowed("ROLE_ADMIN") //only admin can enter

@AnonymousAllowed
@Route(value = "employee", layout = MainLayout.class)
@PageTitle("employee")
public class EmployeeView extends VerticalLayout {
    Grid<Employee> grid = new Grid<>(Employee.class);
    TextField filterText = new TextField();
    EmployeeService service;


    public EmployeeView(EmployeeService service) {
        this.service = service;

        setSizeFull();
        configureGrid();

        Notification notification = Notification.show(username());
        add(getToolbar(), getContnet());
        updateList();
    }

    private void updateList() {
        grid.setItems(service.findAllEmployee());
    }

    private Component getContnet() {


        HorizontalLayout horizontalLayout = new HorizontalLayout(grid);


        horizontalLayout.setSizeFull();

        return horizontalLayout;
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName","email","userName");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));


    }


    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addEmployee = new Button("Add Employee");
        addEmployee.addClickListener(click -> addEmployee.getUI().ifPresent(ui -> ui.navigate("employee/reg")));

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addEmployee);
        toolbar.addClassName("toolbar");
        return toolbar;
    }


    private String username() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getAuthorities().toString();
        } else {
            return principal.toString();
        }


    }
}
