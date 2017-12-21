package github.weizibin.handler;

import github.weizibin.cache.KillGoodsCache;
import github.weizibin.po.KillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KillGoodsHandler {

    @Autowired
    private KillGoodsCache killGoodsCache;

    public void putKillGoods2Cache(KillGoods killGoods) {
        killGoodsCache.put(killGoods);
        killGoodsCache.putStock(killGoods);
    }
}
