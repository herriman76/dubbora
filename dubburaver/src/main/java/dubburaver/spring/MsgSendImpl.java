package dubburaver.spring;

import org.springframework.stereotype.Component;

import com.sanyinggroup.corp.urocissa.client.model.ResultObject;

@Component
public class MsgSendImpl implements MsgSendI {

	@Override
	public ResultObject spush_notification(String msg) {
		// TODO Auto-generated method stub
		
		ResultObject resultObject=new ResultObject();
		
		resultObject.setStatus(9527);
		resultObject.setStatusMsg("我从服务端实现了这个接口！");
		return resultObject;
	}

}
