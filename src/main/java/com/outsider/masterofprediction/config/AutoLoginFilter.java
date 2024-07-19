package com.outsider.masterofprediction.config;

import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import com.outsider.masterofprediction.service.CustomUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static com.outsider.masterofprediction.dto.constatnt.StringConstants.DEFAULT_USER_EMAIL;

@Profile("autoLogin")
@Component
public class AutoLoginFilter extends GenericFilterBean {



    @Bean
    public DevAuthenticationFilter customAuthenticationFilter() {
        return new DevAuthenticationFilter();
    }

    private CustomUserService customUserService;
    @Autowired
    public AutoLoginFilter(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    private void authenticateUserAndSetSession(String username, HttpServletRequest request) {
        UserDetails userDetails = customUserService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }



    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            authenticateUserAndSetSession(DEFAULT_USER_EMAIL, httpRequest);
        }
        chain.doFilter(request, response);
    }
}





