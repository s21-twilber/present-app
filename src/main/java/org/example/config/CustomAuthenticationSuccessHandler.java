package org.example.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain chain,
                                        Authentication authentication)
            throws IOException, ServletException {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Optional<? extends GrantedAuthority> auth = authorities.stream().findFirst();
        String grantedAuthority = auth.get().getAuthority();


        if (grantedAuthority.equals("ROLE_COORDINATOR")
                || grantedAuthority.equals("ROLE_ACCOUNTANT")) {

            response.sendRedirect("/crepository");

        } else if (grantedAuthority.equals("ROLE_ADMIN")) {

            response.sendRedirect("/admin");

        } else if (grantedAuthority.equals("ROLE_USER")) {

            response.sendRedirect("/repository");

        }


    }
}
