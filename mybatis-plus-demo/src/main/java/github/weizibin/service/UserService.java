package github.weizibin.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.weizibin.mapper.UserMapper;
import github.weizibin.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
