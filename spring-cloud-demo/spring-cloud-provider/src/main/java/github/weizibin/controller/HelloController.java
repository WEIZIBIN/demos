package github.weizibin.controller;

import github.weizibin.service.HelloService;
import github.weizibin.service.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements HelloService {

    @Autowired
    private HelloServiceImpl helloService;

    @Override
    public String sayHello(String name) {
        return helloService.sayHello(name);
    }

}
