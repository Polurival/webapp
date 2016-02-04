import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * GKislin
 * 16.10.2015.
 */
public class MainMap {
    public static void main(String[] args) {
        final HashMap<ContactType, String> contacts = new HashMap<ContactType, String>() {
            {
                put(ContactType.HOME_PHONE, "+324334345");
                put(ContactType.HOME_PHONE, "+324334345");
            }

            void print(String str) {
                System.out.println(str);
            }
        };

        contacts.put(ContactType.PHONE, "324334345");
        contacts.put(ContactType.HOME_PHONE, "+324334345");
        contacts.get(ContactType.PHONE);
        System.out.println(contacts);

        final Map<Resume, Integer> resumes = new HashMap<>();

        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>() {{
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                put(ch, (int) ch);
            }
        }};
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println("" + entry.getKey() + ":=" + entry.getValue());
        }

    }
}
