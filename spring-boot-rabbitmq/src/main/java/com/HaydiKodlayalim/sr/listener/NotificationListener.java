package com.HaydiKodlayalim.sr.listener;

import lombok.*;
import com.HaydiKodlayalim.sr.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

   @RabbitListener(queues = "${sr.rabbit.queue.name}")   
public void handleMessage(
    Notification notification){
    System.out.println("Message received: " + notification.toString());
}


}
