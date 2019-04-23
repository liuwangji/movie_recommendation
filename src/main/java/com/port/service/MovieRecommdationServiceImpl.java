package com.port.service;

import com.port.service.service.ReadRecommadationResultService;
import com.port.constant.MovieCategoriesEnum;
import com.port.constant.SearchTypeEnum;
import com.port.service.service.WriteOfflineDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class MovieRecommdationServiceImpl implements MovieRecommdationService {

    @Autowired
    WriteOfflineDataService writeOfflineDataService;
    @Autowired
    ReadRecommadationResultService readRecommadationResultService;
    @RequestMapping(value = "/init")
    public ModelAndView init(Model model) {
        writeOfflineDataService.doInit();
        return new ModelAndView("index");
    }
    @RequestMapping(value = "/index")
    public ModelAndView enterIndex(Model model) {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(Model model) {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(Model model, @RequestParam Integer userId, @RequestParam String nickName, @RequestParam String search_text) {
        String defaultTag = MovieCategoriesEnum.ALL.getCategory();
        String defaultType = SearchTypeEnum.RATE.getType();
        Integer page = 1;
        Integer pageCount = 8;
        // 个性化推荐电影列表
        model.addAttribute("preferenceResults", readRecommadationResultService.getRecommdationMovies(userId, page, pageCount));
        // 用户观影记录列表
        model.addAttribute("recommendationResults", readRecommadationResultService.getPreferenceMovies(userId, page, pageCount));
        // 分类搜索结果
        model.addAttribute("searchResults", readRecommadationResultService.getSearchResult(page, 10, search_text));
        // 设置tag
        model.addAttribute("tag", defaultTag);
        // 设置type
        model.addAttribute("type", defaultType);
        // 用户名
        model.addAttribute("nickName", nickName);
        // 用户Id
        model.addAttribute("userId", userId);
        return new ModelAndView("show", model.asMap());
    }

    @RequestMapping(value = "/show")
    public ModelAndView login(Model model, @RequestParam Integer userId, @RequestParam String nickName) {
        String defaultTag = MovieCategoriesEnum.ALL.getCategory();
        String defaultType = SearchTypeEnum.RATE.getType();
        Integer page = 1;
        Integer pageCount = 8;
        // 个性化推荐电影列表
        model.addAttribute("preferenceResults", readRecommadationResultService.getRecommdationMovies(userId, page, pageCount));
        // 用户观影记录列表
        model.addAttribute("recommendationResults", readRecommadationResultService.getPreferenceMovies(userId, page, pageCount));
        // 分类搜索结果
        model.addAttribute("searchResults", readRecommadationResultService.getCategorySearchResult(page, 10, defaultTag, defaultType));
        // 设置tag
        model.addAttribute("tag", defaultTag);
        // 设置type
        model.addAttribute("type", defaultType);
        // 用户名
        model.addAttribute("nickName", nickName);
        // 用户Id
        model.addAttribute("userId", userId);
        return new ModelAndView("show", model.asMap());
    }

    @RequestMapping(value = "/reSearch")
    public ModelAndView reSearch(Model model, @RequestParam Integer userId, @RequestParam String nickName, @RequestParam String tag, @RequestParam String type) {
        Integer page = 1;
        Integer pageCount = 8;
        // 个性化推荐电影列表
        model.addAttribute("preferenceResults", readRecommadationResultService.getRecommdationMovies(userId, page, pageCount));
        // 用户观影记录列表
        model.addAttribute("recommendationResults", readRecommadationResultService.getPreferenceMovies(userId, page, pageCount));
        // 分类搜索结果
        model.addAttribute("searchResults", readRecommadationResultService.getCategorySearchResult(page, 10, tag, type));
        // 设置tag
        model.addAttribute("tag", tag);
        // 设置type
        model.addAttribute("type", type);
        // 用户名
        model.addAttribute("nickName", nickName);
        // 用户Id
        model.addAttribute("userId", userId);
        return new ModelAndView("show", model.asMap());
    }
}
