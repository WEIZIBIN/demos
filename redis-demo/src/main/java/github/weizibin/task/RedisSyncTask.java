package github.weizibin.task;

import github.weizibin.cache.KillGoodsCache;
import github.weizibin.service.KillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RedisSyncTask {

    @Autowired
    private KillGoodsService killGoodsService;

    @Autowired
    private KillGoodsCache killGoodsCache;

    @Scheduled(cron = "* * * * * ? *")
    public void syncKillGoodsStockFromCache() {
        Map<Object, Object> killGoodsStockMap = killGoodsCache.getAllKillStock();
        for (Object key : killGoodsStockMap.keySet()) {
            Object value = killGoodsStockMap.get(key);
            if (key instanceof Integer && value instanceof Integer) {
                Integer id = (Integer) key;
                Integer stock = (Integer) value;
                
            }
        }
    }

}
