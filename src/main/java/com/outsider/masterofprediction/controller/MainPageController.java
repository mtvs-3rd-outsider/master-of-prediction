package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.*;

import com.outsider.masterofprediction.dto.constatnt.IntConstants;
import com.outsider.masterofprediction.dto.response.MainPageSubjectDTO;
import com.outsider.masterofprediction.dto.response.UserAndAttachmentDTO;
import com.outsider.masterofprediction.mapper.BettingOrderMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.service.MainPageService;
import com.outsider.masterofprediction.service.UserManagementService;
import com.outsider.masterofprediction.utils.ConvertImageUrl;
import com.outsider.masterofprediction.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {

    private final MainPageService mainPageService;
    private final BettingOrderMapper bettingOrderMapper;
    private final UserMapper userMapper;

    @Autowired
    public MainPageController(MainPageService mainPageService, BettingOrderMapper bettingOrderMapper, UserMapper userMapper) {
        this.mainPageService = mainPageService;
        this.bettingOrderMapper = bettingOrderMapper;
        this.userMapper = userMapper;
    }

    /**
     * 주어진 리스트의 절반을 새로운 리스트로 옮기고, 원본 리스트에서 제거하는 함수
     *
     * @param originalList 원본 리스트
     * @return 원본 리스트의 절반 크기를 가지는 새로운 리스트
     */
    private static <T> List<T> splitAndRemoveHalf(List<T> originalList) {
        // 원본 리스트가 null이거나 빈 리스트인 경우 빈 리스트 반환
        if (originalList == null || originalList.isEmpty()) {
            return new ArrayList<>();
        }

        if (originalList.size() <= IntConstants.MAIN_TOP_TEN_SPLIT) {
            List<T> halfList = new ArrayList<>(originalList.subList(0, originalList.size()));
            originalList.subList(0, originalList.size()).clear();
            return halfList;
        }
        // 새로운 리스트 생성 및 요소 이동
        List<T> halfList = new ArrayList<>(originalList.subList(0, IntConstants.MAIN_TOP_TEN_SPLIT));

        // 원본 리스트에서 절반 크기만큼 요소 제거
        originalList.subList(0, IntConstants.MAIN_TOP_TEN_SPLIT).clear();

        return halfList;
    }


    @GetMapping()
    public ModelAndView getMainPage(ModelAndView mv){

        // mv.addObject();
        List<MainPageSubjectDTO> mainPageSubjectDTOS =  mainPageService.findMainPageSubjectList();

        List<ActivityUserSubjectDTO> activityUserSubjectDTO =
                bettingOrderMapper.findActivityCount(IntConstants.MAIN_PAGE_ACTIVITY_COUNT);

        List<UserAndAttachmentDTO> leftUsers = userMapper.findUserOrderByPointLIMIT(IntConstants.MAIN_PAGE_RANK);
        List<UserAndAttachmentDTO> rightUsers = splitAndRemoveHalf(leftUsers);

        ConvertImageUrl.convert(mainPageSubjectDTOS);
        for (ActivityUserSubjectDTO tblAttachmentDTO: activityUserSubjectDTO) {
            tblAttachmentDTO.getAttachmentUser().setAttachmentFileAddress(FileUtil.checkFileOrigin(tblAttachmentDTO.getAttachmentUser().getAttachmentFileAddress()));
            tblAttachmentDTO.getAttachmentSubject().setAttachmentFileAddress(FileUtil.checkFileOrigin(tblAttachmentDTO.getAttachmentSubject().getAttachmentFileAddress()));
        }

        ConvertImageUrl.convert(leftUsers);
        ConvertImageUrl.convert(rightUsers);

        mv.setViewName("layout/index"); // layout/index.html
        mv.addObject("subjects", mainPageSubjectDTOS);
            mv.addObject("title", "Master Of Prediction");
        mv.addObject("activity", activityUserSubjectDTO);
        mv.addObject("leftUsers", rightUsers);
        mv.addObject("rightUsers", leftUsers);
        mv.addObject("view", "content/main-page");
        
        return mv;
    }
}