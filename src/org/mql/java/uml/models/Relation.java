package org.mql.java.uml.models;

import org.mql.java.uml.enums.Multiplicity;
import org.mql.java.uml.enums.RelationType;

public class Relation {

	private RelationType type;
	private Class<?> sourceClass;
	private Class<?> destinationClass ;
	private Multiplicity sourceMultiplicity;
	private Multiplicity destinationMultiplicity;
	
	public Relation() {
		super();
	}

	public Relation(RelationType type, Class<?> sourceClass, Class<?> destinationClass, Multiplicity sourceMultiplicity,
			Multiplicity destinationMultiplicity) {
		super();
		this.type = type;
		this.sourceClass = sourceClass;
		this.destinationClass = destinationClass;
		this.sourceMultiplicity = sourceMultiplicity;
		this.destinationMultiplicity = destinationMultiplicity;
	}

	public RelationType getType() {
		return type;
	}

	public void setType(RelationType type) {
		this.type = type;
	}

	public Class<?> getSouceClass() {
		return sourceClass;
	}

	public void setSouceClass(Class<?> sourceClass) {
		this.sourceClass = sourceClass;
	}

	public Class<?> getDestinationClass() {
		return destinationClass;
	}

	public void setDestinationClass(Class<?> destinationClass) {
		this.destinationClass = destinationClass;
	}

	public Multiplicity getSourceMultiplicity() {
		return sourceMultiplicity;
	}

	public void setSourceMultiplicity(Multiplicity sourceMultiplicity) {
		this.sourceMultiplicity = sourceMultiplicity;
	}

	public Multiplicity getDestinationMultiplicity() {
		return destinationMultiplicity;
	}

	public void setDestinationMultiplicity(Multiplicity destinationMultiplicity) {
		this.destinationMultiplicity = destinationMultiplicity;
	}

	@Override
	public String toString() {
		return "Relation [type=" + type + ", sourceClass=" + sourceClass + ", destinationClass=" + destinationClass
				+ ", sourceMultiplicity=" + sourceMultiplicity + ", destinationMultiplicity=" + destinationMultiplicity
				+ "]";
	}
	
	

}
