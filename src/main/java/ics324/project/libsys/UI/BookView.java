package ics324.project.libsys.UI;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import ics324.project.libsys.entities.Author;
import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.services.AuthorService;
import ics324.project.libsys.entities.services.BookService;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.util.Arrays;

@AnonymousAllowed
@Route(value = "/book",layout = MainLayout.class)
public class BookView  extends VerticalLayout {
    Grid<Book> grid =new Grid<>(Book.class);
    TextField filterText = new TextField("Title");
    DatePicker datePicker= new DatePicker("Publish Date");
    MultiselectComboBox<Author> authorMultiselectComboBox=new MultiselectComboBox<>();

    BookService service;
    AuthorService authorService;
    public BookView(BookService service,AuthorService authorService){
        authorMultiselectComboBox.setLabel("Authors");
        this.service=service;
        this.authorService=authorService;
        configInput();
        setSizeFull();
        configureGrid();

        add(new HorizontalLayout(filterText,datePicker,authorMultiselectComboBox),grid);
        updateList();
    }

    private void configInput() {
        authorMultiselectComboBox.setItems( authorService.findAllAuthor());
        authorMultiselectComboBox.addValueChangeListener(multiselectComboBoxSetComponentValueChangeEvent -> filterChange());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(textFieldStringComponentValueChangeEvent ->  filterChange());
        datePicker.addValueChangeListener(datePickerLocalDateComponentValueChangeEvent -> filterChange());

    }

    private void filterChange() {
        updateList();
    }

    private void updateList() {
        grid.setItems(service.getfilterdBooks(filterText.getValue(),datePicker.getValue(),authorMultiselectComboBox.getValue()));
    }

    private void configureGrid() {

        grid.setSizeFull();
        grid.setColumns("title", "edition","ISBN","price","shelf_num","copies_count","publisher");
        grid.addColumn(book -> book.getPublishDate().toString()).setHeader("Publish Date");
        grid.addColumn(book -> Arrays.toString(book.getAuthors().toArray())).setHeader("Authors");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));


    }

}
