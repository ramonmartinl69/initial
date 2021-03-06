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

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class HomeAddressIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    HomeAddressDataOnDemand dod;

	@Test
    public void testCountHomeAddresses() {
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to initialize correctly", dod.getRandomHomeAddress());
        long count = HomeAddress.countHomeAddresses();
        Assert.assertTrue("Counter for 'HomeAddress' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindHomeAddress() {
        HomeAddress obj = dod.getRandomHomeAddress();
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to provide an identifier", id);
        obj = HomeAddress.findHomeAddress(id);
        Assert.assertNotNull("Find method for 'HomeAddress' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'HomeAddress' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllHomeAddresses() {
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to initialize correctly", dod.getRandomHomeAddress());
        long count = HomeAddress.countHomeAddresses();
        Assert.assertTrue("Too expensive to perform a find all test for 'HomeAddress', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<HomeAddress> result = HomeAddress.findAllHomeAddresses();
        Assert.assertNotNull("Find all method for 'HomeAddress' illegally returned null", result);
        Assert.assertTrue("Find all method for 'HomeAddress' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindHomeAddressEntries() {
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to initialize correctly", dod.getRandomHomeAddress());
        long count = HomeAddress.countHomeAddresses();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<HomeAddress> result = HomeAddress.findHomeAddressEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'HomeAddress' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'HomeAddress' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        HomeAddress obj = dod.getRandomHomeAddress();
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to provide an identifier", id);
        obj = HomeAddress.findHomeAddress(id);
        Assert.assertNotNull("Find method for 'HomeAddress' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyHomeAddress(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'HomeAddress' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        HomeAddress obj = dod.getRandomHomeAddress();
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to provide an identifier", id);
        obj = HomeAddress.findHomeAddress(id);
        boolean modified =  dod.modifyHomeAddress(obj);
        Integer currentVersion = obj.getVersion();
        HomeAddress merged = (HomeAddress)obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'HomeAddress' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to initialize correctly", dod.getRandomHomeAddress());
        HomeAddress obj = dod.getNewTransientHomeAddress(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'HomeAddress' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'HomeAddress' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        HomeAddress obj = dod.getRandomHomeAddress();
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'HomeAddress' failed to provide an identifier", id);
        obj = HomeAddress.findHomeAddress(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'HomeAddress' with identifier '" + id + "'", HomeAddress.findHomeAddress(id));
    }
}
