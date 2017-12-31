package github.weizibin.task;

import github.weizibin.cache.KillGoodsCache;
import github.weizibin.po.KillGoods;
import github.weizibin.service.KillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class RedisSyncTask {

    @Autowired
    private KillGoodsService killGoodsService;

    @Autowired
    private KillGoodsCache killGoodsCache;

    @Scheduled(cron = "* * * * * ?")
    public void syncKillGoodsStockFromCache() throws InterruptedException {
        Map<Object, Object> killGoodsStockMap = killGoodsCache.getAllKillStock();
        for (Object key : killGoodsStockMap.keySet()) {
            Object value = killGoodsStockMap.get(key);
            if (key instanceof String && value instanceof String) {
                int id = Integer.parseInt((String) key);
                int stock = Integer.parseInt((String) value);
                KillGoods killGoods = new KillGoods();
                killGoods.setId(id);
                killGoods.setStock(stock > 0 ? stock : 0);
                killGoods.setInStock(stock > 0);
                killGoodsService.updateSeletive(killGoods);
            }
        }
    }

}
