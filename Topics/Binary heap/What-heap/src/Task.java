// write your code here 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task {
  public static void main(String[] args) {
    Scanner sca = new Scanner(System.in);
    List<int[]> list = new ArrayList<>();

    while (true) {
      String input = sca.nextLine();
      if (input.equals("")) {
        break;
      }
      String[] inp = input.split(" ");
      int[] array = new int[inp.length];
      for (int i = 0; i < array.length; i++) {
        array[i] = Integer.parseInt(inp[i]);
      }
      list.add(array);
    }

    list.forEach(x -> {
      if (isMax(x)) {
        System.out.println("max-heap");
      } else if (isMin(x)) {
        System.out.println("min-heap");
      } else {
        System.out.println("not-heap");
      }
    });


  }

  static boolean isMax(int[] array) {
    boolean isMax = true;
    for (int i = 0; i < array.length; i++) {
      int current = i + 1;
      int left = 2 * current - 1;
      int right = 2 * current;

      if (array.length > right) {
        if (array[i] < array[left] && array[i] < array[right]) {
          isMax = false;
          break;
        }
      } else if (array.length > left) {
        if (array[i] < array[left]) {
          isMax = false;
          break;
        }
      }
    }
    return isMax;
  }


  static boolean isMin(int[] array) {
    boolean isMin = true;
    for (int i = 0; i < array.length; i++) {
      int current = i + 1;
      int left = 2 * current - 1;
      int right = 2 * current;

      if (array.length > right) {
        if (array[i] > array[left] && array[i] > array[right]) {
          isMin = false;
          break;
        }
      } else if (array.length > left) {
        if (array[i] > array[left]) {
          isMin = false;
          break;
        }
      }
    }
    return isMin;
  }
}
