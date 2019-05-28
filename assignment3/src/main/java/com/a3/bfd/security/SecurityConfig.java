package com.a3.bfd.security;

import com.a3.bfd.model.StaffAccount;
import com.a3.bfd.readService.StaffServiceR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
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
    private StaffServiceR staffService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/furniture","/users/order","/users*","/logorreg","/", "/users","/users/register","/users/all", "/users/getProduct").permitAll()
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