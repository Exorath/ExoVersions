package com.exorath.versions.api;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by toonsev on 9/3/2016.
 */
public class VersionAPITest {
    @Test
    public void createNotNullTest(){
        Version version = mock(Version.class);
        when(version.getId()).thenReturn("v1.0.0");
        assertNotNull(VersionAPI.create(version));
    }
}
