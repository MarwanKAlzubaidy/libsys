package ics324.project.libsys.UI;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.copy.LoanedCopy;
import ics324.project.libsys.entities.copy.OwnedCopy;
import ics324.project.libsys.entities.services.CopyService;
import ics324.project.libsys.enums.Availability;
import ics324.project.libsys.enums.returnStatus;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_ADMIN")
@Route(value = "copy", layout = MainLayout.class)
public class CopyView extends VerticalLayout {
    Grid<OwnedCopy> ownedCopyGrid = new Grid<>(OwnedCopy.class);
    Grid<LoanedCopy> loanedCopyGrid = new Grid<>(LoanedCopy.class);
    ComboBox<Availability> availabilityComboBox1 = new ComboBox<>();
    ComboBox<Availability> availabilityComboBox2 = new ComboBox<>();
    ComboBox<returnStatus> return_statusComboBox = new ComboBox<>();

    CopyService copyService;


    public CopyView(CopyService copyService) {

        this.copyService = copyService;
        setComboboxes();
        gridConfig();
        getContent();
        HorizontalLayout horizontalLayout = new HorizontalLayout(availabilityComboBox2, return_statusComboBox);
        add(availabilityComboBox1, ownedCopyGrid, horizontalLayout, loanedCopyGrid);
    }

    private void setComboboxes() {
        availabilityComboBox1.setItems(Availability.values());
        availabilityComboBox2.setItems(Availability.values());
        return_statusComboBox.setItems(returnStatus.values());

        availabilityComboBox1.addValueChangeListener(comboBoxAvailabilityComponentValueChangeEvent -> avalChange1());
        availabilityComboBox2.addValueChangeListener(comboBoxAvailabilityComponentValueChangeEvent -> avalChange2());
        return_statusComboBox.addValueChangeListener(comboBoxReturn_statusComponentValueChangeEvent -> returnChange());
    }

    private void returnChange() {
        if (!loanedCopyGrid.getSelectedItems().isEmpty()) {
            LoanedCopy loanedCopy = loanedCopyGrid.getSelectedItems().iterator().next();
            if (loanedCopy.getReturnToLibraryStatus() != return_statusComboBox.getValue()) {
                loanedCopy.setReturnToLibraryStatus(return_statusComboBox.getValue());
                copyService.saveLoaned(loanedCopy);
                getContent();

            }
        }

    }

    private void avalChange2() {
        if (!loanedCopyGrid.getSelectedItems().isEmpty()) {
            LoanedCopy loanedCopy = loanedCopyGrid.getSelectedItems().iterator().next();
            if (loanedCopy.getAvailability() != availabilityComboBox2.getValue()) {
                loanedCopy.setAvailability(availabilityComboBox2.getValue());
                copyService.saveLoaned(loanedCopy);
                getContent();

            }
        }
        
        
    }

    private void avalChange1() {
        if (!ownedCopyGrid.getSelectedItems().isEmpty()) {
            OwnedCopy ownedCopy = ownedCopyGrid.getSelectedItems().iterator().next();
            if (ownedCopy.getAvailability() != availabilityComboBox1.getValue()) {
                ownedCopy.setAvailability(availabilityComboBox1.getValue());
                copyService.saveOwend(ownedCopy);
                getContent();

            }
        }
    }

    private void getContent() {
        ownedCopyGrid.setItems(copyService.getAllOwend());
        loanedCopyGrid.setItems(copyService.getAllLoaned());
    }

    private void gridConfig() {

        ownedCopyGrid.setColumns("id", "book", "availability", "purchase_date");
        ownedCopyGrid.addSelectionListener(selectionEvent -> selectionEvent1());
        loanedCopyGrid.setColumns("id", "book", "availability", "returnToLibraryStatus", "library");
        loanedCopyGrid.addSelectionListener(selectionEvent -> selectionEvent2());

    }

    private void selectionEvent2() {
        if (!loanedCopyGrid.getSelectedItems().isEmpty()){
                LoanedCopy loanedCopy = loanedCopyGrid.getSelectedItems().iterator().next();
                availabilityComboBox2.setValue(loanedCopy.getAvailability());
                return_statusComboBox.setValue(loanedCopy.getReturnToLibraryStatus());


        }
    }

    private void selectionEvent1() {
        if (!ownedCopyGrid.getSelectedItems().isEmpty()) {
            OwnedCopy ownedCopy = ownedCopyGrid.getSelectedItems().iterator().next();
            availabilityComboBox1.setValue(ownedCopy.getAvailability());

        }

    }
}
