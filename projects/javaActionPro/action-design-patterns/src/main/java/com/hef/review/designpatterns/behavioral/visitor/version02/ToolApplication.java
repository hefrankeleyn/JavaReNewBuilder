package com.hef.review.designpatterns.behavioral.visitor.version02;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试：行为和数据结构分离
 *   多态是一种动态绑定，可以在运行的时候获取对象的实际类型，来运行实际类型对应的方法。
 *   而函数重载是一种静态绑定，是在编译期间，由参数的声明类型决定的，而非运行时，根据参数的实际类型决定的。
 * @Date 2022/10/30
 * @Author lifei
 */
public class ToolApplication {

    public static void main(String[] args) {
        Extractor extractor = new Extractor();
        List<ResourceFile> resourceFiles = listAllResourceFiles();
        for (ResourceFile resourceFile : resourceFiles) {
            // 利用函数重载，下面这句话编译不能通过
//            extractor.extract2txt(resourceFile);
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
