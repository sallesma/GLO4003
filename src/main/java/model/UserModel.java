package model;

public class UserModel {
	public UserModel() {
		lastName = "";
		firstName = "";
		username = "";
		password = "";
		phoneNumber = "";
		address = "";				
	}
	
	private String lastName;
	private String firstName;
	private String username;
	private String password;
	private String phoneNumber;
	private String address;
	
	public void fromView(UserViewModel model) {
		
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
}
