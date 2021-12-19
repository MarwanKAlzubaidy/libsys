package ics324.project.libsys.UI;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import ics324.project.libsys.entities.Library;
import ics324.project.libsys.entities.Publisher;
import ics324.project.libsys.entities.services.LibraryService;
import ics324.project.libsys.entities.services.PublisherService;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_ADMIN")
@Route(value = "/publisherList", layout = MainLayout.class)
public class PublisherView extends VerticalLayout {
    Grid<Publisher> grid = new Grid<>(Publisher.class);
    PublisherService service;

    public PublisherView(PublisherService service){
        this.service = service;
        setSizeFull();
        configureGrid();

        Button addPublisher = new Button("Add Publisher");
        addPublisher.addClickListener(click -> addPublisher.getUI().ifPresent(ui -> ui.navigate("publisher/add")));

        add(addPublisher, getContent());
        updateList();
    }

    private void updateList() { grid.setItems(service.getAllPublishers()); }

    private Component getContent() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid);
        horizontalLayout.setSizeFull();
        return horizontalLayout;
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "name", "location", "phone", "books");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
