# Collections and Inheritance

## Learning Goals

- Show how to iterate through a collection with inherited subclasses.
- Discuss dynamic binding when iterating.

## Introduction

In this lesson, we will review iterating through a `List` with an inheritance
structure.

Fork and clone this lesson. You will see a `ListExample` class and a `Person`
class. Code along with us in this lesson to further your understanding of
collections and dynamic binding.

```java
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
```

```java
public class Person {
    
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCareer() {
        return "I'm just a person!";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

## Adding a Subclass

Now we know that people can have various careers. To keep things simple, let's
create a `Teacher` class that will inherit the `Person` class:

```java
public class Teacher extends Person {

    public Teacher(String name, int age) {
        super(name, age);
    }

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
        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("Winston Bishop", 40),
                new Teacher("Jessica Day", 41)
        ));

        for (Person person : personList) {
            System.out.println(person.getCareer());
        }
    }
}
```

Let's step through the iteration of this list. We'll set a breakpoint at the
`for` loop to see what this looks like in memory:

![arraylist-in-memory](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/java-visualizer-arraylist-personteacherlist.png)

We can look at what is in the `List` by opening up the debug console as well:

![arraylist-debugger](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-person-teacher-list.png)

We'll use the step-over action next to see what the `person` variable gets
assigned to. Since we are a little more familiar with lists, we expect the
`person` variable to be assigned to the first element in the `ArrayList`, which
is the `Person` with the name "Winston Bishop":

![first-iteration](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-personlist-iteration-2.png)

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
assigned to the second element in the `ArrayList`, which is the `Teacher` with
the name "Jessica Day":

![second-iteration](https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/debugger-arraylist-personlist-iteration-3.png)

Since a `Teacher` is a `Person` too because `Teacher` **extends** the `Person`
class, it is completely valid to have the `person` variable set to a `Teacher`
object!

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExample {
    
    public static void main(String[] args) {
        
        // DO NOT MAKE THIS CHANGE
        List<Teacher> teacherList = new ArrayList<>(Arrays.asList(
                new Person("Winston Bishop", 40),
                new Teacher("Jessica Day", 41)
        ));
        
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

    public PoliceOfficer(String name, int age) {
        super(name, age);
    }

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
        List<Person> personList = new ArrayList<>(Arrays.asList(
                new PoliceOfficer("Winston Bishop", 40),
                new Teacher("Jessica Day", 41)
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

  <p>When we first iterate through the loop, the <code>person</code> variable gets assigned to the first element in the list, which happens to be an instance of <code>PoliceOfficer</code></p>
  <img src="https://curriculum-content.s3.amazonaws.com/java-mod-3/collections-dynamic-binding/java-visualizer-police-officer.png" alt="first-iteration-police-officer">
  <p>Java will then use dynamic binding to implement the <code>getCareer()</code> method from the <code>PoliceOfficer</code> class since it overrides the method in the <code>Person</code> class.</p>

</details>
