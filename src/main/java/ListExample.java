import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("Lily Aldrin", 27),
                new Person("Marshall Eriksen", 27),
                new Person("Robin Scherbatsky", 25)
        ));
        System.out.println(personList);
    }
}