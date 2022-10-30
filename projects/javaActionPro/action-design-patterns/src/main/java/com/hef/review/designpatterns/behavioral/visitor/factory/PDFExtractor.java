package com.hef.review.designpatterns.behavioral.visitor.factory;

/**
 * @Date 2022/10/30
 * @Author lifei
 */
public class PDFExtractor implements Extractor{
    @Override
    public void extract2txt(ResourceFile resourceFile) {
        System.out.println("提取PDF文件内容......");
    }
}
