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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    @Resource
    private SearchService searchService;


    @PostMapping("/searchResultForm")
    public String searchResult(Model model, SearchVO searchVO){
        
        // 공지
        model.addAttribute("searchAnnList", searchService.searchAnn(searchVO));
        // QNA
        model.addAttribute("searchQnaList", searchService.searchQna(searchVO));
        
        // 어콤
        model.addAttribute("searchMainAccomList", searchService.searchAccom(searchVO));
        // 어콤 사진
        
        // sub어콤
        model.addAttribute("searchSubAccomList", searchService.searchSubAccom(searchVO));
        // sub 어콤 사진

        return "/content/search/search_result";
    }


}
