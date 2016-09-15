package jsonClasses;

public class JSONFloat extends JSONNummber{
	private Double vfloat;

	public JSONFloat (Object obj) {
		setVfloat((double)obj);
	}
	
	public void setVfloat (double f) {
		vfloat = f;
	}
	
	public double getVfloat () {
		return vfloat;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JSONFloat other = (JSONFloat) obj;
		if (vfloat == null) {
			if (other.vfloat != null)
				return false;
		} else if (!vfloat.equals(other.vfloat))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vfloat == null) ? 0 : vfloat.hashCode());
		return result;
	}
}
