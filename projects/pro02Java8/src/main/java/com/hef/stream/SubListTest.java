package com.hef.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Date 2021/9/5
 * @Author lifei
 */
public class SubListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,4,9));
        List<List<Integer>> res = findAllSubList(list);
        System.out.println(res);
    }

    public static List<List<Integer>> findAllSubList(List<Integer> list) {
        if (list.size()==0) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(Collections.emptyList());
            return res;
        }
        Integer first = list.get(0);
        List<Integer> subList = list.subList(1, list.size());
        List<List<Integer>> allSubList = findAllSubList(subList);
        List<List<Integer>> allSubList2 = insertAll(first, allSubList);
        return concat(allSubList, allSubList2);
    }

    private static List<List<Integer>> concat(List<List<Integer>> allSubList, List<List<Integer>> allSubList2) {
        List<List<Integer>> res = new ArrayList<>();
        res.addAll(allSubList);
        res.addAll(allSubList2);
        return res;
    }

    private static List<List<Integer>> insertAll(Integer item, List<List<Integer>> allSubList) {
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> a : allSubList) {
            List<Integer> oneList = new ArrayList<>();
            oneList.addAll(a);
            oneList.add(item);
            res.add(oneList);
        }
        return res;
    }
}
