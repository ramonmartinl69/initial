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
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Component
@Configurable
@RooDataOnDemand(entity = HomeAddress.class)
public class HomeAddressDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<HomeAddress> data;

	@Autowired
    PersonDataOnDemand personDataOnDemand;

	public HomeAddress getNewTransientHomeAddress(int index) {
        HomeAddress obj = new HomeAddress();
        setAddresNumber(obj, index);
        setAddress(obj, index);
        setAddressType(obj, index);
        setCountry(obj, index);
        setLocationType(obj, index);
        setPopulation(obj, index);
        setPostalCode(obj, index);
        setProvince(obj, index);
        return obj;
    }

	public void setAddresNumber(HomeAddress obj, int index) {
        String addresNumber = "addresNumber_" + index;
        if (addresNumber.length() > 250) {
            addresNumber = addresNumber.substring(0, 250);
        }
        obj.setAddresNumber(addresNumber);
    }

	public void setAddress(HomeAddress obj, int index) {
        String address = "address_" + index;
        if (address.length() > 250) {
            address = address.substring(0, 250);
        }
        obj.setAddress(address);
    }

	public void setAddressType(HomeAddress obj, int index) {
        AddressTypeEnum addressType = AddressTypeEnum.class.getEnumConstants()[0];
        obj.setAddressType(addressType);
    }

	public void setCountry(HomeAddress obj, int index) {
        CountryEnum country = CountryEnum.class.getEnumConstants()[0];
        obj.setCountry(country);
    }

	public void setLocationType(HomeAddress obj, int index) {
        AddresLocationTypeEnum locationType = AddresLocationTypeEnum.class.getEnumConstants()[0];
        obj.setLocationType(locationType);
    }

	public void setPopulation(HomeAddress obj, int index) {
        String population = "population_" + index;
        if (population.length() > 250) {
            population = population.substring(0, 250);
        }
        obj.setPopulation(population);
    }

	public void setPostalCode(HomeAddress obj, int index) {
        String postalCode = "postalCo_" + index;
        if (postalCode.length() > 10) {
            postalCode = postalCode.substring(0, 10);
        }
        obj.setPostalCode(postalCode);
    }

	public void setProvince(HomeAddress obj, int index) {
        ProvinceEnum province = ProvinceEnum.class.getEnumConstants()[0];
        obj.setProvince(province);
    }

	public HomeAddress getSpecificHomeAddress(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        HomeAddress obj = data.get(index);
        Long id = obj.getId();
        return HomeAddress.findHomeAddress(id);
    }

	public HomeAddress getRandomHomeAddress() {
        init();
        HomeAddress obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return HomeAddress.findHomeAddress(id);
    }

	public boolean modifyHomeAddress(HomeAddress obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = HomeAddress.findHomeAddressEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'HomeAddress' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<HomeAddress>();
        for (int i = 0; i < 10; i++) {
            HomeAddress obj = getNewTransientHomeAddress(i);
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
