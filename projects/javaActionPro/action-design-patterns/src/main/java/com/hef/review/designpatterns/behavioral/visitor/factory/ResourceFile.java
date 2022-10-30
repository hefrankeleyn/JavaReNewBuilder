package com.hef.review.designpatterns.behavioral.visitor.factory;

/**
 * @Date 2022/10/30
 * @Author lifei
 */
public abstract class ResourceFile {

    private String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public abstract ResourceFileType getType();
}
