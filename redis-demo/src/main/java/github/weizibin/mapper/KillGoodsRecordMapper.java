package github.weizibin.mapper;

import github.weizibin.po.KillGoodsRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface KillGoodsRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KillGoodsRecord record);

    int insertSelective(KillGoodsRecord record);

    KillGoodsRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KillGoodsRecord record);

    int updateByPrimaryKey(KillGoodsRecord record);
}