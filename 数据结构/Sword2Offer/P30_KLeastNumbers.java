package Sword2Offer;

import java.util.*;

/**
 * 最小的k个数
 */
public class P30_KLeastNumbers {
    //快排O(nlgn)
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(input == null || input.length == 0 || input.length < k) return result;

        Arrays.sort(input);

        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }

        return result;
    }

    //优先队列 O(nlgk) O(k)
    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(input == null || input.length == 0 || input.length < k) return result;
        if(k == 0) return result;

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (int i = 0; i < input.length; i++) {
            if(pq.size() < k){
                pq.add(input[i]);
            }else {
                if(pq.peek() > input[i]){
                    pq.poll();
                    pq.add(input[i]);
                }
            }
        }
        result.addAll(pq);
        Collections.sort(result);
        return result;
    }

    //快排切分 O(n)
    public ArrayList<Integer> GetLeastNumbers_Solution3(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(input == null || input.length == 0 || input.length < k) return result;
        if(k == 0) return result;

        int start = 0, end = input.length-1;
        int index = partition(input, start, end);
        while(index != k-1){
            if(index > k-1){
                end = index-1;
                index = partition(input, start, end);
            }else {
                start = index+1;
                index = partition(input, start, end);
            }
        }
        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }

    //快排切分,返回切分位置
    public int partition(int[] input, int lo, int hi){
        if(lo == hi) return lo;

        int i = lo, j = hi + 1;

        int v = input[lo];

        while(true){
            while(input[++i]<v)
                if(i == hi) break;
            while(input[--j]>v)
                if(j == lo) break;
            if(i >= j){
                break;
            }
            swap(input, i, j);
        }
        swap(input, lo, j);
        return j;
    }

    public void swap(int[] input, int i, int j){
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
