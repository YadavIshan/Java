package Collections.List.Questions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindCommonElements {
    public static void main(String[] args) {
        // Question: Given two ArrayLists, find the common elements between them.
        // The result should contain unique common elements.

        // Example Input:
        // List 1: [1, 2, 3, 4, 5, 3]
        // List 2: [3, 4, 5, 6, 7, 5]
        // Expected Output: [3, 4, 5]

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list2.add(7);
        list2.add(5);

        List<Integer> commonElements = list1.stream().filter(list2::contains).collect(Collectors.toList());

        // Using RetainAll
        List<Integer> commonElementsRetainAll = new ArrayList<>(list1);
        commonElementsRetainAll.retainAll(list2);
        commonElementsRetainAll = commonElementsRetainAll.stream().distinct().collect(Collectors.toList());
        System.out.println("Common Elements (retainAll): " + commonElementsRetainAll);
        System.out.println("Common Elements: " + commonElements);
    }
}
