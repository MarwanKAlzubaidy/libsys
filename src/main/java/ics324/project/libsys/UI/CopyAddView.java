package ics324.project.libsys.UI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;

import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.Library;
import ics324.project.libsys.entities.copy.LoanedCopy;
import ics324.project.libsys.entities.copy.OwnedCopy;
import ics324.project.libsys.entities.services.BookService;
import ics324.project.libsys.entities.services.CopyService;
import ics324.project.libsys.entities.services.LibraryService;
import ics324.project.libsys.enums.Availability;
import ics324.project.libsys.enums.returnStatus;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//this is for adding owned copy
@Route(value = "copy/add", layout = MainLayout.class)
@RolesAllowed("ROLE_ADMIN")
public class CopyAddView extends FormLayout {
    OwnedCopy ownedCopy;
    BookService bookService;
    CopyService copyService;
    LibraryService libraryService;
    ComboBox<Library> libraryComboBox = new ComboBox<>("Library keep empty if owned");


    IntegerField numberOfCopies = new IntegerField("Number of Copies");

    ComboBox<Book> book = new ComboBox();

    Button add = new Button("Add");
    Button back = new Button("Back");

    public CopyAddView(BookService bookService, CopyService copyService, LibraryService libraryService) {
        this.libraryService = libraryService;
        this.bookService = bookService;
        this.copyService = copyService;
        config();
        add(book, numberOfCopies, add, back, libraryComboBox);
    }

    private void config() {
        libraryComboBox.setItems(libraryService.getAllLibraries());
        back.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("copy"));
        add.addClickListener(buttonClickEvent -> AddOwnedCopy());
        book.setRequired(true);
        book.setItems(bookService.getAllbooks());
        numberOfCopies.setMin(1);
        numberOfCopies.setRequiredIndicatorVisible(true);
        numberOfCopies.setHasControls(true);
        numberOfCopies.setValue(1);
        numberOfCopies.addValueChangeListener(integerFieldIntegerComponentValueChangeEvent -> to1());
        ;

    }

    private void to1() {
        if (numberOfCopies.getValue() == null || numberOfCopies.getValue() < 1)
            numberOfCopies.setValue(1);
    }

    private void AddOwnedCopy() {
        if (libraryComboBox.getValue() == null) {
            List<OwnedCopy> ownedCopyList = new ArrayList<>();
            for (int i = 1; i <= numberOfCopies.getValue(); i++) {
                ownedCopy = new OwnedCopy();
                ownedCopy.setBook(book.getValue());
                ownedCopy.setAvailability(Availability.AVAILABLE);
                ownedCopy.setPurchase_date(LocalDate.now());
                ownedCopyList.add(ownedCopy);


            }
            copyService.saveOwendList(ownedCopyList);
        } else {
            List<LoanedCopy> loanedCopyList = new ArrayList<>();
            for (int i = 1; i <= numberOfCopies.getValue(); i++) {
                LoanedCopy loanedCopy = new LoanedCopy();
                loanedCopy.setBook(book.getValue());

                loanedCopy.setAvailability(Availability.AVAILABLE);
                loanedCopy.setReturnToLibraryStatus(returnStatus.NOT_RETURNED);
                loanedCopy.setLibrary(libraryComboBox.getValue());
                loanedCopyList.add(loanedCopy);


            }
            copyService.saveLoanedList(loanedCopyList);
        }

    }
}
