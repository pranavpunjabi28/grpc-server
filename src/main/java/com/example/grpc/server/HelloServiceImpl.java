package com.example.grpc.server;

import com.example.grpc.HelloResponse;
import com.example.grpc.HelloRequest;
import com.example.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String greeting = new StringBuilder()
                .append("Hello, Welcome ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .setAdress("ahd")
                .setPinCode("12345")
                .build();

        // Todo How to handle the error
        try {
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }catch (RuntimeException e) {
            //If a gRPC server or client stream encounters a critical error, such as network issues, invalid data, or unauthorized access,
            //when a stream encounters a critical error, the exception thrown is typically a StatusRuntimeException (a subclass of RuntimeException) on the client side
            System.out.println("while Processing a request Receive a terminating error from the stream.");
            responseObserver.onError(e);
            throw new RuntimeException(e);
        }


    }
}
