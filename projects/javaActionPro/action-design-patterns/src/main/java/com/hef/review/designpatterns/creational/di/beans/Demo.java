package com.hef.review.designpatterns.creational.di.beans;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.Objects;

/**
 * 使用DI容器，从获取中获取Bean对象
 * @Date 2022/10/12
 * @Author lifei
 */
public class Demo {

    public static void main(String[] args) {
//        testReadResourceXML();
//        testParseXML();
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Object productInfo = context.getBean("productInfo");
        System.out.println(productInfo);
        ProductSell productSell = (ProductSell)context.getBean("productSell");
        productSell.sell();
    }

    /**
     * 解析XML
     */
    private static void testParseXML() {
        try (InputStream in = Demo.class.getClassLoader().getResourceAsStream("beans.xml")) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(in);
            Element rootElement = document.getDocumentElement();
            NodeList childNodes = rootElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                System.out.println(node);
                if (!Objects.equals(node.getNodeType(), Node.ELEMENT_NODE)) continue;
                Element element = (Element) node;
                if (element.hasAttribute("id")) {
                    System.out.println(element.getAttribute("id"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试读取 resources 文件夹下的 xml 文件
     */
    private static void testReadResourceXML() {
        try (InputStream in = Demo.class.getClassLoader().getResourceAsStream("beans.xml");
             InputStreamReader inputStreamReader = new InputStreamReader(in);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
