import java.util.Objects;
public class Human implements Comparable<Human>{
    String name;
    String surname;
    int age;
    public Human(String name, String surname, int age){
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override 
    public int compareTo(Human other){
        if (this.age < other.age){
            return this.age - other.age;
        }
        if (this.age > other.age){
            return this.age - other.age;
        }
        return this.age - other.age;
    }
    @Override
    public String toString(){
        return surname + " " + name + " (" + age + ") "; 
    }
    @Override 
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Human human = (Human) o;
        return age == human.age && Objects.equals(name, human.name) && Objects.equals(surname, human.surname);
    }

    @Override 
    public int hashCode(){
        return Objects.hash(name, surname, age);
    }
}
