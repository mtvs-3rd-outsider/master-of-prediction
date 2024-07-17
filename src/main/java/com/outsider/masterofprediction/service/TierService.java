package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.TblTierDTO;
import com.outsider.masterofprediction.mapper.TierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TierService {
    private final TierMapper tierMapper;

    @Autowired
    public TierService(TierMapper tierMapper) {
        this.tierMapper = tierMapper;
    }

    public List<TblTierDTO> getAll() {
        return tierMapper.getAll();
    }

    public List<TblTierDTO> findCategoryLimit(int first, int count) {
        return tierMapper.getLimit(first, count);
    }

    public void create(TblTierDTO tier) {
        tierMapper.insert(tier);
    }

    public void deleteByIds(List<Long> ids) {
        tierMapper.deleteByIds(ids);
    }

    // public void updateById(TblTierDTO  tblCategoryDTO) {
    //     tierMapper.updateCategory(tblCategoryDTO);
    // }

    public int totalCount() {
        return tierMapper.totalCount();
    }

    // public TblTierDTO  findByCategoryNo(long subjectCategoryNo) {
    //     return tierMapper.findByCategoryNo(subjectCategoryNo);
    // }
}
