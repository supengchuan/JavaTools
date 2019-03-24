package kernel;

public class MyPair<T> {
    private T fisrt;
    private T second;

    public MyPair() {
        fisrt = null;
        second = null;
    }

    public MyPair(T fisrt, T second) {
        this.fisrt = fisrt;
        this.second = second;
    }

    public T getFisrt() {
        return fisrt;
    }

    public void setFisrt(T fisrt) {
        this.fisrt = fisrt;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public static void main(String[] args) {
        String[] words = {"Mary", "had", "little", "lamb"};
        MyPair<String> mm = Arraylg.minmax(words);
        System.out.println("min = " + mm.getFisrt() + "\nmax = " + mm.getSecond());
    }
}


class Arraylg {
    public static MyPair<String> minmax(String[] a) {
        if (a == null || a.length == 0) return null;

        String min = a[0];
        String max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }

        return new MyPair<String>(min, max);
    }
}