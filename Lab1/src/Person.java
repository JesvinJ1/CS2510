import tester.*;

class Address {
	String city;
	String state;
	
	Address(String city, String state) {
		this.city = city;
		this.state = state;
	}
}

class Person {
	 
	String name;
	int age;
	String gender;
	Address add;

	Person(String name, int age, String gender, Address add) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.add = add;
	}
}

	class ExamplesPerson {
		
		Address Boston = new Address ("Boston", "MA");
		Address Philadelphia = new Address ("Philadelphia", "PA");
		
		Person tim = new Person ("Tim", 23, "Male", Boston);
		Person kate = new Person ("Kate", 22, "Female", Philadelphia);
		Person rebecca = new Person ("Rebecca", 31, "Non-binary", new Address ("Springfield", "PA"));
		
		
		
	}


