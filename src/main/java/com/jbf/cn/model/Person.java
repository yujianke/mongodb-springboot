package com.jbf.cn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "person")
public class Person {
    @Id
    private String  _id;
    private String  name;
    private Integer age;
}