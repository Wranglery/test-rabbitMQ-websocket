package com.example.demo.websocket;

import com.example.demo.direct.DirectSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/webSocket")
@Component
public class WebSocketServer {

	/**
	 * 存放每个客户端对应的WebSocket对象
	 */
	public static CopyOnWriteArraySet<WebSocketServer> webSockets = new CopyOnWriteArraySet<WebSocketServer>();


	@Autowired
	static DirectSender directSender;

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen() throws InterruptedException, IOException {
		webSockets.add(this);
		System.out.println("有新用户加入");
	}

	@OnClose
	public void onClose() throws IOException {
		webSockets.remove(this);
		System.out.println("有用户离开");
	}

	/**
	 * 收到客户端消息后调用的方法
	 */
	@OnMessage
	public void onMessage(String msg) throws InterruptedException {
		System.out.println("从客户端接受的消息： " + msg);
	}

	@OnError
	public void onError(Throwable error) {
		error.printStackTrace();
	}


}
