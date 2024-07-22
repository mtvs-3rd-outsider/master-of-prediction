package com.outsider.masterofprediction.config;


import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblCommentDTO;
import com.outsider.masterofprediction.dto.TblInquiryDTO;
import com.outsider.masterofprediction.dto.constatnt.StringConstants;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.CommentMapper;
import com.outsider.masterofprediction.mapper.InquiryMapper;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

import static com.outsider.masterofprediction.dto.constatnt.StringConstants.DEFAULT_USER_EMAIL;

@Profile("autoLogin")
@Configuration
@EnableWebSecurity
public class SecurityConfigAutoLogin {

    private final UserMapper userMapper;

    private final AttachmentMapper attachmentMapper;
    private final InquiryMapper inquiryMapper;

    private final String email= DEFAULT_USER_EMAIL;
    private final String password="1";
    private final String role="ROLE_USER";
    private final String name="test";
    private final CustomUserService customUserService;
    private final CommentMapper commentMapper;

    @Autowired
    public SecurityConfigAutoLogin(UserMapper userMapper, AttachmentMapper attachmentMapper, InquiryMapper inquiryMapper, CustomUserService customUserService, CommentMapper commentMapper) {
        this.userMapper = userMapper;
        this.attachmentMapper = attachmentMapper;
        this.inquiryMapper = inquiryMapper;
        this.customUserService = customUserService;
        this.commentMapper = commentMapper;
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
                        new AutoLoginFilter(customUserService), BasicAuthenticationFilter.class)
                .logout(logout ->{
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
                    logout.deleteCookies("JSESSIONID"); // 로그아웃 시 사용자의 JSESSIONID 삭제
                    logout.invalidateHttpSession(true);// 세션을 소멸하도록 허용하는 것
                    logout.logoutSuccessUrl("/"); // 로그아웃시 이동할 페이지 설정
                }).sessionManagement(session ->{
                    session.maximumSessions(1).maxSessionsPreventsLogin(true);// session의 허용 개수를 제한
                    session.invalidSessionUrl("/");
                })
                .csrf(auth -> auth.disable())
                .sessionManagement(auth -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                )
                .sessionManagement(auth -> auth
                        .sessionFixation().changeSessionId()
                );


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
                TblInquiryDTO tblInquiryDTO = new TblInquiryDTO();
                tblInquiryDTO.setInquiryUserNo(id);
                // 현재 시간을 기반으로 Timestamp 객체 생성
                tblInquiryDTO.setInquiryTitle("qewjkjkjkj");
                tblInquiryDTO.setInquiryContent("qewdfsfsfsfsfjkjkjkj");
                Timestamp inquiryTimestamp = Timestamp.from(Instant.now());
                tblInquiryDTO.setInquiryTimestamp(inquiryTimestamp);
                inquiryMapper.insertInquiry(tblInquiryDTO);
//                TblCommentDTO tblCommentDTO = new TblCommentDTO();
//                tblCommentDTO.setCommentUserNo(id);
//                tblCommentDTO.setCommentContent("dfsfsf");
//                tblCommentDTO.setCommentSubjectNo(1);
//                commentMapper.
            }
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                userMapper.deleteUser(email);
                attachmentMapper.deleteAttachmentsByAttachmentUserNo(userMapper.findByEmail(email).getId());
            }));
        };
    }


}
