package com.hef.review.designpatterns.structural.composite;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 组合模式的测试： 文件-目录 对象，使用组合模式
 * @Date 2022/10/20
 * @Author lifei
 */
public class FileTreeDemo {

    public static void main(String[] args) {
        String dirPath = "/Users/lifei/Documents/workspace/githubRepositoies/JavaNoneRebuild/projects/javaActionPro/action-design-patterns/src";
        DirectoryItem directoryItem = createDirectoryItem(dirPath);
        System.out.println("文件的数量：" + directoryItem.countNumberOfFiles());
        System.out.println("文件的大小：" + directoryItem.countSizOfFiles());
    }

    /**
     * 根据根目录创建，一个文件树
     * @param dirPath
     * @return
     */
    public static DirectoryItem createDirectoryItem(String dirPath) {
        File file = new File(dirPath);
        if (StringUtils.isBlank(dirPath) || !file.exists() || file.isFile()) {
            throw new RuntimeException("必须传递一个目录");
        }
        DirectoryItem directoryItem = new DirectoryItem(dirPath);
        String[] fileNames = file.list();
        for (String fileName : fileNames) {
            String subFilePath = dirPath + File.separator + fileName;
            File subFile = new File(subFilePath);
            if (subFile.isFile()) {
                directoryItem.addSubNode(new FileItem(subFilePath));
            }else {
                directoryItem.addSubNode(createDirectoryItem(subFilePath));
            }
        }
        return directoryItem;
    }
}
