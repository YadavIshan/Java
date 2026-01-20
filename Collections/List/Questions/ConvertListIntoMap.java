package Collections.List.Questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConvertListIntoMap {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add("Hello");
        list.add(1);
        list.add(1);
        list.add(3.14);
        list.add(true);
        list.add(new Object());

        Map<Integer, Object> map = new HashMap<Integer, Object>();
        // Converting to Stream , then converting it to map
        // Where list::indexOf is the key
        // Function.identity() is the value
        // (oldValue, newValue) -> oldValue is the merge function
        int[] index = { 0 };
        map = list.stream()
                .collect(Collectors.toMap(e -> index[0]++, Function.identity(),
                        (oldValue, newValue) -> oldValue));
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
