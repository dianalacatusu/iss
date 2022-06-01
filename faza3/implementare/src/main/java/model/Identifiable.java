package model;

import java.io.Serializable;

public interface Identifiable<ID>  {
    ID getId();
    void setId(ID id);
}
