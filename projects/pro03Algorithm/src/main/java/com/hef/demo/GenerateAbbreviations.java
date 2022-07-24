package com.hef.demo;
import java.util.*;
/**
 * @Date 2021/11/22
 * @Author lifei
 */
public class GenerateAbbreviations {

    public static void main(String[] args) {
        /*GenerateAbbreviations generateAbbreviations = new GenerateAbbreviations();
        String word = "like";
        String[] result = generateAbbreviations.generateAbbreviations(word);
        System.out.println(Arrays.toString(result));*/
        String[] a1 = {"3","zoo","zo1","z2","z1o","2o","1oo","1o1"};
        String[] a2 = {"3","2o","1o1","1oo","z2","z1o","zo1","zoo"};
        Arrays.sort(a1);
        Arrays.sort(a2);
        System.out.println(Arrays.equals(a1, a2));
    }

    public String[] generateAbbreviations(String word) {
        List<String> resultList = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (int k=word.length();k>=0;k--) {
            bfs(k, 0, new StringBuilder(), word, resultList, visited);
        }
        String[] result = new String[resultList.size()];
        resultList.toArray(result);
        return result;
    }

    private void bfs(int k, int i, StringBuilder sb, String word,
                     List<String> resultList, Set<String> visited) {
        if (i>=word.length()) {
            resultList.add(sb.toString());
            return;
        }
        if (k>0 && (sb.length()==0 || !(sb.charAt(sb.length()-1)>='1' && sb.charAt(sb.length()-1)<='9'))){
            if (!visited.contains(sb.toString()+k)) {
                sb.append(k);
                visited.add(sb.toString());
                for (int j=word.length()-i-k; j>=0; j--) {
                    bfs(j, i+k, sb, word, resultList, visited);
                }
                sb.delete(sb.length()-(k+"").length(), sb.length());
            }
        }
        if (!visited.contains(sb.toString()+word.charAt(i))) {
            sb.append(word.charAt(i));
            visited.add(sb.toString());
            for (int j=word.length()-i; j>0; j--) {
                bfs(j, i+1, sb, word, resultList, visited);
            }
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
