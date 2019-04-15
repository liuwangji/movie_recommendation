package com.bjut.service;

import com.bjut.service.service.ReadRecommadationResultService;
import com.bjut.service.service.WriteOfflineDataService;
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
    public ModelAndView insertOfflineData() {
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
    public ModelAndView search(Model model) {
        return new ModelAndView("search");
    }

    @RequestMapping(value = "/show")
    public ModelAndView login(Model model, @RequestParam Integer userId, @RequestParam String nickName) {
        Integer page = 1;
        Integer pageCount = 10;
        model.addAttribute("preferenceResults", readRecommadationResultService.getRecommdationMovies(userId,page,pageCount));
        model.addAttribute("recommendationResults", readRecommadationResultService.getPreferenceMovies(userId,page,pageCount));
        model.addAttribute("peopleLikeByLookCountResults", readRecommadationResultService.getPeopleLikeByLookingCountMovies(page,pageCount));
        model.addAttribute("peopleLikeByRatingResults", readRecommadationResultService.getPeopleLikeByRatingMovies(page,pageCount));
        model.addAttribute("nickName",nickName);
        return new ModelAndView("show",model.asMap());
    }
}
