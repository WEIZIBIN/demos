package github.weizibin.controller;

import github.weizibin.po.KillGoods;
import github.weizibin.service.KillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {

    private static final String ATTR_CUSTOMER_ID = "CUSTOMER_ID";

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
    public String add(KillGoods killGoods, String startKillTimeStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startKillTime = sdf.parse(startKillTimeStr);
        killGoods.setStartKillTime(startKillTime);
        killGoodsService.add(killGoods);
        return "redirect:/web/index";
    }

    @RequestMapping(value = "/kill", method = {RequestMethod.GET})
    public String kill(HttpSession session, Model model) {
        if (session.getAttribute(ATTR_CUSTOMER_ID) == null) {
            return "redirect:/setCustomerId";
        }
        model.addAttribute("customerId", session.getAttribute(ATTR_CUSTOMER_ID));
        model.addAttribute("killGoodsList", killGoodsService.getAllInStock());
        model.addAttribute("killGoodsRecordList", killGoodsService.getAllKillRecord());
        return "kill";
    }

    @RequestMapping(value = "/kill", method = {RequestMethod.POST})
    public String kill(Integer killGoodsId, HttpSession session, RedirectAttributes redirectAttributes) {
        int resCode = killGoodsService.killByRedis(killGoodsId, (Integer) session.getAttribute(ATTR_CUSTOMER_ID));
        if (resCode == 0) {
            redirectAttributes.addAttribute("msg", "kill success!");
        } else {
            redirectAttributes.addAttribute("msg", "kill fail!");
        }
        return "redirect:/kill";
    }

    @RequestMapping(value = "/setCustomerId", method = {RequestMethod.GET})
    public String setCustomerId() {
        return "setCustomerId";
    }

    @RequestMapping(value = "/setCustomerId", method = {RequestMethod.POST})
    public String setCustomerId(Integer customerId, HttpSession session) {
        session.setAttribute(ATTR_CUSTOMER_ID, customerId);
        return "redirect:/kill";
    }

    @RequestMapping("/error/access_denied")
    public String access_denied() {
        return "common/access_denied";
    }

}
