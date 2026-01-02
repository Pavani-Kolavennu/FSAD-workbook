package com.main;
import java.util.Scanner;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.model.Product;
import com.util.HibernateUtil;
public class Hql {

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
		      System.out.println("1)Add new product\n"
		      		+ "2)Retrieve products sorted by price in ascending order & descending order\n"
		      		+ "3)Retrieve products sorted by quantity in descending order\n"
		      		+ "4)First 3 products & Next 3 products (Pagination)\n"
		      		+ "5) a. Count total number of products\tb. Count products where quantity > 0\n"
		      		+ "6) Count products grouped by description\n"
		      		+ "7) Find minimum and maximum price\n"
		      		+ "8)a. Names starting with certain letters.\t "
		      		+ "b. Names ending with certain letters\t"
		      		+ "c. Names containing a pattern anywhere (substring)\t"
		      		+ "d. Names with an exact character length");
		      System.out.println("Select your choice: ");
		      choice = sc.nextInt();
		      switch(choice)
		      {
		        case 1: insertProduct(sc);break;
		        case 2: sortProdPrice(sc);break;
		        case 3: sortProdQuantity(sc);break;
		        case 4: pagination(sc);break;
		        case 5: prodAggregate(sc);break;
		        case 6: groupBydesc(sc);break;
		        case 7: minMax(sc);break;
		        case 8: likeClause(sc);break;
		        case 9: System.out.println("Thank you");break;
		        default: System.out.println("Wrong choice...");break;
		      }
		    }while(choice!=9);
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
		static void sortProdPrice(Scanner sc)
		{
			 Session session = HibernateUtil.getSessionFactory().openSession(); 
		     Query<Product> q1 = session.createQuery("from Product order by price desc", Product.class); 
		     System.out.println("\n-- Products Sorted by Price (Descending order) --"); 
		     for(Product p : q1.list()) 
		     { 
		            System.out.println(p.getName() + " -> Rs." + p.getPrice()); 
		     } 
		     Query<Product> q2 = session.createQuery("from Product order by price", Product.class); 
		     System.out.println("\n-- Products Sorted by Price (Ascending order) --"); 
		     for(Product p : q2.list()) 
		     { 
		            System.out.println(p.getName() + " -> Rs." + p.getPrice()); 
		     } 
		     
		    session.close();
		} 
	    static void sortProdQuantity(Scanner sc)
	    {
	    	 Session session = HibernateUtil.getSessionFactory().openSession(); 
		     Query<Product> q1 = session.createQuery("from Product order by quantity desc", Product.class); 
		     System.out.println("\n-- Products Sorted by quantity (Descending order) --"); 
		     for(Product p : q1.list()) 
		     { 
		            System.out.println(p.getName() + " -> " + p.getQuantity()); 
		     } 
		     session.close();
	    }
		static void pagination(Scanner sc)
		{
			Session session = factory.openSession();

	        Query<Product> q1 = session.createQuery("from Product", Product.class);
	        q1.setFirstResult(0);
	        q1.setMaxResults(3);
	        System.out.println("-- First 3 Products --");
	        q1.list().forEach(p -> System.out.println(p.getName()));

	        Query<Product> q2 = session.createQuery("from Product", Product.class);
	        q2.setFirstResult(3);
	        q2.setMaxResults(3);
	        System.out.println("-- Next 3 Products --");
	        q2.list().forEach(p -> System.out.println(p.getName()));

	        session.close();
		}
	   static void prodAggregate(Scanner sc)
	    {
		   Session session = HibernateUtil.getSessionFactory().openSession(); 
	       Query<Long> count1 = session.createQuery("select count(*) from Product",Long.class);
	       System.out.println("\nTotal Products: " + count1.uniqueResult());
	       Query<Long>count2 = session.createQuery("select count(*) from Product WHERE quantity>0",Long.class);
	       System.out.println("\nProducts where quantity > 0: " + count2.uniqueResult());
	       session.close();
	    }
	   static void groupBydesc(Scanner sc)
	    {
		   Session session = HibernateUtil.getSessionFactory().openSession(); 
	       Query<Object[]> group = session.createQuery("select description,count(*) from Product group by description",Object[].class);
	       System.out.println("\nProducts Grouped by description: ");
	       for (Object [] row : group.list()) {
	            System.out.println(row[0] + " -> " + row[1]);
	        }
	       session.close();
	    }
	   static void minMax(Scanner sc)
	    {
		   Session session = HibernateUtil.getSessionFactory().openSession(); 
		   
		   Query<Product> count = session.createQuery("from Product where price between 10000 and 30000",Product.class);
	       System.out.println("\nProducts where price between 10000 and 30000: " );
	       count.list().forEach(p -> System.out.println( ((Product) p).getName()));
	       
	       Query<Integer> min = session.createQuery("select min(price) from  Product",Integer.class);
	       System.out.println("\nProduct with minimum price: " + min.uniqueResult());
	       
	       Query<Integer> max = session.createQuery("select max(price) from  Product",Integer.class);
	       System.out.println("\nProducts with maximum price: " + max.uniqueResult());
	       
	       session.close();
	    }
	   static void likeClause(Scanner sc)
	    {
		   Session session = HibernateUtil.getSessionFactory().openSession(); 
		   
		   Query<Product> namesStart = session.createQuery("from Product WHERE name LIKE 'lap%'",Product.class);
	       System.out.println("\nProducts whose name starts with lap : "); 
	       namesStart.list().forEach(p -> System.out.println( ((Product) p).getName()));
	       
		   Query<Product> namesEnd = session.createQuery("from Product WHERE name LIKE '%ile'",Product.class);
	       System.out.println("\nProducts whose name end with ile : ");
	       namesEnd.list().forEach(p -> System.out.println( ((Product) p).getName()));
	       
		   Query<Product> namesPattern = session.createQuery("from Product WHERE name LIKE '%top%'",Product.class);
	       System.out.println("\nProducts whose name having substring top : ");
	       namesPattern.list().forEach(p -> System.out.println( ((Product) p).getName()));
	       
	       Query<Product> namesLength = session.createQuery("from Product WHERE length(name)=6",Product.class);
	       System.out.println("\nProducts whose names having length 6: ");
	       namesLength.list().forEach(p -> System.out.println( ((Product) p).getName()));
	       
	       session.close();
	    }
	  
}
