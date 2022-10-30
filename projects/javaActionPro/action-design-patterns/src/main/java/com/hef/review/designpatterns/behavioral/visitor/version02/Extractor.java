package com.hef.review.designpatterns.behavioral.visitor.version02;

/**
 * 提取功能： 使用函数的重载
 * @Date 2022/10/30
 * @Author lifei
 */
public class Extractor {

    public void extract2txt(PdfFile pdfFile) {
        System.out.println("提取PDF 文件内容....");
    }

    public void extract2txt(PPTFile pptFile) {
        System.out.println("提取PPT文件内容......");
    }

    public void extract2txt(WordFile wordFile) {
        System.out.println("提取word文件内容.....");
    }
}
