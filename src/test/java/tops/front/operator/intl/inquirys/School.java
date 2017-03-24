package tops.front.operator.intl.inquirys;

public class School {
	
    private String name;
    private String Address;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public String toString() {
		return "School [name=" + name + ", Address=" + Address + "]";
	}
    
}
