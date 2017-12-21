package github.weizibin.cache;

import github.weizibin.po.KillGoods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class KillGoodsCacheTest {

    @Autowired
    private KillGoodsCache killGoodsCache;

    @Test
    public void testPut() {
        KillGoods killGoods = new KillGoods();
        killGoods.setId(1);
        killGoods.setName("123");
        killGoods.setStartKillTime(new Date());
        killGoods.setStock(100);
        killGoods.setInStock(true);
        killGoodsCache.put(killGoods);
    }

    @Test
    public void testGet() {
        testPut();
        assert killGoodsCache.getById(1).getId().equals(1);
    }

}