package com.outsider.masterofprediction.config;

import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.dto.constatnt.StringConstants;
import com.outsider.masterofprediction.mapper.UserMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Profile("dev")
public class DevAuthenticationFilter extends GenericFilterBean {


    @Autowired
    private UserMapper userMapper;



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
        // 개발용 임시 유저
//        String email = StringConstants.DEFAULT_USER_EMAIL;
//        CustomUserDetail user = new CustomUserDetail(userMapper.findByEmail(email));
//        //
//
//        PreAuthenticatedAuthenticationToken authentication =
//                new PreAuthenticatedAuthenticationToken(user, user.getPassword(), user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);



        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
