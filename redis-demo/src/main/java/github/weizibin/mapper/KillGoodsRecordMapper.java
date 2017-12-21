package github.weizibin.mapper;

import github.weizibin.po.KillGoods;
import github.weizibin.po.KillGoodsRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KillGoodsRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KillGoodsRecord record);

    int insertSelective(KillGoodsRecord record);

    KillGoodsRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KillGoodsRecord record);

    int updateByPrimaryKey(KillGoodsRecord record);

    List<KillGoodsRecord> getAll();
}