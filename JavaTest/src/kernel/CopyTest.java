package kernel;

public class CopyTest {

    public static void main(String[] args) {
        Employee orginal = new Employee("john", 5000, 1999, 2, 3);
        try {
            Employee copy = orginal.clone();

            copy.raiseSalary(80);

            System.out.println(orginal.getSalary());

            copy.setName("supc");

            System.out.println(orginal.getName());
            System.out.println(copy.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
