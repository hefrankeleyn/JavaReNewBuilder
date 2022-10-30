package com.hef.review.designpatterns.behavioral.visitor.version04;

/**
 * PPT文件
 * @Date 2022/10/30
 * @Author lifei
 */
public class PPTFile extends ResourceFile {
    public PPTFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
