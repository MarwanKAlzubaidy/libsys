package ics324.project.libsys.UI;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.services.BorrowRecordService;
import ics324.project.libsys.entities.services.CustomerService;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.entities.user.MyUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_USER")
@Route(value = "History", layout = MainLayout.class)
public class HistoryView extends VerticalLayout {
    Grid<BorrowRecord> grid = new Grid<>(BorrowRecord.class);
    TextField filterText = new TextField();
    BorrowRecordService service;
    CustomerService customerService;


    public HistoryView(BorrowRecordService service, CustomerService customerService) {
        this.service = service;
        this.customerService = customerService;
        setSizeFull();
        configGrid();
        ConfigForm();
        add(grid);
        updateList();
    }

    private void updateList() {
        Customer customer = customerService.findByUsername(((MyUserDetail) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        grid.setItems(service.getALlrecordsByCustomer(customer));

    }

    private void ConfigForm() {
    }

    private void configGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "check_out_date", "returnDate", "status");

        grid.addColumn((customer -> customer.getCustomer().getFullName())).setHeader("Customer");
        grid.addColumn(copy -> copy.getCopy().getBook()).setHeader("Book");
        grid.addColumn(copy -> copy.getCopy().getId()).setHeader("Barcode");

    }
}
