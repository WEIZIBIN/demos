package github.weizibin.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import github.weizibin.po.CityPO;
import github.weizibin.qo.CityQO;
import org.springframework.stereotype.Repository;

@Repository
public interface CityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CityPO record);

    int insertSelective(CityPO record);

    CityPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CityPO record);

    int updateByPrimaryKey(CityPO record);

    PageList<CityPO> selectByExample(CityQO example, PageBounds page);
}