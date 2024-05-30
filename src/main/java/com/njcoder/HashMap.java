package com.njcoder;

import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class HashMap<K, V> {
    private LinkedList<Pair<K, V>> [] buckets;
    private Integer bucketSize;
    private Integer noOfElements;
    private Class<K> keyType;

    public static final Integer lambdaRatio = 10;

    public HashMap(Class<K> keyType, Integer bucketSize) {
        this.bucketSize = bucketSize;
        this.keyType = keyType;
        this.buckets = new LinkedList[bucketSize];
        for(int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        this.noOfElements = 0;
    }

    public void rehashing(K key, V value) {
        System.out.println("************rehashing***********");
        //Increase the buckets size twice and take the elements from new buckets to old buckets through same algo
        this.bucketSize *= 2;
        LinkedList<Pair<K, V>> [] newBuckets = new LinkedList[bucketSize];
        for(int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = new LinkedList<>();
        }

        for(LinkedList<Pair<K, V>> bucket : buckets) {
            for(Pair<K, V> pair : bucket) {
                int index = Math.abs(key.hashCode()) % bucketSize;
                newBuckets[index].add(Pair.of(pair.getLeft(), pair.getRight()));
            }
        }
        int index = Math.abs(key.hashCode()) % bucketSize;
        newBuckets[index].add(Pair.of(key, value));

        buckets = newBuckets;
        noOfElements++;

    }

    public void put(K key, V value) {
        Integer lamdaCalculated = (noOfElements+1)/bucketSize;
        if(lamdaCalculated > lambdaRatio) {
            rehashing(key, value);
        }else {
            int index = Math.abs(key.hashCode()) % bucketSize;
            //first we need to check whether this key is present or not in bucket
            for(Pair<K, V> pair : buckets[index]) {
                if(pair.getKey().equals(key)) {
                    pair.setValue(value);
                    return;
                }
            }
            //if key is not present in bucket then we need to add it to the bucket
            buckets[index].add(Pair.of(key, value));
            noOfElements++;
        }

    }

    public K[] keySet() {
        @SuppressWarnings("unchecked")
        K[] keys = (K[]) Array.newInstance(keyType, noOfElements);
        int i = 0;
        for(LinkedList<Pair<K, V>> bucket : buckets) {
            for(Pair<K, V> pair : bucket) {
                keys[i] = pair.getKey();
                i++;
            }
        }
        return keys;
    }

    public Integer size() {
        return noOfElements;
    }

    public V get(K key) {
        int index = Math.abs(key.hashCode()) % bucketSize;
        for(Pair<K, V> pair : buckets[index]) {
            if(pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    public Boolean containsKey(K key) {
        return get(key)!= null;
    }

    public void remove(K key) {
        int index = Math.abs(key.hashCode()) % bucketSize;
        for(Pair<K, V> pair : buckets[index]) {
            if(pair.getKey().equals(key)) {
                buckets[index].remove(pair);
                noOfElements--;
                break;
            }
        }
    }

}
