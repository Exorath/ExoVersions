package com.exorath.reflection.api;

import com.exorath.reflection.impl.IVersion;

/**
 * This class represents a craftbukkit version.
 *
 * {@link Version#equals(Object)} will be true if the two versions have the same id. Just like the hashcodes will be the same.
 *
 * Created by toonsev on 8/30/2016.
 */
public interface Version {
    Version v1_9_R1 = create("v1_9_R1");
    Version v1_10_R1 = create("v1_10_R1");

    String getId();

    static Version create(String id){
        return new IVersion(id);
    }
}
