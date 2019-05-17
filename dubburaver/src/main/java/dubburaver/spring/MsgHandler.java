package dubburaver.spring;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.sanyinggroup.corp.urocissa.client.model.ResultObject;
import com.sanyinggroup.corp.urocissa.server.api.model.MiddleMsg;
import com.sanyinggroup.corp.urocissa.server.api.service.MsgEvent;
import com.sanyinggroup.corp.urocissa.server.api.service.MsgServiceHandler;

public class MsgHandler implements MsgServiceHandler {

	public static Map<String, InvokerHolder> invoderMap = new java.util.HashMap<String, InvokerHolder>();

	/**
	 * 收到消息后，把消息中的调查客户信息，补充进调查客户端对象中。 调查客户端是在客户端上线事件中产生的，这里只补信息。
	 */
	@Override
	public MiddleMsg handleMsgEvent(MsgEvent dm, MiddleMsg msg) {
		// TODO Auto-generated method stub

		System.out.println("invoderMap.size():"+invoderMap.size());
		Iterator<Entry<String, InvokerHolder>> it=invoderMap.entrySet().iterator();
		
		while(it.hasNext()){
			Entry aa= it.next();
			System.out.println("map:"+aa.getKey()+"|"+aa.getValue());
		}
		
		String action = msg.getHeader().getAction();
		String msgBody=msg.getBody().toString();
		

		try {
			System.out.println("action:"+action);
			System.out.println("msgBody:"+msgBody);
			
			InvokerHolder ref = invoderMap.get(action);
			System.out.println("get the Invoker!!!! ref:"+ref);
			
			Object[] arguments=new Object[]{msgBody};
			ResultObject resultObject = (ResultObject) ref.doInvoke(arguments);

			String body = "" + resultObject.getStatus() + resultObject.getStatusMsg();
			
//			body=resultObject.getStatusMsg();
			
			System.out.println("invoker result:-----------"+body);
			msg.setBody(body);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// logger.debug("【处理客户心跳】返回给客户端数据：" +
		// JSONObject.fromObject(msg).toString());
		return msg;

	}

}
