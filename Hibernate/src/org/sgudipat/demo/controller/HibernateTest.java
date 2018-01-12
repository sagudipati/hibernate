package org.sgudipat.demo.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.query.QueryProducer;
import org.sgudipat.demo.dto.Address;
import org.sgudipat.demo.dto.Category;
import org.sgudipat.demo.dto.Phone;
import org.sgudipat.demo.dto.Product;
import org.sgudipat.demo.dto.UserDetails;

public class HibernateTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserDetails user = new UserDetails();
		Address addr = new Address();
		Address addr1 = new Address();
		Phone ph = new Phone();
		user.setUserId(1);
		user.setUserName("sakura");
		user.setJoinedDate(new Date());
		//user.setAddress("California");
		user.setDescription("This is not saved in db-- transient value");

		addr.setCity("Milpitas");
		addr.setState("CA");
		addr.setPincode("30002");
		addr.setStreet("loop");
		user.setAddress(addr);
		
		addr1.setCity("Milpi");
		addr1.setState("CA");
		addr1.setPincode("31102");
		addr1.setStreet("loop1");
		user.setOfficeAddress(addr1);
		
		ph.setHome("37391819");
		ph.setMobile("329873987");
		user.getListOfPhones().add(ph);
		
		Product prod = new Product();
		Category cat = new Category();
		Category cat1 = new Category();
		prod.setProductId(1);
		prod.setProductName("soap");
		cat.setCatId(123);
		cat.setCatName("Bathing");
		
		cat1.setCatId(124);
		cat1.setCatName("Toiletry");
		
		prod.getListOfCategories().add(cat);
		prod.getListOfCategories().add(cat1);
		
		// Persists the user object
		SessionFactory sessionFac = new Configuration().configure().buildSessionFactory();
		Session session = sessionFac.openSession();
		session.beginTransaction();
		session.save(user);
		session.persist(prod); //whenever a persist happens then the cascading happens 
		//instead of saving these list of categories(1-M rel) we can use cascading to persist this data
		//session.save(cat);
		//session.save(cat1);
		
		session.getTransaction().commit();
		session.close();
		
		// Restore the object using pk
		user= null;
		Session session1 = sessionFac.openSession();
		session1.beginTransaction();
		//passed the p.k
		user = (UserDetails) session1.get(UserDetails.class,1);
		//when the user object is retrieved. It only gets the values of the variables(1st level values) in the class and not the embedded objects
		//Lazy fetching: when you call the get method, hibernate fires another query to Phone's table to get the list of info 
		user.getListOfPhones();
		
	//hibernate has a proxy object thru which we are getting the instance of the object but with eager fetching we can get hold of the object even after the session is closed
	//Eager will get all the objects. 
	
	//select * from user details table
		//can also have query as "select userName from UserDetails"
		//select new map(userId,userName) from UserDetails"
		Query query = session.createQuery("from UserDetails where userId > 5");
		//pagination
		query.setFirstResult(5);
		query.setMaxResults(4);
		List<UserDetails> users = (List<UserDetails>) query.list();
		
		//SQL injection 
		int minUserId=5;
		String userName = "User 10";
		Query query1 = session.createQuery("from UserDetails where userId > ? and userName= ?");
		query1.setInteger(0, minUserId);
		query1.setString(1, userName);
		//OR
		//using placeholders instead of positions
		query1 = session.createQuery("from UserDetails where userId > :userId and userName= :userName");
		query1.setInteger("userId", minUserId);
		query1.setString("userName", userName);
		
		//Named query
		((QueryProducer) query1).getNamedQuery("UserDetails.byId");
		
		//Queries using criteria API
		Criteria criteria = session1.createCriteria(UserDetails.class);
		criteria.add(Restrictions.or(Restrictions.between("userId", 0, 3),Restrictions.between("userId", 7, 9)));
		
		Criteria criteria1 = session1.createCriteria(UserDetails.class)
				.setProjection(Projections.count("userId"));
		
		//Query by example
		UserDetails exampleuser = new UserDetails();
		exampleuser.setUserName("1%");
		Example example = Example.create(exampleuser).enableLike();
		criteria1.add(example);
	}

}
