package com.hef.review.designpatterns.structural.composite;

/**
 * 目录和文件的抽象
 * @Date 2022/10/20
 * @Author lifei
 */
public abstract class FileSystemNodeParent {
    protected String path;
    public FileSystemNodeParent(String path) {
        this.path = path;
    }

    public abstract int countNumberOfFiles();

    public abstract long countSizOfFiles();

    public String getPath() {
        return path;
    }
}
