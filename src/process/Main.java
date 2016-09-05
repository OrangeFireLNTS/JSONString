package process;

import java.net.URL;
import java.net.URLClassLoader;

public class Main {

	public static void main(String[] args) throws Throwable {
		URL url1 = new URL("file:///c:/Users/petv/workspace/ToString/bin/");
		URL[] links = {url1};
		
		URLClassLoader ccl1 = new URLClassLoader(links);
		Class<?> klasse1 = ccl1.loadClass("inputs.Class1");
		Object obj1 = klasse1.newInstance();
		
		ToString tostring = new ToString();
		System.out.println(tostring.work(obj1));
		ccl1.close();
	}

}
