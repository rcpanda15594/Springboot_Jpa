package com.test.spring_jdbc_jpa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class EmpService {

	private static List<Employee> list = new ArrayList<Employee>();
	
	static{
		
		list.add(new Employee(1,"RAM",35));
		list.add(new Employee(2,"SITA",30));
		list.add(new Employee(3,"HANUMAN",25));
	}
	
	public List<Employee> findAllEmployee(){
		return list;
	}
	
	public Employee getEmployee(Integer id){
		return list.stream().filter(e -> e.getId() == id).findFirst().get();
	}
	
	public String createEmployee(Integer id,String name,Integer age){
		list.add(new Employee(id,name,age));
		return "Employee added sucessfully..";
	}

	public void addEmployee(Employee emp) {
		list.add(emp);
		
	}

	public void updateEmp(Employee emp, Integer eid) {
		list = list.stream().map(e ->{
			if(e.getId() == emp.getId())
			{
				e.setAge(emp.getAge());
				e.setName(emp.getName());
			
			}
			return e;
		}).collect(Collectors.toList());
		
	}
}
