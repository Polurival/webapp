package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.Config;

/**
 * GKislin
 * 01.11.2015.
 */
public class JsonFileStorageTest extends AbstractStorageTest {
    public JsonFileStorageTest() {
        super(new PathStorage(Config.STORAGE, new JsonFileStorage()));
    }
}
