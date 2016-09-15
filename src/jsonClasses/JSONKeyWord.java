package jsonClasses;

public final class JSONKeyWord extends JSONValue{
	public static final JSONKeyWord t = new JSONKeyWord("true");
	public static final JSONKeyWord f = new JSONKeyWord("false");
	public static final JSONKeyWord n = new JSONKeyWord("null");
	
	private String value;
	
	private JSONKeyWord(String string) {
		this.value = string;
		}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JSONKeyWord other = (JSONKeyWord) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
}
