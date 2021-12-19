package ics324.project.libsys.UI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.services.BookService;
import ics324.project.libsys.entities.services.BorrowRecordService;
import ics324.project.libsys.entities.services.CustomerService;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.entities.user.MyUserDetail;
import ics324.project.libsys.enums.Status;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.gatanaso.MultiselectComboBox;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.List;

@Route(value = "Renew", layout = MainLayout.class)
@RolesAllowed("ROLE_USER")
public class Renew extends FormLayout {
    ComboBox<BorrowRecord> recordComboBox=new ComboBox<BorrowRecord>();

    Button extend=new Button("Extend");
    Customer customer;
    BorrowRecordService borrowRecordService;
    CustomerService customerService;
    public Renew(BorrowRecordService borrowRecordService,CustomerService customerService) {
        this.borrowRecordService = borrowRecordService;
        this.customerService=customerService;
        customer=customerService.findByUsername(((MyUserDetail) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        recordComboBox.setLabel("renewed Books");
        recordComboBox.setItems(borrowRecordService.findByCustomerAndStatusAndExtended(customer, Status.NOT_DUE,false));
        configEvents();
        add(recordComboBox,extend);
    }

    private void configEvents() {
        recordComboBox.addValueChangeListener(multiselectComboBoxSetComponentValueChangeEvent -> bookRenew());
        extend.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(""));
    }

    private void bookRenew() {
        BorrowRecord borrowRecord = null;
        if(recordComboBox.getValue()!=null){
            borrowRecord = recordComboBox.getValue();
            borrowRecord.setExtended(true);
            borrowRecord.setReturnDate(borrowRecord.getReturnDate().plusDays(20));
            try {
                borrowRecordService.saveExtend(borrowRecord);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
