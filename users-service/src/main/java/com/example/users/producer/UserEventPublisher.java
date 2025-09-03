package com.example.users.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.common.config.RabbitMQConstants;
import com.example.common.events.UserCreatedEvent;
import com.example.common.events.UserUpdatedEvent;
import com.example.common.events.UserDeletedEvent;
import com.example.users.model.User;

@Service
public class UserEventPublisher {

  private final RabbitTemplate tpl;
  private final String exchange;
  private final String routing;

  public UserEventPublisher(
      RabbitTemplate tpl,
      @Value(RabbitMQConstants.EXCHANGE) String exchange,
      @Value(RabbitMQConstants.ROUTING_USER_CREATED) String routing) {
    this.tpl = tpl;
    this.exchange = exchange;
    this.routing = routing;
  }

  public void publishUserCreated(User u) {
    tpl.convertAndSend(exchange, routing, new UserCreatedEvent(u.getId(), u.getUsername(), u.getEmail()));
    System.out.println("Published UserCreatedEvent for user ID: " + u.getId());
  }

  public void publishUserUpdated(User u) {
        tpl.convertAndSend(RabbitMQConstants.EXCHANGE,
                           RabbitMQConstants.ROUTING_USER_UPDATED,
                           new UserUpdatedEvent(u.getId(), u.getUsername(), u.getEmail()));
    }

    public void publishUserDeleted(String userId) {
        tpl.convertAndSend(RabbitMQConstants.EXCHANGE,
                           RabbitMQConstants.ROUTING_USER_DELETED,
                           new UserDeletedEvent(userId));
    }

}
