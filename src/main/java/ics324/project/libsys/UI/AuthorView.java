package ics324.project.libsys.UI;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import ics324.project.libsys.entities.Author;
import ics324.project.libsys.entities.services.AuthorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import javax.annotation.security.RolesAllowed;
import java.util.Arrays;

@RolesAllowed("ROLE_ADMIN") //only admin can enter

@Route(value = "Author",layout=MainLayout.class)
@PageTitle("Authors")
public class AuthorView extends VerticalLayout {
    Grid<Author> grid = new Grid<>(Author.class);

    AuthorForm authorForm;

    AuthorService service;

    public AuthorView(AuthorService service) {
        this.service = service;
        //addClassName("list-view"); 
        setSizeFull();
        configureGrid();
        configureForm();
        Notification notification = Notification.show(username());

        add(getToolbar(), getContent());
       updateList();
    }

    private void updateList() {
        grid.setItems(service.findAllAuthor());
    }

    private Component getContent() {


        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, authorForm);
        horizontalLayout.setFlexGrow(2, grid);
        horizontalLayout.setFlexGrow(1, authorForm);
        horizontalLayout.setSizeFull();

        return horizontalLayout;
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName");
        grid.addColumn(contact -> contact.getB_date().toString()).setHeader("date");
        grid.addColumn(contact -> Arrays.toString(contact.getBooks().toArray())).setHeader("Books");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editAuthor(event.getValue()));
    }

    private void editAuthor(Author value) {
        if(value==null)
        {authorForm.setAuthor(null);
        authorForm.setVisible(false);
        }
        else
        {
            authorForm.setAuthor(value);
            authorForm.setVisible(true);
        }
    }

    private void configureForm() {
        authorForm = new AuthorForm();
        authorForm.setWidth("25em");

        authorForm.addListener(AuthorForm.SaveEvent.class,this::saveAuthor);
        authorForm.addListener(AuthorForm.CloseEvent.class,closeEvent -> editAuthor(null));
        authorForm.addListener(AuthorForm.DeleteEvent.class,this::deleteAuthor);
        authorForm.setVisible(false);

    }
    private void saveAuthor(AuthorForm.SaveEvent event){
        service.saveAuthor(event.getAuthor());
    updateList();
    }
    private void deleteAuthor(AuthorForm.DeleteEvent event){
        service.deleteAuthor(event.getAuthor());
        updateList();
    }

    private HorizontalLayout getToolbar() {


        Button addAuthorButton = new Button("Add Author");
        addAuthorButton.addClickListener(click->addAuthor());

        HorizontalLayout toolbar = new HorizontalLayout( addAuthorButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addAuthor() {
        grid.asSingleSelect().clear();
        editAuthor(new Author());
    }

    private String username() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getAuthorities().toString();
        } else {
            return principal.toString();
        }


    }
}
