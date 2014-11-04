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
public class OtherAddressIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    OtherAddressDataOnDemand dod;

	@Test
    public void testCountOtherAddresses() {
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to initialize correctly", dod.getRandomOtherAddress());
        long count = OtherAddress.countOtherAddresses();
        Assert.assertTrue("Counter for 'OtherAddress' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindOtherAddress() {
        OtherAddress obj = dod.getRandomOtherAddress();
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to provide an identifier", id);
        obj = OtherAddress.findOtherAddress(id);
        Assert.assertNotNull("Find method for 'OtherAddress' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'OtherAddress' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllOtherAddresses() {
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to initialize correctly", dod.getRandomOtherAddress());
        long count = OtherAddress.countOtherAddresses();
        Assert.assertTrue("Too expensive to perform a find all test for 'OtherAddress', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<OtherAddress> result = OtherAddress.findAllOtherAddresses();
        Assert.assertNotNull("Find all method for 'OtherAddress' illegally returned null", result);
        Assert.assertTrue("Find all method for 'OtherAddress' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindOtherAddressEntries() {
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to initialize correctly", dod.getRandomOtherAddress());
        long count = OtherAddress.countOtherAddresses();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<OtherAddress> result = OtherAddress.findOtherAddressEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'OtherAddress' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'OtherAddress' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        OtherAddress obj = dod.getRandomOtherAddress();
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to provide an identifier", id);
        obj = OtherAddress.findOtherAddress(id);
        Assert.assertNotNull("Find method for 'OtherAddress' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyOtherAddress(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'OtherAddress' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        OtherAddress obj = dod.getRandomOtherAddress();
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to provide an identifier", id);
        obj = OtherAddress.findOtherAddress(id);
        boolean modified =  dod.modifyOtherAddress(obj);
        Integer currentVersion = obj.getVersion();
        OtherAddress merged = (OtherAddress)obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'OtherAddress' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to initialize correctly", dod.getRandomOtherAddress());
        OtherAddress obj = dod.getNewTransientOtherAddress(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'OtherAddress' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'OtherAddress' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        OtherAddress obj = dod.getRandomOtherAddress();
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'OtherAddress' failed to provide an identifier", id);
        obj = OtherAddress.findOtherAddress(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'OtherAddress' with identifier '" + id + "'", OtherAddress.findOtherAddress(id));
    }
}
