package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;

public class AsIntStream implements IntStream {
    private ArrayList<Integer> lst;

    private AsIntStream(int... lst) {
        this.lst= new ArrayList<Integer>();
    }

    private AsIntStream(ArrayList<Integer> lst) {
        this.lst = lst;
    }

    public static IntStream of(int... values) {
        AsIntStream value = new AsIntStream();
        for (int i = 0; i < values.length; i++) {
            value.lst.add(values[i]);
        }
        return value;
    }

    @Override
    public Double average() {
        double aver;
        if (lst.isEmpty()) {
            throw new IllegalArgumentException();
        }
        else {
            aver = (double) sum()/count();
        }
        return aver;
    }

    @Override
    public Integer max() {
        if (lst.isEmpty()) {
            throw new IllegalArgumentException();
        }
        else {
            int max_v = Integer.MIN_VALUE;
            for (int i: lst){
                if (i > max_v) {
                    max_v = i;
                }
            }
            return max_v;
        }
    }

    @Override
    public Integer min() {
        if (lst.isEmpty()) {
            throw new IllegalArgumentException();
        }

        else {
            int min_v = Integer.MAX_VALUE;
            for (int i: lst) {
                if (i < min_v) {
                    min_v = i;
                }
            }
            return min_v;
        }
    }

    @Override
    public long count() {
        if (lst.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return lst.size();
    }

    @Override
    public Integer sum() {
        if (lst.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int sum_v = 0;
        for (int i : lst) {
            sum_v += i;
        }
        return sum_v;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        AsIntStream stream = new AsIntStream();
        for (int i : lst) {
            if (predicate.test(i)) {
                stream.lst.add(i);
            }
        }
        return stream;
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i : lst) {
            action.accept(i);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        AsIntStream stream = new AsIntStream();
        for (int i : lst) {
            stream.lst.add(mapper.apply(i));
        }
        return stream;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<IntStream> arr = new ArrayList<>();
        for (Integer i : lst) {
            arr.add(func.applyAsIntStream(i));
        }

        ArrayList<Integer> arr2 = new ArrayList<>();
        for (IntStream i : arr) {
            for (int j : i.toArray()) {
                arr2.add(j);
            }
        }
        return new AsIntStream(arr2);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int reduce_v = 0;
        for (int i : lst) {
            reduce_v += op.apply(identity, i);
        }
        return reduce_v;
    }

    @Override
    public int[] toArray() {
        int len = lst.size();
        int[] len2 = new int[len];
        int counter = 0;
        for (int i: lst) {
            len2[counter] = i;
            counter += 1;
        }
        return len2;
    }

}
