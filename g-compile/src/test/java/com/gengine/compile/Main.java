package com.gengine.compile;

import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    @Test
    public void test() throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "D:\\code\\gengine\\g-compile\\src\\main\\java\\com\\gengine\\compile\\section1\\MyClass.java");
        System.out.println("Compile result code = " + result);
//执行java 命令 , 空参数, 所在文件夹
//        Process process = Runtime.getRuntime().exec("java Test", null, new File(""));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String str;
//        while ((str = bufferedReader.readLine()) != null) {
//            System.out.println(str);
//        }
    }
}
