package com.atai.module.gengine.utils;

import groovy.lang.*;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.codehaus.groovy.runtime.EncodingGroovyMethods;
import org.codehaus.groovy.runtime.InvokerHelper;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class EngineExcutor {

    private static final GroovyScriptEngineFactory scriptEngineFactory = new GroovyScriptEngineFactory();
    private static final GroovyShell groovyShell = new GroovyShell();
    private static final ConcurrentHashMap<String, ScriptEngine> scriptEngineConcurrentHashMap = new ConcurrentHashMap<>();
    static ConcurrentHashMap<String, Script> scriptMap = new ConcurrentHashMap<>();
    static ConcurrentHashMap<String, Script> scriptFileMap = new ConcurrentHashMap<>();


    public static Object invoke(String scriptText, String function, Object... objects) throws Exception {
        GroovyClassLoader classLoader = new GroovyClassLoader();
        Class groovyClass = classLoader.parseClass(scriptText);
        try {
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            return groovyObject.invokeMethod(function, objects);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokeClearCache(String scriptText, String function, Object... objects) throws Exception {
        GroovyClassLoader classLoader = new GroovyClassLoader();
        Class groovyClass = classLoader.parseClass(scriptText);
        try {
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            return groovyObject.invokeMethod(function, objects);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            classLoader.clearCache();
        }
        return null;
    }

    static GroovyClassLoader classLoader = new GroovyClassLoader();

    public static Object invokeWithCache(String scriptText, String function, Object... objects) throws Exception {
        GroovyCodeSource groovyCodeSource = new GroovyCodeSource(scriptText, EncodingGroovyMethods.md5(scriptText), "/groovy/script");
        Class groovyClass = classLoader.parseClass(groovyCodeSource, true);
        try {
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            return groovyObject.invokeMethod(function, objects);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object runFileWithCache(File file, Object... objects) throws Exception {

//        GroovyCodeSource groovyCodeSource = new GroovyCodeSource(file, StandardCharsets.UTF_8.name());
//        Class groovyClass = classLoader.parseClass(groovyCodeSource, true);
//        try {
//            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
//
//            return groovyObject.invokeMethod(function, objects);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        return null;
    }


    public static Object clearCache(String scriptText) {
        classLoader.clearCache();
        return null;
    }


    public static <T> T invokeEngine(String script, String function, Object... objects) throws Exception {
        ScriptEngine scriptEngine = scriptEngineFactory.getScriptEngine();
        scriptEngine.eval(script);
        return (T) ((Invocable) scriptEngine).invokeFunction(function, objects);
    }

    public static <T> T invokeEngineWithCache(String script, String function, Object... objects) throws Exception {
        ScriptEngine scriptEngine = scriptEngineConcurrentHashMap.get(script);
        if (scriptEngine == null) {
            scriptEngine = scriptEngineFactory.getScriptEngine();
            scriptEngineConcurrentHashMap.put(script, scriptEngine);
        }
        scriptEngine.eval(script);
        return (T) ((Invocable) scriptEngine).invokeFunction(function, objects);
    }

    /**
     * GroovyShell
     * GroovyShell允许在Java类中（甚至Groovy类）求任意Groovy表达式的值。您可使用Binding对象输入参数给表达式，并最终通过GroovyShell返回Groovy表达式的计算结果。
     * 通常用来运行"script片段"或者一些零散的表达式(Expression)
     * ————————————————
     * 版权声明：本文为CSDN博主「夜里慢慢行456」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/u013257767/article/details/105762624/
     * @param scriptText
     * @param function
     * @param objects
     * @param <T>
     * @return
     * @throws Exception
     */

    public static <T> T invokeShell(String scriptText, String function, Object... objects) throws Exception {
        Script script = groovyShell.parse(scriptText);
        return (T) InvokerHelper.invokeMethod(script, function, objects);
    }

    public static <T> T invokeShellWithCache(String scriptText, String function, Object... objects) throws Exception {
        Script script = scriptMap.get(scriptText);
        if (script == null) {
            script = groovyShell.parse(scriptText);
            scriptMap.put(scriptText, script);
        }
        return (T) InvokerHelper.invokeMethod(script, function, objects);
    }

    public static Object invokeShelFilelWithCache(File file, Map<String,Object> params) throws Exception {
        Script script = scriptFileMap.get(file.getName());
        Binding binding = new Binding();
        params.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(String s, Object o) {
                binding.setVariable(s,o);
            }
        });
        if (script == null) {
            script = groovyShell.parse(file);
            scriptFileMap.put(file.getName(), script);
        }
        script.setBinding(binding);
        return script.evaluate(file);
//        return (T) InvokerHelper.invokeMethod(script, function, objects);
    }


}
