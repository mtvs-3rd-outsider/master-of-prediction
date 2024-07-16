package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.TblCategoryDTO;
import com.outsider.masterofprediction.provider.DeleteByIdsProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM tbl_category")
    List<TblCategoryDTO> findAll();

    @Select("select * from tbl_category limit #{first}, #{count}")
    List<TblCategoryDTO> categoryListLimit(int first, int count);

    @DeleteProvider(type = DeleteByIdsProvider.class, method = "deleteCategoryByIdsProvider")
    void deleteByIds(List<Long> ids);

    @Insert("INSERT INTO tbl_category (category_name) VALUES (#{categoryName})")
    void insertCategory(String categoryName);

    @Update("UPDATE tbl_category SET category_name = #{categoryName} WHERE category_no = #{categoryNo}")
    void updateCategory(TblCategoryDTO tblCategoryDTO);

    @Select("SELECT COUNT(*) as count FROM tbl_category")
    int totalCount();

    @Select("SELECT * FROM tbl_category WHERE category_no = #{subjectCategoryNo}")
    TblCategoryDTO findByCategoryNo(long subjectCategoryNo);
}
