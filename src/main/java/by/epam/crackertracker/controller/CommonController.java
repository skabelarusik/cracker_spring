package by.epam.crackertracker.controller;

import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.PageSelector;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Locale;

@Controller
@RequestMapping(PageConstant.PATH_PAGE_INDEX)
public class CommonController {

    @Autowired
    private LocaleResolver localeResolver;

    @GetMapping(PageConstant.PATH_PAGE_INDEX)
    public String index(){
        return PageConstant.PATH_PAGE_MAIN_INDEX;
    }

    @GetMapping(PageConstant.URI_ABOUT)
    public String about(){
        return PageConstant.PAGE_ABOUT;
    }


    @GetMapping(PageConstant.URI_BACK)
    public String back(@SessionAttribute String startPage){
        String page = startPage;
        if(page.isEmpty()){
            page = PageConstant.PATH_PAGE_MAIN_INDEX;
        }
        return page;
    }

    @RequestMapping(PageConstant.URI_LANG_BY)
    public String langBy(HttpServletRequest request, HttpServletResponse response){
        localeResolver.setLocale(request, response, new Locale(ParameterConstant.BY, ParameterConstant.SUBMIT_BY));
        return request.getParameter(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
    }

    @RequestMapping(PageConstant.URI_LANG_RU)
    public String langRu(HttpServletRequest request, HttpServletResponse response){
        localeResolver.setLocale(request, response, new Locale(ParameterConstant.RU, ParameterConstant.SUBMIT_RU));
        return request.getParameter(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
    }

    @RequestMapping(PageConstant.URI_LANG_EN)
    public String langEn(HttpServletRequest request, HttpServletResponse response){
        localeResolver.setLocale(request, response, new Locale(ParameterConstant.EN, ParameterConstant.SUBMIT_EN));
        return request.getParameter(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
    }

    @GetMapping(PageConstant.LOGOUT)
    public String logout(){
        return PageConstant.PATH_PAGE_MAIN_INDEX;
    }
}