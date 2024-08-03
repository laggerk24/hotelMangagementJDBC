package jdbcTemplate.main;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        NamedParameterJdbcTemplate template = context.getBean("jdbcTemplate",NamedParameterJdbcTemplate.class);
        Customer customer1 = new Customer(3, "Rahul", 3600000, "Mumbai");
        addNewCustomer(template, customer1);
//        Customer customer2 = getCustomerById(template, 1);
//        System.out.println(customer2);
        
        List<Customer> allCustomers = getAllCustomers(template);
        for (Customer customer : allCustomers) {
			System.out.println("Id "+customer.getId()+" Name "+ customer.getName()+" Salary "+ customer.getSalary()+" City "+customer.getCity());
		}
//        deleteACustomerById(template, 3);
        
        allCustomers = getAllCustomers(template);
        for (Customer customer : allCustomers) {
			System.out.println("Id "+customer.getId()+" Name "+ customer.getName()+" Salary "+ customer.getSalary()+" City "+customer.getCity());
		}
        updateCustomerName(template, 2, "Rahul");
        allCustomers = getAllCustomers(template);
        for (Customer customer : allCustomers) {
			System.out.println("Id "+customer.getId()+" Name "+ customer.getName()+" Salary "+ customer.getSalary()+" City "+customer.getCity());
		}
    }
    
    public static void addNewCustomer(NamedParameterJdbcTemplate template,Customer customer) {
    	HashMap<String, Object> map = new HashMap<>();
    	map.put("id", customer.getId());
    	map.put("name", customer.getName());
    	map.put("salary", customer.getSalary());
    	map.put("city", customer.getCity());
    	
    	String sqlString = "INSERT INTO customer(id,name,salary,city)values(:id,:name,:salary,:city)";
    	template.update(sqlString, map);
    	System.out.println("Row Added succesfully");
    	System.out.println();
    }
    
    public static Customer getCustomerById(NamedParameterJdbcTemplate template,int id) {
    	HashMap<String, Object> map = new HashMap<>();
    	map.put("id", id);
    	String sqlString = "SELECT * FROM customer WHERE id=:id";
    	Customer customer = template.queryForObject(sqlString, map, new CustomerMapper());
    	System.out.println("Data Fetched...");
    	System.out.println();
    	return customer;
    }
    
    public static List<Customer> getAllCustomers(NamedParameterJdbcTemplate template){
    	String sqlString = "SELECT * FROM customer";
    	List<Customer> allCustomers = template.query(sqlString, new CustomerMapper());
    	return allCustomers;
    }
    
    public static void deleteACustomerById(NamedParameterJdbcTemplate template, int id) {
    	String sqlString = "DELETE FROM customer WHERE id=:id";
    	HashMap<String, Object> map = new HashMap<>();
    	map.put("id", id);
    	template.update(sqlString, map);
    	System.out.println("Customer deleted succesfully");
    	System.out.println();
    }
    
    public static void updateCustomerName(NamedParameterJdbcTemplate template,int id, String name) {
    	HashMap<String, Object> map = new HashMap<>();
    	map.put("name", name);
    	map.put("id", id);
    	String sqlString = "UPDATE customer SET name=:name WHERE id=:id";
    	template.update(sqlString, map);
    	System.out.println("Customer Updated successfully");
    	System.out.println();
    }
}
