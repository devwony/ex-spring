package com.whlee.websocket.spv4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class ServiceController {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String sayHello() {
        System.out.println("Hello !!");
        return "Hello!!! ";
    }

    @RequestMapping(value = "/pushMsg")
    @ResponseBody
    public void pushMsg(String msg) throws IOException {
        System.out.println("msg : " + msg);
        WebSocketHandler.sendMessage(msg);
    }

}
