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

    public boolean isDeleted() {
        return deleteFlag == null || deleteFlag != 'Y' ? false : true;
    }

    public void setDeleted(boolean deleted) {
        if (deleted) {
            deleteFlag = DELETE;
        } else {
            deleteFlag = null;
        }
    }

    public boolean isNew() {
        return id < 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (id != entity.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
