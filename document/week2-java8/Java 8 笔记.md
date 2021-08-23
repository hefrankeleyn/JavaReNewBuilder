# Java 8 笔记

[toc]

## 一、流

- 流像一个延迟创建的集合
- 流只能消费一次
- 流利用了内部迭代

## 二、流的操作

流的操作分为：中间操作、终端操作。

流的延迟特性：中间操作可以连成一个操作流水线，除非发出一个终端操作，中间操作不会执行任何处理。

利用延迟特性：短路、循环合并。

### 2.1 筛选、去重、截断、跳过

- `filter()`
- `distinct()` 根据 hashCode() 和 equals() 去除
- `limit()` 截断
- `skip()` ，跳过前面n个元素，和limit() 截然相反

### 2.2 映射：map、flatMap

- map 映射： 根据一个元素，创建出一个新的元素
- flatMap 扁平化：把一个流中的每一个值都换成另一个流，然后把所有流连接起来成为一个流。

```
String[] words = {"hello", "world"};
// [h, e, l, l, o, w, o, r, l, d]
List<String> charList = Arrays.stream(words).map(item -> item.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
```

题目：

```
给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应

该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代

表数对。
```

```
Integer[][] aa = {{1, 2, 3},{3, 4}};
        List<Integer[]> pairs = Arrays.stream(aa[0])
                .flatMap(i -> Arrays.stream(aa[1])
                        .map(j -> new Integer[]{i, j})
                ).collect(Collectors.toList());
```



