package jdbcTemplate.main;

public class Customer {
	private int id;
	private String name;
	private double salary;
	private String city;
	
	public Customer(int id, String name, double salary, String city) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", salary=" + salary + ", city=" + city + "]";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public String getCity() {
		return city;
	}
	
	
}
