package junittests;

import static org.junit.Assert.*;

import org.junit.Test;

import jsonClasses.JSONArray;
import jsonClasses.JSONFloat;
import jsonClasses.JSONInt;
import jsonClasses.JSONKeyWord;
import jsonClasses.JSONNummber;
import jsonClasses.JSONObject;
import jsonClasses.JSONString;
import jsonClasses.JSONValue;
import workingClass.JavatoJSON;

public class JavatoJSONTest {
	
	@Test
	public void testequalsIntFalse() throws Throwable {
		JSONInt obj1 = new JSONInt(12);
		JSONInt obj2 = new JSONInt(13);
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsIntTrue() throws Throwable {
		JSONInt obj1 = new JSONInt(666);
		JSONInt obj2 = new JSONInt(666);
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsFloatFlase() throws Throwable {
		JSONFloat obj1 = new JSONFloat(22.12);
		JSONFloat obj2 = new JSONFloat(13.33);
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsFloatTrue() throws Throwable {
		JSONFloat obj1 = new JSONFloat(99.99);
		JSONFloat obj2 = new JSONFloat(99.99);
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsStringFlase() throws Throwable {
		JSONString obj1 = new JSONString("Hallo");
		JSONString obj2 = new JSONString("Bye");
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsStringTrue() throws Throwable {
		JSONString obj1 = new JSONString("Hy");
		JSONString obj2 = new JSONString("Hy");
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsArrayFlase() throws Throwable {
		JSONArray obj1 = new JSONArray();
			JSONString value1 = new JSONString("Hy ");
			JSONString value2 = new JSONString("Dude");
			obj1.addVarray(value1);
			obj1.addVarray(value2);
		JSONArray obj2 = new JSONArray();
			JSONString value3 = new JSONString("Hy ");
			JSONString value4 = new JSONString("Friend");
			obj1.addVarray(value3);
			obj1.addVarray(value4);
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsArrayTrue() throws Throwable {
		JSONArray obj1 = new JSONArray();
			JSONString value1 = new JSONString("Hy ");
			JSONString value2 = new JSONString("Dude");
			obj1.addVarray(value1);
			obj1.addVarray(value2);
		JSONArray obj2 = new JSONArray();
			obj2.addVarray(value1);
			obj2.addVarray(value2);
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsObjectFlase() throws Throwable {
		JSONObject obj1 = new JSONObject();
			JSONString value1 = new JSONString("hi");
			JSONFloat value2 = new JSONFloat(13.13);
			JSONArray value3 = new JSONArray();
				JSONInt entry1 = new JSONInt(12);
				JSONInt entry2 = new JSONInt(22);
				value3.addVarray(entry1);
				value3.addVarray(entry2);
			JSONString key1 = new JSONString("first");
			JSONString key2 = new JSONString("second");
			JSONString key3 = new JSONString("third");
		obj1.setPair(key1, value1);
		obj1.setPair(key2, value2);
		obj1.setPair(key3, value3);
		
		JSONObject obj2 = new JSONObject();
			JSONString value4 = new JSONString("nop");
			JSONFloat value5 = new JSONFloat(666.666);
			JSONArray value6 = new JSONArray();
				JSONInt entry3 = new JSONInt(666);
				JSONInt entry4 = new JSONInt(12);
				value3.addVarray(entry3);
				value3.addVarray(entry4);
			JSONString key4 = new JSONString("first");
			JSONString key5 = new JSONString("second");
			JSONString key6 = new JSONString("third");
		obj2.setPair(key4, value4);
		obj2.setPair(key5, value5);
		obj2.setPair(key6, value6);
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsObjectTrue() throws Throwable {
		JSONObject obj1 = new JSONObject();
			JSONString value1 = new JSONString("hi");
			JSONFloat value2 = new JSONFloat(666.666);
			JSONArray value3 = new JSONArray();
				JSONInt entry1 = new JSONInt(666);
				JSONInt entry2 = new JSONInt(12);
				value3.addVarray(entry1);
				value3.addVarray(entry2);
			JSONString key1 = new JSONString("first");
			JSONString key2 = new JSONString("second");
			JSONString key3 = new JSONString("third");
		obj1.setPair(key1, value1);
		obj1.setPair(key2, value2);
		obj1.setPair(key3, value3);
		
		JSONObject obj2 = new JSONObject();
			JSONString value4 = new JSONString("hi");
			JSONFloat value5 = new JSONFloat(666.666);
			JSONArray value6 = new JSONArray();
				JSONInt entry3 = new JSONInt(666);
				JSONInt entry4 = new JSONInt(12);
				value6.addVarray(entry3);
				value6.addVarray(entry4);
			JSONString key4 = new JSONString("first");
			JSONString key5 = new JSONString("second");
			JSONString key6 = new JSONString("third");
		obj2.setPair(key4, value4);
		obj2.setPair(key5, value5);
		obj2.setPair(key6, value6);
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testSimpleIntFloat() throws Throwable {
		
		JavatoJSON tester = new JavatoJSON();
		
		TestIntFloat test = new TestIntFloat();
		
		JSONObject expectedobj = new JSONObject();
		
		JSONInt v1 = new JSONInt(666);
		JSONFloat v2 = new JSONFloat(12.1234);
		
		JSONString s1 = new JSONString("integer");
		JSONString s2 = new JSONString("floatingpoint");
		
		expectedobj.setPair(s1, v1);
		expectedobj.setPair(s2, v2);
		
		JSONObject actualobj = (JSONObject) tester.handleObject(test);
		
		assertEquals(expectedobj, actualobj);
	}
	
	@Test
	public void testSimpleStringKeyWord() throws Throwable {
		
		JavatoJSON tester = new JavatoJSON();
		
		TestStringKeyWords test = new TestStringKeyWords();
		
		JSONObject expectedobj = new JSONObject();
		
		JSONString v1 = new JSONString("Hallo");
		
		JSONString s1 = new JSONString("string");
		JSONString s2 = new JSONString("keyword");
		
		expectedobj.setPair(s1, v1);
		expectedobj.setPair(s2, JSONKeyWord.t);
		
		JSONObject actualobj = (JSONObject) tester.handleObject(test);
		
		assertEquals(expectedobj, actualobj);
	}
	
	@Test
	public void testMap() throws Throwable {
		
		JavatoJSON tester = new JavatoJSON();
		
		TestMap test = new TestMap();
		
		JSONObject expectedobj = new JSONObject();
		
		JSONString s1 = new JSONString("map");
		
		JSONObject map = new JSONObject();
		
			JSONString ms1 = new JSONString("first");
			JSONString ms2 = new JSONString("second");
			
			JSONInt mv1 = new JSONInt(12);
			JSONString mv2 = new JSONString("place");
		
		map.setPair(ms1, mv1);	
		map.setPair(ms2, mv2);
		
		expectedobj.setPair(s1, map);
		
		JSONObject actualobj = (JSONObject) tester.handleObject(test);
		
		assertEquals(expectedobj, actualobj);
	}
	
	@Test
	public void testarray() throws Throwable {
		
		JavatoJSON tester = new JavatoJSON();
		
		TestArray test = new TestArray();
		
		JSONObject expectedobj = new JSONObject();
		
		JSONArray v1 = new JSONArray();
		
		JSONString s1 = new JSONString("array");
		
			JSONInt av1 = new JSONInt(11);
			JSONInt av2 = new JSONInt(22);
			JSONInt av3 = new JSONInt(33);
			JSONInt av4 = new JSONInt(44);
			
		v1.addVarray(av1);
		v1.addVarray(av2);
		v1.addVarray(av3);
		v1.addVarray(av4);
		
		expectedobj.setPair(s1, v1);
		
		JSONObject actualobj = (JSONObject) tester.handleObject(test);
		
		assertEquals(expectedobj, actualobj);
	}
	
	@Test
	public void testUsedBasic() throws Throwable {
		JavatoJSON tester = new JavatoJSON();
		
		TestArray test = new TestArray();
		
		JSONValue obj1 = tester.handleObject(test);
		JSONValue obj2 = tester.handleObject(test);
		
		assertTrue(obj1 == obj2);
	}
	
	@Test
	public void testUsedComplex() throws Throwable {
		JavatoJSON tester = new JavatoJSON();
		
		TestUsed1 test = new TestUsed1();
		
		JSONValue obj1 = tester.handleObject(test);
		JSONValue obj2 = tester.handleObject(test);
		
		assertTrue(obj1 == obj2);
	}

}
