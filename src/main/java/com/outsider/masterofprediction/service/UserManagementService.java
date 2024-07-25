package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.config.SecurityConfig;
import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.CommentMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserManagementService {


    private AttachmentMapper attachmentMapper;

    private UserMapper userMapper;
    private CommentMapper commentMapper;

    public UserManagementService(AttachmentMapper attachmentMapper, UserMapper userMapper,CommentMapper commentMapper) {
        this.attachmentMapper = attachmentMapper;
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }
    public boolean isUserSessionValid(Long userId, Long sessionId) {
        return userId != null && userId.equals(sessionId);
    }
    public long createDefaultUser(User user) {
        String pwd = user.getPassword();
        user.setPassword(new SecurityConfig().passwordEncoder().encode(pwd));
        user.setAuthority("ROLE_USER");
        return userMapper.createUser(user.getName(), user.getEmail(),  user.getPassword(), user.getAuthority());
    }

    public void createDefaultUserObject(User user) {
        String pwd = user.getPassword();
        user.setPassword(new SecurityConfig().passwordEncoder().encode(pwd));
        user.setAuthority("ROLE_USER");
         userMapper.createUserObject(user);
    }
    public String getUserNameById(Long userId) {
        return userMapper.getUserById(userId).getName();
    }
    public User getUser(Long userId) {
        return userMapper.getUserById(userId);
    }

    public void updateUserProfile(UserProfileDTO userProfileDTO) {
        User user = convertToUser(userProfileDTO);
        userMapper.updateUser(user);
    }
    public void updateUserWithdrawalStatus(UserWithdrawalStatusDTO userWithdrawalStatusDTO) {

        User user  = convertToUser(userWithdrawalStatusDTO);
        userMapper.updateWithdrawalStatusByUser(user);
    }

    public void updateUser(User user)
    {
        userMapper.updateUser(user);

    }
    public BigDecimal getUserPoint()
    {

        if(UserSession.getUserId()==0)
        {
            return BigDecimal.ZERO;
        }else {
           User getUser =  userMapper.getUserById(UserSession.getUserId());
           return getUser.getPoint();
        }
    }

    public TblAttachmentDTO getAttachmentsByUserNo(Long userId){
        return attachmentMapper.getAttachmentsByUserNo(userId);
    }

    public void updateUserImage(TblAttachmentDTO tblAttachmentDTO)
    {
        attachmentMapper.updateAttachmentByAttachmentUserNo(tblAttachmentDTO);
    }
    public void registerUserImage(TblAttachmentDTO tblAttachmentDTO)
    {
        attachmentMapper.setAttachmentsByAttachmentUserNo(tblAttachmentDTO);
    }
    private User convertToUser(UserWithdrawalStatusDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());
        user.setWithdrawalStatus(dto.isWithdrawal());
        return user;
    }

    private User convertToUser(UserProfileDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());
        user.setName(dto.getUserName());
        user.setPassword(dto.getUserPassword());
        return user;
    }

    private TblAttachmentDTO convertToAttechment(UserImageDTO userImageDTO) {
        TblAttachmentDTO tblAttachmentDTO = new TblAttachmentDTO();
        tblAttachmentDTO.setAttachmentUserNo(userImageDTO.getUserId());
        tblAttachmentDTO.setAttachmentFileAddress(userImageDTO.getFileAddress());
        return tblAttachmentDTO;
    }

    public List<CommentDTO> getCommentsByUserId(UserPaginationDTO userPaginationDTO) {
        return commentMapper.getCommentsByUserId(userPaginationDTO);
    }
    public int getCommentCountByUserId(Long userId) {
        return commentMapper.getCommentCountByUserId(userId);
    }

    public String getAuthorityBySubjectNo(long subjectUserNo) {
        return userMapper.getAuthorityBySubjectNo(subjectUserNo);
    }

    public long getSumYPointByDTO(TblBettingOrderDTO dto) {
        Long sumYPoint = userMapper.getSumYPointByDTO(dto);
        if(sumYPoint==null){
            return 0;
        }

        return sumYPoint;

    }

    public long getSumNPointByDTO(TblBettingOrderDTO dto) {
        Long sumNPoint = userMapper.getSumNPointByDTO(dto);
        if(sumNPoint==null){
            return 0;
        }

        return sumNPoint;
    }
}
