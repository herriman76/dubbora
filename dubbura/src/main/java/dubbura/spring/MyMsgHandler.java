package dubbura.spring;

import com.sanyinggroup.corp.urocissa.client.api.handler.MsgHandler;
import com.sanyinggroup.corp.urocissa.client.model.MiddleMsg;

public class MyMsgHandler implements MsgHandler {

	@Override
	public void callback(MiddleMsg msg) {
		// TODO Auto-generated method stub
		System.out.println("----------");
		System.out.println("----------");
		System.out.println("【消息发送成功】"+msg.getBody());
		System.out.println("----------");
		System.out.println("----------");
	}

}
