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
        securityService = Service;
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
                , login_logout()
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink authorLink = new RouterLink("Author list", AuthorView.class);    //only emp
        RouterLink BookLink = new RouterLink("Book list", BookView.class);//all
        RouterLink BookAdd = new RouterLink("Book add", BookForm.class);
        BookAdd.setVisible(false);
        RouterLink BorrowLink = new RouterLink("Borrow records", BorrowView.class); //emp
        BorrowLink.setVisible(false);
        RouterLink CopyLink = new RouterLink("Copies list", CopyView.class);//emp
        CopyLink.setVisible(false);
        RouterLink CustomerLink = new RouterLink("Customer list", CustomerView.class);//emp
        CustomerLink.setVisible(false);
        RouterLink EmployeeLink = new RouterLink("Employee list", EmployeeView.class);//emp
        EmployeeLink.setVisible(false);
        RouterLink historyLink = new RouterLink("History View", HistoryView.class);//cust
        historyLink.setVisible(false);
        RouterLink homeLink = new RouterLink("Home", MainViewC.class);//all
        RouterLink reserveLink = new RouterLink("Reserve", reserveView.class);//cust
        reserveLink.setVisible(false);
        RouterLink returnLink = new RouterLink("Returns ", ReturnView.class);//emp
        returnLink.setVisible(false);
        RouterLink renewLink = new RouterLink("Renew", Renew.class);//cust
        returnLink.setVisible(false);
        RouterLink recordLink = new RouterLink("Records", BorrowView.class);
        recordLink.setVisible(false);
        RouterLink addRecLink = new RouterLink("add record", BorrowForm.class);
        addRecLink.setVisible(false);


        RouterLink LibraryLink = new RouterLink("Libraries list", LibraryView.class);//lib
        LibraryLink.setVisible(false);

        authorLink.setVisible(false);
        Object prince = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (prince instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) prince;
            if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                BookAdd.setVisible(true);
                BorrowLink.setVisible(true);
                authorLink.setVisible(true);
                CopyLink.setVisible(true);
                CustomerLink.setVisible(true);
                EmployeeLink.setVisible(true);
                returnLink.setVisible(true);
                recordLink.setVisible(true);
                addRecLink.setVisible(true);
                returnLink.setVisible(true);
                LibraryLink.setVisible(true);
            }
             else {
                historyLink.setVisible(true);
                reserveLink.setVisible(true);
                renewLink.setVisible(true);

            }
        }
        authorLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(

                BookLink,
                BookAdd,
                homeLink,
                historyLink,
                reserveLink,
                authorLink,
                CopyLink,
                CustomerLink,
                EmployeeLink,
                returnLink,
                recordLink,
                addRecLink,
                renewLink,
                LibraryLink,
                returnLink
        ));
    }

    private Component login_logout() {
        if (securityService.getAuthenticatedUser() != null) {
            Button logout = new Button("Log out", e -> securityService.logout());
            return logout;
        }
        Button login = new Button("log in");
        login.addClickListener(e -> login.getUI().ifPresent(ui -> ui.navigate("login")));
        return login;
    }
}