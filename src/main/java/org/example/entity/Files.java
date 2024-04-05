package org.example.entity;

import java.util.UUID;

public class Files {
    private Long id;
    private String name;
    private String reference;
    private String addReference;


    public Files(Long id, String name, String reference, String addReference) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.addReference = addReference;
    }

    // Id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Reference
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }

    // AddReference
    public String getAddReference() {
        return addReference;
    }
    public void setAddReference(String addReference) {
        this.addReference = addReference;
    }
}
