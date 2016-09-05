package unitTest;

import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;

public class CustomClass {
	static int i = 12;
	static boolean b = true;
	static String s = "Hello";
	static Map<String, Object> map = new HashMap<>();
	static {
		map.put("Peter", 12);
		map.put("Hans", 17);
	}
		
	public int geti() {
		return i;
	}
	
	public boolean getb() {
		return b;
	}
	
	public String gets() {
		return s;
	}
	
	public Map<String, Object> getmap() {
		return map;
	}
}
