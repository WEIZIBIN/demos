package github.weizibin.cache;

import github.weizibin.po.KillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KillGoodsCache {

    private static final String KILL_GOODS_ID = "KILLGOODS";
    private static final String KILL_GOODS_STOCK_ID = "KILLGOODS:STOCK";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void put(KillGoods killGoods) {
        redisTemplate.opsForHash().put(KILL_GOODS_ID , String.valueOf(killGoods.getId()), killGoods);
    }

    public KillGoods getById(Integer killGoodsId) {
        return (KillGoods) redisTemplate.opsForHash().get(KILL_GOODS_ID, String.valueOf(killGoodsId));
    }

    public void putStock(KillGoods killGoods) {
        redisTemplate.opsForHash().put(KILL_GOODS_STOCK_ID , String.valueOf(killGoods.getId()), killGoods.getStock());
    }

    public boolean killStock(Integer killGoodsId) {
        long stock = redisTemplate.opsForHash().increment(KILL_GOODS_STOCK_ID , String.valueOf(killGoodsId), -1);
        return stock >= 0;
    }

    public long getKillStock(Integer killGoodsId) {
        long stock = (Long) redisTemplate.opsForHash().get(KILL_GOODS_STOCK_ID , String.valueOf(killGoodsId));
        return stock;
    }

}
