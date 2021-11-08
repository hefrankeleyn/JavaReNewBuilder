package com.hef.demo;

import java.util.*;
/**
 * @Date 2021/11/8
 * @Author lifei
 */
public class FindTopKQuery {

    public static void main(String[] args) {
        int[][] orders = {
                {2004, 200, 200},
                {2005, 300, 300}
        };
        int k = 5;
        FindTopKQuery findTopKQuery = new FindTopKQuery();
        int[] res = findTopKQuery.findTopKQuery(orders, k);
        System.out.println(Arrays.toString(res));
    }

    public int[] findTopKQuery(int[][] orders, int k) {
        int m = orders.length;
        Map<Integer, List<Integer>> tMap = new HashMap<>();
        Map<Integer, Integer> idMap = new HashMap<>();
        for (int i=0; i<m; i++) {
            int id = orders[i][0];
            int t = orders[i][1];
            tMap.putIfAbsent(t, new ArrayList<>());
            tMap.get(t).add(id);
            idMap.put(id, orders[i][2]);
        }
        List<Integer> result = new ArrayList<>();
        for (int diff=0;diff<k;diff++) {
            int minT = Integer.MAX_VALUE;
            for (Map.Entry<Integer, List<Integer>> entry: tMap.entrySet()) {
                minT = Math.min(minT, entry.getKey());
            }
            List<Integer> list = tMap.get(minT);
            Collections.sort(list, Integer::compareTo);
            result.addAll(list);
            tMap.remove(minT);
            for (Integer id: list) {
                int temT = minT + idMap.get(id);
                tMap.putIfAbsent(temT, new ArrayList<>());
                tMap.get(temT).add(id);
            }
        }
        int[] a = new int[k];
        for (int j=0; j<k; j++) {
            a[j]=result.get(j);
        }
        return a;
    }
}
