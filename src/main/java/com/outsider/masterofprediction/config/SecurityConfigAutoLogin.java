package com.outsider.masterofprediction.config;


import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.constatnt.StringConstants;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import com.outsider.masterofprediction.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import static com.outsider.masterofprediction.dto.constatnt.StringConstants.DEFAULT_USER_EMAIL;

@Profile("autoLogin")
@Configuration
@EnableWebSecurity
public class SecurityConfigAutoLogin {

    private final UserMapper userMapper;

    private final AttachmentMapper attachmentMapper;

    private final String email= DEFAULT_USER_EMAIL;
    private final String password="1";
    private final String role="ROLE_USER";
    private final String name="test";
    private final CustomUserService customUserService;

    @Autowired
    public SecurityConfigAutoLogin(UserMapper userMapper, AttachmentMapper attachmentMapper, CustomUserService customUserService) {
        this.userMapper = userMapper;
        this.attachmentMapper = attachmentMapper;
        this.customUserService = customUserService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                )
                .formLogin(AbstractHttpConfigurer::disable
                )
                .addFilterAfter(
                        new AutoLoginFilter(customUserService), BasicAuthenticationFilter.class);;


        return http.build();
    }
    @Bean
    @Transactional
    public ApplicationRunner init() {
        return args -> {
            if (userMapper.findByEmail(email) == null) {
                userMapper.createUser(name, email, passwordEncoder().encode(password), role);
                Long id = userMapper.findByEmail(email).getId();
                TblAttachmentDTO tblAttachmentDTO = new TblAttachmentDTO();
                tblAttachmentDTO.setAttachmentUserNo(id);
                tblAttachmentDTO.setAttachmentRegistUserNo(id);
                tblAttachmentDTO.setAttachmentFileAddress("https://lh3.googleusercontent.com/a/ACg8ocKCH9UgnTgbHfYEi5WGYXXlaMd-uz3gSyTyXUz-w78BUCC38XA=s96-c");
                attachmentMapper.setAttachmentsByAttachmentUserNo(tblAttachmentDTO);
            }
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                userMapper.deleteUser(email);
                attachmentMapper.deleteAttachmentsByAttachmentUserNo(userMapper.findByEmail(email).getId());
            }));
        };
    }


}
