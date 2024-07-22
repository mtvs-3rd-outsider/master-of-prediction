package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblTierDTO;
import com.outsider.masterofprediction.dto.constatnt.AdminPaginationConstant;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.service.TierService;
import com.outsider.masterofprediction.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin-page/tier")
public class TierController {

    private final TierService tierService;
    private final AttachmentMapper attachmentMapper;
    @Value("${file.imgUrl}")
    private String imgUrl;

    @Autowired
    public TierController(TierService tierService, AttachmentMapper attachmentMapper) {
        this.tierService = tierService;
        this.attachmentMapper = attachmentMapper;
    }


    @GetMapping
    public String showCategoryPage(@RequestParam(defaultValue = "1") int page, Model model) {
        if (page < 1) page = 1;

        int totalCount = tierService.totalCount();
        List<TblTierDTO> tblTierDTO = tierService.findCategoryLimit((page - 1) * 10, AdminPaginationConstant.itemCount);
        PaginationUtils.modelSetPaginationReturnPageNumber(model, page, totalCount);
        model.addAttribute("tiers", tblTierDTO);
        return "content/admin-page/tier/index";
    }

    @GetMapping("/create")
    public String createPage() {
        return "content/admin-page/tier/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute TblTierDTO tier,
                         @RequestParam("attachmentFileAddress") MultipartFile file) {
        tierService.create(tier, file);
        return "redirect:/admin-page/tier";
    }

    @GetMapping("/{tierNo}")
    public String updatePage(@PathVariable Long tierNo, Model model) {
        TblTierDTO tier = tierService.findByTierNo(tierNo);
        TblAttachmentDTO attachment = attachmentMapper.getAttachmentsByTierNo(tierNo);
        attachment.setAttachmentFileAddress(Paths.get(imgUrl).resolve(attachment.getAttachmentFileAddress()).toString());
        model.addAttribute("attachmentFileAddress", attachment.getAttachmentFileAddress());
        model.addAttribute("tier", tier);
        return "content/admin-page/tier/update";
    }

    @PostMapping("/admin-page/tier/delete")
    public String deleteBetting(@RequestParam List<Long> ids) {
        tierService.deleteList(ids);
        return "redirect:/admin-page/tier";
    }

}
