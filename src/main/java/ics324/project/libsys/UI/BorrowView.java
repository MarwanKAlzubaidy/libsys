package ics324.project.libsys.UI;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.services.BorrowRecordService;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_ADMIN")
@Route(value = "records",layout = MainLayout.class)
public class BorrowView extends VerticalLayout {
    Grid<BorrowRecord> grid= new Grid<>(BorrowRecord.class);
    TextField filterText = new TextField();
    BorrowRecordService service;

    public BorrowView(BorrowRecordService service) {
        this.service = service;
        setSizeFull();
        configGrid();
        ConfigForm();
        add(grid);
        updateList();
    }

    private void updateList() {
        grid.setItems(service.getALlrecords());

    }

    private void ConfigForm() {
    }

    private void configGrid() {
      grid.setSizeFull();
        grid.setColumns("id","check_out_date","returnDate","status");

      grid.addColumn((customer->customer.getCustomer().getFullName())).setHeader("Customer");
      grid.addColumn(copy->copy.getCopy().getBook()).setHeader("Book");
      grid.addColumn(copy->copy.getCopy().getId()).setHeader("Barcode");

    }
}
