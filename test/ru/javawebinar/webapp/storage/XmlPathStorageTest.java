package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.Config;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(Config.STORAGE, new XmlFileStorage()));
    }
}