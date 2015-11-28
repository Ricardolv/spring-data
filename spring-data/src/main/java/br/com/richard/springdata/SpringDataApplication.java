package br.com.richard.springdata;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import br.com.richard.springdata.entity.Address;
import br.com.richard.springdata.entity.Address.TypeAddress;
import br.com.richard.springdata.entity.Document;
import br.com.richard.springdata.entity.Person;
import br.com.richard.springdata.entity.Phone;
import br.com.richard.springdata.entity.Phone.TypePhone;
import br.com.richard.springdata.repository.AddressRepository;
import br.com.richard.springdata.repository.DocumentRepository;
import br.com.richard.springdata.repository.PersonRepository;
import br.com.richard.springdata.repository.PhoneRepository;

@SpringBootApplication
//@ImportResource(value = "spring-data.xml")
public class SpringDataApplication implements CommandLineRunner {
	
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private PhoneRepository phoneRepository; 

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }
    
	@Override
	public void run(String... args) throws Exception {
		
		//testConfiguration();
		//testSave();
		//testUpdate();
		//testDelete();
		//testeSavePersons();
		//testeDeletePersons();
		//testeFindAndSort();
		//testeFindBayIds();
		//testeExists();
		//testePaginationPerson();
		
		//testeFindByAge();
		testeByFirstNameLike();
		
	}

	private void testeByFirstNameLike() {
		
		List<Person> p1 = personRepository.findByFirstNameLike("Antonio");
		p1.forEach(System.out::println);
		
		System.out.println("**********************************************************");
		
		List<Person> p2 = personRepository.findByFirstNameNotLike("Antonio");
		p2.forEach(System.out::println);
	}

	private void testeFindByAge() {
		
		List<Person> persons = personRepository.findByAge(30);
		persons.forEach(System.out::println);
		
		System.out.println("**********************************************************");
		
		List<Person> p2 = personRepository.findByAgeNot(30);
		p2.forEach(System.out::println);
		
	}

	private void testePaginationPerson() {

		Page<Person> pages = personRepository.findAll(new PageRequest(0, 3));
		pages.getContent().forEach(System.out::println);
		
		pages = personRepository.findAll(new PageRequest(1, 3));
		pages.getContent().forEach(System.out::println);
		
		pages = personRepository.findAll(new PageRequest(2, 3));
		pages.getContent().forEach(System.out::println);
		
	}

	private void testeExists() {

		boolean p1 = personRepository.exists(2l);
		System.out.println("p1 is: " + p1);
		
		boolean p2 = personRepository.exists(30l);
		System.out.println("p2 is: " + p2);
		
	}

	private void testeFindBayIds() {

		List<Person> persons = personRepository.findAll(Arrays.asList(21l, 22l ,23l));	
		persons.forEach(System.out::println);
	}

	private void testeFindAndSort() {

		Order orderAsc = new Order(Direction.ASC, "lastName");
		
		Order orderDesc = new Order(Direction.ASC, "firstName");
		
		Sort sort = new Sort(orderAsc, orderDesc);
		
		List<Person> persons = personRepository.findAll(sort);
		persons.forEach(System.out::println);
	}

	private void testeDeletePersons() {

		Person p1 = personRepository.findOne(12l);
		Person p2 = personRepository.findOne(13l);
		Person p3 = personRepository.findOne(14l);
		
		personRepository.delete(Arrays.asList(p1, p2, p3));
		
		System.out.println("**************************************************************");
		
		Person p4 = personRepository.findOne(15l);
		Person p5 = personRepository.findOne(16l);
		Person p6 = personRepository.findOne(17l);
		
		personRepository.deleteInBatch(Arrays.asList(p4, p5, p6));
	}

	private void testeSavePersons() {
		Person p1 = new Person();
		p1.setFirstName("Bruno");
		p1.setLastName("Souza");
		p1.setAge(34);
		p1.setDocument(new Document("999.999.999-70", 1111111));
		
		Person p2 = new Person();
		p2.setFirstName("Antonio ");
		p2.setLastName("Beltrano");
		p2.setAge(33);
		p2.setDocument(new Document("999.999.999-71", 2222222));
		
		Person p3 = new Person();
		p3.setFirstName("Breno");
		p3.setLastName("Caju");
		p3.setAge(32);
		p3.setDocument(new Document("999.999.999-72", 3333333));
		
		Person p4 = new Person();
		p4.setFirstName("Anderson");
		p4.setLastName("Alfredo");
		p4.setAge(31);
		p4.setDocument(new Document("999.999.999-73", 4444444));
		
		Person p5 = new Person();
		p5.setFirstName("Antonio");
		p5.setLastName("Souza");
		p5.setAge(30);
		p5.setDocument(new Document("999.999.999-74", 55555555));
		
		Person p6 = new Person();
		p6.setFirstName("Beltrano");
		p6.setLastName("Souza");
		p6.setAge(29);
		p6.setDocument(new Document("999.999.999-75", 6666666));
		
		List<Person> persons = personRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		persons.forEach(System.out::println);
	}

	private void testDelete() {
		
		personRepository.delete(1L);
		
		Person person = personRepository.findOne(3L);
		
		personRepository.delete(person);
		
		List<Person> persons = personRepository.findAll();
		persons.forEach(System.out::println);
		
	}

	private void testUpdate() {
		
		Person person = personRepository.findOne(1L);
		
		System.out.println(person.toString());
		
		person.setLastName("Aguiar");
		
		personRepository.save(person);
		
		Person p2 =  personRepository.findOne(1L);
		
		System.out.println(p2.toString());
		
	}

	private void testSave() {
		
		Person person = new Person();
		person.setFirstName("OPa Luiz");
		person.setLastName("Viana");
		person.setAge(3);
		person.setDocument(new Document("841.852.963-81", 12365499));
		
		Address address = new Address();
		address.setCity("Manaus");
		address.setStreet("Rua das Valquirias, 32");
		address.setType(TypeAddress.RESIDENCIAL);
		
		person.setAddresses(Arrays.asList(address));
		person.addPhone(new Phone(TypePhone.RESIDENCIAL, "32220303"));
		
		personRepository.save(person);
		
		Person p2 = personRepository.findOne(person.getId());
		
		System.out.println(p2.toString());
		
	}

	private void testConfiguration() {
		
		long total = personRepository.count();		
		System.out.println("Total of persons = " + total);
		
		List<Person> persons = personRepository.findAll();
		persons.forEach(System.out::println);
		
		long t2 = addressRepository.count();		
		System.out.println("Total of addresses = " + t2);
		
		long t3 = documentRepository.count();		
		System.out.println("Total of documents = " + t3);
		
		long t4 = phoneRepository.count();		
		System.out.println("Total of phones = " + t4);
		
	}
}
