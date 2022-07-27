package com.hef.guava.stringutilities;

import com.google.common.base.*;
import com.google.common.collect.Lists;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Date 2022/7/25
 * @Author lifei
 */
public class StringUtilitiesDemo {

    public static void main(String[] args) {
//        joinTest();
//        splitTest();
//        splitFactoriesTest();
//        splitModifiersTest();
//        charMatcherTest();
//        charsetsTest();
        caseFormatTest();
    }

    private static void caseFormatTest() {
        String s = "helloWorld";
        String to = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, s);
        System.out.println(to);
        boolean res01 = Strings.isNullOrEmpty(" ");
        System.out.println(res01);
        String res02 = Strings.lenientFormat("%svs%s", 23, 24);
        System.out.println(res02);
        String s1 = Strings.commonPrefix("abcd1235", "abc135");
        System.out.println(s1);
        String s2 = Strings.commonSuffix("abcd1235", "abc135");
        System.out.println(s2);
        String s3 = Strings.emptyToNull("");
        System.out.println(s3);
        String s4 = Strings.nullToEmpty(null);
        System.out.println(s4);
        String s5 = Strings.padStart("abcd", 8, '*');
        System.out.println(s5);
        String s6 = Strings.repeat("abcd", 2);
        System.out.println(s6);


    }

    private static void charsetsTest() {
        String s = "您好";
//        byte[] bytes = s.getBytes(Charsets.UTF_8);
        try {
            byte[] bytes = s.getBytes("UTF-8");
            System.out.println(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    private static void charMatcherTest() {
        String s = "java\u0001";
        System.out.println(s);
        String res = CharMatcher.javaIsoControl().removeFrom(s);
        System.out.println(res);
        // 仅保留数字
        String s2 = "aa12bb23";
//        String s1 = CharMatcher.digit().retainFrom(s2);
        String s1 = CharMatcher.inRange('0', '9').retainFrom(s2);
        System.out.println(s1);
        //
        String s3 = " \ta\tbc   f\t";
        System.out.println(s3);
        String s4 = CharMatcher.whitespace().trimAndCollapseFrom(s3, ' ');
        System.out.println(s4);
        String s5 = "12345s";
        System.out.println(s5);
        // 将字符串中所有的数字变成"*"
//        String s6 = CharMatcher.javaDigit().replaceFrom(s5, "*");
        String s6 = CharMatcher.inRange('0', '9').replaceFrom(s5, "*");
        System.out.println(s6);
        // 只保留小子字母和数字
        String s7 = "12Avs51B";
//        String s8 = CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom(s7);
        String s8 = CharMatcher.inRange('0', '9').or(CharMatcher.inRange('a', 'z')).retainFrom(s7);
        System.out.println(s8);
        String s9 = "a\t\nb";
        System.out.println(s9);
        String s10 = CharMatcher.breakingWhitespace().removeFrom(s9);
//        String s10 = CharMatcher.whitespace().removeFrom(s9);
        System.out.println(s10);
        String s11 = "aBcbEf";
        String s12 = CharMatcher.anyOf("abc").removeFrom(s11);
        String s13 = CharMatcher.is('a').removeFrom(s11);
        String s14 = CharMatcher.inRange('a', 'z').replaceFrom(s11, "-1");
        boolean b01 = CharMatcher.inRange('a', 'z').matchesAllOf(s11);
        System.out.println(b01);
        System.out.println(s12);
        System.out.println(s13);
        System.out.println(s14);
    }

    private static void splitModifiersTest() {
        String s = "a,b,,c,";
        Iterable<String> res01 = Splitter.on(",").omitEmptyStrings().split(s);
        System.out.println(res01);
        String s2 = "a, b,  c  ";
        Iterable<String> res02 = Splitter.on(", ").trimResults().split(s2);
        System.out.println(res02);
        String s3 = "a_-b__-c _";
        Iterable<String> res03 = Splitter.on("-").trimResults(CharMatcher.is('_')).split(s3);
        System.out.println(res03);
        Iterable<String> res04 = Splitter.on("-").limit(2).split(s3);
        List<String> res05 = Splitter.on("-").limit(2).splitToList(s3);
        System.out.println(res04);
        String m = "name:23;value:12;age:10";
        Map<String, String> map = Splitter.on(";").withKeyValueSeparator(":").split(m);
        System.out.println(map);

    }

    private static void splitFactoriesTest() {
        String s = "a-b-c-d-e--f-";
        Iterable<String> res01 = Splitter.on('-').split(s);
        System.out.println(res01);
        String s2 = "a b c d e\tf";
        Iterable<String> res02 = Splitter.on(CharMatcher.breakingWhitespace()).split(s2);
        System.out.println(res02);
        String s3 = "a,b;c";
        Iterable<String> res03 = Splitter.on(CharMatcher.anyOf(",;")).split(s3);
        System.out.println(res03);
        String s4 = "a, b, c";
        Iterable<String> res04 = Splitter.on(", ").split(s4);
        System.out.println(res04);
        String s5 = "a12b3c891d";
        Iterable<String> res05 = Splitter.on(Pattern.compile("\\d+")).split(s5);
        Iterable<String> res06 = Splitter.onPattern("\\d+").split(s5);
        System.out.println(res05);
        System.out.println(res06);
        Iterable<String> res07 = Splitter.fixedLength(2).split(s5);
        System.out.println(res07);

    }

    private static void splitTest() {
        String s = ",a,,b,";
        String[] res = s.split(",");
        System.out.println(Arrays.toString(res));
        Iterable<String> res02 = Splitter.on(",").trimResults().omitEmptyStrings().split(s);
        System.out.println(res02);
    }

    private static void joinTest() {
        Joiner joiner = Joiner.on(", ").skipNulls();
        String result01 = joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(result01);
        // 如果join的第三个参数时null，将会报错
//        String result02 = Joiner.on(", ").useForNull("-1").join("aa", "bb", null);
        String result02 = Joiner.on(", ").useForNull("-1").join(Lists.newArrayList("aa", "bb", null));
        System.out.println(result02);
    }
}

