package com.example.demo.direct;

import com.example.demo.config.AppConfig;
import com.example.demo.websocket.WebSocketServer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lyd
 * @Description: 监听RabbitMQ中队列消息
 * @date 15:16
 */
@Component
public class DirectReceive {


	/**
	 * 监听客户端发送到RabbitMQ队列中的消息，并把消息发送给WebSocketServer
	 * @param msg
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@RabbitListener(queues = AppConfig.DIRECT_QUEUE)
	@RabbitHandler
	public void processToPre(String msg) throws InterruptedException, IOException {
		Thread.sleep(500);
		for (WebSocketServer webSocketServer : WebSocketServer.webSockets) {
			System.out.println("WebSocket从队列中取出客户端（"+webSocketServer.toString()+"）发送过来的消息：");
			webSocketServer.onMessage(msg);
		}
	}



}