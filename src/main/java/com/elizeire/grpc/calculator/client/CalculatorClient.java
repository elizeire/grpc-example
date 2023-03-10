package com.elizeire.grpc.calculator.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.PrimeNumberDecompositionRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        // UNARY
//        SumRequest request = SumRequest.newBuilder().setFirstNumber(10).setSecondNumber(25).build();
//        SumResponse response = stub.sum(request);
//
//        System.out.println(request.getFirstNumber()+" + "+ request.getSecondNumber()+" = " + response.getSumResult());

        // Streaming Server

        Integer  number = 567890;
        stub.primeNumberDecomposition(PrimeNumberDecompositionRequest.newBuilder().setNumber(number).build())
                        .forEachRemaining(primeNumberDecompositionResponse -> {
                            System.out.println(primeNumberDecompositionResponse.getPrimeFactor()+" ");
                        });



        channel.shutdown();
    }
}
