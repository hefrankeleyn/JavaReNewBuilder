package com.hef.review.designpatterns.behavioral.visitor.version01;

/**
 * 提取pdf文件内容到txt
 * @Date 2022/10/30
 * @Author lifei
 */
public class PdfFile extends ResourceFile{

    public PdfFile(String filePath) {
        super(filePath);
    }

    @Override
    public void extract2txt() {
        System.out.println("提取Pdf文件内容到txt");
    }
}
