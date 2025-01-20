package org.mql.java.introspection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mql.java.uml.introspection.services.RelationMapper;
import org.mql.java.uml.models.Classe;

class RelationMapperTest {

	@Test
	void test() {
		
		Classe cls = new Classe();

        System.out.println(RelationMapper.toString(cls));
        assertTrue(false);
	}

}
