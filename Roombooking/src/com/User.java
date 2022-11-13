package com;


import java.io.Serializable;

public class User  implements Serializable {
    private final int _id;
    private final String name;
    private String hashpass;
    private Boolean role;
    private final String email;
    private final int phone;
    private final int office;

    public User(int id, String name, String hashpass, Boolean role, String email, int phone, int office) {
        this._id = id;
        this.name = name;
        this.hashpass = hashpass;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.office = office;
    }

    public User(int id, String name, String email, int phone, int office) {
        this._id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.office = office;
    }

    public int get_id() { return _id; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String gethashpass() {
        return hashpass;
    }

    public int getPhone() {
        return phone;
    }

    public Boolean getRole() {
        return role;
    }

    public int getOffice() {
        return office;
    }
}
