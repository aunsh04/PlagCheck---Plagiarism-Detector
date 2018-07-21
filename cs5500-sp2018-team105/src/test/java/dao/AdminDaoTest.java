package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dao.AdminDao;
import model.Instructor;
import service.AdminService;

/*
 * @author Anirudh
 * AdminTests for admin's functions - setting up instructors
 */
public class AdminDaoTest
{
	
	AdminService adminService = new AdminService();

	/*
	 *  test for setting up a Professor
	 */
	@Test
	public void setUpProf()
	{
		Instructor inst = new Instructor("abc", "def", "qwe", "def", "abc@def.com");
		assertEquals(1, adminService.setUpInstructor(inst, 0));
	}
	
	/*
	 *  test for setting up a TeachingAssistant
	 */
	@Test
	public void setUpTA()
	{
		Instructor inst = new Instructor("ghi", "jkl", "oip", "jkl", "ghi@jkl.com");
		assertEquals(1, adminService.setUpInstructor(inst, 1));
	}
	
	/*
	 * tests for checking if all details have been provided or not
	 */
	@Test
	public void invalidDetails()
	{
		
		boolean check1 = adminService.invalidForm("", "", "", "");
		assertTrue(check1);
		
		boolean check2 = adminService.invalidForm("a", "", "", "");
		assertTrue(check2);
		
		boolean check3 = adminService.invalidForm("", "a", "", "");
		assertTrue(check3);
		
		boolean check4 = adminService.invalidForm("", "", "a", "");
		assertTrue(check4);
		
		boolean check5 = adminService.invalidForm("", "", "", "a");
		assertTrue(check5);
		
		boolean check6 = adminService.invalidForm("", "", "", "", "");
		assertTrue(check6);
		
		boolean check7 = adminService.invalidForm("a", "", "", "", "");
		assertTrue(check7);
		
		boolean check8 = adminService.invalidForm("", "a", "", "", "");
		assertTrue(check8);
		
		boolean check9 = adminService.invalidForm("", "", "a", "", "");
		assertTrue(check9);
		
		boolean check10 = adminService.invalidForm("", "", "", "a", "");
		assertTrue(check10);
		
		boolean check11 = adminService.invalidForm("", "", "", "", "a");
		assertTrue(check11);
		
	}
	
	/*
	 * tests for checking if all the provided details are valid or not
	 */
	@Test
	public void validDetails()
	{
		boolean check1 = adminService.invalidForm("a", "b", "c", "d", "e");
		assertFalse(check1);
		
		boolean check2 = adminService.invalidForm("a", "b", "c", "d");
		assertFalse(check2);
	}
	
	
	/*
	 * tests for checking the function to return all users of system
	 */
	@Test
	public void testListOfUsers() {
		assertTrue(AdminDao.getUsers(0).size() >0);
		assertTrue(AdminDao.getUsers(1).size()>0);
	}
	
}
