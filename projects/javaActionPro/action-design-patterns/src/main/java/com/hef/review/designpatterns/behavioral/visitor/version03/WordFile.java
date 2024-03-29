package com.hef.review.designpatterns.behavioral.visitor.version03;

/**
 * word文件
 * @Date 2022/10/30
 * @Author lifei
 */
public class WordFile extends ResourceFile{
    public WordFile(String filePath) {
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
