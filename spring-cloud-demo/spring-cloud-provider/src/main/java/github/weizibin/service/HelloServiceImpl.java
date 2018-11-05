package github.weizibin.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl {

    public String sayHello(String name) {
        return "Hello " + name;
    }

}
