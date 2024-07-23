package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.ActivityUserSubjectDTO;
import com.outsider.masterofprediction.dto.TblUserDTO;
import com.outsider.masterofprediction.dto.UserAttachmentDTO;
import com.outsider.masterofprediction.dto.constatnt.IntConstants;
import com.outsider.masterofprediction.dto.response.MainPageSubjectDTO;
import com.outsider.masterofprediction.dto.response.UserAndAttachmentDTO;
import com.outsider.masterofprediction.mapper.BettingOrderMapper;
import com.outsider.masterofprediction.mapper.UserMapper;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.service.MainPageService;
import com.outsider.masterofprediction.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CategoryService categoryService;
    private final BettingOrderMapper bettingOrderMapper;
    private final UserMapper userMapper;

    @Autowired
    public MainPageController(MainPageService mainPageService, CategoryService categoryService, BettingOrderMapper bettingOrderMapper, UserMapper userMapper) {
        this.mainPageService = mainPageService;
        this.categoryService = categoryService;
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

        // 리스트의 크기 계산
        int size = originalList.size();
        // 리스트의 절반 크기 계산 (소수점 이하 올림)
        int halfSize = (size + 1) / 2;

        // 새로운 리스트 생성 및 요소 이동
        List<T> halfList = new ArrayList<>(originalList.subList(0, halfSize));

        // 원본 리스트에서 절반 크기만큼 요소 제거
        for (int i = 0; i < halfSize; i++) {
            originalList.remove(0);
        }

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

        System.out.println(leftUsers);
        System.out.println(rightUsers);


        mv.setViewName("/layout/main-page/index");
        mv.addObject("subjects", mainPageSubjectDTOS);
        mv.addObject("categories", categoryService.findAll());
        mv.addObject("title", "Master Of Prediction");
        mv.addObject("activity", activityUserSubjectDTO);
        mv.addObject("leftUsers", rightUsers);
        mv.addObject("rightUsers", leftUsers);
        mv.addObject("view", "content/main-page/main-page");

        return mv;
    }
}