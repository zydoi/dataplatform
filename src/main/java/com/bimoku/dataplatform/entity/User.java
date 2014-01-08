package com.bimoku.dataplatform.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bimoku.dataplatform.entity.dto.UserDTO;
import com.bimoku.dataplatform.entity.type.UserType;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "UUID")
	private String uUID;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "USER_TYPE", columnDefinition = "TINYINT")
	@Enumerated(EnumType.ORDINAL)
	private UserType userType;
	
	@Embedded
	private UserProfile userProfile = new UserProfile();
	
	@ManyToMany
	@JoinTable(name = "User_Follow_User",
			joinColumns = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "FOLLOWER_ID"))
	private Set<User> followers = new HashSet<User>();
	
	@ManyToMany(mappedBy = "followers")
	private Set<User> followings = new HashSet<User> ();
	
	//private Set<User> concerns = new HashSet<User>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "User_Like_Book",
			joinColumns = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
	private Set<Book> likeBooks = new HashSet<Book>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "User_Search_Book",
		joinColumns = @JoinColumn(name = "USER_ID"),
		inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
	private Set<Book> searchBooks = new HashSet<Book>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CollectedBook> collectedBooks = new HashSet<CollectedBook>();
	
	public User() {
		
	}
	
	public User(UserDTO dto) {
		this.name = dto.getName();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUUID() {
		return uUID;
	}

	public void setUUID(String uUID) {
		this.uUID = uUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public Set<Book> getLikeBooks() {
		return likeBooks;
	}

	public void setLikeBooks(Set<Book> likeBooks) {
		this.likeBooks = likeBooks;
	}

	public Set<Book> getSearchBooks() {
		return searchBooks;
	}

	public void setSearchBooks(Set<Book> searchBooks) {
		this.searchBooks = searchBooks;
	}

	public Set<CollectedBook> getCollectedBooks() {
		return collectedBooks;
	}

	public void setCollectedBooks(Set<CollectedBook> collectedBooks) {
		this.collectedBooks = collectedBooks;
	}

	public Set<User> getFollowings() {
		return followings;
	}

	public void setFollowings(Set<User> followings) {
		this.followings = followings;
	}

}
