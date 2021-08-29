package com.hef.stream;

/**
 * @Date 2021/8/29
 * @Author lifei
 */
public class SpliteratorMain {

    public static void main(String[] args) {
        String str = "i  have a dream";
        int res = wordCount(str);
        System.out.println(res);
    }

    /**
     * 获取单词的数量
     * @param str
     * @return
     */
    private static int wordCount(String str) {
        int count = 0;
        boolean flag = true;
        for (char c : str.toCharArray()) {
//            if (str.charAt(i)==' ') {
            if (Character.isWhitespace(c)) {
                flag=true;
            }else {
                if (flag) count++;
                flag=false;
            }
        }
        return count;
    }
}
