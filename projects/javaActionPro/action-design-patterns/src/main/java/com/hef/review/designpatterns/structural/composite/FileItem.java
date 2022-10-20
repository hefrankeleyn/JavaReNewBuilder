package com.hef.review.designpatterns.structural.composite;

import java.io.File;

/**
 * 文件的实现
 * @Date 2022/10/20
 * @Author lifei
 */
public class FileItem extends FileSystemNodeParent {

    public FileItem(String path) {
        super(path);
    }

    @Override
    public int countNumberOfFiles() {
        return 1;
    }

    @Override
    public long countSizOfFiles() {
        File file = new File(path);
        if (!file.exists()) {
            return 0l;
        }
        return file.length();
    }
}
