package com.example.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component//使map全局可用
public class CaptureConfig {

    public static Map<String,String> CAPTURE_MAP=new HashMap<>();

}
