package com.njcoder;
import com.njcoder.HashMap;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>(String.class, 5);
        hashMap.put("India", 10);
        hashMap.put("Nagaland", 8);
        hashMap.put("Russia", 15);
        hashMap.put("Usa", 90);
        hashMap.put("Australia", 8);
        hashMap.put("Russi", 15);
        hashMap.put("Us", 90);
        hashMap.put("Autralia", 8);
        hashMap.put("Rsia", 15);
        hashMap.put("a", 90);
        hashMap.put("stralia", 8);
        hashMap.put("dda", 15);
        hashMap.put("dddda", 90);
        hashMap.put("oaoosoralia", 8);

        for(String key : hashMap.keySet())
        {
            System.out.println(key);
        }

//        System.out.println("Imphad".hashCode());

        System.out.println(hashMap.size());
        System.out.println(hashMap.get("Australia1"));
        System.out.println(hashMap.containsKey("Shola"));
        System.out.println(hashMap.containsKey("Australia"));


    }
}