package com.outsider.masterofprediction.service;


import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PrincipalOauthUserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final AttachmentMapper attachmentMapper;
    @Autowired
    public PrincipalOauthUserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper, AttachmentMapper attachmentMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.attachmentMapper = attachmentMapper;
    }

    //구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        //"registraionId" 로 어떤 OAuth 로 로그인 했는지 확인 가능(google,naver등)
         //구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> code를 리턴(OAuth-Clien라이브러리가 받아줌) -> code를 통해서 AcssToken요청(access토큰 받음)
        // => "userRequest"가 감고 있는 정보
        //회원 프로필을 받아야하는데 여기서 사용되는것이 "loadUser" 함수이다 -> 구글 로 부터 회원 프로필을 받을수 있다.


        /**
         *  OAuth 로그인 회원 가입
         */
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo =null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else{
            System.out.println("지원하지 않은 로그인 서비스 입니다.");
        }

        String provider = oAuth2UserInfo.getProvider(); //google , naver, facebook etc
        String providerId = oAuth2UserInfo.getProviderId();
        String providerWithId = provider + "_" + providerId;
        String username = oAuth2UserInfo.getName();
        String password =  bCryptPasswordEncoder.encode(UUID.randomUUID().toString()); //중요하지 않음 그냥 패스워드 암호화 하
        String email = oAuth2UserInfo.getEmail();
        String pictureUrl = oAuth2UserInfo.getPictureUrl();

        User user = userMapper.findByEmail(email);
        //처음 서비스를 이용한 회원일 경우
        if(user == null) {
            LocalDateTime createTime = LocalDateTime.now();
            user = User.builder()
                    .name(username)
                    .password(password)
                    .email(email)
                    .authority("ROLE_USER")
                    .provider(provider)
                    .provideId(providerId)
                    .joinDate(createTime)
                    .build();

            userMapper.createUser(user.getName(), user.getEmail(),  user.getPassword(), user.getAuthority());
            User user1=userMapper.findByEmail(email);
            if (user1 != null){
                user.setId(user1.getId());
            }
            if(!pictureUrl.equals(""))
            {
                TblAttachmentDTO tblAttachmentDTO = new TblAttachmentDTO();
                tblAttachmentDTO.setAttachmentRegistUserNo(user1.getId());
                tblAttachmentDTO.setAttachmentUserNo(user1.getId());
                tblAttachmentDTO.setAttachmentFileAddress(pictureUrl);
                attachmentMapper.setAttachmentsByAttachmentUserNo(tblAttachmentDTO);
            }

        }


        return new CustomUserDetail(user, oAuth2User.getAttributes());
    }
}