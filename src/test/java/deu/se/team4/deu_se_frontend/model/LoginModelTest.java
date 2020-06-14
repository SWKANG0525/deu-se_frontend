/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.model;

import deu.se.team4.deu_se_frontend.EnumAccount;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 강호동
 */
public class LoginModelTest {
    
    public LoginModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setLoginStrategy method, of class LoginModel.
     */
    public void testSetLoginStrategy() {
        System.out.println("setLoginStrategy");
        EnumAccount account_type = null;
        LoginModel instance = new LoginModel();
        instance.setLoginStrategy(account_type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createModel method, of class LoginModel.
     */
    public void testCreateModel() {
        System.out.println("createModel");
        String stringJson = "";
        LoginModel instance = new LoginModel();
        instance.createModel(stringJson);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printModel method, of class LoginModel.
     */
    public void testPrintModel() {
        System.out.println("printModel");
        LoginModel instance = new LoginModel();
        instance.printModel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkId method, of class LoginModel.
     */
    @Test
    public void testCheckId() {
        System.out.println("checkId");
        String id = "ghehd8947";
        EnumAccount account_type = EnumAccount.AIRLINE_STAFF;
        
        LoginModel instance = new LoginModel();
        instance.setLoginStrategy(EnumAccount.AIRLINE_STAFF);
        Boolean expResult = false;
        Boolean result = instance.checkId(id, account_type);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult, result);

    }

    /**
     * Test of login method, of class LoginModel.
     */
    public void testLogin() {
        System.out.println("login");
        String id = "";
        String pw = "";
        EnumAccount account_type = null;
        LoginModel instance = new LoginModel();
        String expResult = "";
        String result = instance.login(id, pw, account_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerName method, of class LoginModel.
     */
    public void testGetCustomerName() {
        System.out.println("getCustomerName");
        String id = "";
        LoginModel instance = new LoginModel();
        String expResult = "";
        String result = instance.getCustomerName(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAirlineStaffName method, of class LoginModel.
     */
    public void testGetAirlineStaffName() {
        System.out.println("getAirlineStaffName");
        String id = "";
        LoginModel instance = new LoginModel();
        String expResult = "";
        String result = instance.getAirlineStaffName(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAirline_kor method, of class LoginModel.
     */
    public void testGetAirline_kor() {
        System.out.println("getAirline_kor");
        LoginModel instance = new LoginModel();
        String expResult = "";
        String result = instance.getAirline_kor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
