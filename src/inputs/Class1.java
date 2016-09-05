package inputs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Class1 {
	
	public int i = 22;
	public String s =  null;
	private byte b = 2;
	private static Class2 o = new Class2();
	private static Class2 d = new Class2();
	private Class2 p = new Class2();
	private int[] array3 = {22, 33, 44};
	private Integer[] array = {1, 2, 4};
	private Object[] array2 = {array};
	private static int[][] multiarray = new int[2] [2];
	private static Set<Object> set = new HashSet<Object>();
	private static Set<Object> set2 = new HashSet<Object>();
	private static Map<Object, Object> map = new HashMap<>();
	
	static {
		set2.add(o);
		set.add(set2);
		map.put(0, d);
		map.put(1, o);
		multiarray [0][0] = 10;
		multiarray [0][1] = 20;
		multiarray [1][0] = 100;
		multiarray [1][1] = 200;
	}
	
	public String gets() {
		return s;
	}
	
	public int geti() {
		return i;
	}
	
	public byte getb() {
		return b;
	}
	
	public Class2 geto() {
		return o;
	}
	
	public Integer[] getarray() {
		return array;
	}
	
	public Object[] getarray2() {
		return array2;
	}
	
	public int[] getarray3() {
		return array3;
	}
	
	public Set<Object> getset() {
		return set;
	}
	
	public Map<Object, Object> getmap() {
		return map;
	}
	
	public int[][] getmultiarray() {
		return multiarray;
	}
}
