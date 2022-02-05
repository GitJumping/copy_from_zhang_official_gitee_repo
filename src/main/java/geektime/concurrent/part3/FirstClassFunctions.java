package geektime.concurrent.part3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class FirstClassFunctions {
	static int COUNT = 1000;

	public static void main(String[] args) {
		List<Integer> alist = new ArrayList<Integer>(COUNT);
        for (int i = 0; i < COUNT; i++) {
        	alist.add(new Random().nextInt(COUNT));
        }
        System.out.println("origin : ");
        printQueue10(alist);
        List<Integer> blist = new ArrayList<Integer>(alist);
        System.out.println("sort with lambda : ");
        sortWithLambda(alist);
        printQueue10(alist);
        System.out.println("sort without lambda : ");
        sortWithoutLambda(blist);
        printQueue10(blist);
	}
    private static void printQueue10(List<Integer> alist) {
        for (int i = 0; i < 10; i++) {
        	System.out.print(" " + alist.get(i));
        }
        System.out.println();
	}
	public static List<Integer> sortWithoutLambda(List<Integer> numbers) {
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n1.compareTo(n2);
            }
        });
        return numbers;
    }

    public static List<Integer> sortWithLambda(List<Integer> numbers) {
        Collections.sort(numbers, (n1, n2) -> n1.compareTo(n2));
        return numbers;
    }

}
