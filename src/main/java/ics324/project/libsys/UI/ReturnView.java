package ics324.project.libsys.UI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.services.BorrowRecordService;
import ics324.project.libsys.entities.services.CustomerService;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.enums.Status;
import org.vaadin.gatanaso.MultiselectComboBox;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Route(value = "return", layout = MainLayout.class)
@RolesAllowed("ROLE_ADMIN")
public class ReturnView extends FormLayout {
   // MultiselectComboBox<BorrowRecord>  = new MultiselectComboBox<BorrowRecord>("returns");
    MultiSelectListBox<BorrowRecord> borrowRecordMultiselectComboBox= new MultiSelectListBox();


    ComboBox<Customer> customerComboBox = new ComboBox<>("Customer");
    Button save = new Button("Save");
    Button back = new Button("Back");
    CustomerService customerService;
    BorrowRecordService borrowRecordService;

    public ReturnView(CustomerService customerService, BorrowRecordService borrowRecordService) {

        this.customerService = customerService;
        this.borrowRecordService = borrowRecordService;
        config();
        add(customerComboBox, borrowRecordMultiselectComboBox, save, back);

    }

    private void config() {
        customerComboBox.setItemLabelGenerator(customer -> customer.getFullName());
        customerComboBox.setItems(customerService.findAllCustomer());
        customerComboBox.addValueChangeListener(comboBoxCustomerComponentValueChangeEvent -> customerEvent());
        borrowRecordMultiselectComboBox.addValueChangeListener(multiselectComboBoxSetComponentValueChangeEvent -> BorrowComboEvent());
        back.addClickListener(buttonClickEvent -> back());
        save.addClickListener(buttonClickEvent -> save());

        borrowRecordMultiselectComboBox.setEnabled(false);

    }

    private void save() {
        borrowRecordService.saveMultiReturns(borrowRecordMultiselectComboBox.getValue());

    }

    private void BorrowComboEvent() {
        if (borrowRecordMultiselectComboBox.getValue().size()==0) {
            save.setEnabled(false);
        } else {
            save.setEnabled(true);
        }
    }

    private void customerEvent() {
        if (customerComboBox.getValue() == null) {
            borrowRecordMultiselectComboBox.setEnabled(false);
            save.setEnabled(false);

        } else {
            borrowRecordMultiselectComboBox.setEnabled(true);
            List<BorrowRecord> borrowRecords=borrowRecordService.findByCustomerAndStatusNot(customerComboBox.getValue(), Status.RETURNED);

            borrowRecordMultiselectComboBox.setItems(borrowRecords);

        }
    }

    private void back() {
        UI.getCurrent().navigate("");
    }
}
