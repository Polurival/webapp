package ru.javawebinar.webapp.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.model.Section;
import ru.javawebinar.webapp.util.JsonSectionAdapter;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * GKislin
 * 30.10.2015.
 */
public class JsonFileStorage implements StreamSerializer {

    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                .registerTypeAdapter(Section.class, new JsonSectionAdapter())
                .create();
    }

    @Override
    public void write(Resume r, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            GSON.toJson(r, writer);
        }
    }

    @Override
    public Resume read(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return GSON.fromJson(reader, Resume.class);
        }
    }
}
