package github.weizibin.controller;

import github.weizibin.po.KillGoods;
import github.weizibin.service.KillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public String addSubmit(KillGoods killGoods, String startKillTimeStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startKillTime = sdf.parse(startKillTimeStr);
        killGoods.setStartKillTime(startKillTime);
        killGoodsService.insert(killGoods);
        return "redirect:/web/index";
    }

    @RequestMapping("/kill")
    public String kill() {
        return "kill";
    }

    @RequestMapping("/killSubmit")
    public String killSubmit() {
        return "kill";
    }

    @RequestMapping("/error/access_denied")
    public String access_denied() {
        return "common/access_denied";
    }

}
