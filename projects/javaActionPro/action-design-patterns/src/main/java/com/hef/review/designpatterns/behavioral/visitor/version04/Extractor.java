package com.hef.review.designpatterns.behavioral.visitor.version04;


/**
 * 提取功能： 使用函数的重载
 * @Date 2022/10/30
 * @Author lifei
 */
public class Extractor implements Visitor{

    @Override
    public void visitor(PdfFile pdfFile) {
        System.out.println("提取PDF 文件内容....");
    }

    @Override
    public void visitor(PPTFile pptFile) {
        System.out.println("提取PPT文件内容......");
    }

    @Override
    public void visitor(WordFile wordFile) {
        System.out.println("提取word文件内容.....");
    }
}
