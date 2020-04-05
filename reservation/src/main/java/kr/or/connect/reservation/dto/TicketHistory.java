package kr.or.connect.reservation.dto;

public class TicketHistory {
	private String priceTypeName = "";
	private int price;
	private int count;

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "TicketHistory [priceTypeName=" + priceTypeName + ", price=" + price + ", count=" + count + "]";
	}
}
