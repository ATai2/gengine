package com.gengine.compile;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
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

    @Test
    public void test_sql(){
//        String sql= "Select 'abc' as a, `hahah` as c  From a aS table;";
//        ANTLRInputStream input = new ANTLRInputStream(sql);  //将输入转成antlr的input流
//        DslLexer lexer = new DslLexer(input);  //词法分析
//        CommonTokenStream tokens = new CommonTokenStream(lexer);  //转成token流
//        DslParser parser = new DslParser(tokens); // 语法分析
//        DslParser.StaContext tree = parser.sta();  //获取某一个规则树，这里获取的是最外层的规则，也可以通过sql()获取sql规则树......
//        System.out.println(tree.toStringTree(parser)); //打印规则数
    }
}
