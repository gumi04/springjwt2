package com.example.demogradle.constans;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-${spring.profiles.active}.properties")
@Getter
@Setter
public class Constants {


}

