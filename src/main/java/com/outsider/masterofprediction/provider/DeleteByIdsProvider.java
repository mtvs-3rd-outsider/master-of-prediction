package com.outsider.masterofprediction.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class DeleteByIdsProvider {

    public String deleteByIdsProvider(List<Long> ids) {
        return new SQL() {
            {
                DELETE_FROM("tbl_notice");
                WHERE("notice_no IN (" + joinIds(ids) + ")");
            }
        }.toString();
    }

    public String deleteCategoryByIdsProvider(List<Long> ids) {
        return new SQL() {
            {
                DELETE_FROM("tbl_category");
                WHERE("category_no IN (" + joinIds(ids) + ")");
            }
        }.toString();
    }

    public String deleteTierByIdsProvider(List<Long> ids) {
        return new SQL() {
            {
                DELETE_FROM("tbl_tier");
                WHERE("tier_no IN (" + joinIds(ids) + ")");
            }
        }.toString();
    }



    private String joinIds(List<Long> ids) {
        StringBuilder builder = new StringBuilder();
        for (Long id: ids){
            builder.append(id.toString());
            builder.append(",");
        }
        if (!builder.isEmpty())
            builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}


