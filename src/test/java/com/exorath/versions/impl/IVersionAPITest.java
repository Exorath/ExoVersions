package com.exorath.versions.impl;

import com.exorath.versions.api.Version;
import com.exorath.versions.api.VersionHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by toonsev on 9/3/2016.
 */
public class IVersionAPITest {
    private static final String VERSION = "v1.0.0";
    private IVersionAPI versionAPI;

    @Before
    public void setup(){
        versionAPI = new IVersionAPI(getNewVersion(VERSION));
    }

    private Version getNewVersion(String id){
        Version version = new IVersion(id);
        return version;
    }
    @Test
    public void versionNotNullByDefaultTest(){
        assertNotNull(versionAPI.getVersion());
    }
    @Test
    public void versionEqualsVersionTest(){
        assertEquals(getNewVersion(VERSION), versionAPI.getVersion());
    }

    @Test
    public void registerWithSameVersionReturnsTrue(){
        assertTrue(versionAPI.register(getNewVersion(VERSION), () -> mock(VersionHandler.class)));
    }

    @Test
    public void registerWithDifferentVersionReturnsFalse(){
        assertFalse(versionAPI.register(getNewVersion(VERSION + ".1"), () -> mock(VersionHandler.class)));
    }

    @Test
    public void getIsNullByDefaultTest(){
        assertNull(versionAPI.get());
    }
    @Test
    public void getReturnsRegisteredHandlerWithSameVersionTest(){
        VersionHandler handler = mock(VersionHandler.class);
        versionAPI.register(getNewVersion(VERSION), ()-> handler);
        assertEquals(handler, versionAPI.get());
    }
    @Test
    public void getReturnsDefaultHandlerTest(){
        VersionHandler handler = mock(VersionHandler.class);
        versionAPI.registerDefault(()-> handler);
        assertEquals(handler, versionAPI.get());
    }

    @Test
    public void getReturnsVersionHandlerRegisteredWithSameVersionAfterDefaultRegisteredTest(){
        VersionHandler versionHandler = mock(VersionHandler.class);
        VersionHandler defHandler = mock(VersionHandler.class);
        versionAPI.registerDefault(()-> defHandler);
        versionAPI.register(getNewVersion(VERSION), ()-> versionHandler);
        assertEquals(versionHandler, versionAPI.get());
    }
    @Test
    public void getReturnsVersionHandlerRegisteredWithSameVersionBeforeDefaultRegisteredTest(){
        VersionHandler versionHandler = mock(VersionHandler.class);
        VersionHandler defHandler = mock(VersionHandler.class);
        versionAPI.register(getNewVersion(VERSION), ()-> versionHandler);
        versionAPI.registerDefault(()-> defHandler);
        assertEquals(versionHandler, versionAPI.get());
    }
    @Test
    public void getReturnsDefaultVersionHandlerRegisteredBeforeVersionHandlerWithWrongVersionIdRegisteredTest(){
        VersionHandler versionHandler = mock(VersionHandler.class);
        VersionHandler defHandler = mock(VersionHandler.class);
        versionAPI.registerDefault(()-> defHandler);
        versionAPI.register(getNewVersion(VERSION + "0.1"), ()-> versionHandler);
        assertEquals(defHandler, versionAPI.get());
    }
    @Test
    public void getReturnsDefaultVersionHandlerRegisteredAfterVersionHandlerWithWrongVersionIdRegisteredTest(){
        VersionHandler versionHandler = mock(VersionHandler.class);
        VersionHandler defHandler = mock(VersionHandler.class);
        versionAPI.register(getNewVersion(VERSION + "0.1"), ()-> versionHandler);
        versionAPI.registerDefault(()-> defHandler);
        assertEquals(defHandler, versionAPI.get());
    }
}
