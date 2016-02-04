package ru.javawebinar.webapp.storage;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.javawebinar.webapp.ResumeTestData;
import ru.javawebinar.webapp.exceptions.ExceptionType;
import ru.javawebinar.webapp.exceptions.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * GKislin
 * 09.10.2015.
 */
public abstract class AbstractStorageTest {
    protected static final class ExceptionTypeMatcher extends BaseMatcher<ExceptionType> {
        private final ExceptionType type;

        public ExceptionTypeMatcher(ExceptionType type) {
            this.type = type;
        }

        @Override
        public boolean matches(Object item) {
            return ((WebAppException) item).getType() == type;
        }

        @Override
        public void describeTo(Description description) {
            return;
        }
    }

    //    http://stackoverflow.com/questions/3404301/whats-wrong-with-overridable-method-calls-in-constructors
    private final IStorage storage;   // = getStorage(); bad!
//    protected abstract IStorage getStorage();

    public AbstractStorageTest(IStorage storage) {
        this.storage = storage;
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        storage.clear();
        ResumeTestData.init(storage.isSectionSupported());
        storage.save(ResumeTestData.R1);
        storage.save(ResumeTestData.R2);
        storage.save(ResumeTestData.R3);
    }

    @After
    public void tearDown() throws Exception {
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.load(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    @Test
    public void testClear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void testUpdate() throws Exception {
        ResumeTestData.R2.setFullName("Updated N2");
        storage.update(ResumeTestData.R2);
        assertGet(ResumeTestData.R2);
    }

    @Test
    public void testLoad() throws Exception {
        assertGet(ResumeTestData.R1);
        assertGet(ResumeTestData.R2);
        assertGet(ResumeTestData.R3);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(WebAppException.class);
        thrown.expectMessage(ExceptionType.NOT_FOUND.getMessage());
        thrown.expect(new ExceptionTypeMatcher(ExceptionType.NOT_FOUND));
        storage.load("dummy");
    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(ResumeTestData.R1.getUuid());
        assertSize(2);
        assertGet(ResumeTestData.R2);
        assertGet(ResumeTestData.R3);
        thrown.expect(WebAppException.class);
        thrown.expectMessage(ExceptionType.NOT_FOUND.getMessage());
        assertGet(ResumeTestData.R1);
    }

    @Test
    public void testGetAllSorted() throws Exception {
        List<Resume> list = Arrays.asList(ResumeTestData.R1, ResumeTestData.R2, ResumeTestData.R3);
        assertEquals(list, new ArrayList<>(storage.getAllSorted()));
    }

    @Test
    public void testSize() throws Exception {
        assertSize(3);
    }

    @Test
    public void testSaveAlreadyExist() throws Exception {
        thrown.expect(WebAppException.class);
        thrown.expectMessage(ExceptionType.ALREADY_EXISTS.getMessage());
        storage.save(ResumeTestData.R1);
    }

    @Test
    public void testUpdateMissed() throws Exception {
        Resume resume = new Resume("fullName_U1", "location_U1");
        thrown.expect(WebAppException.class);
        thrown.expectMessage(ExceptionType.NOT_FOUND.getMessage());
        storage.update(resume);
    }
}