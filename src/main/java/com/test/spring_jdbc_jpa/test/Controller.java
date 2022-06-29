package com.test.spring_jdbc_jpa.test;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private EmpService service;
	
	@GetMapping("/message")
	public String message() {
		return "Good Morning";

	}

	/*@GetMapping("/allEmployeeNormalWay")
	public List<Employee> allEmployee() {

		return service.findAllEmployee();

	}*/
	
	@GetMapping("/allEmployeeWithResponseEntity")
	public ResponseEntity<List<Employee>> allEmployee() {

		List<Employee> empList = service.findAllEmployee();
		if (empList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(empList));
		}

	}

	@GetMapping("/employee/{eid}")
	public Employee employee(@PathVariable("eid") Integer id) {
		//String name = null;
		//int legnthOfName = name.length();
		
		return service.getEmployee(id);

	}
	
	@PostMapping("/employee/{eid}/{name}/{age}")
	public String creatEmployee(@PathVariable("eid") Integer id,@PathVariable("name") String name,@PathVariable("age") Integer age) {

		return service.createEmployee(id, name, age);

	}
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee emp) {

		 service.addEmployee(emp);
		 return emp;

	}
	
	//Update Employee
	
	@PutMapping("/updateEmployee/{eid}")
	public Employee updateEmp(@RequestBody Employee emp, @PathVariable("eid") Integer eid){
		
		this.service.updateEmp(emp,eid);
		return emp;
	}
	
}
