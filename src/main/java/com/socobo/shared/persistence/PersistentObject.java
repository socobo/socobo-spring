package com.socobo.shared.persistence;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by patrick on 09.11.16.
 */
@MappedSuperclass
public abstract class PersistentObject implements Persistable{

    @Id
    private String id = UUIDGenerator.createId();

    protected PersistentObject(){
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistentObject)) return false;

        PersistentObject that = (PersistentObject) o;

        return id != null && id.equals(that.id);

    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 31 * result + (id == null ? 0 : id.hashCode());
        return result;
    }
}
