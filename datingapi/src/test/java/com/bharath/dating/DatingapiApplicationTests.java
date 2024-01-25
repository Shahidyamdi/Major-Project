package com.bharath.dating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.dating.controllers.UserAccountController;
import com.bharath.dating.entities.Interest;
import com.bharath.dating.entities.UserAccount;
import com.bharath.dating.repos.InterestRepository;
import com.bharath.dating.repos.UserAccountRepository;

@SpringBootTest
class DatingapiApplicationTests {

	@Mock
	UserAccountRepository userRepo;
	@Mock
	InterestRepository interestRepo;
	@InjectMocks
	UserAccountController controller;

	@Test
	public void testRegisteruser() {
		UserAccount userAccount = new UserAccount();
		UserAccount saveduserAccount = new UserAccount();
		saveduserAccount.setId(123);
		when(userRepo.save(userAccount)).thenReturn(saveduserAccount);
		UserAccount outputUserAccount = controller.registerUser(userAccount);
		assertNotNull(outputUserAccount);
		assertNotNull(outputUserAccount.getId());
		assertEquals(123, outputUserAccount.getId());
		verify(userRepo).save(userAccount);

	}

	@Test
	public void testupdateuser() {
		Interest interest = new Interest();
		interest.setUserAccountId(123);
		when(userRepo.findById(123)).thenReturn(Optional.of(new UserAccount()));
		Interest saveinterest = new Interest();
		saveinterest.setId(123);
		when(interestRepo.save(interest)).thenReturn(saveinterest);

		Interest updateInterest = controller.updateInterest(interest);
		assertNotNull(updateInterest);
		assertNotNull(updateInterest.getId());
		assertEquals(123, updateInterest.getId());
		verify(userRepo).findById(123);
		verify(interestRepo).save(interest);
		
		
	}

	@Test
	public void testgetuser() {
	ArrayList<UserAccount> users = new ArrayList<UserAccount>();
	users.add(new UserAccount());
	users.add(new UserAccount());
	users.add(new UserAccount());
	List<UserAccount> outputuser = controller.getUsers();
	when(userRepo.findAll()).thenReturn(outputuser);
	assertNotNull(outputuser);
	assertEquals(3, users.size());
	verify(userRepo).findAll();
	}
	
	@Test
	public void testdeleteuser() {
		doNothing().when(interestRepo).deleteById(123);
		controller.deleteInterest(123);
		verify(interestRepo).deleteById(123);
	}
	
	@Test
	public void testfindmatchuser() {
		UserAccount userAccount = new UserAccount();
		userAccount.setId(123);
		userAccount.setAge(20);
		userAccount.setCountry("usa");
	    userAccount .setCity("Austin");
	    ArrayList<UserAccount> users = new ArrayList<UserAccount>();
		users.add(new UserAccount());
		users.add(new UserAccount());
		users.add(new UserAccount());
		when(userRepo.findById(123)).thenReturn(Optional .of(userAccount));
		when(userRepo.findMatches(20, "Austin", "usa", 123)).thenReturn(users);
		List<UserAccount> matches = controller.findMatches(123);
		verify(userRepo).findById(123);
		verify(userRepo).findMatches(20, "Austin", "usa", 123);
		assertNotNull(matches);
		assertEquals(3, users.size());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
