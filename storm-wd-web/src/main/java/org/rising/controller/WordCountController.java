package org.rising.controller;

import org.rising.service.WordCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class WordCountController {
    @Autowired
    private WordCountService wordCountService;

    @RequestMapping("view")
    public String wordCountView(){
        return "view";
    }

    @RequestMapping("data")
    @ResponseBody
    public Map<String,String> queryData(){
        return this.wordCountService.queryData();
    }

}
