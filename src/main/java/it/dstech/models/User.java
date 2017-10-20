package it.dstech.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String username;
	
	private String password;
	
	private UserProfileType profileType;
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		      name="USER_CELLJ",
		      joinColumns= @JoinColumn (name="USERJ_ID", referencedColumnName="ID"),
		      inverseJoinColumns=@JoinColumn(name="CELLJ_ID", referencedColumnName="ID")
		      )
	private List<Cellulare> listaCellulari;

	
	
	public User() {
		this.listaCellulari = new ArrayList<Cellulare>();
	}

	public int getId() {
		return id;
	}

	public List<Cellulare> getListaCellulari() {
		return listaCellulari;
	}

	public void setListaCellulari(List<Cellulare> listaCellulari) {
		this.listaCellulari = listaCellulari;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(UserProfileType userProfileType) {
		this.profileType = userProfileType;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", userProfileType="
				+ profileType + ", listaCellulari=" + listaCellulari + "]";
	}


}
