package com.model;
import javax.persistence.*;
@Entity
@Table(name="product")
public class Product {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int productId;
	  private String name;
	  private String description;
	  private int price;
	  private int quantity;
	  public void setprodId(int productId)
	  {
	    this.productId= productId;
	  }
	  public int getprodId()
	  {
	    return productId;
	  }
	  public void setName(String name)
	  {
	    this.name=name;
	  }
	  public String getName()
	  {
	    return name;
	  }
	  public void setDesc(String description)
	  {
	    this.description=description;
	  }
	  public String getDesc()
	  {
	    return description;
	  }
	  public void setPrice(int price)
	  {
		  this.price=price;
	  }
	  public int getPrice()
	  {
		  return price;
	  }
	  public void setQuantity(int quantity)
	  {
		  this.quantity=quantity;
	  }
	  public int getQuantity()
	  {
		  return quantity;
	  }
	}
