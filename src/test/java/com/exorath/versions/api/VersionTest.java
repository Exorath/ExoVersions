package com.exorath.versions.api;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by toonsev on 9/3/2016.
 */
public class VersionTest {
    @Test
    public void createNotNullTest(){
        assertNotNull(Version.create("id"));
    }
}
