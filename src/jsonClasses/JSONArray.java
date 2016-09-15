package jsonClasses;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class JSONArray extends JSONValue{
	private List<JSONValue> varray = new ArrayList<>();
	
	public void addVarray (JSONValue v) {
		varray.add(v);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JSONArray other = (JSONArray) obj;
		if (varray == null) {
			if (other.varray != null)
				return false;
		} else if (!varray.equals(other.varray))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((varray == null) ? 0 : varray.hashCode());
		return result;
	}
}
