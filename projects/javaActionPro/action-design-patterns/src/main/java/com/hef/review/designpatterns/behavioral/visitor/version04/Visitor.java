package com.hef.review.designpatterns.behavioral.visitor.version04;

public interface Visitor {
    void visitor(PdfFile pdfFile);
    void visitor(PPTFile pptFile);
    void visitor(WordFile wordFile);
}
