import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExample {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("Winston Bishop", 40),
                new Person("Jessica Day", 41)
        ));

        for (Person person : personList) {
            System.out.println(person.getCareer());
        }
    }
}