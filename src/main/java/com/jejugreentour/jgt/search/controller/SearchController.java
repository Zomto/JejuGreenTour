package com.jejugreentour.jgt.search.controller;

import com.jejugreentour.jgt.accom.service.AccomService;
import com.jejugreentour.jgt.csCenter.service.CsService;
import com.jejugreentour.jgt.search.service.SearchService;
import com.jejugreentour.jgt.search.vo.SearchVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    @Resource
    private SearchService searchService;
    private CsService csService;
    private AccomService accomService;


    @GetMapping("/searchResultForm")
    public String searchResult(Model model, SearchVO searchVO){
        model.addAttribute("AnnList", searchService.searchAnn(searchVO));
        return "/content/search/search_result";
    }

}
