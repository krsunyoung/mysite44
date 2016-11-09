package com.bit2016.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//target은 메소드에 붙여할지 파라미터 ,타입에 해야할지 결정하는것.
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) //이노테이션 지속시간. 
public @interface Auth {
	String role() default "user";

}
