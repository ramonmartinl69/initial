package org.myorg.initial.roo.core.domain.security;
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
public class AuthRoleIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    AuthRoleDataOnDemand dod;

	@Test
    public void testCountAuthRoles() {
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to initialize correctly", dod.getRandomAuthRole());
        long count = AuthRole.countAuthRoles();
        Assert.assertTrue("Counter for 'AuthRole' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindAuthRole() {
        AuthRole obj = dod.getRandomAuthRole();
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to provide an identifier", id);
        obj = AuthRole.findAuthRole(id);
        Assert.assertNotNull("Find method for 'AuthRole' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'AuthRole' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllAuthRoles() {
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to initialize correctly", dod.getRandomAuthRole());
        long count = AuthRole.countAuthRoles();
        Assert.assertTrue("Too expensive to perform a find all test for 'AuthRole', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<AuthRole> result = AuthRole.findAllAuthRoles();
        Assert.assertNotNull("Find all method for 'AuthRole' illegally returned null", result);
        Assert.assertTrue("Find all method for 'AuthRole' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindAuthRoleEntries() {
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to initialize correctly", dod.getRandomAuthRole());
        long count = AuthRole.countAuthRoles();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<AuthRole> result = AuthRole.findAuthRoleEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'AuthRole' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'AuthRole' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        AuthRole obj = dod.getRandomAuthRole();
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to provide an identifier", id);
        obj = AuthRole.findAuthRole(id);
        Assert.assertNotNull("Find method for 'AuthRole' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAuthRole(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'AuthRole' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        AuthRole obj = dod.getRandomAuthRole();
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to provide an identifier", id);
        obj = AuthRole.findAuthRole(id);
        boolean modified =  dod.modifyAuthRole(obj);
        Integer currentVersion = obj.getVersion();
        AuthRole merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'AuthRole' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to initialize correctly", dod.getRandomAuthRole());
        AuthRole obj = dod.getNewTransientAuthRole(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'AuthRole' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'AuthRole' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        AuthRole obj = dod.getRandomAuthRole();
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AuthRole' failed to provide an identifier", id);
        obj = AuthRole.findAuthRole(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'AuthRole' with identifier '" + id + "'", AuthRole.findAuthRole(id));
    }
}
