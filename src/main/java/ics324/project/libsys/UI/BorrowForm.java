package ics324.project.libsys.UI;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.entities.services.BookService;
import ics324.project.libsys.entities.services.BorrowRecordService;
import ics324.project.libsys.entities.services.CopyService;
import ics324.project.libsys.entities.services.CustomerService;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.enums.Availability;
import ics324.project.libsys.enums.Status;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;

@RolesAllowed("ROLE_ADMIN")
@Route(value = "records/add",layout = MainLayout.class)
public class BorrowForm extends FormLayout {
    BorrowRecord borrowRecord;
    BookService bookService;
    CopyService copyService;
    CustomerService customerService;
    BorrowRecordService borrowRecordService;
    DatePicker datePicker=new DatePicker("return date");
    ComboBox<Book> bookComboBox=new ComboBox<Book>("Book title and isbn");
    ComboBox<Copy> copyComboBox=new ComboBox<Copy>("barcode");
    ComboBox<Customer> customerComboBox=new ComboBox<>("Customer name");
    Button save=new Button("Save");
    Button back=new Button("Back");


    public BorrowForm(BookService bookService, CopyService copyService, CustomerService customerService,BorrowRecordService borrowRecordService) {
        this.bookService = bookService;
        this.copyService = copyService;
        this.customerService = customerService;
        this.borrowRecordService=borrowRecordService;
        add(bookComboBox,copyComboBox,customerComboBox,datePicker,save,back);
        bookComboBox.setItems(bookService.getAllbooks());
        copyComboBox.setEnabled(false);
        customerComboBox.setEnabled(false);
        save.setEnabled(false);
        configerButtonsAndCombo();
    }

    private void configerButtonsAndCombo() {
        datePicker.setRequired(true);
        datePicker.setMin(LocalDate.now().plusDays(1));
        datePicker.setMax(LocalDate.now().plusDays(90));
        bookComboBox.addValueChangeListener(comboBoxBookComponentValueChangeEvent -> bookCombo());
        copyComboBox.addValueChangeListener(comboBoxCopyComponentValueChangeEvent -> copyCombo());
        customerComboBox.addValueChangeListener(comboBoxCustomerComponentValueChangeEvent -> customerCombo());
        save.addClickListener(buttonClickEvent -> save());
        back.addClickListener(buttonClickEvent -> back());

    }

    private void customerCombo()
    {
        if(customerComboBox.getValue()==null){
            save.setEnabled(false);

        }
        save.setEnabled(true);
    }

    private void copyCombo() {
        if(copyComboBox.getValue()==null)
        {   customerComboBox.setItems();
            customerComboBox.setEnabled(false);
            save.setEnabled(false);
        }
        else {
            customerComboBox.setEnabled(true);
            customerComboBox.setItemLabelGenerator(customer -> customer.getFullName());
            customerComboBox.setItems(customerService.CustomersAvalibleForBorrow());

        }
    }

    private void bookCombo() {
        if(bookComboBox.getValue()==null)
        {
            copyComboBox.setItems();
            customerComboBox.setItems();
            copyComboBox.setEnabled(false);
            customerComboBox.setEnabled(false);
            save.setEnabled(false);

        }
        else{
            copyComboBox.setEnabled(true);
            copyComboBox.setItemLabelGenerator( copy -> copy.getId().toString());
            copyComboBox.setItems(copyService.getAllbyBook(bookComboBox.getValue(), Availability.AVAILABLE));




        }
    }

    private void back() {
        UI.getCurrent().navigate("records");
    }

    private void save() {
        borrowRecord=new BorrowRecord();
        borrowRecord.setStatus(Status.NOT_DUE);
        borrowRecord.setCopy(copyComboBox.getValue());
        borrowRecord.setCustomer(customerComboBox.getValue());
        borrowRecord.setCheck_out_date(LocalDate.now());
        borrowRecord.setReturn_date(datePicker.getValue());
        try {
            borrowRecordService.save(borrowRecord);
        } catch (Exception e) {
            Notification notification= Notification.show(e.getMessage());
            notification.setPosition(Notification.Position.MIDDLE);


        }
        bookComboBox.clear();


    }
}
