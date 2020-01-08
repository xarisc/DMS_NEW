package com.company;

import java.lang.reflect.Array;
import java.util.List;

public class Immobilie {
    private int id;
    private String address;
    private String type;
    private int owner_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public Immobilie() {
        this.id = id;
        this.address = address;
        this.type = type;
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "IMMOBILIE " + id +
                "\n  -  address:    " + address +
                "\n  -  type:       " + type +
                "\n  -  owner_id:   " + owner_id +
                "\n\n";
    }


}
