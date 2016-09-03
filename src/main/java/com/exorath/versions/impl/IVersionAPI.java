package com.exorath.versions.impl;

import com.exorath.versions.api.Version;
import com.exorath.versions.api.VersionAPI;
import com.exorath.versions.api.VersionHandler;

import java.util.concurrent.Callable;

/**
 * Created by toonsev on 9/1/2016.
 */
public class IVersionAPI<T extends VersionHandler> implements VersionAPI<T> {
    private Version version;

    private Callable<T> defaultHandlerCallable;
    private T defaultHandler;
    private T versionHandler;

    public IVersionAPI(Version version){
        this.version = version;

    }
    @Override
    public T get() {
        if (versionHandler != null) {
            return versionHandler;
        } else if(defaultHandler != null){
            return defaultHandler;
        } else if(defaultHandlerCallable != null){
            try {
                defaultHandler = defaultHandlerCallable.call();
                return defaultHandler;
            } catch (Exception e) {e.printStackTrace();}
        }
        return null;
    }


    @Override
    public boolean register(Version version, Callable<T> versionHandler) {
        if (getVersion().equals(version)) {
            try {
                this.versionHandler = versionHandler.call();
                if(defaultHandler != null) {
                    defaultHandlerCallable = null;
                    defaultHandler = null;
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    @Override
    public void registerDefault(Callable<T> versionHandlerGetter) {
        if (versionHandler != null)
            return;
        if (defaultHandler == null)
            defaultHandlerCallable = versionHandlerGetter;
    }

    @Override
    public Version getVersion() {
        return version;
    }
}
