package com.hef.review.designpatterns.behavioral.visitor.version03;

/**
 * PDF文件
 * @Date 2022/10/30
 * @Author lifei
 */
public class PdfFile extends ResourceFile{
    public PdfFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Extractor extractor) {
        extractor.extract2txt(this);
    }

    @Override
    public void accept(Compressor compressor) {
        compressor.compressor(this);
    }
}
