package org.myorg.initial.roo.core.domain.security;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.myorg.initial.roo.core.domain.model.PersonDataOnDemand;
import org.myorg.initial.roo.core.repository.security.PrincipalRepository;
import org.myorg.initial.roo.core.service.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class PrincipalDataOnDemand {

	public void setUserName(Principal obj, int index) {
        String userName = "userName" + index + "@myorg.org";
        if (userName.length() > 50) {
            userName = userName.substring(0, 50);
        }
        obj.setUserName(userName);
    }

	private Random rnd = new SecureRandom();

	private List<Principal> data;

	@Autowired
    PersonDataOnDemand personDataOnDemand;

	@Autowired
    PrincipalService principalService;

	@Autowired
    PrincipalRepository principalRepository;

	public Principal getNewTransientPrincipal(int index) {
        Principal obj = new Principal();
        setActivationKey(obj, index);
        setEnabled(obj, index);
        setPassword(obj, index);
        setUserName(obj, index);
        return obj;
    }

	public void setActivationKey(Principal obj, int index) {
        String activationKey = "activationKey_" + index;
        if (activationKey.length() > 100) {
            activationKey = activationKey.substring(0, 100);
        }
        obj.setActivationKey(activationKey);
    }

	public void setEnabled(Principal obj, int index) {
        Boolean enabled = Boolean.TRUE;
        obj.setEnabled(enabled);
    }

	public void setPassword(Principal obj, int index) {
        String password = "password_" + index;
        if (password.length() > 100) {
            password = password.substring(0, 100);
        }
        obj.setPassword(password);
    }

	public Principal getSpecificPrincipal(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Principal obj = data.get(index);
        Long id = obj.getId();
        return principalService.findPrincipal(id);
    }

	public Principal getRandomPrincipal() {
        init();
        Principal obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return principalService.findPrincipal(id);
    }

	public boolean modifyPrincipal(Principal obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = principalService.findPrincipalEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Principal' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Principal>();
        for (int i = 0; i < 10; i++) {
            Principal obj = getNewTransientPrincipal(i);
            try {
                principalService.savePrincipal(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            principalRepository.flush();
            data.add(obj);
        }
    }
}
