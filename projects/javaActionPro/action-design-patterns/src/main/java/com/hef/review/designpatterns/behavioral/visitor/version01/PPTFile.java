package com.hef.review.designpatterns.behavioral.visitor.version01;

/**
 * 抽取PPT文件内容到txt
 * @Date 2022/10/30
 * @Author lifei
 */
public class PPTFile extends ResourceFile{

    public PPTFile(String filePath) {
        super(filePath);
    }

    @Override
    public void extract2txt() {
        System.out.println("提取PPT文件内容......");
    }
}
