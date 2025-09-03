package com.example.courses.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.common.config.RabbitMQConstants;
import com.example.common.events.CourseCreatedEvent;
import com.example.common.events.CourseUpdatedEvent;
import com.example.common.events.CourseDeletedEvent;
import com.example.courses.model.Course;

@Service
public class CourseEventPublisher {

  private final RabbitTemplate tpl;
  private final String exchange;
  private final String routing;

  public CourseEventPublisher(
      RabbitTemplate tpl,
      @Value("${app.rabbitmq.exchange}") String exchange,
      @Value("${app.rabbitmq.routing.courseCreated}") String routing) {
    this.tpl = tpl; this.exchange = exchange; this.routing = routing;
  }

  public void publishCourseCreated(Course c) {
    tpl.convertAndSend(exchange, routing, new CourseCreatedEvent(c.getId(), c.getTitle()));
  }

  public void publishCourseUpdated(Course c) {
        tpl.convertAndSend(RabbitMQConstants.EXCHANGE,
                           RabbitMQConstants.ROUTING_COURSE_UPDATED,
                           new CourseUpdatedEvent(c.getId(), c.getTitle()));
    }

    public void publishCourseDeleted(String courseId) {
        tpl.convertAndSend(RabbitMQConstants.EXCHANGE,
                           RabbitMQConstants.ROUTING_COURSE_DELETED,
                           new CourseDeletedEvent(courseId));
    }

}
