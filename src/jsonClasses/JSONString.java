package jsonClasses;

public class JSONString extends JSONValue{
	private String vstring;
	
	public JSONString (Object obj) {
		setVstring((String)obj);
	}
	
	public void setVstring (String s) {
		vstring = s;
	}
	
	public String getVstring () {
		return vstring;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JSONString other = (JSONString) obj;
		if (vstring == null) {
			if (other.vstring != null)
				return false;
		} else if (!vstring.equals(other.vstring))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vstring == null) ? 0 : vstring.hashCode());
		return result;
	}
}
