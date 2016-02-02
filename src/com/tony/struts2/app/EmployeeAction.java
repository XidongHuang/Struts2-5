package com.tony.struts2.app;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

public class EmployeeAction implements RequestAware {

	private Dao dao = new Dao();

	private Map<String, Object> request;

	
	//Need to define attribute "employeeId" in the current EmployeeAction
	//To accept request parameters
	private Integer employeeId;
	
	
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public String delete(){
		
		dao.delete(employeeId);
		
		//the result type should be: redirectAction
		//Or chain: Actually chain is not necessary. Because we do not need to keep 
		//the current Action's status in the next one.
		//And, it will be still the "delete" address in the goal page, it will be some problems happened
		//if refresh the page
		return "delete";
				
		
	}
	
	
	public String list() {
		
		request.put("emps", dao.getEmployees());
		
		return "list";
	}
	
	

	@Override
	public void setRequest(Map<String, Object> arg0) {
		request = arg0;
	}

}
