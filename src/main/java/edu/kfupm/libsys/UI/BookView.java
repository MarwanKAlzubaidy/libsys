package edu.kfupm.libsys.UI;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@AnonymousAllowed
@Route("/book")
public class BookView extends VerticalLayout {
    public BookView() {
        VerticalLayout todosList = new VerticalLayout();
        TextField taskField = new TextField();

        Button addButton = new Button("Add");
        addButton.addClickShortcut(Key.ENTER);
        addButton.addClickListener(click -> {

            Checkbox checkbox = new Checkbox(taskField.getValue());
            todosList.add(checkbox);
        });
        add(
                new H1("Vaadin Todo"),
                todosList,
                new HorizontalLayout(
                        taskField,
                        addButton
                )
        );
    }
}
