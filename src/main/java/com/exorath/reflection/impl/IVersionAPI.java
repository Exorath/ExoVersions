package com.exorath.reflection.impl;

import com.exorath.reflection.api.Version;
import com.exorath.reflection.api.VersionAPI;
import com.exorath.reflection.api.VersionHandler;
import org.bukkit.Server;

import java.util.concurrent.Callable;

/**
 * Created by toonsev on 9/1/2016.
 */
public class IVersionAPI<T extends VersionHandler> implements VersionAPI<T> {
    private Version version;

    private Callable<T> defaultHandlerCallable;
    private T defaultHandler;
    private T versionHandler;

    public IVersionAPI(Server server){
        this.version = Version.create(server.getClass().getPackage().getName().split("\\.")[3]);

    }
    @Override
    public T get() {
        if (versionHandler != null) {
            return versionHandler;
        } else {
            setDefaultHandlerFromCallableIfNonAssigned();
            return defaultHandler;
        }
    }

    protected void setDefaultHandlerFromCallableIfNonAssigned() {
        try {
            if (defaultHandler == null)
                defaultHandler = defaultHandlerCallable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        if (defaultHandler != null)
            defaultHandlerCallable = versionHandlerGetter;
    }

    @Override
    public Version getVersion() {
        return version;
    }
}
