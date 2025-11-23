package Collection.Compartor_And_Comparble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Person{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
public class SortByIdThenName {
    public static void main(String[] args) {
        List<Person>ListOfPerson=new ArrayList<>();
        ListOfPerson.add(new Person(2,"dhruv"));
        ListOfPerson.add(new Person(1,"sachin"));
        ListOfPerson.add(new Person(4,"raj"));
        ListOfPerson.add(new Person(3,"ankit"));
        ListOfPerson.add(new Person(2,"amit"));
        ListOfPerson.add(new Person(4,"chandan"));
        ListOfPerson.add(new Person(1,"mayur"));
        ListOfPerson.add(new Person(3,"atul"));

        Collections.sort(ListOfPerson, new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                 int IdSort=Integer.compare(o1.getId(),o2.getId());
                 if(IdSort!=0){
                     return IdSort;
                 }
                 return o1.getName().compareTo(o2.getName());
            }
        });
        for(Person p:ListOfPerson){
            System.out.println(p);
        }
    }
}
