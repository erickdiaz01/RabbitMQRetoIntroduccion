package com.sofka.broker.config;

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
 * Clase de configuracion para enviar el correo a todo el piso
 */

@Configuration
public class RabbitMQFanoutConfig {

    @Bean
    Queue pisoUnoFanout(){
        return new Queue("piso1Queue", false);
    }

    @Bean
    Queue pisoDosFanout(){
        return new Queue("piso2Queue", false);
    }

    @Bean
    Queue pisoTresFanout(){
        return new Queue("piso3Queue", false);
    }

    @Bean
    Queue pisoCuatroFanout(){
        return new Queue("piso4Queue", false);
    }

    @Bean
    Queue pisoCincoFanout(){
        return new Queue("piso5Queue", false);
    }
    @Bean
    Queue pisoSeisFanout(){
        return new Queue("piso6Queue", false);
    }

    @Bean
    Queue todosLosPisosFanout(){
        return new Queue("todosLosPisosQueue",false);
    }
    @Bean
    FanoutExchange exchange(){
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    Binding pisoUnoFanoutBinding(Queue pisoUnoFanout, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoUnoFanout).to(exchange);
    }

    @Bean
    Binding pisoDosFanoutBinding(Queue pisoDosFanout, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoDosFanout).to(exchange);
    }

    @Bean
    Binding pisoTresFanoutBinding(Queue pisoTresFanout, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoTresFanout).to(exchange);
    }

    @Bean
    Binding pisoCuatroFanoutBinding(Queue pisoCuatroFanout, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoCuatroFanout).to(exchange);
    }

    @Bean
    Binding pisoCincoFanoutBinding(Queue pisoCincoFanout, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoCincoFanout).to(exchange);
    }
    @Bean
    Binding pisoSeisFanoutBinding(Queue pisoSeisFanout, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoSeisFanout).to(exchange);
    }

    @Bean
    Binding todosLosPisosFanoutBinding(Queue todosLosPisosFanout, FanoutExchange exchange) {
        return BindingBuilder.bind(todosLosPisosFanout).to(exchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
