package github.weizibin.cache;

import github.weizibin.po.KillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KillGoodsCache {

    private static final String KILL_GOODS_ID = "KILLGOODS";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void put(KillGoods killGoods) {
        redisTemplate.opsForHash().put(KILL_GOODS_ID , killGoods.getId(), killGoods.getStock());
    }

}
