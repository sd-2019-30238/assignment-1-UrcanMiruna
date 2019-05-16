package com.deals.furniture.security;

import com.deals.furniture.model.StaffAccount;
import com.deals.furniture.model.UserAccount;
import com.deals.furniture.model.UserAccountRepository;
import com.deals.furniture.service.StaffService;
import com.deals.furniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig(){
        super();
    }

    @Autowired
    private StaffService staffService;

    @Autowired
    private UserService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/logorreg","/","/users/register","/users/all", "users/getProduct").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/staffPage/all")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/logorreg" )
                .permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        List<StaffAccount> staffAccounts =staffService.getAllUSers();
        for(StaffAccount staffAccount:staffAccounts){
            manager.createUser(User.withUsername(staffAccount.getUsername()).password(encoder().encode(staffAccount.getPassword())).roles("ADMIN").build());
        }
        return manager;
    }
    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}