package com.exorath.reflection.api;

import com.exorath.reflection.impl.IVersionAPI;
import org.bukkit.Server;

import java.util.concurrent.Callable;

/**
 * Created by toonsev on 9/1/2016.
 */
public interface VersionAPI<T extends VersionHandler> {

    /**
     * Gets the versionHandler of this version, or the default versionHandler if no versionHandler was registered, or null if no default was registered.
     *
     * @return the versionHandler of this version, or the default versionHandler if no versionHandler was registered, or null if no default was registered
     */
    T get();

    /**
     * If the version of this handler matches the server's version, the provided callable will be called and the result will be registered.
     * <p>
     * This method will also override the defaultHandler if one was registered.
     *
     * @param versionHandler the handler to register if the versions match.
     */
    boolean register(Version version, Callable<T> versionHandler);

    /**
     * If no versionHandler is registered before the {@link #get()} is called the versionHandler from the callable will be returned.
     * As soon as a handler is registered with {@link #register(Version, Callable)} method, it will override this default.
     * <p>
     * For clarification, the callable will never be called if a versionHandler is registered before an {@link #get()} is called.
     *
     * @param versionHandlerGetter the default versionHandler to register.
     */
    void registerDefault(Callable<T> versionHandlerGetter);

    /**
     * Gets the CraftBukkit version this server is running on, most likely will be v1_10_R1.
     *
     * @return the CraftBukkit version this server is running on, most likely will be v1_10_R1
     */
    Version getVersion();

    static <T extends  VersionHandler> VersionAPI<T> create(Server server){
        return new IVersionAPI<T>(server);
    }

}
