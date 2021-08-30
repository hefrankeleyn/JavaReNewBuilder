package com.hef.stream;

import com.hef.domain.Car;
import com.hef.domain.Insurance;
import com.hef.domain.Person;

import java.util.Optional;

/**
 * @Date 2021/8/30
 * @Author lifei
 */
public class OptionalTest {

    public static void main(String[] args) {
        // 创建一个空 的 Optional
        Optional<Insurance> empty = Optional.empty();
        // 依据一个非空的值创建 optional
        Insurance insurance = new Insurance();
        Optional<Insurance> op2 = Optional.of(insurance);
        // 可接受null 的 optional
        Optional<Insurance> op3 = Optional.ofNullable(null);

        // 使用 map 从Optional 中提取或转换值
        Optional<String> carNameOp = op2.map(Insurance::getInsuranceName);
        Optional<Integer> lenOp = carNameOp.map(String::length);
        Optional<Integer> resOp = lenOp.map(k -> k * 2);
        System.out.println(resOp.orElse(-1));

        Optional<Integer> res2OP = op2.map(Insurance::getInsuranceName).map(String::length).map(k -> k * 3);
        System.out.println(res2OP.orElse(-2));

        // 使用 flatMap 将两层Optional 变成一层
        Optional<Person> personOptional = Optional.ofNullable(null);
        String res = personOptional.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getInsuranceName)
                .orElse("unknow");
        System.out.println(res);
    }

}
