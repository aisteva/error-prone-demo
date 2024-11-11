package com.example.talk;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@SpringBootApplication
public class TalkApplication {

	public static void main(String[] args) {
		if (args.length == 0 || args[0] != null) {
			new IllegalArgumentException();
		}
		SpringApplication.run(TalkApplication.class, args);
	}

}

//@RequiredArgsConstructor
@Controller
@ResponseBody
class CustomerController {
	private final CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
	Iterable<Customer> customers() {
		String name = "";
		String convertedName = findName(name);

		String calculatedName = getVeryCalculatedName(name);
		System.out.println(calculatedName);
		return this.customerRepository.findAll();
	}

	private static String getVeryCalculatedName(String name) {
		return Optional.of(name).orElse("VERY Calculated Name");
	}

	private static String findName(String name) {
		if(name == null) {
			return  "Rudolf";
		} else {
			return name;
		}
	}

}

interface CustomerRepository extends CrudRepository <Customer, Integer>  {

}

record Customer (@Id Integer id, String name){

}
