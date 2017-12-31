package github.weizibin.service;

import github.weizibin.cache.KillGoodsCache;
import github.weizibin.handler.KillGoodsHandler;
import github.weizibin.mapper.KillGoodsMapper;
import github.weizibin.mapper.KillGoodsRecordMapper;
import github.weizibin.po.KillGoods;
import github.weizibin.po.KillGoodsRecord;
import github.weizibin.response.ServiceResponse;
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
    public ServiceResponse killByRedis(int killGoodsId, int customerId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        if (killGoodsCache.getById(killGoodsId).getStartKillTime().compareTo(new Date()) > 0) {
            serviceResponse.setSuccess(Boolean.FALSE);
            serviceResponse.setResCode(-1);
            serviceResponse.setMsg("not start killing!");
            return serviceResponse;
        }
        if (killGoodsCache.getKillStock(killGoodsId) <= 0 || !killGoodsCache.killStock(killGoodsId)) {
            serviceResponse.setSuccess(Boolean.FALSE);
            serviceResponse.setResCode(-1);
            serviceResponse.setMsg("no available stock!");
            return serviceResponse;
        }
        KillGoodsRecord killGoodsRecord = new KillGoodsRecord();
        killGoodsRecord.setGoodsId(killGoodsId);
        killGoodsRecord.setCustomerId(customerId);
        killGoodsRecord.setKillTime(new Date());
        int insert = killGoodsRecordMapper.insert(killGoodsRecord);
        if (insert == 1) {
            serviceResponse.setSuccess(Boolean.TRUE);
            serviceResponse.setResCode(0);
            serviceResponse.setMsg("kill success");
            return serviceResponse;
        }
        serviceResponse.setSuccess(Boolean.FALSE);
        serviceResponse.setResCode(-2);
        serviceResponse.setMsg("error, please kill again");
        return serviceResponse;
    }

    @Transactional
    public int updateSeletive(KillGoods killGoods) {
        return killGoodsMapper.updateByPrimaryKeySelective(killGoods);
    }
}
