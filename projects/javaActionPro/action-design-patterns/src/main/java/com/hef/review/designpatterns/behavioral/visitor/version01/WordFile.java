package com.hef.review.designpatterns.behavioral.visitor.version01;

/**
 * 提取word文件内容到txt
 * @Date 2022/10/30
 * @Author lifei
 */
public class WordFile extends ResourceFile{

    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public void extract2txt() {
        System.out.println("提取Word文件内容到txt文件");
    }
}
