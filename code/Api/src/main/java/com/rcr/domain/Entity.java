package com.rcr.domain;


public abstract class Entity implements java.io.Serializable {

    public static Character DELETE = 'Y';

    protected long id;

    protected Character deleteFlag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Character getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Character deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public boolean isNew() {
        return id < 1;
    }
}
