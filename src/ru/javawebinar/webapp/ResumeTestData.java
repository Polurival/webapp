package ru.javawebinar.webapp;

import ru.javawebinar.webapp.model.*;

import java.time.Month;

/**
 * GKislin
 * 30.10.2015.
 */
public class ResumeTestData {
    public static Resume R1;
    public static Resume R2;
    public static Resume R3;

    public static void init(boolean sectionSupported) {
        R1 = new Resume("Полное Имя1");
        R2 = new Resume("Полное Имя2");
        R3 = new Resume("Полное Имя3");
        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");

        if (sectionSupported) {
            R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
            R1.addSection(SectionType.ACHIEVEMENT, new MultiTextSection("Achivment11", "Achivment12"));
            R1.addSection(SectionType.QUALIFICATIONS, new MultiTextSection("Java", "SQL"));
            R1.addSection(SectionType.EXPERIENCE,
                    new OrganizationSection(
                            new Organization("Organization11", null,
                                    new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                    new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
            R1.addSection(SectionType.EDUCATION,
                    new OrganizationSection(
                            new Organization("Institute", null,
                                    new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                    new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                            new Organization("Organization12", "http://Organization12.ru")));
        }
    }
}
