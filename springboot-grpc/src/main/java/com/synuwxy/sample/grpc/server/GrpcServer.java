package com.synuwxy.sample.grpc.server;

import com.synuwxy.sample.grpc.HelloProto;
import com.synuwxy.sample.grpc.SayGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GrpcServer {

    private Logger logger = LoggerFactory.getLogger(GrpcServer.class);

    private Server server;

    public void start(int port) throws IOException {
        logger.info("Grpc 服务启动");
        server = ServerBuilder.forPort(port).addService(new SayImpl()).intercept(new GrpcServerInterceptor()).build();
        server.start();
    }

    public void close() {
        if (null != server) {
            server.shutdown();
            logger.info("Grpc 服务关闭");
        }
    }

    private static class SayImpl extends SayGrpc.SayImplBase {
        @Override
        public void sayHello(HelloProto.HelloModel request, StreamObserver<HelloProto.HelloReply> responseObserver) {
            String requestName = request.getName();
            String response = "hi " + requestName + ", helloworld!";
            HelloProto.HelloReply.Builder builder = HelloProto.HelloReply.newBuilder().setMessage(response);
            HelloProto.HelloReply helloReply = builder.build();
            responseObserver.onNext(helloReply);
            responseObserver.onCompleted();
        }
    }
}
