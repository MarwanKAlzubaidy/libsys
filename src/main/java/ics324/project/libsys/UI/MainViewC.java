package ics324.project.libsys.UI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@AnonymousAllowed
@Route(value = "/",layout = MainLayout.class)
public class MainViewC extends VerticalLayout {
    Button  register=new Button("Register");
    Button  catalog= new Button("Catalog");

    public MainViewC(){
        cofigButton();
        VerticalLayout verticalLayout = new VerticalLayout(register,catalog);
        verticalLayout.setSizeFull();
        add(verticalLayout);
    }

    private void cofigButton() {
        register.addClickListener(buttonClickEvent ->UI.getCurrent().navigate("register"));
        catalog.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("book"));
    }


}
