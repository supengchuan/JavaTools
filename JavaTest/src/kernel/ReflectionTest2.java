package kernel;

import java.lang.reflect.Field;

public class ReflectionTest2 {
    public static void main(String[] args) {
        String name = Manager.class.getName();

        try {
            Class cl = Class.forName(name);
            Object o = cl.newInstance();
            Field[] fields1 = cl.getFields();
            for (Field field : fields1) {
                System.out.print(field + " ");
            }
            System.out.println();
            Field[] fields2 = cl.getDeclaredFields();
            for (Field field : fields2) {
                System.out.print(field + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
