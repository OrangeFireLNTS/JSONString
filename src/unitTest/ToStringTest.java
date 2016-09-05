package unitTest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import process.ToString;

public class ToStringTest {
	
	ToString tostring = new ToString();
	
	@Test
	public void workBasic() throws Throwable {
		int i = 12;
		
		String output = tostring.work(i);
		System.out.println(output);
		assertEquals("Invalid entry", output);
	}
	
//	@Test
//	public void testArray() throws Throwable {
//		String[] array = {"Hallo", "du"};
//		
//		String output = tostring.work(array);
//		System.out.println(output);
//		assertEquals("[\"Hallo\", \"du\"]", output);
//	}
//	
//	@Test
//	public void testCollection() throws Throwable {
//		Set<Object> set = new HashSet<>();
//		set.add(22);
//		set.add(44);
//		
//		String output = tostring.work(set);
//		System.out.println(output);
//		assertEquals("[22, 44]" , output);
//	}
	
//	@Test
//	public void testMap() throws Throwable {
//		Map<String, Object> map = new HashMap<>();
//		map.put("Peter", 12);
//		map.put("Hans", 17);
//		
//		String output = tostring.work(map);
//		System.out.println(output);
//		assertEquals("\"Peter\" : 12, \"Hans\" : 17" , output);
//	}
	
	@Test
	public void testComplexType() throws Throwable {
		CustomClass obj = new CustomClass();
		
		String output = tostring.work(obj);
		System.out.println(output);
//		assertEquals("{\n\t\"__ID\" :" + System.identityHashCode(obj) 
//		+ ",\n\t\"b\" : true,\n\t\"i\" : 12,\n\t\"s\" : \"Hello\"\n}", output);
	}
}
