package com.hef.review.designpatterns.behavioral.visitor.version01;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 * @Date 2022/10/30
 * @Author lifei
 */
public class ToolApplication {

    public static void main(String[] args) {
        List<ResourceFile> resourceFiles = listAllResourceFiles();
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.extract2txt();
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
