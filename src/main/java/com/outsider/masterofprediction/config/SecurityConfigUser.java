package com.outsider.masterofprediction.config;



import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import com.outsider.masterofprediction.service.PrincipalOauthUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

//태스트 중
@Profile("user")
@Configuration
@EnableWebSecurity
public class SecurityConfigUser {

    private final AttachmentMapper attachmentMapper;

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.redirect.uri}")
    private String redirectUri;

    UserMapper userMapper;

    public SecurityConfigUser(UserMapper userMapper, AttachmentMapper attachmentMapper) {
        this.userMapper = userMapper;
        this.attachmentMapper = attachmentMapper;
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.requiresChannel(channel -> channel
                        .anyRequest().requiresSecure()
                )
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/css/**", "/images/**").permitAll()
                        .requestMatchers("/", "/login", "/register", "/loginProc").permitAll()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()

                )
                .formLogin(auth -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .usernameParameter("email")// 로그인 처리를 위한 URL 설정
                        .passwordParameter("password")// 로그인 처리를 위한 URL 설정
                        .defaultSuccessUrl("/", true)
                        .permitAll()

                )
                .oauth2Login(oauth2 -> oauth2
                                .loginPage("/login")
                                .authorizationEndpoint(authorization -> authorization
                                        .baseUri("/oauth2/authorization"))
                                .redirectionEndpoint(redirection -> redirection
                                        .baseUri("/login/oauth2/code/*"))
                                .userInfoEndpoint(userInfo -> userInfo
//                                .oidcUserService(this.oidcUserService())
                                        .userService(this.oauth2UserService()))
                                .defaultSuccessUrl("/", true)
                ).logout(logout -> {
                    logout.logoutUrl("/logout");
                    logout.deleteCookies("JSESSIONID"); // 로그아웃 시 사용자의 JSESSIONID 삭제
                    logout.invalidateHttpSession(true);// 세션을 소멸하도록 허용하는 것
                    logout.logoutSuccessUrl("/"); // 로그아웃시 이동할 페이지 설정
                }).sessionManagement(session -> {
                    session.maximumSessions(1)
                            .maxSessionsPreventsLogin(true);// session의 허용 개수를 제한
                    session.invalidSessionUrl("/");
                    session.sessionFixation().changeSessionId();
                })
                .csrf(auth -> auth.disable())
                .sessionManagement(auth -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                );

        return http.build();
    }
    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService( ) {
        final PrincipalOauthUserService delegate = new PrincipalOauthUserService(passwordEncoder(),userMapper ,attachmentMapper);
        return (userRequest) -> {

            // Delegate to the default implementation for loading a user
            CustomUserDetail oAuth2User = (CustomUserDetail) delegate.loadUser(userRequest);
            return oAuth2User;
        };

    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://*.google.com");
        config.addAllowedOrigin("https://lh3.googleusercontent.com");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://example.com", "http://anotherdomain.com")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*")
//                        .allowCredentials(true);
//            }
//        };
//    }
    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("https://master-of-prediction.shop:8081/login/oauth2/code/google")
                .scope( "profile", "email")
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("Google")
                .build();
    }

}
