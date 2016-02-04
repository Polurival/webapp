import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * User: gkislin
 * Date: 18.06.2014
 */
public class MainList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
//        for (Integer i : list) {
//            if (i % 2 == 0) {
//                list.remove(i);
//            }
//        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if (i % 2 == 0) {
                iterator.remove();
            }
        }
        System.out.println(list);

        List<Integer> integers = Collections.synchronizedList(list);
        List<Integer> integers2 = Collections.unmodifiableList(list);
        List<Integer> integers3 = Collections.singletonList(1);

        List<String> listStr = new ArrayList<>();
        listStr.add("fgghf");

        ((List)listStr).add(2);
        System.out.println(list.getClass()==listStr.getClass());
        System.out.println(listStr);
        Object o = listStr.get(1);
        System.out.println(o.getClass());
    }
}
