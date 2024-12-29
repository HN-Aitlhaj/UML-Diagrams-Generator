package org.mql.java.models;

import org.mql.java.enums.Multiplicity;
import org.mql.java.enums.RelationType;

public class Relation {

	private RelationType type;
	private Classe sourceClass;
	private Classe destinationClass ;
	private Multiplicity sourceMultiplicity;
	private Multiplicity destinationMultiplicity;
	
	public Relation() {
		super();
	}

	public Relation(RelationType type, Classe sourceClass, Classe destinationClass, Multiplicity sourceMultiplicity,
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

	public Classe getSouceClass() {
		return sourceClass;
	}

	public void setSouceClass(Classe sourceClass) {
		this.sourceClass = sourceClass;
	}

	public Classe getDestinationClass() {
		return destinationClass;
	}

	public void setDestinationClass(Classe destinationClass) {
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
