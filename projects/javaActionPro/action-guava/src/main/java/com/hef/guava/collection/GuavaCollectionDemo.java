package com.hef.guava.collection;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.hef.guava.beans.TypeThatsTooLongForItsOwnGood;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.annotation.CheckForNull;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

/**
 * @Date 2022/7/18
 * @Author lifei
 */
public class GuavaCollectionDemo {

    public static void main(String[] args) {
//        immutableTest();
//        multisetDemo();
        ListMultimap<String, Integer> multimap = MultimapBuilder.hashKeys().arrayListValues().build();
//        ArrayListMultimap<@Nullable Object, @Nullable Object> multimap02 = ArrayListMultimap.create();
        List<Integer> list = multimap.get("name");
        list.add(1);
        multimap.put("age", 23);
        System.out.println(multimap.get("name"));
        System.out.println(multimap.containsKey("name"));
        Multiset<String> keys = multimap.keys();
        System.out.println(keys.count("name"));
        System.out.println(keys);
        Set<String> strings = multimap.keySet();
        System.out.println(strings);
        Collection<Integer> values = multimap.values();
        System.out.println(values);
        Iterable<Integer> concat = Iterables.concat(multimap.asMap().values());
        ImmutableTable<Object, Object, Object> table =
                ImmutableTable.builder().build();
        MutableClassToInstanceMap<Object> objectMutableClassToInstanceMap = MutableClassToInstanceMap.create();
        String res = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "helloWorld");
        System.out.println(res);

//        GuavaCollectionDemo demo = new GuavaCollectionDemo();
//        demo.multisetTest();
//        demo.sortedMultisetTest();
//        demo.multimapTest();
//        demo.biMapTest();
//        demo.tableTest();
//        demo.classToInstanceMapTest();
//        demo.rangeSetTest();
//        demo.rangedSetView();
//        demo.rangedMapTest();
//        demo.constructorCollectionUtilitiesTest();
//        demo.iterableTest();
//        demo.iterablesTest02();
//        demo.listsTest();
//        demo.setsTest();
//        demo.mapsTest();
//        demo.biMapTest02();
//        demo.multisetsTest();
//        demo.multimapsTest();
//        demo.tablesTest();
//        demo.peekIteratorTest();
//        demo.sequentialIteratorTest();
    }

    private static void multisetDemo() {
        HashMultiset<String> multiset = HashMultiset.<String>create();
        multiset.addAll(Arrays.asList("aa", "bb", "aa", "aa", "bb"));
        multiset.setCount("c", 5);
        multiset.remove("c", 4);
        Iterator<String> iterator = multiset.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(multiset.size());
        System.out.println(multiset.count("aa"));
        Set<Multiset.Entry<String>> entries = multiset.entrySet();
        System.out.println(entries);
        System.out.println(multiset.elementSet());
    }

    private static void immutableTest() {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        List<String> list02 = Collections.unmodifiableList(list);
        ImmutableList<String> list03 = ImmutableList.copyOf(list);
        System.out.println(list02); // [aaa, bbb]
        System.out.println("list03:" + list03);  // list03:[aaa, bbb]
        list.add("ccc");
        System.out.println(list02); // [aaa, bbb, ccc]
        System.out.println("list03:" + list03); // list03:[aaa, bbb]
        ImmutableList<String> list04 = ImmutableList.<String>of("aa", "bb", "cc");
        System.out.println(list04);
        ImmutableList<String> build = ImmutableList.<String>builder().add("aa").add("cc").add("ff").build();
        ImmutableSet<String> set = ImmutableSet.<String>builder().add("aa").add("cc").build();
        ImmutableList<String> strings = set.asList();
    }

    private void sequentialIteratorTest() {
        Iterator<Integer> integerIterator = sequentialIterator();
        while (integerIterator.hasNext()) {
            System.out.println(integerIterator.next());
        }
    }

    private Iterator<Integer> sequentialIterator() {
        return new AbstractSequentialIterator<Integer>(2) {
            @CheckForNull
            @Override
            protected Integer computeNext(Integer previous) {
                return previous>100?null:previous*3;
            }
        };
    }

    private Iterator<String> skipNullIterator(final Iterator<String> in){
        return new AbstractIterator<String>() {
            @CheckForNull
            @Override
            protected String computeNext() {
                while (in.hasNext()) {
                    String s = in.next();
                    if (s!=null) {
                        return s;
                    }
                }
                return endOfData();
            }
        };
    }

    private void peekIteratorTest() {
        List<String> result = Lists.newArrayList("a", "b", "c");
        PeekingIterator<String> peekingIterator = Iterators.peekingIterator(result.iterator());
        for (int i = 0; i < 5; i++) {
            System.out.println(peekingIterator.peek());
        }

    }

    private void tablesTest() {
        Table<String, String, Integer> table =
                Tables.<String, String, Integer>newCustomTable(new HashMap<String, Map<String, Integer>>(),
                        LinkedHashMap::new);
        table.put("name", "value", 2);
        System.out.println(table);
        Table<String, String, Integer> transpose =
                Tables.transpose(table);
        System.out.println(transpose);
        Table<String, String, Integer> unmodifiableTable = Tables.unmodifiableTable(table);
        RowSortedTable<String, String, Integer> treeBasedTable = TreeBasedTable.create();
        treeBasedTable.put("name1", "name2", 23);
        RowSortedTable<String, String, Integer> unmodifiableRowSortedTable = Tables.unmodifiableRowSortedTable(treeBasedTable);
        System.out.println(unmodifiableRowSortedTable);
    }

    private void multimapsTest() {
        ListMultimap<String, Integer> multimap = MultimapBuilder.hashKeys().arrayListValues().build();
        multimap.put("name", 23);
        multimap.put("name", 21);
        multimap.put("value", 21);
        System.out.println(multimap);
        ImmutableListMultimap<Integer, String> multimap1 = Multimaps.index(Lists.newArrayList("aa", "bb", "cc", "dd", "world"), String::length);
        System.out.println(multimap1);
        ListMultimap<Integer, String> multimap2 = Multimaps.invertFrom(multimap, MultimapBuilder.hashKeys().arrayListValues().build());
        System.out.println(multimap2);
        ImmutableListMultimap<String, Integer> inverse = multimap1.inverse();
        System.out.println(inverse);
        Map<String, Integer> map = ImmutableMap.of("aa", 1, "bb", 1, "cc", 3);
        System.out.println(map);
        SetMultimap<String, Integer> setMultimap = Multimaps.forMap(map);
        System.out.println(setMultimap);
        HashMultimap<Integer, String> multimap3 = Multimaps.invertFrom(setMultimap, HashMultimap.<Integer, String>create());
        System.out.println(multimap3);
        Multimap<String, Integer> multimap4 = Multimaps.newMultimap(new HashMap<String, Collection<Integer>>(), Lists::newArrayList);
        multimap4.put("aa", 1);
        multimap4.put("aa", 2);
        System.out.println(multimap4);
    }

    private void multisetsTest() {
        HashMultiset<String> multiset = HashMultiset.create();
        HashMultiset<String> multiset02 = HashMultiset.create();
        HashMultiset<String> multiset03 = HashMultiset.create();
        multiset.addAll(Arrays.asList("aa", "bb", "aa", "bb", "cc", "cc", "cc"));
        multiset02.addAll(Arrays.asList("aa", "bb", "aa", "bb", "cc", "dd"));
        multiset03.addAll(Arrays.asList("aa"));
        System.out.println(multiset);
        boolean res = Multisets.containsOccurrences(multiset, multiset02);
        System.out.println(res);
        int num = multiset.count("aa");
//        Multisets.removeOccurrences(multiset, multiset02);
//        Multisets.retainOccurrences(multiset, multiset03);
        System.out.println(multiset);
        Multiset<String> intersection = Multisets.intersection(multiset, multiset02);
        System.out.println(intersection);
        ImmutableMultiset<String> multiset1 = Multisets.copyHighestCountFirst(multiset);
        Multiset<String> multiset2 = Multisets.unmodifiableMultiset(multiset);
        System.out.println(multiset1);
        TreeMultiset<String> treeMultiset = TreeMultiset.create();
        treeMultiset.addAll(Lists.newArrayList("aa","cc", "bb", "aa", "cc", "bb"));
        System.out.println(treeMultiset);
        SortedMultiset<String> sortedMultiset = Multisets.unmodifiableSortedMultiset(treeMultiset);
        System.out.println(sortedMultiset);
    }

    private void biMapTest02() {
        HashBiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("aa", 23);
        biMap.forcePut("bb", 23);
        System.out.println(biMap);
        BiMap<String, Integer> biMap1 = Maps.synchronizedBiMap(biMap);
        Collections.synchronizedMap(biMap1);
        BiMap<String, Integer> biMap2 = Maps.unmodifiableBiMap(biMap);
        Collections.unmodifiableMap(biMap2);
        IdentityHashMap<String, Integer> objectObjectIdentityHashMap = Maps.newIdentityHashMap();
    }

    private void mapsTest() {
        ArrayList<String> list = Lists.newArrayList("a", "bb", "ccc", "dddd", "eeeee", "ffffff");
        ImmutableMap<Integer, String> lenMap = Maps.uniqueIndex(list, String::length);
        String item = lenMap.get(5);
        System.out.println(item);
        Map<String, Integer> map01 = ImmutableMap.of("aa", 1, "bb", 2, "cc", 3);
        Map<String, Integer> map02 = ImmutableMap.of("aa", 2, "d", 2, "cc", 3);
        MapDifference<String, Integer> difference = Maps.difference(map01, map02);
        Map<String, Integer> entriesInCommon = difference.entriesInCommon();
        System.out.println(entriesInCommon);
        Map<String, MapDifference.ValueDifference<Integer>> entriesDiffering = difference.entriesDiffering();
        System.out.println(entriesDiffering);
        Map<String, Integer> entriesOnlyOnLeft = difference.entriesOnlyOnLeft();
        System.out.println(entriesOnlyOnLeft);
        Map<String, Integer> entriesOnlyOnRight = difference.entriesOnlyOnRight();
        System.out.println(entriesOnlyOnRight);

    }

    private void setsTest() {
        HashSet<String> set01 = Sets.newHashSet("aa", "bb", "cc");
        HashSet<String> set02 = Sets.newHashSet("ee", "bb", "ff");
        Sets.SetView<String> result = Sets.union(set01, set02);
        System.out.println(result);
        Sets.SetView<String> intersection = Sets.intersection(set01, set02);
        System.out.println(intersection);
        Sets.SetView<String> difference = Sets.difference(set01, set02);
        System.out.println(difference);
        Sets.SetView<String> difference1 = Sets.symmetricDifference(set01, set02);
        System.out.println(difference1);
        HashSet<String> set03 = Sets.newHashSet("aaa");
        difference1.copyInto(set03);
        System.out.println(set03);
        ImmutableSet<String> immutableSet = difference1.immutableCopy();
        Set<List<String>> lists = Sets.cartesianProduct(set01, set02);
        System.out.println(lists);
        Set<Set<String>> sets = Sets.powerSet(set01);
        System.out.println("*****************");
        for (Set<String> set : sets) {
            System.out.println(set);

        }
        System.out.println("*****************");
        Sets.newLinkedHashSet();
    }

    private void comparatorsTest() {
        Math.max(2, 3);
        Ints.max(2, 3);
        Comparators.max("aa", "bb", Comparator.comparingInt(String::length));
        Collections.max(Arrays.asList("aa", "bb"),Comparator.comparingInt(String::length));
    }

    private void listsTest() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        List<List<Integer>> partition = Lists.partition(list, 3);
        System.out.println(partition);
        List<Integer> reverse = Lists.reverse(list);
        System.out.println(list);
        System.out.println(reverse);
        LinkedList<@Nullable Object> objects = Lists.newLinkedList();
    }

    private void iterablesTest02() {
        ArrayList<String> result = Lists.newArrayList();
        boolean b = Iterables.addAll(result, Arrays.asList("aa", "bb"));
        System.out.println(result);
        boolean bb = Iterables.contains(result, "bb");
        boolean b1 = Iterables.retainAll(result, Lists.newArrayList("bb", "cc"));
        System.out.println(result);
        int size = Iterables.size(result);
        System.out.println(size);
        @Nullable String[] a = Iterables.toArray(result, String.class);
        System.out.println(a);
    }

    private void iterableTest() {
        List<Integer> list = Arrays.asList(5, 1, 5, 6, 9, 1, 5);
        List<Integer> list2 = Arrays.asList(5, 1, 5, 6, 9, 1, 5);
        int frequency = Iterables.frequency(list, 5);
        System.out.println(frequency);
        int frequency1 = Collections.frequency(list, 5);
        System.out.println(frequency1);
        Iterable<List<Integer>> partition = Iterables.partition(list, 2);
        System.out.println(partition);
        Iterable<List<@Nullable Integer>> lists = Iterables.paddedPartition(list, 2);
        System.out.println(lists);
        Integer first = Iterables.getLast(list, -1);
        System.out.println(first);
        boolean b = Iterables.elementsEqual(list, list2);
        Iterable<Integer> integers = Iterables.unmodifiableIterable(list);
        System.out.println(b);
        Iterable<Integer> limit = Iterables.limit(list, 3);
        System.out.println(limit);
        Integer onlyElement = Iterables.getOnlyElement(Arrays.asList(5));
        Integer onlyElement02 = Iterables.getOnlyElement(Arrays.asList(), -1);
        System.out.println(onlyElement);
        System.out.println(onlyElement02);
    }

    private void constructorCollectionUtilitiesTest() {
        List<TypeThatsTooLongForItsOwnGood> list = new ArrayList<>();
        ArrayList<TypeThatsTooLongForItsOwnGood> list02 = Lists.newArrayList();
        ArrayList<String> list03 = Lists.newArrayList("aa", "bb", "cc");
        ArrayList<String> list04 = Lists.newArrayListWithCapacity(10);
        ArrayList<String> list05 = Lists.newArrayListWithExpectedSize(10);
        for (int i = 0; i < 15; i++) {
            list04.add("aa"+i);
        }
        System.out.println(list04);
        HashSet<Integer> set = Sets.newHashSetWithExpectedSize(10);
    }

    private void rangedMapTest() {
        TreeRangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo");
        System.out.println(rangeMap);
        rangeMap.put(Range.open(3, 6), "bar");
        System.out.println(rangeMap);
        rangeMap.put(Range.open(10, 20), "foo");
        System.out.println(rangeMap);
        rangeMap.remove(Range.closed(5, 11));
        System.out.println(rangeMap);
        Map<Range<Integer>, String> mapRange = rangeMap.asMapOfRanges();
        RangeMap<Integer, String> subRangeMap = rangeMap.subRangeMap(Range.open(2, 5));
        System.out.println(subRangeMap);
    }

    private void rangedSetView() {
        TreeRangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.openClosed(2, 5));
        rangeSet.add(Range.open(7, 12));
        RangeSet<Integer> complement = rangeSet.complement();
        System.out.println(rangeSet);
