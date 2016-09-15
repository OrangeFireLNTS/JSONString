package workingClass;

import jsonClasses.JSONObject;

public class Frame {
	
	JSONObject workingjobject = new JSONObject();
	
	public void convert(Object obj) throws Throwable {
		if (obj instanceof java.lang.Object) {
			JavatoJSON worker = new JavatoJSON();
			workingjobject = (JSONObject) worker.handleObject(obj);
			print(workingjobject);
		}
		else {
			System.out.println("No JavaObject");
		}
	}
	
	public void print(JSONObject obj) {
		
	}

}
