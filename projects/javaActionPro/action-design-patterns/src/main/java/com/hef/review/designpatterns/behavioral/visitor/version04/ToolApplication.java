package com.hef.review.designpatterns.behavioral.visitor.version04;


import java.util.ArrayList;
import java.util.List;

/**
 * 使用访问者模式
 * @Date 2022/10/30
 * @Author lifei
 */
public class ToolApplication {

    public static void main(String[] args) {
        Extractor extractor = new Extractor();
        Compressor compressor = new Compressor();
        List<ResourceFile> resourceFiles = listAllResourceFiles();
        for (ResourceFile resourceFile : resourceFiles) {
            // 执行下面这行代码的时候，会根据多台调用实际类型的accept函数
            resourceFile.accept(extractor);
            resourceFile.accept(compressor);
        }
    }
    private static List<ResourceFile> listAllResourceFiles() {
        List<ResourceFile> result = new ArrayList<>();
        result.add(new PdfFile("a.pdf"));
        result.add(new WordFile("b.docx"));
        result.add(new PPTFile("c.pptx"));
        return result;
    }
}
