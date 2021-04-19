import java.io.Serializable;

public class ItemProduct implements Serializable {

	
	private int itemProductID;
	private String name="";
	private float price;
	
	
	public int getItemProductID() {
		return itemProductID;
	}
	public void setItemProductID(int itemProductID) {
		this.itemProductID = itemProductID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}

