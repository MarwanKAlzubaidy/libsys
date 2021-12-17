package ics324.project.libsys.UI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.services.BookService;
import ics324.project.libsys.entities.services.CustomerService;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.entities.user.MyUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.gatanaso.MultiselectComboBox;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_USER")
@Route(value = "reserve",layout= MainLayout.class)
public class reserveView extends FormLayout {
    MultiselectComboBox<Book> bookMultiselectComboBox=new MultiselectComboBox<Book>();

    Button back=new Button("Back");
    Customer customer;
    BookService bookService;
    CustomerService customerService;
    public reserveView(BookService bookService,CustomerService customerService) {
        this.bookService = bookService;
        this.customerService=customerService;
        customer=customerService.findByUsername(((MyUserDetail) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        bookMultiselectComboBox.setLabel("reserved Books");
        bookMultiselectComboBox.setItems(bookService.getBookNotAvalible());
        bookMultiselectComboBox.setValue(customer.getBooks());
        configEvents();
        add(bookMultiselectComboBox,back);
    }

    private void configEvents() {
        bookMultiselectComboBox.addValueChangeListener(multiselectComboBoxSetComponentValueChangeEvent -> bookReserve());
        back.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(""));
    }

    private void bookReserve() {
        customer.setBooks(bookMultiselectComboBox.getSelectedItems());
        customerService.save(customer);

    }
}
