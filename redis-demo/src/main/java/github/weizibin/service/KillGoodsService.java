package github.weizibin.service;

import github.weizibin.cache.KillGoodsCache;
import github.weizibin.handler.KillGoodsHandler;
import github.weizibin.mapper.KillGoodsMapper;
import github.weizibin.mapper.KillGoodsRecordMapper;
import github.weizibin.po.KillGoods;
import github.weizibin.po.KillGoodsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class KillGoodsService {

    @Autowired
    private KillGoodsMapper killGoodsMapper;

    @Autowired
    private KillGoodsRecordMapper killGoodsRecordMapper;

    @Autowired
    private KillGoodsHandler killGoodsHandler;

    @Autowired
    private KillGoodsCache killGoodsCache;

    @Transactional
    public int add(KillGoods killGoods) {
        if (killGoods.getStock() != null && killGoods.getStock().compareTo(0) > 0) {
            killGoods.setInStock(Boolean.TRUE);
        } else {
            killGoods.setInStock(Boolean.FALSE);
        }
        int insert = killGoodsMapper.insert(killGoods);
        if (insert == 0) {
            throw new RuntimeException("fail to add kill goods to db!");
        }
        try {
            killGoodsHandler.putKillGoods2Cache(killGoods);
        } catch (Exception e) {
            throw new RuntimeException("fail to add kill goods to cache!");
        }
        return insert;
    }

    public List<KillGoods> getAllInStock() {
        return killGoodsMapper.getAllInStock();
    }

    public List<KillGoodsRecord> getAllKillRecord() {
        return killGoodsRecordMapper.getAll();
    }

    @Transactional
    public int killByRedis(int killGoodsId, int customerId) {
        if (killGoodsCache.getKillStock(killGoodsId) <= 0) {
            return -1;
        }
        if (!killGoodsCache.killStock(killGoodsId)) {
            return -1;
        }
        KillGoodsRecord killGoodsRecord = new KillGoodsRecord();
        killGoodsRecord.setGoodsId(killGoodsId);
        killGoodsRecord.setCustomerId(customerId);
        killGoodsRecord.setKillTime(new Date());
        int insert = killGoodsRecordMapper.insert(killGoodsRecord);
        if (insert == 1) {
            return 0;
        }
        return -1;
    }
}
