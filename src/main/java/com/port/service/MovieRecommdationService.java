package com.port.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface MovieRecommdationService {

    public ModelAndView init(Model model);

    public ModelAndView enterIndex(Model model);

    public ModelAndView register(Model model, @RequestParam Integer userId, @RequestParam String nickName, @RequestParam  String password);

    public ModelAndView login(Model model, @RequestParam Integer userId, @RequestParam  String password);

    public ModelAndView search(Model model, @RequestParam Integer userId, @RequestParam String nickName, @RequestParam String search_text);

    public ModelAndView reSearch(Model model, @RequestParam Integer userId, @RequestParam String nickName, @RequestParam String tag, @RequestParam String type);

}
