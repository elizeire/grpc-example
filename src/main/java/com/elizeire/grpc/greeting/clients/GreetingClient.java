package com.elizeire.grpc.greeting.clients;

import com.proto.greet.GreetManyTimesRequest;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        System.out.println("Creating stub");
//        DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
//        DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.DummyServiceFutureStub.newStub(channel);


        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        //Unary
//        Greeting greeting = Greeting.newBuilder()//
//                .setFirstName("Guilherme")//
//                .setLastName("Elizeire")//
//                .build();//
//        GreetRequest greetRequest = GreetRequest.newBuilder()//
//                .setGreeting(greeting)//
//                .build();//
//        GreetResponse greetResponse = greetClient.greet(greetRequest);
//
//        System.out.println(greetResponse.getResult());
        // do something


        //Server Streaming

        GreetManyTimesRequest request = GreetManyTimesRequest.newBuilder()
                .setGreeting(Greeting.newBuilder()
                        .setFirstName("Guilherme")
                        .setLastName("Elizeire")
                        .build()).build();


        greetClient.greetManytimes(request).forEachRemaining(greetManyTimesResponse -> {
            System.out.println(greetManyTimesResponse.getResult());
        });

        System.out.println("Shutting down channel");
        channel.shutdown();

    }
}
