package crawler;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import cwe.CWE;

public class SaveCWE {
	public static JSONArray saveCWEAsJson(ArrayList<CWE> cweList) {
		
		JSONArray cweJson = new JSONArray();
		
		for(CWE cwe : cweList) {
			JSONObject obj = new JSONObject();
			obj.put("id", cwe.getId());	
			obj.put("name", cwe.getName());
			obj.put("description", cwe.getDescription());
			obj.put("childOf", cwe.getChildOf());
			obj.put("parentOf", cwe.getParentOf());
			obj.put("memberOf", cwe.getMemberOf());
			obj.put("exampleCode", cwe.getExampleCode());
			obj.put("exampleDescription", cwe.getExampleDescription());
			
			cweJson.add(obj);
			System.out.println(cwe.getId()+" "+obj.toJSONString());
		}
		
		return cweJson;
	}
}
