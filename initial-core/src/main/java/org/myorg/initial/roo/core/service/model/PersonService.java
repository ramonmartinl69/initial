package org.myorg.initial.roo.core.service.model;

import java.util.List;
import org.myorg.initial.roo.core.domain.model.Person;

public interface PersonService {

	public abstract long countAllPeople();


	public abstract void deletePerson(Person person);


	public abstract Person findPerson(Long id);


	public abstract List<Person> findAllPeople();


	public abstract List<Person> findPersonEntries(int firstResult, int maxResults);


	public abstract void savePerson(Person person);


	public abstract Person updatePerson(Person person);

}
