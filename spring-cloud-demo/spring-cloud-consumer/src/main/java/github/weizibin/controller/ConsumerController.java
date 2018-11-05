package github.weizibin.controller;

import github.weizibin.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/{name}")
    public String sayHello(@PathVariable String name) {
        return helloService.sayHello(name);
    }

}
