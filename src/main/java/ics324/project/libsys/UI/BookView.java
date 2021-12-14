package ics324.project.libsys.UI;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.services.BookService;

import java.util.Arrays;

@AnonymousAllowed
@Route(value = "/book",layout = MainLayout.class)
public class BookView  extends VerticalLayout {
    Grid<Book> grid =new Grid<>(Book.class);
    TextField filterText = new TextField();
    BookService service;
    public BookView(BookService service){
        this.service=service;
        setSizeFull();
        configureGrid();
        add(grid);
        updateList();
    }

    private void updateList() {
        grid.setItems(service.getAllbooks());
    }

    private void configureGrid() {

        grid.setSizeFull();
        grid.setColumns("title", "edition","ISBN","price","shelf_num","copies_count","publisher");
        grid.addColumn(book -> book.getPublishDate().toString()).setHeader("Publish Date");
        grid.addColumn(book -> Arrays.toString(book.getAuthors().toArray())).setHeader("Authors");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));


    }

}
