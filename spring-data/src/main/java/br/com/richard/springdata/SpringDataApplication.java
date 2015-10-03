package br.com.richard.springdata;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		
		testConfiguration();
		//testSave();
		//testUpdate();
		//testDelete();
		
	}

	private void testDelete() {
		
		personRepository.delete(15L);
		
		Person person = personRepository.findOne(14L);
		
		personRepository.delete(person);
		
		List<Person> persons = personRepository.findAll();
		persons.forEach(System.out::println);
		
	}

	private void testUpdate() {
		
		Person person = personRepository.findOne(15L);
		
		System.out.println(person.toString());
		
		person.setLastName("Aguiar");
		
		personRepository.save(person);
		
		Person p2 =  personRepository.findOne(15L);
		
		System.out.println(p2.toString());
		
	}

	private void testSave() {
		
		Person person = new Person();
		person.setFirstName("João Luiz");
		person.setLastName("Rios");
		person.setAge(35);
		person.setDocument(new Document("841.852.963-74", 12365485));
		
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
