package github.weizibin.cache;

import github.weizibin.po.KillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class KillGoodsCache {

    private static final String KILL_GOODS_ID = "KILLGOODS";
    private static final String KILL_GOODS_STOCK_ID = "KILLGOODS:STOCK";

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "redisJDKTemplate")
    private RedisTemplate<String, Object> redisJDKTemplate;

    public void put(KillGoods killGoods) {
        redisJDKTemplate.opsForHash().put(KILL_GOODS_ID , String.valueOf(killGoods.getId()), killGoods);
    }

    public KillGoods getById(Integer killGoodsId) {
        return (KillGoods) redisJDKTemplate.opsForHash().get(KILL_GOODS_ID, String.valueOf(killGoodsId));
    }

    public void putStock(KillGoods killGoods) {
        redisTemplate.opsForHash().put(KILL_GOODS_STOCK_ID , String.valueOf(killGoods.getId()), String.valueOf(killGoods.getStock()));
    }

    public boolean killStock(Integer killGoodsId) {
        long stock = redisTemplate.opsForHash().increment(KILL_GOODS_STOCK_ID , String.valueOf(killGoodsId), -1);
        return stock >= 0;
    }

    public int getKillStock(Integer killGoodsId) {
        int stock = Integer.parseInt((String) redisTemplate.opsForHash().get(KILL_GOODS_STOCK_ID , String.valueOf(killGoodsId)));
        return stock;
    }

    public Map<Object, Object> getAllKillStock() {
        return redisTemplate.opsForHash().entries(KILL_GOODS_STOCK_ID);
    }

}
