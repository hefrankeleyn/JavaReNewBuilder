package com.hef.stream;

import java.util.Optional;

/**
 * @Date 2022/7/3
 * @Author lifei
 */
public class HomeA {

    private Optional<StudentB> studentB;

    public Optional<StudentB> getStudentB() {
        return studentB;
    }

    public void setStudentB(Optional<StudentB> studentB) {
        this.studentB = studentB;
    }
}
