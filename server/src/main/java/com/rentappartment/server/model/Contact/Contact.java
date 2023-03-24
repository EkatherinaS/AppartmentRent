package com.rentappartment.server.model.Contact;

import com.rentappartment.server.model.Address.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "\"Contact\"")
public class Contact {

    @Id
    @Column(name = "phone_number", nullable = false, length = 16)
    private String phoneNumber;

    @Column(name = "name", length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public boolean equals(Object object) {
        Contact a = (Contact) object;
        if (this.name.equals(a.name) && this.phoneNumber.equals(a.phoneNumber)) {
            return true;
        }
        return false;
    }
}