package com.sofka.broker.config;

import com.sofka.broker.service.VerifyPairService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuracion para enviar el correo a los apartamentos impares
 */

@Configuration
public class RabbitMQTopicConfig {

    private final VerifyPairService verifyPairService;

    public RabbitMQTopicConfig(VerifyPairService verifyPairService) {
        this.verifyPairService = verifyPairService;
    }

    @Bean
    Queue pisoUnoTopic(){
        return new Queue("piso1Queue", false);
    }

    @Bean
    Queue pisoDosTopic(){
        return new Queue("piso2Queue", false);
    }

    @Bean
    Queue pisoTresTopic(){
        return new Queue("piso3Queue", false);
    }

    @Bean
    Queue pisoCuatroTopic(){
        return new Queue("piso4Queue", false);
    }

    @Bean
    Queue pisoCincoTopic(){
        return new Queue("piso5Queue", false);
    }
    @Bean
    Queue pisoSeisTopic(){
        return new Queue("piso6Queue", false);
    }

    @Bean
    Queue todosLosPisosTopic(){
        return new Queue("todosLosPisosQueue",false);
    }
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding pisoUnoTopicBinding(Queue pisoUnoTopic, TopicExchange exchange) {
        return BindingBuilder.bind(pisoUnoTopic).to(exchange).with(verifyPairService.verifyPairMethod(pisoUnoTopic.getName()));
    }

    @Bean
    Binding pisoDosTopicBinding(Queue pisoDosTopic, TopicExchange exchange) {
        return BindingBuilder.bind(pisoDosTopic).to(exchange).with(verifyPairService.verifyPairMethod(pisoDosTopic.getName()));
    }

    @Bean
    Binding pisoTresTopicBinding(Queue pisoTresTopic, TopicExchange exchange) {
        return BindingBuilder.bind(pisoTresTopic).to(exchange).with(verifyPairService.verifyPairMethod(pisoTresTopic.getName()));
    }

    @Bean
    Binding pisoCuatroTopicBinding(Queue pisoCuatroTopic, TopicExchange exchange) {
        return BindingBuilder.bind(pisoCuatroTopic).to(exchange).with(verifyPairService.verifyPairMethod(pisoCuatroTopic.getName()));
    }

    @Bean
    Binding pisoCincoTopicBinding(Queue pisoCincoTopic, TopicExchange exchange) {
        return BindingBuilder.bind(pisoCincoTopic).to(exchange).with(verifyPairService.verifyPairMethod(pisoCincoTopic.getName()));
    }
    @Bean
    Binding pisoSeisTopicBinding(Queue pisoSeisTopic, TopicExchange exchange) {
        return BindingBuilder.bind(pisoSeisTopic).to(exchange).with(verifyPairService.verifyPairMethod(pisoSeisTopic.getName()));
    }

    @Bean
    Binding todosLosPisosTopicBinding(Queue todosLosPisosTopic, TopicExchange exchange) {
        return BindingBuilder.bind(todosLosPisosTopic).to(exchange).with("queue.todoslospisos");
    }


    @Bean
    public MessageConverter jsonMessageConverterTopic() {
        return new Jackson2JsonMessageConverter();
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    MessageListenerContainer messageListenerContainerTopic(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverterTopic());
        return rabbitTemplate;
    }


}
