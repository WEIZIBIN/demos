package github.weizibin.controller;

import github.weizibin.service.KillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @Autowired
    private KillGoodsService killGoodsService;

    @RequestMapping("/web/index")
    public String webindex() {
        return "index";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public String add() {
        return "add";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public String addSubmit() {
        return "add";
    }

    @RequestMapping("/kill")
    public String kill() {
        return "index";
    }

    @RequestMapping("/error/access_denied")
    public String access_denied() {
        return "common/access_denied";
    }

}
