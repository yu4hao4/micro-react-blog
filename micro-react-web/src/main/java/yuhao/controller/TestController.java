package yuhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yuhao.service.impl.TestServiceImpl;

/**
 * @author yuhao5
 * @date 2020-07-27
 */
@RestController
public class TestController {
    @Autowired
    private TestServiceImpl testService;

    @GetMapping("/test1")
    public void test1(){
        testService.test1();
    }

    @GetMapping("/test2")
    public void test2(){
        testService.test2();
    }
}
