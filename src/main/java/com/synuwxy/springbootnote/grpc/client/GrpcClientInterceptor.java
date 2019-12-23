package com.synuwxy.springbootnote.grpc.client;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcClientInterceptor implements ClientInterceptor {

    private Logger logger = LoggerFactory.getLogger(GrpcClientInterceptor.class);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        //创建client
        ClientCall<ReqT,RespT> clientCall = channel.newCall(methodDescriptor,callOptions);
        logger.info("[Client 拦截器] 进入拦截器");
        return new ForwardingClientCall<ReqT, RespT>() {
            @Override
            protected ClientCall<ReqT, RespT> delegate() {
                logger.info("[Client 拦截器] delegate return clientCall");
                return clientCall;
            }
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                String id = "123456";
                String keyName = "id";
                Metadata.Key<String> key = Metadata.Key.of(keyName, Metadata.ASCII_STRING_MARSHALLER);

                //设置id
                logger.info("[Client 拦截器] 设置id: {}", id);
                headers.put(key, id);
                super.start(responseListener, headers);
            }
        };
    }
}