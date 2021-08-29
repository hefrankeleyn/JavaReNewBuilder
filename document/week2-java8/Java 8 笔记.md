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

### 3.5 收集器接口

#### (1) `Collector` 接口

`Collector<T,A,R>` : T 流的类型，A 为累加器类型，R为收集操作得到的类型；

- `supplier()`: 建立新的结果容器; 建立空的累加器实例，供数据收集过程中使用；
- `accumulator()` ： 累加器
- `finisher()`： 将累加器对象转换成为整个集合操作的最终结果；
- `combiner`: 合并两个结果容器
- `characterististics`: 返回一个不可变得Characterististics集合，定义了收集器的行为
  - UNORDERED 归约结果不受流中项目的遍历和累积顺序的影响；
  - CONCURRENT 并行归约流，它仅在用于无序数据源时才可以并行归约；
  - IDENTIFY_FINISH  表明直接将累加器的结果作为最终结果

第一个示例：[ToListCollector](https://github.com/hefrankeleyn/JavaReNewBuilder/blob/master/projects/pro02Java8/src/main/java/com/hef/stream/ToListCollector.java)

第二个案例：[获取质数](https://github.com/hefrankeleyn/JavaReNewBuilder/blob/master/projects/pro02Java8/src/main/java/com/hef/stream/MyPrimCollector.java) ， 及对比 [对比两种获取质数的方法](https://github.com/hefrankeleyn/JavaReNewBuilder/blob/master/projects/pro02Java8/src/main/java/com/hef/stream/PrimMain.java)

#### (2) 使用stream.collect(supplier, accumulator, combiner)  的重载方法

```
ArrayList<String> res = list.stream().collect(ArrayList::new, List::add, List::addAll);
```

### 3.6 并行数据处理与性能高

#### （1）并行流与串行流的转化

`parallel` ， 将串行流转化成并行流；

`sequential` ，将并行流转化成并行流；

parallel和sequential可以结合使用，在内部实际上是一个boolean标识，最后一次的parallel或sequential调用会影响整个流水线。

#### （2）默认并行流线程池：`ForkJoinPool`

- 默认线程数的数量就是处理器数量：`Runtime.getRuntime().availableProcessors()`

  availableProcessors 看起来是处理器，实际上返回的是可用内核的数量，包括超线程生成的虚拟内核。

- 可以通过系统属性,进行全局设置线程数：(没有好的理由，强烈不建议修改)

  ```
  System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5");
  ```

#### （3） 并行流的反例

##### 反例一：使用不易并行化的操作导致，性能变差

在下面的案例中，在实测中发现，此处的并行流比串行流慢很多。如果采用不易并行化的操作，可能会让程序的整体性能变得更差。

```
    // 串行流
    public long sequentialSum2(long n) {
        return LongStream.iterate(1l, i->i+1l).limit(n).reduce(0l, Long::sum);
    }
    // 并行流
    public long parallelSum(long n) {
        return LongStream.iterate(1l, i->i+1l).limit(n)
                .parallel()
                .reduce(0l, Long::sum);
    }
```

将上面案例改为使用 range , 将得到并行的效率高于串行：

```
    // 串行
    public long sequentialSum3(long n) {
        return LongStream.rangeClosed(1l, n).limit(n).reduce(0l, Long::sum);
    }
    
    //  并行
    public long parallelSum3(long n) {
        return LongStream.rangeClosed(1l, n).limit(n)
                .parallel()
                .reduce(0l, Long::sum);
    }
```

##### 反例二：使用共享变量导致结果错误

```
    class Accumulator {
        public long total = 0;
        public void add(long value) {
            total += value;
        }
    }
    
    public long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1l, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }
```

#### （4）何时使用并行流

- 留意装箱；
- 需要流中的n个元素，而不是前n个；
- 估算：N个元素，处理每个元素的成本为Q，Q越大，并行处理的性能好的可能行更大；
- 对于较小的数据量，不建议使用并行流；
- 考虑数据结构是否更容易分解；
- 考虑并行流处理的合并时的成本；

可分解性：

| 数据结构        | 可分解性 |
| --------------- | -------- |
| ArrayList       | 极好     |
| LinkedList      | 极差     |
| IntStream.range | 极好     |
| Stream.iterator | 极好     |
| HashSet         | 好       |
| TreeSet         | 好       |

### 3.7 分支/合并框架

它是ExecutorService 接口的实现。把子任务分配给线程池（ForkJoinPool）。

**分治算法的并行版本。**

#### （1）创建`RecursiveTask<R> ` 或`RecursiveAction`的子类

只需要实现其唯一的抽象方法`compute`：

- 定义将任务拆分为子任务的逻辑；
- 以及无法再拆分时，生成单个子任务结果的逻辑；

```
if (任务足够小或不可分){
   顺序计算该任务
}else {
   将任务分成两个子任务
   递归调用本方法，拆分每个子任务，等待所有子任务完成
   合并每个子任务的结果
}
```

#### （2）调用`new ForkJoinPool().invoke()`

在实际应用中，使用多个ForkJoinPool是没有意义的。一般来说，将其实例化一次，然后把实例保存在静态字段中，使之成为单例。

一个ForkJoinPool的案例：[MyRecursiveSumTask](https://gitee.com/lf-ren/java-re-new-builder/blob/master/projects/pro02Java8/src/main/java/com/hef/stream/MyRecursiveSumTask.java)

#### （3）使用分支合并框架的注意事项

- `join()`方法会阻塞调用方，所以有必要在两个子任务的就算都开始之后再调用它；

- 不应该在`RecursiveTask`内部调用`ForkJoinPool()`的`invoke`方法，应该始终调用 `fork()`或`compute（)`方法

- 任务调用`fork()`方法可以把它排进`ForkJoinPool()`， 左右两个任务同时调用`fork()`的效率 比 让其中一个任务调用`compute()` 的效率低，这样做可以为其中一个子任务重用同一线程，避免在线程池多分配一个任务造成开销；

- 分支/合并框架需要“预热”或者说要执行几遍才会被JIT编译器优化；

- 划分成许多小任务而不是大任务，更有助于工作线程的负载平衡；

  这其中使用了“工作窃取算法”：每个线程都有双端队列（保存分配给该线程的任务），某一个线程任务队列空了，会去其他任意一个线程任务队列中的末尾取一个任务。

### 3.8 Spliterator 可分迭代器

案例，创建一个切分字符串的Spliterator：[MyWordCountSpliterator](https://gitee.com/lf-ren/java-re-new-builder/blob/master/projects/pro02Java8/src/main/java/com/hef/stream/MyWordCountSpliterator.java)



## 其他、Optional和OptionalInt

- `OPtional` 对象流返回值
- `OptionalInt` 数值流返回值



