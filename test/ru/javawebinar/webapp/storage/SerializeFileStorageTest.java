package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.Config;

/**
 * GKislin
 * 09.01.2015.
 */
public class SerializeFileStorageTest extends AbstractStorageTest {
    public SerializeFileStorageTest() {
        super(new PathStorage(Config.STORAGE, new SerializeFileStorage()));
    }
}
