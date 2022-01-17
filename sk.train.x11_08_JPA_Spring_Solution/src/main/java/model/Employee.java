package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the EMPLOYEES database table.
 * 
 */
@Entity
@Table(name="EMPLOYEES", schema="HR")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLOYEE_ID")
	private long employeeId;

	@Column(name="COMMISSION_PCT")
	private BigDecimal commissionPct;

	private String email;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HIRE_DATE")
	private Date hireDate;

	@Column(name="JOB_ID")
	private String jobId;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="PHONE_NUMBER")
	private String phoneNumber;

	private BigDecimal salary;

	//bi-directional many-to-one association to Department
	@OneToMany(mappedBy="depmanager")
	private List<Department> departments;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private Department department;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="MANAGER_ID")
	private Employee manager;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="manager")
	private List<Employee> employees;

	public Employee() {
	}

	public long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getCommissionPct() {
		return this.commissionPct;
	}

	public void setCommissionPct(BigDecimal commissionPct) {
		this.commissionPct = commissionPct;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department addDepartment(Department department) {
		getDepartments().add(department);
		department.setDepmanager(this);

		return department;
	}

	public Department removeDepartment(Department department) {
		getDepartments().remove(department);
		department.setDepmanager(null);

		return department;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getManager() {
		return this.manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setManager(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setManager(null);

		return employee;
	}

	@Override	//Vorsicht: gegenseitige bzw. rekursive Aufrufe unterbinden!
	public String toString() {
		String managerid = "";
		String depid = "";
		if (manager != null) managerid = Long.toString(manager.getEmployeeId());
		if (department != null)depid = Long.toString(department.getDepartmentId());
		return "Employee [employeeId=" + employeeId + ", commissionPct=" + commissionPct + ", email=" + email
				+ ", firstName=" + firstName + ", hireDate=" + hireDate + ", jobId=" + jobId + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", salary=" + salary + ", department=" + depid + ", manager="
				+ managerid + "]";
	}
	
	

}