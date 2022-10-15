package com.hef.review.designpatterns.creational.di.beans;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 解析XML，使用JDK自带的DOM解析类库
 * @Date 2022/10/12
 * @Author lifei
 */
public class BeanXmlConfigParser implements BeanConfigParser {

    /**
     * 根据流进行解析
     * @param in
     * @return
     */
    @Override
    public List<BeanDefinition> parse(InputStream in) {
        try {
            List<BeanDefinition> result = new ArrayList<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(in);
            doc.getDocumentElement().normalize();

            NodeList beanList = doc.getElementsByTagName("bean");
            for (int i = 0; i < beanList.getLength(); i++) {
                Node node = beanList.item(i);
                if (!Objects.equals(node.getNodeType(), Node.ELEMENT_NODE)) continue;
                Element element = (Element) node;
                BeanDefinition beanDefinition = new BeanDefinition(element.getAttribute("id"), element.getAttribute("class"));
                if (element.hasAttribute("scope")
                        && StringUtils.equals(element.getAttribute("scope"), BeanDefinition.Scope.PROTOTYPE.name())) {
                    beanDefinition.setScope(BeanDefinition.Scope.PROTOTYPE);
                }
                if (element.hasAttribute("lazy-init")
                        && Boolean.valueOf(element.getAttribute("lazy-init"))) {
                    beanDefinition.setLazyInit(true);
                }
                List<BeanDefinition.ConstructorArg> constructorArgs = createConstructorArgs(element);
                if (CollectionUtils.isNotEmpty(constructorArgs)) {
                    beanDefinition.setConstructorArgs(constructorArgs);
                }
                result.add(beanDefinition);
            }
            return result;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建构造函数
     * @param element
     * @return
     */
    private List<BeanDefinition.ConstructorArg> createConstructorArgs(Element element) {
        List<BeanDefinition.ConstructorArg> result = new ArrayList<>();
        NodeList nodeList = element.getElementsByTagName("constructor-arg");
        if (nodeList.getLength()==0) return result;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (!Objects.equals(node.getNodeType(), Node.ELEMENT_NODE)) continue;
            Element ele = (Element) node;
            BeanDefinition.ConstructorArg arg = new BeanDefinition.ConstructorArg();
            if (ele.hasAttribute("type") && StringUtils.isNoneBlank(ele.getAttribute("type"))) {
                String type = ele.getAttribute("type");
                String value = ele.getAttribute("value");
                arg.setType(fetchClassType(type));
                arg.setArg(fetchArgValue(type, value));
                arg.setRef(false);
            }else if (ele.hasAttribute("ref")) {
                arg.setRef(true);
                arg.setArg(ele.getAttribute("ref"));
            }
            result.add(arg);
        }
        return result;
    }

    /**
     * 获取构造函数 参数的值
     * @param typeValue
     * @param value
     * @return
     */
    private Object fetchArgValue(String typeValue, String value) {
        if (StringUtils.equals(typeValue, "int") || StringUtils.contains(typeValue, "Integer")) {
            return Integer.parseInt(value);
        }else if (StringUtils.contains(typeValue, "String")) {
            return value;
        } else {
            throw new RuntimeException("未知类型");
        }
    }

    /**
     * 获取构造函数的类型， 注意原始类型的 Class表示
     * @param typeValue
     * @return
     */
    private Class<?> fetchClassType(String typeValue) {
        if (StringUtils.equals(typeValue, "int")){
            return Integer.TYPE;
        } else if(StringUtils.contains(typeValue, "Integer")) {
            return Integer.class;
        }else if (StringUtils.contains(typeValue, "String")) {
            return String.class;
        } else {
            throw new RuntimeException("未知类型");
        }
    }
}
