/**
 * Write a description of class ArrayListDemo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ArrayListDemo
{
    public static void main(String[] args) {
        // 创建一个新的 ArrayList
        ArrayList<String> list = new ArrayList<>();
        
        // 使用 add() 方法向 list 中添加元素
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("After add() : " + list);
        
        // 使用 addAll() 方法向 list 中添加一个集合的所有元素
        List<String> additionalList = Arrays.asList("Date", "Elderberry");
        list.addAll(additionalList);
        System.out.println("After addAll(): " + list);
        
        // 使用 get() 方法获取指定索引的元素
        String item = list.get(1);
        System.out.println("get(1): " + item);
        
        list.set(1, "Blueberry");
        System.out.println("After set(1, Blueberry)" + list);
        
        // 使用 size() 方法获取 list 中的元素数量
        int size = list.size();
        System.out.println("size(): " + size);
        
        // 使用 contains() 方法检查 list 中是否存在某个元素
        boolean hasApple = list.contains("Apple");
        System.out.println("contains('Apple'): " + hasApple);
        
        // 使用 containsAll() 方法检查 list 中是否包含另一个集合的所有元素
        boolean containsAll = list.containsAll(additionalList);
        System.out.println("containsAll(additionalList) " + containsAll);
        
        // 使用 remove() 方法移除指定元素
        list.remove("Apple");
        System.out.println("After remove('Apple'): " + list);
        
        // 使用 removeAll() 方法移除包含在另一个集合中的所有元素
        list.removeAll(additionalList);
        System.out.println("After removeAll (additionalList): " + list);
        
        // retainAll() 方法保留包含在另一个集合中的所有元素，移除其他元素
        list.retainAll(Arrays.asList("Cherry"));
        System.out.println("After retainAll (Arrays.asList('Cherry')): " + list);
        
        // 使用 toArray() 方法将一个 list 转换为一个数组
        Object[] array = list.toArray();
        System.out.println("toArray(): " + Arrays.toString(array));
        
        // 使用 clear() 方法清空 list 中的元素
        list.clear();
        System.out.println("After clear(): " + list);
        
        // 使用 isEmpty() 方法检查 list 是否为空
        boolean isEmpty = list.isEmpty();
        System.out.println("isEmpty(): " + isEmpty);
    }
}
