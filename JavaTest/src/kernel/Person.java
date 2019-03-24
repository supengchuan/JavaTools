package kernel;

public abstract class Person {
    public abstract String getDescription();
    public abstract double getSalary();
    public Person(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
    public void  setName(String n) {
        name = n;
    }
    private String name;
}
