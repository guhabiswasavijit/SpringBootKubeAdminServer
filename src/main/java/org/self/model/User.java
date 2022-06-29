package org.self.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter @Getter @EqualsAndHashCode
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@Column(name = "full_name")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "enabled")
	private Short enabled;
	
}