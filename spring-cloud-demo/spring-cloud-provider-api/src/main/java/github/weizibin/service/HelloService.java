package github.weizibin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/hello")
@FeignClient(value = "spring-cloud-provider")
public interface HelloService {

    @RequestMapping(method = RequestMethod.GET)
    String sayHello(@RequestParam(value = "name") String name);

}
