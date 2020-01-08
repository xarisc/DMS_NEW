package com.company;

public class Owner {
    private int id;
    private String first;
    private String last;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Owner() {
        this.id = id;
        this.first = first;
        this.last = last;
    }

    @Override
    public String toString() {
        return "OWNER " + id +
                "\n  -  first:    " + first +
                "\n  -  last:       " + last +
                "\n\n";
    }
}
