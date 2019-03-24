package kernel;

import java.util.Arrays;

public class classSortTest {
    public static void main(String[] args) {
        classSort[] staff = new classSort[3];

        staff[0] = new classSort("spc", 10000);
        staff[1] = new classSort("yw", 9000);
        staff[2] = new classSort("hh", 4000);

        Arrays.sort(staff);

        for (classSort cs : staff) {
            System.out.println("name = " + cs.getName() + "salary = " + cs.getSalary());
        }
    }

}

class classSort implements Comparable<classSort> {
    private String name;
    private double salary;

    public classSort(String n, double s) {
        name = n;
        salary = s;
    }

    public void raiseSalary(double byPecent) {
        double raise = salary * byPecent / 100;
        salary += raise;
    }

    public int compareTo(classSort other) {
        if (salary < other.salary) return -1;
        if (salary > other.salary) return 1;
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
