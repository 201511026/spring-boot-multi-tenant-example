package com.gdb.entity;

import java.io.Serializable;

public class PersonId implements Serializable {

	private Long id;

    private String cmpCode;
    
    public PersonId(Long id, String cmpCode) {
    	this.id = id;
    	this.cmpCode = cmpCode;
    }
   
}
