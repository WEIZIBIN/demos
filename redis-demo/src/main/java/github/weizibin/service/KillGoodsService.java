package github.weizibin.service;

import github.weizibin.persistence.KillGoodsMapper;
import github.weizibin.po.KillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KillGoodsService {

    @Autowired
    private KillGoodsMapper killGoodsMapper;

    public int insert(KillGoods killGoods) {
        return killGoodsMapper.insert(killGoods);
    }
}
