package com.hef.review.designpatterns.behavioral.visitor.version04;

/**
 * word文件
 * @Date 2022/10/30
 * @Author lifei
 */
public class WordFile extends ResourceFile {
    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
