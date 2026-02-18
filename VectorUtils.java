import java.util.Vector;
interface Predicate<T>{
    boolean test(T t);
}
public class VectorUtils <T>{
    public static <T> void swap(Vector<T> vec, int index1, int index2){
        T temp = vec.get(index1);
        vec.set(index1, vec.get(index2));
        vec.set(index2, temp);
    }
    public static <T extends Comparable<T>> T findMax(Vector<T> vec){
        T max = vec.get(0);
        if(vec.isEmpty()) return null;
        for(T elem : vec){
            if(elem.compareTo(max) > 0) max = elem;
        }
        return max;
    }
    public static <T> int countMatches(Vector<T> vec, T target){
        int count = 0;
        for(T elem : vec){
            if(elem.equals(target)) count++;
        }
        return count;
    }
    public static <T> Vector<T> filter(Vector<T> vec, Predicate<T> condition){
        Vector<T> result = new Vector<T>();
        for(T elem : vec){
            if(condition.test(elem)) result.add(elem);
        }
        return result;
    }

    public static <T extends Number> double sumNumbers(Vector<T> numbers){
        double sum = 0;
        for(T elem : numbers){
            sum += elem.doubleValue();
        }
        return sum;
    }
    public static <T extends Number> double averageNumbers(Vector<T> numbers){
        if(numbers.isEmpty()) return 0;
        return sumNumbers(numbers)/numbers.size();
    }

}
