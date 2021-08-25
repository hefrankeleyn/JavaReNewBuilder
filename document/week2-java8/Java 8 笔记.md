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

### 2.3 查找和匹配

匹配

- `anyMatch()` 检查是否至少匹配一个元素，返回boolean
- `allMatch()` 检查是否匹配所有元素，返回boolean
- `noneMath()` 流中没有任何元素与给定的判断匹配

查找

- `findAny()` 返回任意一个
- `findFirst()` 查找第一个元素

### 2.4 归约 reduce

求和：

```
List<Integer> a = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 8));
Integer res = a.stream().reduce(0, (i, j) -> i + j);
Integer res1 = a.stream().reduce(0, Integer::sum);
Optional<Integer> res2 = a.stream().reduce(Integer::sum);
System.out.println(res2.orElse(0));
```

最大值和最小值：

```
Optional<Integer> maxOptional = a.stream().reduce(Integer::max);
System.out.println(maxOptional.orElse(0));
Optional<Integer> minOptional = a.stream().reduce(Integer::min);
System.out.println(minOptional.orElse(0));
```

使用流中内置的方法统计个数：

```
long count = a.stream().count();
```

### 2.5 数值流

- 原始类型流特化：避免装箱成本。IntStream、DoubleStream、LongStream。
- 映射为数值流： mapToInt() ，返回一个IntStream
- 转回对象流：intStream.boxed()
- 数值范围：通过数值流， intStream.range() 和 intStream.rangeClosed()

### 2.6 创建流

- 由值创建流

  ```
  Stream<Integer> stream = Stream.of(2, 3, 4, 5, 6);
  ```

- 由数组创建流

  ```
  Stream<Integer> stream1 = Arrays.stream(new Integer[]{3, 5, 6, 7});
  ```

- 由文件生成流

  ```
  // 由文件生成流
          String filePath ="pom.xml";
          try (Stream<String> fileStream = Files.lines(Paths.get(filePath), Charset.defaultCharset())){
              long count = fileStream.flatMap(line -> Arrays.stream(line.split(" "))).count();
              System.out.println(count);
          } catch (IOException e) {
              e.printStackTrace();
          }
  ```

- 由函数生成流：创建无限流

  - `Stream.iterate()`
  - `Stream.generate()`

  ```
  // 打印前十个偶数
  Stream.iterate(0, n->n+2).limit(10).forEach(System.out::println);
  // 斐波那契数列
  Stream.iterate(new Integer[]{0, 1}, a->new Integer[]{a[1], a[0]+a[1]})
                  .limit(10).map(a->a[0]).forEach(k-> System.out.print(k+ " "));
  ```

  generate 生成

  ```
  Stream.generate(Math::random).limit(5).forEach(System.out::println);
  // generate 产生斐波那契序列
  IntStream.generate(new IntSupplier() {
              private int pre = 0;
              private int cur = 1;
              @Override
              public int getAsInt() {
                  int oldPre = this.pre;
                  int nexVal = this.pre + this.cur;
                  this.pre = this.cur;
                  this.cur = nexVal;
                  return oldPre;
              }
          }).limit(10).forEach(k-> System.out.print(k+ " "));
  ```


## 三、流的收集器

### 3.1 `collect`、`Collector`、`Collectors` 的区别

-  `collect()` 触发归约操作
- `Collector` 是接口。其方法的实现决定了如何对流进行归约
- `Collectors` 提供了很多静态方法，可以很方便的创建常见的收集器；

### 3.2 归约和汇总

- `Collectors.counting()` 统计数量

  ```
  Long count = list.stream().collect(Collectors.counting());
  long count1 = list.stream().count();
  ```

- `Collectors.maxBy()`和`Collectors.minBy()`  最大值和最小值

  ```
  Optional<String> maxOpt = list.stream().collect(Collectors.maxBy(String::compareTo));
  Optional<String> minOpt = list.stream().collect(Collectors.minBy(String::compareTo));
  ```

- 汇总，求和、求平均

  - `Collectors.summingInt()`
  - `Collectors.summingDouble()`
  - `Collectors.summingLong()`

  求平均

  - `Collectors.averagingInt()`
  - `Collectors.averagingDouble()`
  - `Collectors.averagingLong()`

  ```
  Integer sum = Arrays.stream(a).collect(Collectors.summingInt(Integer::intValue));
  Double avg = Arrays.stream(a).collect(Collectors.averagingInt(Integer::intValue));
  ```

- 同时获得：最大值、最小值、平均值、和

  - `Collectors.summarizingInt()`
  - `Collectors.summarizingDouble()`
  - `Collectors.summarizingLong()`

  ```
  IntSummaryStatistics summaryStatistics = Arrays.stream(a).collect(Collectors.summarizingInt(Integer::intValue));
  ```

- 连接字符：`Collectors.joining()`

  ```
  String res = list.stream().collect(Collectors.joining());
  String res2 = list.stream().collect(Collectors.joining(","));
  ```

- 广义的归约汇总： `Collectors.reducing()`

### 3.3 分组

- `Collectors.groupingBy()`

  ```
  Map<Integer, List<Integer>> collect = Arrays.asList(a).stream().collect(Collectors.groupingBy(Integer::intValue));
  Map<String, List<Integer>> collect2 = Arrays.asList(a).stream().collect(Collectors.groupingBy(item -> {
              if (item > 3) {
                  return "aa";
              } else {
                  return "bb";
              }
          }));
  ```

- `Collectors.groupingBy(Dish::getName(), Collectors.groupingBy(Dish::getType()))`

- 传递给第一个groupingBy() 的第二个收集器可以是任何类型，而不一定是groupingBy()

  - `Collectors.groupingBy(Dish::getName(), Collectors.counting())`

  ```
  Map<String, Long> collect1 = Arrays.asList(a).stream().collect(Collectors.groupingBy(item -> {
              if (item > 3) {
                  return "aa";
              } else {
                  return "bb";
              }
          }, Collectors.counting()));
  ```

- `collectingAndThen`将收集器的结果转换成另外一种结果

  ```
  Map<String, Dish> map1 = dishList.stream().collect(Collectors.groupingBy(Dish::getType,
                  Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getName)),
                          Optional::get)));
  ```

### 3.4 分区

- `Collectors.partitioningBy()` 分组的特殊情况，将数据分为 true和false两组。

```
    // 判断一个数值是否为质数
    private static boolean isPrime(int n) {
        return IntStream.range(2, n).noneMatch(v-> n % v==0);
    }

    // 判断一个数值是否为质数   优化
    private static boolean isPrime2(int n) {
        int sqrt = (int)Math.sqrt(n);
        return IntStream.rangeClosed(2, sqrt).noneMatch(v-> n % v==0);
    }
```



## 其他、Optional和OptionalInt

- `OPtional` 对象流返回值
- `OptionalInt` 数值流返回值



