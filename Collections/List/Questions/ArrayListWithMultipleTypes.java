package Collections.List.Questions;

import java.util.ArrayList;

public class ArrayListWithMultipleTypes {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add("Hello");
        list.add(1);
        list.add(true);
        list.add(3.14);
        list.add(new Object());

        System.out.println(list);
    }
}
