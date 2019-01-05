package github.weizibin.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import github.weizibin.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        userMapper.insert(new User().setAge(25).setName("test"));
    }

    @Test
    public void testDelete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 25);
        queryWrapper.eq("name", "test");
        userMapper.delete(queryWrapper);
    }

    @Test
    public void testSearchAfterDelete() {
        testInsert();
        testDelete();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 25).or().eq("name", "test");
        List<User> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

}
