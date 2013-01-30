package com.nhncorp.myapp;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTest {
 
     private String password;
     private boolean isValid;
     private static PasswordValidator validator;
 
     @BeforeClass
     public static void setUp() {
          validator = new PasswordValidator();
     }
 
     public ParameterizedTest(String password, boolean isValid) {
          this.password = password;
          this.isValid = isValid;
     }
 
     @Parameters
     public static Collection passwords() {
          return Arrays.asList(new Object[][] { { "1234qwer", true }, {"12345678", false}, {"1q2w3e4r", true} });
     }
 
     @Test
     public void isValidPasswordWithParams() {
          assertEquals(validator.isValid(this.password), this.isValid);
     }
}
