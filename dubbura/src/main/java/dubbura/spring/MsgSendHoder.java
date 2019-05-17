package dubbura.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.sanyinggroup.corp.urocissa.client.model.ResultObject;

@Component
//@Scope("prototype")
public class MsgSendHoder implements InitializingBean {
	
	@Autowired
	private MsgSendI send;

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("555555555555555555555555555555555555555555555555:");
		
		JSONObject pushMsgObj = new JSONObject();
		JSONObject pushMsgBodyObj = new JSONObject();
		JSONObject pushMsgBodyExtrasObj = new JSONObject();
		
		pushMsgBodyExtrasObj.put("test", "liujun");
		
		pushMsgBodyObj.put("content_type","text");
		pushMsgBodyObj.put("msg_type","NORMAL");//您对“***”发起的背调已完成，请至报告记录页查看报告详情。
		pushMsgBodyObj.put("msg_content","您的申请已经批准，请查看详情。");
		pushMsgBodyObj.put("title","任务完成");
		pushMsgBodyObj.put("extras",pushMsgBodyExtrasObj);
		
		pushMsgObj.put("uid","b3defa35366141c4bb1d08c83e3780c1");
		pushMsgObj.put("platform","all");
		pushMsgObj.put("message",pushMsgBodyObj);
		
		
		
		ResultObject result=send.spush_notification(pushMsgObj.toJSONString());
		
		System.out.println("result:"+result.getStatusMsg());
		
	}

}
