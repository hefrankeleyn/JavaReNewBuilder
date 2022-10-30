package com.hef.review.designpatterns.behavioral.visitor.version04;


/**
 * PDF文件
 * @Date 2022/10/30
 * @Author lifei
 */
public class PdfFile extends ResourceFile {
    public PdfFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
