package co.wildsoft.swt.coffee.suppliers;

public class Supplier {
	private Integer coffeetype;
	private String name;
	private String address;
	private String telephone;
	
	public Supplier(Integer coffeetype, String name, String address, String telephone) {
		this.coffeetype = coffeetype;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCoffeetype() {
		return coffeetype;
	}

	public void setCoffeetype(Integer coffeetype) {
		this.coffeetype = coffeetype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
