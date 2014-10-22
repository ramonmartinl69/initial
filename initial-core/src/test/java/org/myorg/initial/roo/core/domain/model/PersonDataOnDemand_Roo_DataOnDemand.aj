// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.myorg.initial.roo.core.domain.model.Person;
import org.myorg.initial.roo.core.domain.model.PersonDataOnDemand;
import org.myorg.initial.roo.core.domain.reference.CountryEnum;
import org.myorg.initial.roo.core.domain.reference.SemanticQuestionEnum;
import org.myorg.initial.roo.core.domain.security.PrincipalDataOnDemand;
import org.myorg.initial.roo.core.repository.model.PersonRepository;
import org.myorg.initial.roo.core.service.model.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect PersonDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PersonDataOnDemand: @Component;
    
    private Random PersonDataOnDemand.rnd = new SecureRandom();
    
    private List<Person> PersonDataOnDemand.data;
    
    @Autowired
    PrincipalDataOnDemand PersonDataOnDemand.principalDataOnDemand;
    
    @Autowired
    PersonService PersonDataOnDemand.personService;
    
    @Autowired
    PersonRepository PersonDataOnDemand.personRepository;
    
    public Person PersonDataOnDemand.getNewTransientPerson(int index) {
        Person obj = new Person();
        setAlternateQuestion(obj, index);
        setAlternateResponse(obj, index);
        setBirthDate(obj, index);
        setFirstName(obj, index);
        setLastName(obj, index);
        setLastName2(obj, index);
        setNIF(obj, index);
        setNationality(obj, index);
        setPhone(obj, index);
        setPhotoFile(obj, index);
        setQuestion(obj, index);
        setResponse(obj, index);
        return obj;
    }
    
    public void PersonDataOnDemand.setAlternateQuestion(Person obj, int index) {
        String alternateQuestion = "alternateQuestion_" + index;
        if (alternateQuestion.length() > 100) {
            alternateQuestion = alternateQuestion.substring(0, 100);
        }
        obj.setAlternateQuestion(alternateQuestion);
    }
    
    public void PersonDataOnDemand.setAlternateResponse(Person obj, int index) {
        String alternateResponse = "alternateResponse_" + index;
        if (alternateResponse.length() > 100) {
            alternateResponse = alternateResponse.substring(0, 100);
        }
        obj.setAlternateResponse(alternateResponse);
    }
    
    public void PersonDataOnDemand.setBirthDate(Person obj, int index) {
        Date birthDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setBirthDate(birthDate);
    }
    
    public void PersonDataOnDemand.setFirstName(Person obj, int index) {
        String firstName = "firstName_" + index;
        if (firstName.length() > 50) {
            firstName = firstName.substring(0, 50);
        }
        obj.setFirstName(firstName);
    }
    
    public void PersonDataOnDemand.setLastName(Person obj, int index) {
        String lastName = "lastName_" + index;
        if (lastName.length() > 50) {
            lastName = lastName.substring(0, 50);
        }
        obj.setLastName(lastName);
    }
    
    public void PersonDataOnDemand.setLastName2(Person obj, int index) {
        String lastName2 = "lastName2_" + index;
        if (lastName2.length() > 50) {
            lastName2 = lastName2.substring(0, 50);
        }
        obj.setLastName2(lastName2);
    }
    
    public void PersonDataOnDemand.setNationality(Person obj, int index) {
        CountryEnum nationality = CountryEnum.class.getEnumConstants()[0];
        obj.setNationality(nationality);
    }
    
    public void PersonDataOnDemand.setPhone(Person obj, int index) {
        String phone = "phone_" + index;
        if (phone.length() > 15) {
            phone = phone.substring(0, 15);
        }
        obj.setPhone(phone);
    }
    
    public void PersonDataOnDemand.setPhotoFile(Person obj, int index) {
        byte[] photoFile = String.valueOf(index).getBytes();
        obj.setPhotoFile(photoFile);
    }
    
    public void PersonDataOnDemand.setQuestion(Person obj, int index) {
        SemanticQuestionEnum question = SemanticQuestionEnum.class.getEnumConstants()[0];
        obj.setQuestion(question);
    }
    
    public void PersonDataOnDemand.setResponse(Person obj, int index) {
        String response = "response_" + index;
        if (response.length() > 100) {
            response = response.substring(0, 100);
        }
        obj.setResponse(response);
    }
    
    public Person PersonDataOnDemand.getSpecificPerson(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Person obj = data.get(index);
        Long id = obj.getId();
        return personService.findPerson(id);
    }
    
    public Person PersonDataOnDemand.getRandomPerson() {
        init();
        Person obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return personService.findPerson(id);
    }
    
    public boolean PersonDataOnDemand.modifyPerson(Person obj) {
        return false;
    }
    
    public void PersonDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = personService.findPersonEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Person' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person obj = getNewTransientPerson(i);
            try {
                personService.savePerson(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            personRepository.flush();
            data.add(obj);
        }
    }
    
}
