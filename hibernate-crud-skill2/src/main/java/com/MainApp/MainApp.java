package com.MainApp;
import com.model.Product;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.*;
public class MainApp 
{
	static SessionFactory factory = HibernateUtil.getSessionFactory();
	public static void main(String[] args)
	{
		Session session = factory.openSession();
	    Transaction tx = session.beginTransaction();
	    Scanner sc = new Scanner(System.in);
	    int choice;
	    do
	    {
	      System.out.println(".....Main menu.....");
	      System.out.println("1.Add new product\n2.Retrieve product information\n3.Update Price\n4.Update Quantity\n5.Delete product.");
	      System.out.println("Select your choice: ");
	      choice = sc.nextInt();
	      switch(choice)
	      {
	        case 1: insertProduct(sc);
	        		break;
	        case 2 : displayProduct(sc);
	             	break;
	        case 3 : updatePrice(sc);
	            	break;
	        case 4: updateQuantity(sc);
	            	break;
	        case 5: deleteProduct(sc);
            		break;
	        case 6: System.out.println("Thank you");
	            	break;
	        default: System.out.println("Wrong choice...");
	              	break;
	      }
	    }while(choice!=6);
	    sc.close();
	    tx.commit();
	    factory.close();
	    session.close();
	  }
	
	static void insertProduct(Scanner sc)
	{
		Session session = factory.openSession();
	    Transaction tx = session.beginTransaction();
	    System.out.println("Enter Product name : ");
	    String prodName = sc.next();
	    Product p = new Product();
	    p.setName(prodName);
	    System.out.println("Enter product description: ");
	    String desc = sc.next();
	    p.setDesc(desc);
	    System.out.println("Enter product price : ");
	    int price = sc.nextInt();
	    p.setPrice(price);
	    System.out.println("Enter product quantity : ");
	    int quan = sc.nextInt();   
	    p.setQuantity(quan);
	    session.persist(p);
	    tx.commit();
	    session.close();
	    System.out.println("Product inserted successfully");
	}
	static void displayProduct(Scanner sc)
	{
		Session session = factory.openSession();
	    System.out.println("Enter Product id : ");
	    int id = sc.nextInt();
	    Product p = session.get(Product.class,id);
	    if(p!=null)
	    {
	      System.out.println("Name : "+p.getName());
	      System.out.println("Description : "+p.getDesc());
	      System.out.println("Price : "+p.getPrice());
	      System.out.println("Quantity : "+p.getQuantity());
	    }
	    else
	    {
	      System.out.println("Product not found!");
	    }
	    session.close();
	} 
   static void updatePrice(Scanner sc)
   {
    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    System.out.println("Enter product id : ");
    int id = sc.nextInt();
    Product p = session.get(Product.class,id);
    if(p!=null)
    {
      System.out.println("Enter new price: ");
      p.setPrice(sc.nextInt());
      tx.commit();
      System.out.println("Price updated!");
    }
    else
    {
      System.out.println("Product not found!");
      tx.rollback();
    }
    session.close();
   }
  static void updateQuantity(Scanner sc)
  {
    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    System.out.println("Enter product id : ");
    int id = sc.nextInt();
    Product p = session.get(Product.class,id);
    if(p!=null)
    {
      System.out.println("Enter new quantity: ");
      p.setQuantity(sc.nextInt());
      tx.commit();
      System.out.println("Quantity updated!");
    }
    else
    {
      System.out.println("Product not found!");
      tx.rollback();
    }
    session.close();
  }
  static void deleteProduct(Scanner sc)
  {
    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    System.out.println("Enter product id : ");
    int id = sc.nextInt();
    Product p = session.get(Product.class,id);
    if(p!=null)
    {
      session.remove(p);
      tx.commit();
      System.out.println("Product deleted!");
    }
    else
    {
      System.out.println("Product not found!");
      tx.rollback();
    }
    session.close();
  }
}
 