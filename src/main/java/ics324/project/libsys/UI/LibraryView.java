package ics324.project.libsys.UI;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.Library;
import ics324.project.libsys.entities.services.BookService;
import ics324.project.libsys.entities.services.LibraryService;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_ADMIN")
@Route(value = "/libraryList", layout = MainLayout.class)
public class LibraryView extends VerticalLayout {
    Grid<Library> grid = new Grid<>(Library.class);
    LibraryService service;

    public LibraryView(LibraryService service){
        this.service = service;
        setSizeFull();
        configureGrid();

        Button addLibrary = new Button("Add Library");
        addLibrary.addClickListener(click -> addLibrary.getUI().ifPresent(ui -> ui.navigate("/libraryAdd")));

        add(addLibrary, getContent());
        updateList();
    }

    private void updateList() { grid.setItems(service.getAllLibraries()); }

    private Component getContent() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid);
        horizontalLayout.setSizeFull();
        return horizontalLayout;
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "name", "location");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
