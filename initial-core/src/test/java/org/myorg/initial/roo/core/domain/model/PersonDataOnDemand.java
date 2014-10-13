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
import org.myorg.initial.roo.core.domain.reference.CountryEnum;
import org.myorg.initial.roo.core.domain.reference.SemanticQuestionEnum;
import org.myorg.initial.roo.core.domain.security.PrincipalDataOnDemand;
import org.myorg.initial.roo.core.repository.model.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Configurable
@Component
@RooDataOnDemand(entity = Person.class)
public class PersonDataOnDemand {

	public void setNIF(Person obj, int index) {
        String nIF = "0858559" + String.valueOf(index).charAt(0) + "A";
        if (nIF.length() > 15) {
            nIF = nIF.substring(0, 15);
        }
        obj.setNIF(nIF);
    }

	private Random rnd = new SecureRandom();

	private List<Person> data;

	@Autowired
    PrincipalDataOnDemand principalDataOnDemand;

	@Autowired
    PersonRepository personRepository;

	public Person getNewTransientPerson(int index) {
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

	public void setAlternateQuestion(Person obj, int index) {
        String alternateQuestion = "alternateQuestion_" + index;
        if (alternateQuestion.length() > 100) {
            alternateQuestion = alternateQuestion.substring(0, 100);
        }
        obj.setAlternateQuestion(alternateQuestion);
    }

	public void setAlternateResponse(Person obj, int index) {
        String alternateResponse = "alternateResponse_" + index;
        if (alternateResponse.length() > 100) {
            alternateResponse = alternateResponse.substring(0, 100);
        }
        obj.setAlternateResponse(alternateResponse);
    }

	public void setBirthDate(Person obj, int index) {
        Date birthDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setBirthDate(birthDate);
    }

	public void setFirstName(Person obj, int index) {
        String firstName = "firstName_" + index;
        if (firstName.length() > 50) {
            firstName = firstName.substring(0, 50);
        }
        obj.setFirstName(firstName);
    }

	public void setLastName(Person obj, int index) {
        String lastName = "lastName_" + index;
        if (lastName.length() > 50) {
            lastName = lastName.substring(0, 50);
        }
        obj.setLastName(lastName);
    }

	public void setLastName2(Person obj, int index) {
        String lastName2 = "lastName2_" + index;
        if (lastName2.length() > 50) {
            lastName2 = lastName2.substring(0, 50);
        }
        obj.setLastName2(lastName2);
    }

	public void setNationality(Person obj, int index) {
        CountryEnum nationality = CountryEnum.class.getEnumConstants()[0];
        obj.setNationality(nationality);
    }

	public void setPhone(Person obj, int index) {
        String phone = "phone_" + index;
        if (phone.length() > 15) {
            phone = phone.substring(0, 15);
        }
        obj.setPhone(phone);
    }

	public void setPhotoFile(Person obj, int index) {
        byte[] photoFile = String.valueOf(index).getBytes();
        obj.setPhotoFile(photoFile);
    }

	public void setQuestion(Person obj, int index) {
        SemanticQuestionEnum question = SemanticQuestionEnum.class.getEnumConstants()[0];
        obj.setQuestion(question);
    }

	public void setResponse(Person obj, int index) {
        String response = "response_" + index;
        if (response.length() > 100) {
            response = response.substring(0, 100);
        }
        obj.setResponse(response);
    }

	public Person getSpecificPerson(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Person obj = data.get(index);
        Long id = obj.getId();
        return personRepository.findOne(id);
    }

	public Person getRandomPerson() {
        init();
        Person obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return personRepository.findOne(id);
    }

	public boolean modifyPerson(Person obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = personRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
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
                personRepository.save(obj);
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
