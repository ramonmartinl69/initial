package org.myorg.initial.roo.core.domain.model;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class WorkAddressIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    WorkAddressDataOnDemand dod;

	@Test
    public void testCountWorkAddresses() {
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to initialize correctly", dod.getRandomWorkAddress());
        long count = WorkAddress.countWorkAddresses();
        Assert.assertTrue("Counter for 'WorkAddress' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindWorkAddress() {
        WorkAddress obj = dod.getRandomWorkAddress();
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to provide an identifier", id);
        obj = WorkAddress.findWorkAddress(id);
        Assert.assertNotNull("Find method for 'WorkAddress' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'WorkAddress' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllWorkAddresses() {
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to initialize correctly", dod.getRandomWorkAddress());
        long count = WorkAddress.countWorkAddresses();
        Assert.assertTrue("Too expensive to perform a find all test for 'WorkAddress', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<WorkAddress> result = WorkAddress.findAllWorkAddresses();
        Assert.assertNotNull("Find all method for 'WorkAddress' illegally returned null", result);
        Assert.assertTrue("Find all method for 'WorkAddress' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindWorkAddressEntries() {
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to initialize correctly", dod.getRandomWorkAddress());
        long count = WorkAddress.countWorkAddresses();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<WorkAddress> result = WorkAddress.findWorkAddressEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'WorkAddress' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'WorkAddress' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        WorkAddress obj = dod.getRandomWorkAddress();
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to provide an identifier", id);
        obj = WorkAddress.findWorkAddress(id);
        Assert.assertNotNull("Find method for 'WorkAddress' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyWorkAddress(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'WorkAddress' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        WorkAddress obj = dod.getRandomWorkAddress();
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to provide an identifier", id);
        obj = WorkAddress.findWorkAddress(id);
        boolean modified =  dod.modifyWorkAddress(obj);
        Integer currentVersion = obj.getVersion();
        WorkAddress merged = (WorkAddress)obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'WorkAddress' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to initialize correctly", dod.getRandomWorkAddress());
        WorkAddress obj = dod.getNewTransientWorkAddress(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'WorkAddress' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'WorkAddress' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        WorkAddress obj = dod.getRandomWorkAddress();
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'WorkAddress' failed to provide an identifier", id);
        obj = WorkAddress.findWorkAddress(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'WorkAddress' with identifier '" + id + "'", WorkAddress.findWorkAddress(id));
    }
}
