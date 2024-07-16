package com.outsider.masterofprediction.utils;

import com.outsider.masterofprediction.dto.constatnt.AdminPaginationConstant;
import org.springframework.ui.Model;

public class PaginationUtils {


        public static void modelSetPaginationReturnPageNumber(Model model, int page, int totalCount) {
            int totalPage = totalCount % AdminPaginationConstant.maxPagingControl > 0 ?
                    totalCount / AdminPaginationConstant.maxPagingControl+ 1 :
                    totalCount / AdminPaginationConstant.maxPagingControl;
            if (page > totalPage) page = totalPage;

            int start = ((page - 1) / AdminPaginationConstant.maxPagingControl)
                    * AdminPaginationConstant.maxPagingControl + 1;
            int end = Math.min(start + AdminPaginationConstant.maxPagingControl - 1, totalPage);

            model.addAttribute("currentPage", page);
            model.addAttribute("start", start);
            model.addAttribute("end", end);
            model.addAttribute("totalPages", totalPage);
    }
}
