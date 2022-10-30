package com.hef.review.designpatterns.behavioral.visitor.version03;

/**
 * 资源文件
 * @Date 2022/10/30
 * @Author lifei
 */
public abstract class ResourceFile {
    protected String filePath;
    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public abstract void accept(Extractor extractor);

    public abstract void accept(Compressor compressor);
}
