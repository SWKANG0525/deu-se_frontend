/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.vo;

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
public class VersionVOTest {
    
    public VersionVOTest() {
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
     * Test of getVersion method, of class VersionVO.
     */

    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        VersionVO instance = new VersionVO("Beta 1.0.5");
        String expResult = "";
        String result = instance.getVersion();
        assertNotNull(result);

        // TODO review the generated test code and remove the default call to fail.
    }
    
}
