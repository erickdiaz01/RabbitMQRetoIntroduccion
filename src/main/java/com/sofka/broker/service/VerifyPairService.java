package com.sofka.broker.service;

import org.springframework.stereotype.Service;

@Service
public class VerifyPairService {
    public VerifyPairService() {
    }

    public String verifyPairMethod(String nameOfQueue){
       int numberOfFloor= Integer.parseInt(nameOfQueue.substring(4,5));
        return numberOfFloor % 2 == 0 ? "queue.par" : "queue.impar";
    }
}
