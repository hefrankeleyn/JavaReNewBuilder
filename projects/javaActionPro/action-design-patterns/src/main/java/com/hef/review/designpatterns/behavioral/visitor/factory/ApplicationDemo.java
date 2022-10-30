package com.hef.review.designpatterns.behavioral.visitor.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/10/30
 * @Author lifei
 */
public class ApplicationDemo {

    public static void main(String[] args) {
        List<ResourceFile> resourceFiles = listAllResourceFiles();
        for (ResourceFile resourceFile : resourceFiles) {
            Extractor extractor = ExtractorFactory.getExtractor(resourceFile.getType());
            extractor.extract2txt(resourceFile);
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
