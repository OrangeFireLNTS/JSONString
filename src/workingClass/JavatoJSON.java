package workingClass;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import jsonClasses.JSONObject;
import jsonClasses.JSONValue;
import jsonClasses.JSONArray;
import jsonClasses.JSONFloat;
import jsonClasses.JSONInt;
import jsonClasses.JSONKeyWord;
import jsonClasses.JSONString;

public class JavatoJSON {
	private static final Set<Class <?>> BASIC_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> WRAPPER_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> EASY_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> Collection_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> NUMMBERS = new HashSet<Class <?>>();
	private static final Set<Class <?>> INTEGERS = new HashSet<Class <?>>();
	private static final Set<Class <?>> FLOATINGPOINTS = new HashSet<Class <?>>();
	private Map<Integer, JSONValue> used = new HashMap<>();
	
	static {
		BASIC_TYPES.add(byte.class);
		BASIC_TYPES.add(short.class);
		BASIC_TYPES.add(int.class);
		BASIC_TYPES.add(long.class);
		BASIC_TYPES.add(float.class);
		BASIC_TYPES.add(double.class);
		BASIC_TYPES.add(boolean.class);
		BASIC_TYPES.add(char.class);
		
		WRAPPER_TYPES.add(Byte.class);
		WRAPPER_TYPES.add(Short.class);
		WRAPPER_TYPES.add(Integer.class);
		WRAPPER_TYPES.add(Long.class);
		WRAPPER_TYPES.add(Float.class);
		WRAPPER_TYPES.add(Double.class);
		WRAPPER_TYPES.add(Boolean.class);
		WRAPPER_TYPES.add(Character.class);
		
		EASY_TYPES.addAll(BASIC_TYPES);
		EASY_TYPES.addAll(WRAPPER_TYPES);
		
		EASY_TYPES.add(String.class);
		EASY_TYPES.add(Date.class);
		
		Collection_TYPES.add(Set.class);
		Collection_TYPES.add(List.class);
		
		INTEGERS.add(byte.class);
		INTEGERS.add(short.class);
		INTEGERS.add(int.class);
		INTEGERS.add(long.class);
		INTEGERS.add(Byte.class);
		INTEGERS.add(Short.class);
		INTEGERS.add(Integer.class);
		INTEGERS.add(Long.class);
		
		FLOATINGPOINTS.add(Float.class);
		FLOATINGPOINTS.add(Double.class);
		FLOATINGPOINTS.add(float.class);
		FLOATINGPOINTS.add(double.class);
		
		NUMMBERS.addAll(INTEGERS);
		NUMMBERS.addAll(FLOATINGPOINTS);
	}
	
	public JSONValue getRegistred(Object obj) {
		return used.get(System.identityHashCode(obj));
	}
	
	private void addtoRegidtred(Object obj, JSONValue value) {
		used.put(System.identityHashCode(obj), value);
	}
	
	//Methode die den Objekttyp bestimmt und dieses an die entsprechende Methode weitergiebt
	public JSONValue handleObject(Object obj) throws Throwable {
		JSONValue value = getRegistred(obj);
		if (value == null) {
			
			if (obj == null)
				value = handleKeyWord(obj);
			else if (EASY_TYPES.contains(obj.getClass()))
				value = handleEasyTypes(obj);
			else if (obj.getClass().isArray())
				value = handleArray(obj);
			else if (obj instanceof Map)
				value = handleMaps((Map<Object, Object>)obj);
			else if (obj instanceof Collection)
				value = handleCollection(obj);
			else
				value = handleComplexTypes(obj);
		}
		
		return value;
	}

	private JSONValue handleComplexTypes(Object obj) throws Throwable {
			Object klasse = obj.getClass().newInstance();
			Map<String, Object> map = new HashMap<>();
			List< Method > methods = Arrays.asList(obj.getClass().getMethods());
			List< Method > getters = new ArrayList<>();
			for (Method method : methods) {
				if (method.getName().startsWith("get") &&
				method.getParameterTypes().length == 0 &&
				method.getReturnType() != void.class  &&
				method.getName() != "getClass") {
					getters.add(method);
				}
			}
			for (Method getter : getters) {
				String name = getter.getName().substring(3);
				map.put(name, getter.invoke(klasse));
			}
			return handleGoodMaps(map);		
	}

	private JSONValue handleUsedComplexTypes(Object obj) {
		System.out.println("Are u mad?!");
		return null;
		
	}

	private JSONValue handleCollection(Object obj) throws Throwable{
		Collection<Object> collection = (Collection<Object>)obj;
		Object[] array = collection.toArray();
		JSONValue retarr = null;
		addtoRegidtred(obj, retarr);
		retarr = handleasArray(array);
		return retarr;
		
	}

	private JSONValue handleasArray(Object array) throws Throwable {
		JSONArray retarr = new JSONArray();
		
		int lenght = Array.getLength(array);
		
		for (int i = 0; i < lenght; i++) {
			retarr.addVarray(handleObject(Array.get(array, i)));
		}
		return retarr;
	}

	private JSONValue handleMaps(Map<Object, Object> obj) throws Throwable {
		Set<?> set = obj.keySet();
		Map<String, Object> map = new HashMap<>();
			for ( Object key : set) {
				map.put((String) key, obj.get(key));
			}
			return handleGoodMaps(map);

	}

	private JSONValue handleGoodMaps(Map<String, Object> map) throws Throwable {
			Set<String> keys = map.keySet();
			List<String> keylist = new ArrayList<>();
			keylist.addAll(keys);
			Collections.sort( keylist, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return -o1.compareTo(o2);
				}
			} );
			JSONObject retobj = new JSONObject();
			for (String key : keylist) {
				retobj.setPair((JSONString)handleObject(key), handleObject(map.get(key)));
			}
			return retobj;
	}

	private JSONValue handleArray(Object obj) throws Throwable {
		JSONArray retarr = new JSONArray();
		addtoRegidtred(obj, retarr);
		retarr = (JSONArray) (handleasArray(obj));
		return retarr;
		
	}

	private JSONValue handleEasyTypes(Object obj) {
		JSONValue value = null;
		if (NUMMBERS.contains(obj.getClass())) {
			value = handleNummbers(obj);
		}
		if (obj instanceof String || obj instanceof Character) {
			value = handleString(obj);
		}
		if (obj instanceof Boolean) {
			value = handleKeyWord(obj);
		}
		else {
			value = null;
		}
		return value;
	}

	private JSONValue handleKeyWord(Object obj) {
		if (obj instanceof Boolean) {
			if ((Boolean)obj == true) {
				return JSONKeyWord.t;
			}
			else {
				return JSONKeyWord.f;
			}
		}
		else {
			return JSONKeyWord.n;
		}
		
	}

	private JSONValue handleString(Object obj) {
		JSONString retstr = new JSONString(obj);
		return retstr;
		
	}

	private JSONValue handleNummbers(Object obj) {
		if (INTEGERS.contains(obj.getClass())) {
			JSONInt retint = new JSONInt(obj);
			return retint;
		}
		else if (FLOATINGPOINTS.contains(obj.getClass())) {
			JSONFloat retfloat = new JSONFloat(obj);
			return retfloat;
		}
		else {
			return null;
		}
	}

}
