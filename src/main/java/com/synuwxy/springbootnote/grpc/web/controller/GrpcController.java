package com.synuwxy.springbootnote.grpc.web.controller;

import com.synuwxy.springbootnote.common.ResultCode;
import com.synuwxy.springbootnote.common.ResultObject;
import com.synuwxy.springbootnote.grpc.client.GrpcClient;
import com.synuwxy.springbootnote.grpc.server.GrpcServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/grpc")
public class GrpcController {

    private GrpcServer grpcServer = new GrpcServer();

    @GetMapping("/server/start")
    public Map<String, Object> serverStart(@RequestParam("port") int port) throws IOException {
        grpcServer.start(port);
        return ResultObject.newInstance(ResultCode.SUCCESS, "success");
    }

    @GetMapping("/server/close")
    public Map<String, Object> serverClose() {
        grpcServer.close();
        return ResultObject.newInstance(ResultCode.SUCCESS, "success");
    }

    @GetMapping("/client/say")
    public Map<String, Object> say(@RequestParam(value = "addr",defaultValue = "127.0.0.1", required = false) String addr, @RequestParam("port") int port) {
        GrpcClient grpcClient = new GrpcClient(addr, port);
        return ResultObject.newInstance(ResultCode.SUCCESS, "Grpc信息：" + grpcClient.say());
    }
}