package com.exorath.versions.impl;

import com.exorath.versions.api.Version;

/**
 * Created by toonsev on 8/30/2016.
 */
public class IVersion implements Version {
    private String id;

    public IVersion(String id){
        this.id = id;
    }
    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Version))
            return false;
        if (obj == this)
            return true;

        return id.equals(((Version) obj).getId());
    }
}
