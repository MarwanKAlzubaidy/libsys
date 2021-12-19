package ics324.project.libsys.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;

import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.copy.OwnedCopy;
import ics324.project.libsys.entities.services.BookService;
import ics324.project.libsys.entities.services.CopyService;
import ics324.project.libsys.enums.Availability;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//this is for adding owned copy
@Route(value = "copy/add",layout = MainLayout.class)
@RolesAllowed("ROLE_ADMIN")
public class CopyAddView    extends FormLayout {
    OwnedCopy ownedCopy;
    BookService bookService;
    CopyService copyService;


    IntegerField numberOfCopies=new IntegerField("Number of Copies");

    ComboBox<Book> book=new ComboBox();

    Button add=new Button("Add");
    Button back=new Button("Back");

    public CopyAddView(BookService bookService, CopyService copyService) {

        this.bookService = bookService;
        this.copyService = copyService;
        config();
        add(book,numberOfCopies,add,back);
    }

    private void config() {
        add.addClickListener(buttonClickEvent -> AddOwnedCopy());
        book.setRequired(true);
        book.setItems(bookService.getAllbooks());
        numberOfCopies.setMin(1);
        numberOfCopies.setRequiredIndicatorVisible(true);
        numberOfCopies.setHasControls(true);
        numberOfCopies.setValue(1);
        numberOfCopies.addValueChangeListener(integerFieldIntegerComponentValueChangeEvent -> to1());;

    }

    private void to1() {
        if(numberOfCopies.getValue()==null||numberOfCopies.getValue()<1)
            numberOfCopies.setValue(1);
    }

    private void AddOwnedCopy() {
        List<OwnedCopy> ownedCopyList=new ArrayList<>();
        for (int i=1;i<=numberOfCopies.getValue();i++){
            ownedCopy=new OwnedCopy();
            ownedCopy.setBook(book.getValue());
            ownedCopy.setAvailability(Availability.AVAILABLE);
            ownedCopy.setPurchase_date(LocalDate.now());
            ownedCopyList.add(ownedCopy);


        }
        copyService.saveOwendList(ownedCopyList);

    }
}
