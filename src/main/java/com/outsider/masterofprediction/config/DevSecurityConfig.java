package com.outsider.masterofprediction.config;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.constatnt.StringConstants;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
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


@Profile("dev")
@Configuration
@EnableWebSecurity
public class    DevSecurityConfig{
    private final String email= DEFAULT_USER_EMAIL;
    private final String password="1";
    private final String role="ROLE_ADMIN";
    private final String name="admin";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DevAuthenticationFilter customAuthenticationFilter() {
        return new DevAuthenticationFilter();
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
                tblAttachmentDTO.setAttachmentFileAddress(StringConstants.BASIC_IMAGE);
                attachmentMapper.setAttachmentsByAttachmentUserNo(tblAttachmentDTO);
            }
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                userMapper.deleteUser(email);
                attachmentMapper.deleteAttachmentsByAttachmentUserNo(userMapper.findByEmail(email).getId());
            }));
        };
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .addFilterAfter(customAuthenticationFilter(),
                        // UsernamePasswordAuthenticationFilter.class);
                        BasicAuthenticationFilter.class).csrf(AbstractHttpConfigurer::disable);

        return http.build();

    }

}


// http
//         .authorizeHttpRequests((auth) -> auth
//         .requestMatchers("/**").permitAll()
//                 .anyRequest().authenticated()); // 모든 경로에 대해 접근 허용
// http
//         .csrf(AbstractHttpConfigurer::disable); // CSRF 보호 비활성화 (개발 환경에서만 사용 권장)
// http
//         .formLogin((auth) -> auth.loginPage("/admin-page/login")
//                 .loginProcessingUrl("/admin-loginProc")
//                 .permitAll()
//         );
// // 기본 로그인 페이지 사용하지 않음
//
// http
//         .sessionManagement((auth) -> auth
//                 .sessionFixation().changeSessionId());
// return http.build();