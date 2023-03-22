package com.zzb.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebMvc {


    @RequestMapping("/index.html")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/jjgg.html")
    public String jjggIndex() {
        return "jjgg.html";
    }

    @RequestMapping("/zjhgg.html")
    public String zjhggIndex() {
        return "zjhgg.html";
    }

    @RequestMapping("/sjsgg.html")
    public String sjsIndex() {
        return "sjsgg.html";
    }

    @RequestMapping("/gsgg.html")
    public String newIndex() {
        return "gsgg.html";
    }

    @RequestMapping("/jyts.html")
    public String jytsIndex() {
        return "jyts.html";
    }

    @RequestMapping("/tfp.html")
    public String tfpIndex() {
        return "tfp.html";
    }

    @RequestMapping("/gsgl.html")
    public String gsglIndex() {
        return "gsgl.html";
    }


    @RequestMapping("/rightIndex.html")
    public String rightIndex() {
        return "L2_right.html";
    }
    @RequestMapping("/ggHtml")
    public String ggHtml() {
        return "交易规则.html";
    }

    @RequestMapping("/ipo.html")
    public String ipo() {
        return "ipo_index.html";


    }

}
