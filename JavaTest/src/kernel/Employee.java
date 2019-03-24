package kernel;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee extends Person implements Cloneable {
    private double salary;
    private Date hireDay;

    public Employee(String n, double s, int year, int month, int day) {
        super(n);
        salary = s;
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        hireDay = calendar.getTime();
    }


    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();

        cloned.hireDay = (Date) hireDay.clone();

        return cloned;
    }


    public String getDescription() {
        return "a Employee with salary" + this.getSalary();
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
