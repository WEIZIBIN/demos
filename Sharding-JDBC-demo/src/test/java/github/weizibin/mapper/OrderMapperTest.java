package github.weizibin.mapper;

import github.weizibin.Application;
import github.weizibin.po.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @org.junit.Test
    public void insert() throws Exception {
        Order order = new Order();
        Date date = new Date();
        order.setOrderId(date.getTime());
        order.setCreateTime(date);
        int insert = orderMapper.insert(order);
        assert insert == 1;
    }

    @org.junit.Test
    public void selectByOrderId() throws Exception {
        Order order = orderMapper.selectByOrderId(1522076169968L);
        System.out.println(order.getOrderId());
    }

}
