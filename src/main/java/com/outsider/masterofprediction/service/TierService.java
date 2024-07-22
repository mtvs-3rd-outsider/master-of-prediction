package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.TblTierDTO;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.TierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class TierService {
    private final TierMapper tierMapper;
    private final FileStorageService fileStorageService;
    private final AttachmentMapper attachmentMapper;

    @Autowired
    public TierService(TierMapper tierMapper, FileStorageService fileStorageService, AttachmentMapper attachmentMapper) {
        this.tierMapper = tierMapper;
        this.fileStorageService = fileStorageService;
        this.attachmentMapper = attachmentMapper;
    }

    public List<TblTierDTO> getAll() {
        return tierMapper.findAll();
    }

    public List<TblTierDTO> findCategoryLimit(int first, int count) {
        return tierMapper.findLimit(first, count);
    }

    @Transactional
    public void create(TblTierDTO tier, MultipartFile file) {
        long userId = UserSession.getUserId();
        if (file.getOriginalFilename() == null) {
            return;
        }
        try {
            tierMapper.insert(tier);
            TblAttachmentDTO tblAttachmentDTO = new TblAttachmentDTO();
            File uploadFile = fileStorageService.storeFile(file);
            tblAttachmentDTO.setAttachmentFileAddress(uploadFile.getName());
            tblAttachmentDTO.setAttachmentRegistUserNo(userId);
            tblAttachmentDTO.setTierNo(tier.getTierNo());
            attachmentMapper.setAttachmentsByTierNo(tblAttachmentDTO);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByIds(List<Long> ids) {
        tierMapper.deleteByIds(ids);
    }

    public int totalCount() {
        return tierMapper.totalCount();
    }

    public TblTierDTO findByTierNo(Long tierNo) {
        return tierMapper.findByTierNo(tierNo);
    }

    public void deleteList(List<Long> ids) {
    }



}
