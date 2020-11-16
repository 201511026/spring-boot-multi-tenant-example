package com.gdb.entity;

import java.io.Serializable;

public class PersonId implements Serializable {

	private Long id;

    private String hdqrtCode;
    
    public PersonId(Long id, String hdqrtCode) {
    	this.id = id;
    	this.hdqrtCode = hdqrtCode;
    }
   
}
