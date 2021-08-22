package com.hef.reflex;

import com.hef.domain.Person;
import com.hef.domain.SingletonPerson;
import com.hef.domain.Student;

import java.lang.reflect.*;

/**
 * @Date 2021/8/22
 * @Author lifei
 */
public class ClassObjTest {

    public static void main(String[] args) {
        ClassObjTest objTest = new ClassObjTest();
        try {
//            objTest.getClassObj();
//            objTest.createObj();
//            objTest.fetchField();
            // 通过反射获取父类的属性
//            objTest.fetchParentField();
            objTest.reflexMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载class 文件的三种方式
     * @throws ClassNotFoundException
     */
    private void getClassObj() throws ClassNotFoundException {
        // 方式一： 类.class
        Class<Person> c1 = Person.class;
        // 方式二： 实例.class
        Person person = new Person();
        Class<? extends Person> c2 = person.getClass();

        // 方式三： Class.forName("类的全路径");
        Class<?> c3 = Class.forName("com.hef.domain.Person");
        System.out.println(c1==c2);
        System.out.println(c1==c3);
        System.out.println(c2==c3);
    }


    /**
     * 通过反射创建对象
     */
    private void createObj() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        /** 1. 获得Person的字节码 */
        Class<?> pC = Class.forName("com.hef.domain.Person");
        /** 2. 通过Class对象，创建构造方法对象 */
        Constructor<?> constructor1 = pC.getConstructor();
        Constructor<?> constructor2 = pC.getConstructor(String.class, Integer.class, Byte.class, Boolean.class);

        /** 3. 通过构造方法创建对象 */
        Person p = (Person) constructor1.newInstance();
        Person p2 = (Person)constructor2.newInstance("aaa", 3, (byte)2, true);
        System.out.println(p);
        System.out.println(p2);

        /** 补充内容： 通过反射 创建单例对象。 破坏了单例 */
        System.out.println("补充内容: 通过反射强制创建新对象");
        Class<?> spC = Class.forName("com.hef.domain.SingletonPerson");
        Constructor<?> declaredConstructor = spC.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);

        SingletonPerson sp = (SingletonPerson) declaredConstructor.newInstance();

        SingletonPerson sp2 = SingletonPerson.instance();
        System.out.println(sp==sp2);

    }

    /**
     * 获取field
     */
    public void fetchField() throws Exception{
        // 获取class
        Class<?> pC = Class.forName("com.hef.domain.Person");
        // 获取构造函数
        Constructor<?> constructor = pC.getConstructor();
        // 根据构造函数创建对象
        Person pObj = (Person) constructor.newInstance();
        // 获取public 字段
        Field fieldName = pC.getField("name");
        System.out.println(fieldName.get(pObj));
        // 获取非public字段
        Field fieldAge = pC.getDeclaredField("age");
        // 除非加上 setAccessible(true) 非则不能访问值
        fieldAge.setAccessible(true);
        System.out.println(fieldAge.get(pObj));
    }

    /**
     * 获取父类的属性
     */
    public void fetchParentField() throws Exception{
        // 获取class
        Class<?> sc = Class.forName("com.hef.domain.Student");
        // 获取构造函数
        Constructor<?> constructor = sc.getConstructor();
        // 创建对象
        Student student = (Student) constructor.newInstance();
        System.out.println(student);
        System.out.println(student);
        Field fieldName = sc.getField("name");
        System.out.println(fieldName.get(student));
//        Field ageField = sc.getField("age");
//        System.out.println(ageField.get(student));

        // Field 的重要属性
        System.out.println("获取字段类型： " + fieldName.getType());
        System.out.println("获取字段的名字： " + fieldName.getName());
        System.out.println("获取字段的访问修饰符： " + Modifier.toString(fieldName.getModifiers()));
        System.out.println("获取字段所在类的全路径： " + fieldName.getDeclaringClass().getName());

    }

    private void reflexMethod() throws Exception{
        Class<?> sc = Class.forName("com.hef.domain.Student");
        // 获取构造函数
        Constructor<?> constructor = sc.getConstructor();
        Student student = (Student) constructor.newInstance();
        // 获取公共方法
        Method toStringMethod = sc.getMethod("toString");
        System.out.println(toStringMethod.invoke(student));
        // 获取私有访问
        Method studyMethod = sc.getDeclaredMethod("study");
        System.out.println(studyMethod);
        studyMethod.setAccessible(true);
        studyMethod.invoke(student);
    }


}
