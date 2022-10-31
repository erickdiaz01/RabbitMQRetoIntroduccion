package com.sofka.broker.controller;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value= "/broker")
public class ResourceREST{
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping(value = "/producer/pisos")
    public Mono<String> producerPisos(@RequestParam("exchangeName") String exchange,
                                      @RequestParam("messageData") String messageData){
        amqpTemplate.convertAndSend(exchange, "", messageData);
        return Mono.just("Correo repartido a todo el piso");
    }

    @GetMapping(value = "/producer/pisos/impares")
    public Mono<String> producer(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey,
                                 @RequestParam("messageData") String messageData) {

   amqpTemplate.convertAndSend(exchange,routingKey,messageData);

        return Mono.just("Message sent to the RabbitMQ Header Exchange Successfully");
    }
}


