package com.example.demo.controller;

import com.example.demo.direct.DirectSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lyd
 * @Description:
 * @date 14:49
 */
@RestController
public class MessageController {

	@Autowired
	DirectSender directSender;

	/**
	 * 接口：
	 * 调用向队列中发送消息的方法
	 */
	@RequestMapping("senderMsg")
	@ResponseBody
	public void senderMsg() {
		directSender.sendDirect("我是向队列中存储的消息");
	}


}