package com.hef.review.designpatterns.behavioral.visitor.version02;

/**
 * 资源文件
 * @Date 2022/10/30
 * @Author lifei
 */
public abstract class ResourceFile {
    protected final String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
