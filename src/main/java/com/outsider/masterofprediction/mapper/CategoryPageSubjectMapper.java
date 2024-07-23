package com.outsider.masterofprediction.mapper;

import com.outsider.masterofprediction.dto.CategoryPageSubjectDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryPageSubjectMapper {

    @Select("SELECT " +
            "    s.subject_no, " +
            "    s.subject_title, " +
            "    s.subject_status, " +
            "    CASE " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000000000000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000000000000000, 1), 'P')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000000000, 1), 'T')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000000, 1), 'G')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000, 1), 'M')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000, 1), 'K')  " +
            "        ELSE " +
            "            CAST(s.subject_total_no_point + s.subject_total_yes_point AS CHAR) " +
            "    END AS total_points, " +
            " " +
            "    CASE " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) > 0 THEN " +
            "            CONCAT( " +
            "                ROUND( " +
            "                    (s.subject_total_yes_point / (s.subject_total_no_point + s.subject_total_yes_point)) * 100, 0 " +
            "                ), '%' " +
            "            ) " +
            "        ELSE " +
            "            '50%'" +
            "    END AS yes_percentage, " +
            " " +
            "    CASE " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) > 0 THEN " +
            "            CONCAT( " +
            "                ROUND( " +
            "                    (s.subject_total_no_point / (s.subject_total_no_point + s.subject_total_yes_point)) * 100, 0 " +
            "                ), '%' " +
            "            ) " +
            "        ELSE " +
            "            '50%' " +
            "    END AS no_percentage, " +
            " " +
            "    (COALESCE(c.comment_count, 0) + COALESCE(r.reply_count, 0)) AS total_comments_replies, " +
            "    ta.attachment_file_address " +
            "FROM " +
            "    TBL_SUBJECT s " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            comment_subject_no, " +
            "            COUNT(*) AS comment_count " +
            "        FROM " +
            "            TBL_COMMENT " +
            "        GROUP BY " +
            "            comment_subject_no " +
            "    ) c ON s.subject_no = c.comment_subject_no " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            c.comment_subject_no, " +
            "            COUNT(*) AS reply_count " +
            "        FROM " +
            "            TBL_COMMENT_REPLY r " +
            "        JOIN " +
            "            TBL_COMMENT c ON r.reply_comment_no = c.comment_no " +
            "        GROUP BY " +
            "            c.comment_subject_no " +
            "    ) r ON s.subject_no = r.comment_subject_no " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            subject_no, " +
            "            attachment_file_address " +
            "        FROM " +
            "            tbl_attachment " +
            "    ) ta ON s.subject_no = ta.subject_no " +
            "WHERE " +
            "   s.subject_status = '진행중' " +
            "   AND s.subject_category_no = #{ categoryNo } " +
            "ORDER BY " +
            "    (s.subject_total_no_point + s.subject_total_yes_point) DESC;"
    )
    List<CategoryPageSubjectDTO> findCategoryPageSubjectList(int categoryNo);

    @Select("SELECT " +
            "    s.subject_no, " +
            "    s.subject_title, " +
            "    s.subject_status, " +
            "    CASE " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000000000000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000000000000000, 1), 'P')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000000000, 1), 'T')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000000, 1), 'G')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000, 1), 'M')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000, 1), 'K')  " +
            "        ELSE " +
            "            CAST(s.subject_total_no_point + s.subject_total_yes_point AS CHAR) " +
            "    END AS total_points, " +
            " " +
            "    CASE " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) > 0 THEN " +
            "            CONCAT( " +
            "                ROUND( " +
            "                    (s.subject_total_yes_point / (s.subject_total_no_point + s.subject_total_yes_point)) * 100, 0 " +
            "                ), '%' " +
            "            ) " +
            "        ELSE " +
            "            '50%'" +
            "    END AS yes_percentage, " +
            " " +
            "    CASE " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) > 0 THEN " +
            "            CONCAT( " +
            "                ROUND( " +
            "                    (s.subject_total_no_point / (s.subject_total_no_point + s.subject_total_yes_point)) * 100, 0 " +
            "                ), '%' " +
            "            ) " +
            "        ELSE " +
            "            '50%' " +
            "    END AS no_percentage, " +
            " " +
            "    (COALESCE(c.comment_count, 0) + COALESCE(r.reply_count, 0)) AS total_comments_replies, " +
            "    ta.attachment_file_address " +
            "FROM " +
            "    TBL_SUBJECT s " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            comment_subject_no, " +
            "            COUNT(*) AS comment_count " +
            "        FROM " +
            "            TBL_COMMENT " +
            "        GROUP BY " +
            "            comment_subject_no " +
            "    ) c ON s.subject_no = c.comment_subject_no " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            c.comment_subject_no, " +
            "            COUNT(*) AS reply_count " +
            "        FROM " +
            "            TBL_COMMENT_REPLY r " +
            "        JOIN " +
            "            TBL_COMMENT c ON r.reply_comment_no = c.comment_no " +
            "        GROUP BY " +
            "            c.comment_subject_no " +
            "    ) r ON s.subject_no = r.comment_subject_no " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            subject_no, " +
            "            attachment_file_address " +
            "        FROM " +
            "            tbl_attachment " +
            "    ) ta ON s.subject_no = ta.subject_no " +
            "WHERE " +
            "   s.subject_status = '진행중' " +
            "ORDER BY " +
            "    (s.subject_total_no_point + s.subject_total_yes_point) DESC;"
    )
    List<CategoryPageSubjectDTO> findAllCategoryPageList();

    @Select("SELECT " +
            "    s.subject_no, " +
            "    s.subject_title, " +
            "    s.subject_status, " +
            "    CASE " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000000000000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000000000000000, 1), 'P')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000000000, 1), 'T')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000000, 1), 'G')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000000, 1), 'M')  " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) >= 1000 THEN " +
            "            CONCAT(ROUND((s.subject_total_no_point + s.subject_total_yes_point) / 1000, 1), 'K')  " +
            "        ELSE " +
            "            CAST(s.subject_total_no_point + s.subject_total_yes_point AS CHAR) " +
            "    END AS total_points, " +
            " " +
            "    CASE " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) > 0 THEN " +
            "            CONCAT( " +
            "                ROUND( " +
            "                    (s.subject_total_yes_point / (s.subject_total_no_point + s.subject_total_yes_point)) * 100, 0 " +
            "                ), '%' " +
            "            ) " +
            "        ELSE " +
            "            '50%'" +
            "    END AS yes_percentage, " +
            " " +
            "    CASE " +
            "        WHEN (s.subject_total_no_point + s.subject_total_yes_point) > 0 THEN " +
            "            CONCAT( " +
            "                ROUND( " +
            "                    (s.subject_total_no_point / (s.subject_total_no_point + s.subject_total_yes_point)) * 100, 0 " +
            "                ), '%' " +
            "            ) " +
            "        ELSE " +
            "            '50%' " +
            "    END AS no_percentage, " +
            " " +
            "    (COALESCE(c.comment_count, 0) + COALESCE(r.reply_count, 0)) AS total_comments_replies, " +
            "    ta.attachment_file_address " +
            "FROM " +
            "    TBL_SUBJECT s " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            comment_subject_no, " +
            "            COUNT(*) AS comment_count " +
            "        FROM " +
            "            TBL_COMMENT " +
            "        GROUP BY " +
            "            comment_subject_no " +
            "    ) c ON s.subject_no = c.comment_subject_no " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            c.comment_subject_no, " +
            "            COUNT(*) AS reply_count " +
            "        FROM " +
            "            TBL_COMMENT_REPLY r " +
            "        JOIN " +
            "            TBL_COMMENT c ON r.reply_comment_no = c.comment_no " +
            "        GROUP BY " +
            "            c.comment_subject_no " +
            "    ) r ON s.subject_no = r.comment_subject_no " +
            "    LEFT JOIN ( " +
            "        SELECT " +
            "            subject_no, " +
            "            attachment_file_address " +
            "        FROM " +
            "            tbl_attachment " +
            "    ) ta ON s.subject_no = ta.subject_no " +
            "WHERE " +
            "   s.subject_status = '진행중' " +
            "ORDER BY " +
            "    s.subject_register_timestamp DESC;"
    )
    List<CategoryPageSubjectDTO> findRecentCategoryPageList();
}
