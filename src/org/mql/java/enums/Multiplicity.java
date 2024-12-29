package org.mql.java.enums;

public enum Multiplicity {
	
	ONE, //single Object
	ZERO_ONE, //if it can be null
	ONE_MANY,//1..* => collections(list-set...)
	ZERO_MANY // if the collection can be null
}
