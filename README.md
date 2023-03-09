# Collections and Dynamic Binding

## Learning Goals

- Step through the iterations of a `List` of `Person` objects.
- Add a subclass to the `Person` objects and look at the dynamic binding.
- 

## Introduction

In the last lesson, we learned about the `List` interface and one of its most
popular implementations, the `ArrayList`. We'll continue looking at this
collection and how to iterate through a `List` by stepping through the code. We
will also expand on our `Person` object we worked with in the last lesson.

Fork and clone this lesson so you can see the `ListExample` class and the
`Person` class. These two classes are the same ones we left off with in the last
lesson. Code along with us in this lesson to further your understanding of
collections and dynamic binding.

## Iterating Through a List

Let's make the following changes to the `ListExample` class:

```java
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

        // Iterate through the Person List to print the names of each Person
        for (Person person : personList) {
            System.out.println(person.getName());
        }
    }
}
```

Let's iterate through the `personList` we have created to print the names of
each person. Notice, we can use a `for` loop here since we know how many people
are in our list. We can also use an enhanced `for` loop here too - just like we
would for an array.

Set a breakpoint at the `for` loop: `for (Person person : personList)` and run
the debugger. If we look at the Java Visualizer, we can see how the `ArrayList`
is stored in memory. This should look the same from the last lesson.

