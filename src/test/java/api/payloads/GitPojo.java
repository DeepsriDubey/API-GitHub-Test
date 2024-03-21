package api.payloads;

public class GitPojo {
	
	int id;
	String name;
	String full_name;
	String description = "This is your first repo!";
	String homepage="https://github.com";
	Boolean Private=true;
	String owner= "DeepsriDubey";
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	int userStatus=0;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public Boolean getPrivate() {
		return Private;
	}
	public void setPrivate(Boolean private1) {
		Private = private1;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	
	
	
	

}
