package com.synuwxy.sample.grpc.server;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcServerInterceptor implements ServerInterceptor {

    private Logger logger = LoggerFactory.getLogger(ServerInterceptor.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        logger.info("[Server 拦截器]");
        return serverCallHandler.startCall(serverCall, metadata);
    }
}
