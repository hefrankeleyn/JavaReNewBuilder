package com.hef.review.designpatterns.behavioral.visitor.version03;

/**
 * 压缩功能
 * @Date 2022/10/30
 * @Author lifei
 */
public class Compressor {

    public void compressor(PdfFile pdfFile) {
        System.out.println("压缩pdf...");
    }

    public void compressor(PPTFile pdfFile) {
        System.out.println("压缩PPT...");
    }

    public void compressor(WordFile pdfFile) {
        System.out.println("压缩word...");
    }
}
