package process;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
public class ToString {
	
	private static final Set<Class <?>> BASIC_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> WRAPPER_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> EASY_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> Collection_TYPES = new HashSet<Class <?>>();
	private Set<Number> used = new HashSet<>();
	
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
		
	}
	
	private StringWriter sw = new StringWriter();
	private PrintWriter pw = new PrintWriter(sw);
	private PrintWriter tpw = new PrintWriter(sw);
	
	private int tabCounter = 0;
	private boolean tabsRequired = false;
	
	public ToString() {
	}
	
	//Einstiegsmethode
	public String work(Object obj) throws Throwable {
		if (EASY_TYPES.contains(obj.getClass())){
			pw.print("Invalid entry");
		}
		else {
			handleObject(obj);
			pw.flush();
		}
		return sw.toString();
	}
	
	//Methode die den Objekttyp bestimmt und dieses an die entsprechende Methode weitergiebt
	private void handleObject(Object obj) throws Throwable {
		pw.print("");
		if (obj == null) {
			handleNULL(obj);
		}
		else if (EASY_TYPES.contains(obj.getClass())) {
			handleEasyTypes(obj);
		}
		else if (obj.getClass().isArray()) {
			handleArray(obj);
		}
		else if (obj instanceof Map) {
			handleMaps((Map<Object, Object>)obj);
		}
		else if (obj instanceof Collection){
			handleCollection((Collection<?>) obj);
		}
		else {
			handleComplexTypes(obj);
		}
	}

	private void handleMaps(Map<Object, Object> obj) throws Throwable {
		Set<?> set = obj.keySet();
		Object[] array = set.toArray();
		Map<String, Object> map = new HashMap<>();
		if (array.getClass().getComponentType().equals(String.class)) {
			for ( Object key : set) {
				map.put((String) key, obj.get(key));
			}
			handleGoodMaps(map);
		}
	}

	//Methode für einfachauszugebende Objekte
	private void handleEasyTypes(Object obj) {
		if (obj instanceof String) {
			print("\"" + obj.toString() + "\"");
		}
		else if (obj instanceof Character) {
			print("\"" + obj.toString() + "\"");
		}
		else {
			print(obj.toString());
		}
	}
	
	//Methode falls obj NULL ist
	private void handleNULL(Object obj) {
		print("null");
		
	}

	//Methode für komplexe Objekte welche bereits ausgegeben wurden
	private void handleUsedComplexTypes(Object obj) {
		print("{");
		println("");
		incTab();
		println("\"__IDref\" : " + System.identityHashCode(obj));
		decTab();
		print("}");
	}

	//Methode für Maps
	private void handleGoodMaps(Map<String, Object> map) throws Throwable {
		if (used.contains(System.identityHashCode(map))){
			handleUsedComplexTypes(map);
		}
		else {
			used.add(System.identityHashCode(map));
			Collection<String> keys = map.keySet();
			List<String> keylist = new ArrayList<>();
			keylist.addAll(keys);
			Collections.sort( keylist, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			} );
			int i = 1;
			print("{");
			println("");
			incTab();
			println("\"__ID\" : " + System.identityHashCode(map) + ",");
			for (Object key : keylist) {
				print("\"" + key.toString().toLowerCase() + "\" : ");
				handleObject(map.get(key));
				if (i < map.size()) {
					print(",");
					i++;
				}
				println("");
			}
			decTab();
			print("}");
		}
	}

	//Methode für Collections
	private void handleCollection(Collection<?> collection) throws Throwable {
		print("[");
		int i = 1;
		for (Object entry : collection) {
			handleObject(entry);
			if (i < collection.size()) {
				pw.print(", ");
				i++;
			}
		}
		print("]");
	}

	//Methode für komplexe Objekte
	private void handleComplexTypes(Object obj) throws Throwable {
		if (used.contains(System.identityHashCode(obj))){
			handleUsedComplexTypes(obj);
		}
		else {
			used.add(System.identityHashCode(obj));
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
			handleGoodMaps(map);
		}
	}
	
	private void incTab()
	{
		++tabCounter;
	}
	
	private void decTab()
	{
		--tabCounter;
	}
	
	private void print(String msg)
	{
		printIdentationIfNecessary();
		pw.print(msg);
	}
	
	private void println(String msg)
	{
		printIdentationIfNecessary();
		pw.println(msg);
		tabsRequired = true;
	}
	
	private void printIdentationIfNecessary()
	{
		if ( tabsRequired == true )
		{
			for ( int i = 0 ; i < tabCounter ; ++i ) {
				pw.print( "\t" );
			}
			tabsRequired = false;
		}
	}
	
	//Methode für Arrays
	private void handleArray(Object array) throws Throwable {
		pw.print("[");
		for ( int i = 0; i<Array.getLength(array); i++) {
			handleObject(Array.get(array, i));
			if (i<Array.getLength(array)-1) {
				pw.print(", ");
			}
		}
		pw.print("]");
	}
}
