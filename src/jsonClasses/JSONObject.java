package jsonClasses;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JSONObject extends JSONValue{
	private Map<JSONString, JSONValue> pairs = new HashMap<>();
	
	public void setPair (JSONString s, JSONValue v) {
		pairs.put(s, v);
	}
	
	public JSONValue getPair(JSONString s) {
		return pairs.get(s);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pairs == null) ? 0 : pairs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JSONObject other = (JSONObject) obj;
		if (pairs == null) {
			if (other.pairs != null)
				return false;
		} else if (!pairs.equals(other.pairs))
			return false;
		return true;
	}
}
