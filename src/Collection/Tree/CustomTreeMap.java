package Collection.Tree;

import java.util.TreeMap;

class Student implements Comparable<Student>{
    private  int id;
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

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }
}
public class CustomTreeMap {
    public static void main(String[] args) {
        TreeMap<Student,String>treeMap=new TreeMap<>();
        treeMap.put(new Student(1,"dhruv"),"Developer");
        treeMap.put(new Student(2,"amit"),"Testing");
        treeMap.put(new Student(3,"raj"),"cloud");

        System.out.println(treeMap); // we will get ClassCastException we need to implement comparable interface


        // usally  it sort based on key but if we want based on value . after using comparable
        // Step 1: Get entries
    /*    List<Map.Entry<Employee, String>> entries = new ArrayList<>(map.entrySet());

        // Step 2: Sort by values
        entries.sort(Map.Entry.comparingByValue());

        // Step 3: Preserve order in LinkedHashMap
        Map<Employee, String> sortedByValue = new LinkedHashMap<>();
        for (Map.Entry<Employee, String> e : entries) {
            sortedByValue.put(e.getKey(), e.getValue());
        }

        System.out.println("Sorted by Value: " + sortedByValue);  */
    }
}
