package com.hef.review.designpatterns.behavioral.strategy.example02;

import java.io.File;

/**
 * 文件排序
 * @Date 2022/10/23
 * @Author lifei
 */
public class FileSorter {

    private static final long GB = 1024 * 1024 * 1024;

    public void sort(String filePath) {
        // 省略校验逻辑
        File file = new File(filePath);
        long fileSize = file.length();
        ISortAlg sortAlg;
        /*if (fileSize < 6 * GB) {
//            quickSort(filePath);
//            sortAlg = new QuickSort();
            sortAlg = SortAlgFactory.getSortAlg("QuickSort");
        } else if (fileSize < 10 * GB) {
//            externalSort(filePath);
//            sortAlg = new ExternalSort();
            sortAlg = SortAlgFactory.getSortAlg("ExternalSort");
        } else if (fileSize < 100 * GB) {
//            concurrentExternalSort(filePath);
//            sortAlg = new ConcurrentExternalSort();
            sortAlg = SortAlgFactory.getSortAlg("ConcurrentExternalSort");
        } else {
//            mapReduceSort(filePath);
//            sortAlg = new MapReduceSort();
            sortAlg = SortAlgFactory.getSortAlg("MapReduceSort");
        }*/
        sortAlg = SortAlgRangeFactory.getSortAlg(fileSize);
        sortAlg.sort(filePath);
    }

    private void mapReduceSort(String filePath) {
        System.out.println("当文件超级大的时候，使用真正的Map-Reduce排序");
    }

    private void concurrentExternalSort(String filePath) {
        System.out.println("当文件过大当时候，使用并发当外部排序");
    }

    private void externalSort(String filePath) {
        System.out.println("当文件比较大当时候，使用外部排序");
    }

    private void quickSort(String filePath) {
        System.out.println("当文件不是很大当时候，使用快速排序");
    }
}
