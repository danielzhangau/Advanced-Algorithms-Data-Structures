import java.util.Arrays;
import java.util.List;

public class SNI_create {

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(9, 8, 4, 5, 0 ,0, 4, 8, 3, 0, 5, 2);
    System.out.println("Origin : " + list.toString());

    for (int i = 2; i < 12; i++) {
      if (list.get(i).equals(list.get(i - 1))){
        list.set(i, (list.get(i) + 3) % 10);
      }
    }
    System.out.println("new : " + list.toString());
  }
}
