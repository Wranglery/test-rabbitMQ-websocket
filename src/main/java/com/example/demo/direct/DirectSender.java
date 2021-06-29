package com.example.demo.direct;

import com.example.demo.config.AppConfig;
import com.example.demo.websocket.WebSocketServer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lyd
 * @Description:
 * @date 14:50
 */
@Component
public class DirectSender {

	@Autowired
	RabbitTemplate rabbitTemplate;

	/**
	 * 向RabbitMQ队列中发送消息，方便后面客户端可以从队列中读取该消息
	 *
	 * 也可以用来代替客户端向队列中发送消息，我不会写前端连接rabbitmq的代码，就用这个接口代替了。或者在RabbitMQ的管理面板中手动输入数据
	 * @param msg
	 */
	public void sendDirect(String msg) {

		for (WebSocketServer webSocketServer : WebSocketServer.webSockets) {
			rabbitTemplate.convertAndSend(AppConfig.DIRECT_EXCHANGE, AppConfig.ROUTING_KEY, msg +" ("+webSocketServer.toString()+")");
		}

	}


}