package org.myorg.initial.roo.core.domain.security;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.myorg.initial.roo.core.repository.security.PrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class PrincipalIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    PrincipalDataOnDemand dod;

	@Autowired
    PrincipalRepository principalRepository;

	@Test
    public void testCount() {
        Assert.assertNotNull("Data on demand for 'Principal' failed to initialize correctly", dod.getRandomPrincipal());
        long count = principalRepository.count();
        Assert.assertTrue("Counter for 'Principal' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFind() {
        Principal obj = dod.getRandomPrincipal();
        Assert.assertNotNull("Data on demand for 'Principal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Principal' failed to provide an identifier", id);
        obj = principalRepository.findOne(id);
        Assert.assertNotNull("Find method for 'Principal' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Principal' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAll() {
        Assert.assertNotNull("Data on demand for 'Principal' failed to initialize correctly", dod.getRandomPrincipal());
        long count = principalRepository.count();
        Assert.assertTrue("Too expensive to perform a find all test for 'Principal', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Principal> result = principalRepository.findAll();
        Assert.assertNotNull("Find all method for 'Principal' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Principal' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEntries() {
        Assert.assertNotNull("Data on demand for 'Principal' failed to initialize correctly", dod.getRandomPrincipal());
        long count = principalRepository.count();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Principal> result = principalRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
        Assert.assertNotNull("Find entries method for 'Principal' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Principal' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Principal obj = dod.getRandomPrincipal();
        Assert.assertNotNull("Data on demand for 'Principal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Principal' failed to provide an identifier", id);
        obj = principalRepository.findOne(id);
        Assert.assertNotNull("Find method for 'Principal' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyPrincipal(obj);
        Integer currentVersion = obj.getVersion();
        principalRepository.flush();
        Assert.assertTrue("Version for 'Principal' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveUpdate() {
        Principal obj = dod.getRandomPrincipal();
        Assert.assertNotNull("Data on demand for 'Principal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Principal' failed to provide an identifier", id);
        obj = principalRepository.findOne(id);
        boolean modified =  dod.modifyPrincipal(obj);
        Integer currentVersion = obj.getVersion();
        Principal merged = principalRepository.save(obj);
        principalRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Principal' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSave() {
        Assert.assertNotNull("Data on demand for 'Principal' failed to initialize correctly", dod.getRandomPrincipal());
        Principal obj = dod.getNewTransientPrincipal(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Principal' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Principal' identifier to be null", obj.getId());
        try {
            principalRepository.save(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        principalRepository.flush();
        Assert.assertNotNull("Expected 'Principal' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDelete() {
        Principal obj = dod.getRandomPrincipal();
        Assert.assertNotNull("Data on demand for 'Principal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Principal' failed to provide an identifier", id);
        obj = principalRepository.findOne(id);
        principalRepository.delete(obj);
        principalRepository.flush();
        Assert.assertNull("Failed to remove 'Principal' with identifier '" + id + "'", principalRepository.findOne(id));
    }
}
