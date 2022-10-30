package com.hef.review.designpatterns.behavioral.visitor.version04;

/**
 * 压缩功能
 * @Date 2022/10/30
 * @Author lifei
 */
public class Compressor implements Visitor{

    @Override
    public void visitor(PdfFile pdfFile) {
        System.out.println("压缩pdf...");
    }

    @Override
    public void visitor(PPTFile pdfFile) {
        System.out.println("压缩PPT...");
    }

    @Override
    public void visitor(WordFile pdfFile) {
        System.out.println("压缩word...");
    }
}
