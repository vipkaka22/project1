package assign2.bean;

import java.sql.Date;

public class SoldItem {
	
	private int itemId;
	private String buyername;
	private String title;
	private String author;
	private int cart;
	private int sold;
	private String soldtime;
	private String addtime;
	private String removetime;
	private String sellername;
	
	
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the buyeremail
	 */
	public String getBuyername() {
		return buyername;
	}
	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}
	
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the cart
	 */
	public int getCart() {
		return cart;
	}
	/**
	 * @param cart the cart to set
	 */
	public void setCart(int cart) {
		this.cart = cart;
	}
	/**
	 * @return the sold
	 */
	public int getSold() {
		return sold;
	}
	/**
	 * @param sold the sold to set
	 */
	public void setSold(int sold) {
		this.sold = sold;
	}
	/**
	 * @return the soldtime
	 */
	public String getSoldtime() {
		return soldtime;
	}
	public void setSoldtime(String soldtime) {
		this.soldtime = soldtime;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getRemovetime() {
		return removetime;
	}
	public void setRemovetime(String removetime) {
		this.removetime = removetime;
	}
}
