package jsonClasses;

public class JSONInt extends JSONNummber{
	private Integer vint;
	
	public JSONInt (Object obj) {
		setVint((Integer)obj);
	}
	
	public void setVint (Integer i) {
		vint = i;
	}
	
	public long getVint () {
		return vint.longValue();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JSONInt other = (JSONInt) obj;
		if (vint == null) {
			if (other.vint != null)
				return false;
		} else if (!vint.equals(other.vint))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vint == null) ? 0 : vint.hashCode());
		return result;
	}
}
