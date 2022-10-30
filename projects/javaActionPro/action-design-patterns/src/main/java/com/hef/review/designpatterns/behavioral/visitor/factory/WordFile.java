package com.hef.review.designpatterns.behavioral.visitor.factory;

/**
 * @Date 2022/10/30
 * @Author lifei
 */
public class WordFile extends ResourceFile{
    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public ResourceFileType getType() {
        return ResourceFileType.WORD;
    }
}
