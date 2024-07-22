package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.TblTierDTO;
import com.outsider.masterofprediction.provider.DeleteByIdsProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TierMapper {

    @Select("SELECT * FROM tbl_tier")
    List<TblTierDTO> findAll();

    @Select("SELECT * FROM tbl_tier LIMIT #{first}, #{count}")
    List<TblTierDTO> findLimit(int first, int count);

    @Insert("INSERT INTO tbl_tier (tier_name, tier_content) VALUES (#{tierName}, #{tierContent})")
    @Options(useGeneratedKeys = true, keyProperty = "tierNo")
    void insert(TblTierDTO tier);

    @Update("UPDATE tbl_tier SET tier_name = #{tierName}, tier_content = #{tierContent} WHERE tier_no = #{tierNo}")
    void update(TblTierDTO tier);

    @Select("SELECT COUNT(*) as count FROM tbl_tier")
    int totalCount();

    @DeleteProvider(type = DeleteByIdsProvider.class, method = "deleteTierByIdsProvider")
    void deleteByIds(List<Long> ids);

    @Select("SELECT * FROM tbl_tier WHERE tier_no = #{tierNo}")
    TblTierDTO findByTierNo(Long tierNo);
}
