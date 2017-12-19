package com.howard.spring4.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype") //每次请求创建一个bean
public class DemoPrototypeService {
}
