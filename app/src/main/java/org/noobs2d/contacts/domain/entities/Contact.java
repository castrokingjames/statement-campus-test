package org.noobs2d.contacts.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    public String id;
    public String name;
    public List<String> phones;

    public Contact(String id, String name) {
        this.id = id;
        this.name = name;
        this.phones = new ArrayList<>();
    }
}
