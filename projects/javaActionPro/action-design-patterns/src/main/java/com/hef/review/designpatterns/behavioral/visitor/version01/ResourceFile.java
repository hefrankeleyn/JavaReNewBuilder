package com.hef.review.designpatterns.behavioral.visitor.version01;

/**
 * 资源文件的抽象
 * @Date 2022/10/30
 * @Author lifei
 */
public abstract class ResourceFile {

    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    // 提取文件内容到txt文件
    public abstract void extract2txt();
}
