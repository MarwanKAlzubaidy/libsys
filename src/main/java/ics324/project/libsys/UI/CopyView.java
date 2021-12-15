package ics324.project.libsys.UI;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.copy.LoanedCopy;
import ics324.project.libsys.entities.copy.OwnedCopy;
import ics324.project.libsys.entities.services.CopyService;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_ADMIN")
@Route(value = "copy",layout = MainLayout.class)
public class CopyView extends VerticalLayout {
    Grid<OwnedCopy> ownedCopyGrid=new Grid<>(OwnedCopy.class);
    Grid<LoanedCopy> loanedCopyGrid =new Grid<>(LoanedCopy.class);
    CopyService copyService;


    public CopyView(CopyService copyService) {
        this.copyService = copyService;
        gridConfig();
        getContent();
        add(ownedCopyGrid,loanedCopyGrid);
    }

    private void getContent() {
        ownedCopyGrid.setItems(copyService.getAllOwend());
        loanedCopyGrid.setItems(copyService.getAllLoaned());
    }

    private void gridConfig() {
  //      ownedCopyGrid.setSizeFull();
      //  loanedCopyGrid.setSizeFull();
        ownedCopyGrid.setColumns("id","book","availability","purchase_date");
        loanedCopyGrid.setColumns("id","book","availability","returnToLibraryStatus","library");

    }
}
