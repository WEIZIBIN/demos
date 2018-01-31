package github.weizibin.mapper;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import github.weizibin.Application;
import github.weizibin.qo.CityQO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void selectByPrimaryKey() throws Exception {
        System.out.println(cityMapper.selectByPrimaryKey(1));
    }

    @Test
    public void selectByExample() throws Exception {
        CityQO cityQO = new CityQO();
        cityQO.setCountrycode("CHN");
        System.out.println(cityMapper.selectByExample(cityQO,
                new PageBounds(2, 10, Order.create("population", "DESC"))));
    }

}