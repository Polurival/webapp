import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * User: gkislin
 * Date: 18.06.2014
 */
public class Main {
    public static void main(String[] args) {
        String value = "value";
        System.out.println(value.charAt(3));
        byte[] bytes = value.getBytes();
        System.out.println(Charset.defaultCharset().name());
        char[] chars = value.toCharArray();
        System.out.println(Arrays.toString(bytes));
        String a = "abc";
        String b = ("ab" + new String("c")).intern();
        System.out.println(a == b);
        System.out.println(a.equals(b));
        Integer integer1 = Integer.valueOf(200);
        Integer integer2 = Integer.valueOf(200);
        System.out.println(integer1 == integer2);
        System.out.println(integer1.equals(integer2));

        String str = "";
        for (int i = 0; i < 10; i++) {
            str += i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
        System.out.println(sb.toString());
    }
}
