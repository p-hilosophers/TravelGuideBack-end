package com.travelg.Authentication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class EncryptServiceTest {

    @Test
    public void encryptPasswordTest()
    {
        String tester = EncryptService.encryptPassword("");
        assertEquals(tester,EncryptService.encryptPassword(""));
    }

}
