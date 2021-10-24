package com.hef.recursion;


import java.util.*;
/**
 * @Date 2021/10/23
 * @Author lifei
 */
public class FindLadders {

    public static void main(String[] args) {
        FindLadders findLadders = new FindLadders();
        String beginWord = "hit", endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        List<List<String>> res = findLadders.findLadders(beginWord, endWord, Arrays.asList(wordList));
        System.out.println(res);
//        Deque<Integer> deque = new LinkedList<>();
//        Map<String, Integer> map = new HashMap<>();
//        List<Integer> list = new ArrayList<>(deque);
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> resultList = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return resultList;
        wordSet.remove(beginWord);
        Deque<String> deque = new LinkedList<>();
        deque.offer(beginWord);
        Map<String, Integer> steps = new HashMap<>();
        steps.put(beginWord, 0);
        Map<String, List<String>> from = new HashMap<>();
        int step = 1;
        boolean flag = false;
        while(!deque.isEmpty()) {
            int n = deque.size();
            for (int i=0; i<n; i++) {
                String current = deque.pop();
                char[] ca = current.toCharArray();
                for (int k=0; k<ca.length; k++) {
                    char old = ca[k];
                    for (char c='a'; c<='z'; c++) {
                        if (c==old) continue;
                        ca[k] = c;
                        String next = String.valueOf(ca);
                        if (steps.containsKey(next) && step == steps.get(next)) {
                            from.get(next).add(current);
                        }
                        if (!wordSet.contains(next)) continue;
                        wordSet.remove(next);
                        deque.offer(next);
                        steps.put(next, step);
                        from.putIfAbsent(next, new ArrayList<>());
                        from.get(next).add(current);

                        if (next.equals(endWord)) {
                            flag=true;
                        }
                    }
                    ca[k] = old;
                }
            }
            step++;
            if (flag) {
                break;
            }
        }

        if (flag) {
            Deque<String> resultDeque = new LinkedList<>();
            resultDeque.push(endWord);
            dfs(resultList, beginWord, endWord, from, resultDeque);
        }

        return resultList;
    }

    private void dfs(List<List<String>> resultList, String beginWord, String curr, Map<String, List<String>> from, Deque<String> deque) {
        if (curr.equals(beginWord)) {
            resultList.add(new ArrayList<>(deque));
            return;
        }
        for (String pre: from.get(curr)) {
            deque.push(pre);
            dfs(resultList, beginWord, pre, from, deque);
            deque.removeFirst();
        }


    }


}
