package com.synuwxy.springbootnote.grpc.client;

import com.synuwxy.springbootnote.grpc.HelloProto;
import com.synuwxy.springbootnote.grpc.SayGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcClient {

    private SayGrpc.SayBlockingStub sayBlockingStub;

    private Logger logger = LoggerFactory.getLogger(GrpcClient.class);

    public GrpcClient(String addr, int port) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(addr, port).usePlaintext(true).intercept(new GrpcClientInterceptor()).build();
        sayBlockingStub = SayGrpc.newBlockingStub(managedChannel);
        logger.info("绑定sever addr:{} ,port:{}", addr, port);
    }

    public String say() {
        logger.info("Grpc 客户端请求");
        HelloProto.HelloModel.Builder builder = HelloProto.HelloModel.newBuilder().setName("grpc");
        HelloProto.HelloModel helloModel = builder.build();
        HelloProto.HelloReply helloReply = sayBlockingStub.sayHello(helloModel);
        String message = helloReply.getMessage();
        logger.info("Grpc 服务端返回值：{}", message);
        return message;
    }
}
