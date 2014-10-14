package org.myorg.initial.roo.core.repository.model;
import org.myorg.initial.roo.core.domain.model.Person;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Person.class)
public interface PersonRepository {
}
