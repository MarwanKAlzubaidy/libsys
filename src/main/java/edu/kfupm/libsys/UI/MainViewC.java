package edu.kfupm.libsys.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@AnonymousAllowed
@Route(value = "/",layout = MainLayout.class)
public class MainViewC extends VerticalLayout {
    Button  register=new Button("Register");
    Button  catalog= new Button("Catalog");
    Button  reservebook= new Button("Reserver");
    public MainViewC(){

        VerticalLayout verticalLayout = new VerticalLayout(register,catalog,reservebook);
        add(verticalLayout);
    }



}
