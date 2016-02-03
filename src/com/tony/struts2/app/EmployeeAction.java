package com.tony.struts2.app;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EmployeeAction implements RequestAware, ModelDriven<Employee>, Preparable {

	private Dao dao = new Dao();

	private Map<String, Object> request;

	// // Need to define attribute "employeeId" in the current EmployeeAction
	// // To accept request parameters
	private Integer employeeId;

	//
	// private String firstName;
	// private String lastName;
	// private String email;
	//
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	//
	// public String getFirstName() {
	// return firstName;
	// }
	//
	// public void setFirstName(String firstName) {
	// this.firstName = firstName;
	// }
	//
	// public String getLastName() {
	// return lastName;
	// }
	//
	// public void setLastName(String lastName) {
	// this.lastName = lastName;
	// }
	//
	// public String getEmail() {
	// return email;
	// }
	//
	// public void setEmail(String email) {
	// this.email = email;
	// }
	//
	// public Integer getEmployeeId() {
	// return employeeId;
	// }

	private Employee employee;

	public String delete() {

		dao.delete(employeeId);

		// the result type should be: redirectAction
		// Or chain: Actually chain is not necessary. Because we do not need to
		// keep
		// the current Action's status in the next one.
		// And, it will be still the "delete" address in the goal page, it will
		// be some problems happened
		// if refresh the page
		return "success";

	}

	public String save() {

		// 1. Gain request Parameters: Define corresponding attributes methods

		// 2. Invoke Dao's save method
		// Employee employee = new Employee(null, firstName, lastName, email);
		dao.save(employee);

		// 3. Pass result to emp-list page by redirectAction
		return "success";
	}

	public void prepareSave() {

		employee = new Employee();

	}

	public String list() {

		request.put("emps", dao.getEmployees());

		return "list";
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		request = arg0;
	}

	@Override
	public Employee getModel() {

		// Judge the employee is going to be created or edited
		// if it will be created, then employee = new Employee();
		// if it will be editing, then employee = dao.get(employeeId);
		// The stander of judgment: If there is a current parameter
		// (employeeId), then edit;
		// if not, then it is create
		// If check by employeeId, then launch a params filter before
		// modelDriven!
		// Such function can be using paramsPrepareParams filter to achieve
		// Need to configure paramsPrepareParams in struts.xml

		// if(employeeId == null)
		// employee = new Employee();
		// else
		// employee = dao.get(employeeId);

		// employee = new Employee();

		return employee;
	}

	public String edit() {

		// 1. Gain employeeId: employee.getEmployeeId()

		// 2. Base on employeeId to gain Employee Object
		// Employee emp = dao.get(employee.getEmployeeId());

		// 3. Pack attributes of the object on the valueStack top: employee
		// object must on the top of valueStack
		// Current employee object just has employeeId attribute, other
		// attributes are null
		/*
		 * Struts2 form page re-show: Search attributes from valueStack, if
		 * found then add into value attributes
		 * 
		 * 
		 */
		// employee.setEmail(emp.getEmail());
		// employee.setFirstName(emp.getFirstName());
		// employee.setLastName(emp.getLastName());

		// It does not work, because the employee object has been modified, it
		// is not the one that on the top of valueStack
		// employee= dao.get(emp.getEmployeeId());

		// Can push Employee Object that abstracted from database on the
		// valueStack top
		// But there are two Employee Objects on the valueStack
		// ActionContext.getContext().getValueStack().push(dao.get(employee.getEmployeeId()));

		return "edit";
	}

	public void prepareEdit() {
		employee = dao.get(employeeId);

	}

	public String update() {
		dao.update(employee);

		return "success";
	}

	public void prepareUpdate() {
		employee = new Employee();

	}

	/**
	 * prepare method's function: prepare a model for getModel()
	 * 
	 * @throws Exception
	 */
	@Override
	public void prepare() throws Exception {
		// if(employeeId == null)
		// employee = new Employee();
		// else
		// employee = dao.get(employeeId);
		System.out.println("prepare...");

	}

}
