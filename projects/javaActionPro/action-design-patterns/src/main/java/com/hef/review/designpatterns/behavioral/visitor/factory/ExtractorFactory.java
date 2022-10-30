package com.hef.review.designpatterns.behavioral.visitor.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2022/10/30
 * @Author lifei
 */
public class ExtractorFactory {
    private static final Map<ResourceFileType, Extractor> extractors = new HashMap<>();
    static {
        extractors.put(ResourceFileType.PDF, new PDFExtractor());
        extractors.put(ResourceFileType.PPT, new PPTExtractor());
        extractors.put(ResourceFileType.WORD, new WordExtractor());
    }

    public static Extractor getExtractor(ResourceFileType type) {
        return extractors.get(type);
    }
}
