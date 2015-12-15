
public class OrdersPOJO 
{
	protected String order_id;
	public String getOrder_id() 
	{
		return order_id;
	}
	public void setOrder_id(String order_id) 
	{
		this.order_id = order_id;
	}
	public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	public String getProdName() 
	{
		return prodName;
	}
	public void setProdName(String prodName) 
	{
		this.prodName = prodName;
	}
	public String getProdDesc() 
	{
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) 
	{
		this.prodDesc = prodDesc;
	}
	public String getDeliveryDate() 
	{
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) 
	{
		this.deliveryDate = deliveryDate;
	}
	public String getPrice() 
	{
		return price;
	}
	public void setPrice(String price) 
	{
		this.price = price;
	}
	public String getQuantity() 
	{
		return quantity;
	}
	public void setQuantity(String quantity) 
	{
		this.quantity = quantity;
	}
	protected String userName;
	protected String prodName;
	protected String prodDesc;
	protected String deliveryDate;
	protected String price;
	protected String quantity;
}
