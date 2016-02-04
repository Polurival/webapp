package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * GKislin
 * 24.10.2015.
 */
public interface StreamSerializer {
    void write(Resume r, OutputStream os) throws IOException;
    Resume read(InputStream is) throws IOException;
}
