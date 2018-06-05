package com.epam.engx.cleancode.naming.task2;

import java.util.Arrays;
import java.util.Date;

public class User {

	private Date birthdayDate;
	private String userName;
	private boolean isAdmin;

	private User[] subordinateArray;

	private int iR;

	public User(String userName) {
		super();
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [birthdayDate=" + birthdayDate + ", userName=" + userName + ", isAdmin=" + isAdmin + ", subordinateArray="
				+ Arrays.toString(subordinateArray) + ", iRating=" + iR + "]";
	}

}
