package org.myorg.initial.roo.core.domain.model;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.myorg.initial.roo.core.domain.reference.AddresLocationTypeEnum;
import org.myorg.initial.roo.core.domain.reference.AddressTypeEnum;
import org.myorg.initial.roo.core.domain.reference.CountryEnum;
import org.myorg.initial.roo.core.domain.reference.ProvinceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class WorkAddressDataOnDemand {

	public void setPostalCode(WorkAddress obj, int index) {
        String postalCode = "123" + index;
        if (postalCode.length() > 10) {
            postalCode = postalCode.substring(0, 10);
        }
        obj.setPostalCode(postalCode);
    }

	private Random rnd = new SecureRandom();

	private List<WorkAddress> data;

	@Autowired
    PersonDataOnDemand personDataOnDemand;

	public WorkAddress getNewTransientWorkAddress(int index) {
        WorkAddress obj = new WorkAddress();
        setAddresNumber(obj, index);
        setAddress(obj, index);
        setAddressType(obj, index);
        setCountry(obj, index);
        setEnterpriseName(obj, index);
        setLocationType(obj, index);
        setPopulation(obj, index);
        setPostalAddress(obj, index);
        setPostalCode(obj, index);
        setProvince(obj, index);
        return obj;
    }

	public void setAddresNumber(WorkAddress obj, int index) {
        String addresNumber = "addresNumber_" + index;
        if (addresNumber.length() > 250) {
            addresNumber = addresNumber.substring(0, 250);
        }
        obj.setAddresNumber(addresNumber);
    }

	public void setAddress(WorkAddress obj, int index) {
        String address = "address_" + index;
        if (address.length() > 250) {
            address = address.substring(0, 250);
        }
        obj.setAddress(address);
    }

	public void setAddressType(WorkAddress obj, int index) {
        AddressTypeEnum addressType = AddressTypeEnum.class.getEnumConstants()[0];
        obj.setAddressType(addressType);
    }

	public void setCountry(WorkAddress obj, int index) {
        CountryEnum country = CountryEnum.class.getEnumConstants()[0];
        obj.setCountry(country);
    }

	public void setEnterpriseName(WorkAddress obj, int index) {
        String enterpriseName = "enterpriseName_" + index;
        if (enterpriseName.length() > 250) {
            enterpriseName = enterpriseName.substring(0, 250);
        }
        obj.setEnterpriseName(enterpriseName);
    }

	public void setLocationType(WorkAddress obj, int index) {
        AddresLocationTypeEnum locationType = AddresLocationTypeEnum.class.getEnumConstants()[0];
        obj.setLocationType(locationType);
    }

	public void setPopulation(WorkAddress obj, int index) {
        String population = "population_" + index;
        if (population.length() > 250) {
            population = population.substring(0, 250);
        }
        obj.setPopulation(population);
    }

	public void setPostalAddress(WorkAddress obj, int index) {
        String postalAddress = "postalAddress_" + index;
        if (postalAddress.length() > 250) {
            postalAddress = postalAddress.substring(0, 250);
        }
        obj.setPostalAddress(postalAddress);
    }

	public void setProvince(WorkAddress obj, int index) {
        ProvinceEnum province = ProvinceEnum.class.getEnumConstants()[0];
        obj.setProvince(province);
    }

	public WorkAddress getSpecificWorkAddress(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        WorkAddress obj = data.get(index);
        Long id = obj.getId();
        return WorkAddress.findWorkAddress(id);
    }

	public WorkAddress getRandomWorkAddress() {
        init();
        WorkAddress obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return WorkAddress.findWorkAddress(id);
    }

	public boolean modifyWorkAddress(WorkAddress obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = WorkAddress.findWorkAddressEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'WorkAddress' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<WorkAddress>();
        for (int i = 0; i < 10; i++) {
            WorkAddress obj = getNewTransientWorkAddress(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
}
