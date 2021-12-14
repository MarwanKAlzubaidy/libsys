package ics324.project.libsys.UI;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import ics324.project.libsys.Secuirty.SecurityService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@AnonymousAllowed
public class MainLayout extends AppLayout {
    private final SecurityService securityService;
    public MainLayout(SecurityService Service) {
        securityService=Service;
        createHeader();
        createDrawer();

    }

    private void createHeader() {
        H1 logo = new H1("Library System");
        logo.addClassNames("text-l", "m-m");

        Button logout = new Button("Log out", e -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(
          new DrawerToggle(), 
          logo
                ,login_logout()
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header); 

    }

    private void createDrawer() {
        RouterLink authorLink = new RouterLink("Author list", AuthorView.class);
        authorLink.setVisible(false);
        Object prince=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if( prince instanceof UserDetails){
            UserDetails userDetails=  (UserDetails)prince;
            if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))

               authorLink.setVisible(true);




        }
        authorLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout( 
            authorLink
        ));
    }
    private Component login_logout(){
        if(securityService.getAuthenticatedUser() !=null){
        Button logout = new Button("Log out", e -> securityService.logout());
        return logout;}
        Button login = new Button("log in");
        login.addClickListener(e->login.getUI().ifPresent(ui-> ui.navigate("login")));
        return login;
    }
}