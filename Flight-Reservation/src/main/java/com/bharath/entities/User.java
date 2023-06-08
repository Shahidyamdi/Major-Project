package com.bharath.entities;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class User extends AbstractEntity {
	private String firstName;
	private String lastName;
	private String email;
	private String password;

}
