package dubbura.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





import com.sanyinggroup.corp.urocissa.client.model.ResultObject;

import dubbura.spring.MsgSendI;



/**
 * 原1.0中app传过来的请求数据的类
 * <P>
 * </P>
 * 
 * @author liujun
 * @date 2017年12月26日 下午7:20:13
 */
@Controller
//@RequestMapping("/aaa/")
public class TestController {
	
	
	@Autowired
	private MsgSendI send;

	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, Object> test(HttpServletRequest request) throws Exception {
		Map<String, Object> retDatas = new HashMap<String, Object>();
		JSONObject pushMsgObj = new JSONObject();
		JSONObject pushMsgBodyObj = new JSONObject();
		JSONObject pushMsgBodyExtrasObj = new JSONObject();
		
		pushMsgBodyExtrasObj.put("test", "liujun");
		
		pushMsgBodyObj.put("content_type","text");
		pushMsgBodyObj.put("msg_type","NORMAL");//您对“***”发起的背调已完成，请至报告记录页查看报告详情。
		pushMsgBodyObj.put("msg_content","您的报告已经生成了，请查看详情。");
		pushMsgBodyObj.put("title","Web任务完成");
		pushMsgBodyObj.put("extras",pushMsgBodyExtrasObj);
		
		pushMsgObj.put("uid","b3defa35366141c4bb1d08c83e3780c1");
		pushMsgObj.put("platform","all");
		pushMsgObj.put("message",pushMsgBodyObj);
		
		
		
		ResultObject result=send.spush_notification(pushMsgObj.toString());
		
		System.out.println("--------result---------:"+result);
		System.out.println("--------result---------:"+result.getStatus());
		System.out.println("--------result---------:"+result.getStatusMsg());
		System.out.println("--------result---------:"+result.getAttachment());
		
		pushMsgObj.put("result", result.getAttachment());
		
		retDatas.put("data", pushMsgObj);

		return retDatas;
	}



}