![arraylist-in-memory](https://curriculum-content.s3.amazonaws.com/java-mod-3/list/java-visualizer-arraylist-personlist.png)

Notice how each element in the `ArrayList` points to a `Person` object. We can
also see this if we look at the debug console:

![arraylist-debugger](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-personlist.png)

Let's use the step-over action to see which `Person` object gets assigned to
the variable `person` first:

![first-iteration](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-basic-personlist-iteration-1.png)

As we can see, the `person` variable gets assigned to the first element in the
`personList` when we iterate through. Iterating through an `ArrayList` is
similar to iterating through an array - we will go through each element in the
list in the order they were inserted. Let's use the step-into action now to see
what happens when we call `person.getName()`:

![first-iteration-getname](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-getname-1.png)

We see that the execution jumps over to the `Person` class and calls the
`getName()` method, which will return "Lily Aldrin" in this case. If we
step-over, we'll be taken back to the `ListExample` class. When we step-over
again, we'll see that the name is printed to the console:

```text
Lily Aldrin
```

If we continue stepping through this code, we'll notice that the `person`
variable gets assigned to the `Person` object with "Marshall Eriksen" next:

![second-iteration](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-basic-personlist-iteration-2.png)

We can use the step-into action again to see the execution jump to the `Person`
class and call the `getName()` instance method. This will then return the
value of `name` before hopping back to the loop in the `ListExample` class,
just as we saw before.

When we step-over one more time, we'll see the `person` variable get assigned
to the last `Person` object in the `ArrayList`:

![third-iteration](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-basic-personlist-iteration-3.png)

If we resume the program, we'll see the following output in the console:

```text
Lily Aldrin
Marshall Eriksen
Robin Scherbatsky
```

## Adding a Subclass

Let's mix things up a bit! Go ahead and add the following method to the Person
class:

```java
    public String getCareer() {
        return "I'm just a person!";
    }
```

Now we know that people can have various careers. To keep things simple, let's
create a `Teacher` class that will inherit the `Person` class:

```java
public class Teacher extends Person {

    @Override
    public String getCareer() {
        return "I'm a teacher!";
    }
}
```

The `Teacher` will override the `getCareer()` method from the `Person` class. So
when we call the `getCareer()` method on an instance of a `Teacher` method, we
expect it to return the `String` "I'm a teacher!" instead of "I'm just a person"

## Iterating and Dynamic Binding

Modify the `ListExample` class to look like this:

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {

        Person person1 = new Person();
        person1.setName("Winston Bishop");
        person1.setAge(40);
        Teacher teacher1 = new Teacher();
        teacher1.setName("Jessica Day");
        teacher1.setAge(41);

        List<Person> personList = new ArrayList<>(Arrays.asList(
            person1,
            teacher1
        ));

        for (Person person : personList) {
            System.out.println(person.getCareer());
        }
    }
}
```

Let's iterate through a list again! But this time, we'll call our new
`getCareer()` method.

Also notice how we created a `Person` object and a `Teacher` object and then
added both to the `personList` this time. We'll set a breakpoint at the `for`
loop, like we did last time, to see what this looks like in memory:

![arraylist-in-memory](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/java-visualizer-arraylist-personteacherlist.png)

Since we have declared a `Person` object and a `Teacher` object and then added
them to the `List`, each element in the `List` will point to the respective
objects in the order that they were inserted. We can see this in the debugger as
well:

![arraylist-debugger](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-person-teacher-list.png)

Like before, we'll use the step-over action. When we do so, we expect the
`person` variable to be assigned to the first element in the `ArrayList`, which
is `person1`:

![first-iteration](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/visualizer-debugger-person1.png)

Now let's step-into the `getCareer()` method:

![first-iteration-getCareer](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-personlist-iteration-1.png)

We see that the execution jumps over to the `Person` class and calls the
`getCareer()` method, which will return "I'm just a person!" If we step-over,
we'll be taken back to the `ListExample` class. When we step-over again, we'll
see that the message is printed to the console:

```text
I'm just a person!
```

Let's move onto the second iteration. This time, the `person` variable will be
assigned to the second element in the `ArrayList`, which is `teacher1`:

![second-iteration](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/visualizer-debugger-teacher1.png)

We might be thinking... wait, `person` is of type `Person` and `teacher1` is of
type `Teacher`. And our `List` is expecting `Person` elements. Is this allowed?
Remember, the `Teacher` class **extends** the `Person` class since a `Teacher`
is a `Person` too. This is completely valid to have `Person person = teacher1;`

So what happens when we step-into the `getCareer()` method this time if `person`
is assigned to a `Teacher` object?

![second-iteration-getCareer](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-getCareer-teacher.png)

The execution jumps over to the `Teacher` class this time and calls the
`getCareer()` method. Java uses dynamic binding for the `person.getCareer()`
method call to figure out which implementation to execute. **Dynamic binding**
is a process that occurs at runtime and makes use of the overriding methods.

If we resume the program, we'll see that the final output of this program in
the console:

```text
I'm just a person!
I'm a teacher!
```

This is an example of how we can iterate through a collection utilizing dynamic
binding!

### Common Error

Consider the following code but do not modify the `ListExample` class at this
time.

```java
public class ListExample {
    public static void main(String[] args) {

        Person person1 = new Person();
        person1.setName("Winston Bishop");
        person1.setAge(40);
        Teacher teacher1 = new Teacher();
        teacher1.setName("Jessica Day");
        teacher1.setAge(41);

        // DO NOT MAKE THIS CHANGE
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(person1);
        teacherList.add(teacher1);

        for (Teacher teacher : teacherList) {
            System.out.println(teacher.getCareer());
        }
    }
}
```

We talked about before how a `Teacher` is a `Person` because it extends the
`Person` class. However, this does not mean that every `Person` instance is
also a `Teacher`. Therefore, if we have a `List` that is expecting _only_
`Teacher` objects, we **cannot** add a `Person` object to `teacherList`. If we
tried to run the code above, it would not compile due to this reason. Instead,
a compile-time error would be thrown:

```text
java: incompatible types: Person cannot be converted to Teacher
```

## Comprehension Check

Create a new class called `PoliceOfficer` that also inherits from the `Person`
class:

```java
public class PoliceOfficer extends Person {

    @Override
    public String getCareer() {
        return "I'm a police officer!";
    }
}
```

Modify the `ListExample` class to look like this:

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {

        Person person1 = new PoliceOfficer();
        person1.setName("Winston Bishop");
        person1.setAge(40);
        Person teacher1 = new Teacher();
        teacher1.setName("Jessica Day");
        teacher1.setAge(41);

        List<Person> personList = new ArrayList<>(Arrays.asList(
            person1,
            teacher1
        ));

        for (Person person : personList) {
            System.out.println(person.getCareer());
        }
    }
}
```

<details>
    <summary>What do you think the program will output now?</summary>

  <p>Answer: <br>
     <p><code>I'm a police officer!</code></p>
     <p style="margin-top: -18px"><code>I'm a teacher!</code></p>
  </p>

  <p>When we iterate through the loop the first time, we encounter <code>Person</code> as a <code>PoliceOfficer</code> instead of just a <code>Person</code></p>
  <p>This will then exemplify dynamic binding again as it will execute the <code>getCareer()</code> method in the <code>PoliceOfficer</code> class instead.</p>

</details>
