package github.weizibin.service;

public class DemoServiceImpl implements DemoService{

    public String sayHello(String name) {
        return "Hello!" + name;
    }

    public String whoIAm () {
        return "dubbo-privider-1";
    }
;
}