//        System.out.println(complement);
        RangeSet<Integer> subRangeSet = rangeSet.subRangeSet(Range.closed(4, 5));
//        System.out.println(subRangeSet);
        Set<Range<Integer>> ranges = rangeSet.asRanges();
        System.out.println(ranges);
        ImmutableRangeSet<Integer> immutableRangeSet = ImmutableRangeSet.copyOf(rangeSet);
        ImmutableSortedSet<Integer> set = immutableRangeSet.asSet(DiscreteDomain.integers());
        System.out.println(set);
        System.out.println(rangeSet.contains(4));
        Range<Integer> range = rangeSet.rangeContaining(8);
        System.out.println(range);
        System.out.println(rangeSet.encloses(Range.open(6,7)));
        Range<Integer> span = rangeSet.span();
        System.out.println(span);
    }

    private void rangeSetTest() {
        TreeRangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10));
        System.out.println(rangeSet);
        rangeSet.add(Range.closedOpen(11, 15));
        System.out.println(rangeSet);
        rangeSet.add(Range.closedOpen(15, 20));
        System.out.println(rangeSet);
        rangeSet.add(Range.openClosed(0, 0));
        System.out.println(rangeSet);
        rangeSet.remove(Range.open(5, 10));
        System.out.println(rangeSet);
        DiscreteDomain<Integer> discreteDomain = DiscreteDomain.integers();
        Integer minVal = discreteDomain.minValue();
        Integer maxVal = discreteDomain.maxValue();
        System.out.println("minVal: " + minVal + ", maxVal: " + maxVal);
    }

    private void classToInstanceMapTest() {
        MutableClassToInstanceMap<Object> map = MutableClassToInstanceMap.create();
        map.putInstance(String.class, "aa");
        map.putInstance(Integer.class, 23);
        String val = map.getInstance(String.class);
        System.out.println(val);
    }

    private void tableTest() {
        HashBasedTable<String, String, Integer> table = HashBasedTable.create();
        table.put("aa", "bb", 23);
        table.put("aa", "cc", 21);
        table.put("bb", "bb", 23);
        Map<String, Map<String, Integer>> rowMap = table.rowMap();
        System.out.println(rowMap);
        Set<String> key = table.rowKeySet();
        System.out.println(key);
        Map<String, Integer> map01 = table.row("aa");
        System.out.println(table);
        map01.put("aa", 1);
        System.out.println(table);
        Set<Table.Cell<String, String, Integer>> cells = table.cellSet();
        for (Table.Cell<String, String, Integer> cell : cells) {
            System.out.println(cell);
        }

    }

    private void biMapTest() {
        Map<String, Integer> nameToId = Maps.newHashMap();
        Map<Integer, String> idToName = Maps.newHashMap();
        nameToId.put("Bob", 42);
        idToName.put(42, "Bob");
        // 如果 Bob 或 42 已经存在了，会怎么样？
        // 诡异的bug将会出现，如果我们忘了同步更新

        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("Bob", 42);
//        biMap.put("Job", 42);
        biMap.forcePut("Job", 42);
        System.out.println(biMap);
    }

    private void multimapTest() {
        ListMultimap<String, Integer> listMultimap =
                MultimapBuilder.hashKeys().arrayListValues().build();

//        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        List<Integer> nums = listMultimap.get("name");
        System.out.println(nums);
        nums.clear();
        nums.add(23);
        nums.add(21);
        listMultimap.put("name", 31);
        Iterables.addAll(listMultimap.get("name"), Arrays.asList(1, 2));
        System.out.println(listMultimap.get("name"));
        Collection<Integer> values = listMultimap.values();
        System.out.println(values);
        List<Integer> result = listMultimap.replaceValues("name", Arrays.asList(1, 11, 111));
        System.out.println(result);
        Map<String, Collection<Integer>> stringCollectionMap = listMultimap.asMap();
        Collection<Integer> value0 = stringCollectionMap.get("value");
        List<Integer> value = (List<Integer>)stringCollectionMap.get("value");
        System.out.println(value0);
        System.out.println(value);
        for (Map.Entry<String, Integer> entry : listMultimap.entries()) {
            System.out.println(entry.getKey()+ " : " + entry.getValue());
        }


    }

    private void sortedMultisetTest() {
        TreeMultiset<Comparable<Integer>> set = TreeMultiset.create();
        set.addAll(Arrays.asList(5, 5, 2, 3, 7, 9, 8, 8));
        SortedMultiset<Comparable<Integer>> result = set.subMultiset(3, BoundType.OPEN, 9, BoundType.CLOSED);
        for (Comparable<Integer> item : result) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }

    private void multisetTest() {
        Multiset<String> set = HashMultiset.create();
        set.add("aa");
        set.add("aa");
        set.add(null);
        set.addAll(Arrays.asList("aa", "cc", "ee", "cc", "bb"));
        for (String name : set) {
            System.out.print(name + ", ");
        }
        System.out.println();
        System.out.println(set.size());
        set.remove("aa", 2);
        System.out.println("aa count: " + set.count("aa"));
        Set<String> keys = set.elementSet();
        System.out.println(keys);
        System.out.println(keys.size());
        System.out.println(set.count("cc"));
        System.out.println(keys.size());
        for (String key : set.elementSet()) {
            System.out.println(key);
        }
    }

    private void wordCount(List<String> words) {
        Multiset<String> set = HashMultiset.create();
        set.addAll(words);
        Map<String, Integer> result = new HashMap<>();
        for (String word : words) {
            if (result.containsKey(word)) {
                result.put(word,result.get(word)+1);
            }else {
                result.put(word, 1);
            }
        }
    }
}
