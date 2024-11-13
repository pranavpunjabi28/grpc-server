package com.example.grpc.server;

import com.example.grpc.AddRequest;
import com.example.grpc.AddResponse;
import com.example.grpc.AddServiceGrpc;
import io.grpc.stub.StreamObserver;

public class AddServiceImpl extends AddServiceGrpc.AddServiceImplBase {


    @Override
    public void add(AddRequest request, StreamObserver<AddResponse> responseObserver) {

        long sum = request.getNum1() + request.getNum2();

        AddResponse answer = AddResponse.newBuilder().setAnswer(sum).build();

        responseObserver.onNext(answer);
        responseObserver.onCompleted();//Complete the RPC call
    }


}
