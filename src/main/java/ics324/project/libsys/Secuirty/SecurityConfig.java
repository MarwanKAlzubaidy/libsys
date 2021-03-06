package ics324.project.libsys.Secuirty;


import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import ics324.project.libsys.UI.LoginView;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity 
@Configuration
public class SecurityConfig extends VaadinWebSecurityConfigurerAdapter { 

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        setLoginView(http, LoginView.class);
    }

    /**
     * Allows access to static resources, bypassing Spring security.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**"); 
        super.configure(web);
    }

    /**
     * Demo UserDetailService which only provides two hardcoded
     * in memory users and their roles.
     * NOTE: This should not be used in real-world applications.
     */

}