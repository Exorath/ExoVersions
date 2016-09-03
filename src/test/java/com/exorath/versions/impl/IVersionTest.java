package com.exorath.versions.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by toonsev on 9/3/2016.
 */
public class IVersionTest {
    private static final String ID = "v1.0.0";
    private IVersion version;

    @Before
    public void setup(){
        version = new IVersion(ID);
    }

    @Test
    public void getIdNotNullTest(){
        assertNotNull(version.getId());
    }
    @Test
    public void getIdEqualsIdTest(){
        assertEquals(ID, version.getId());
    }

    @Test
    public void equalsEqualsItselfTest(){
        assertEquals(version, version);
    }

    @Test
    public void equalsEqualsVersionWithSameIdTest(){
        assertEquals(new IVersion(ID), version);
    }

    @Test
    public void hashCodeEqualsHashCodeOfVersionWithSameIdTest(){
        assertEquals(new IVersion(ID).hashCode(), version.hashCode());
    }
}
