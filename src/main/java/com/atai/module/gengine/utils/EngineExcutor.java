package com.atai.module.gengine.utils;

import groovy.lang.*;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.codehaus.groovy.runtime.EncodingGroovyMethods;
import org.codehaus.groovy.runtime.InvokerHelper;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.util.concurrent.ConcurrentHashMap;

public class EngineExcutor {

    private static final GroovyScriptEngineFactory scriptEngineFactory = new GroovyScriptEngineFactory();
    private static GroovyShell groovyShell = new GroovyShell();
    private static ConcurrentHashMap<String, ScriptEngine> scriptEngineConcurrentHashMap = new ConcurrentHashMap<>();

    public static Object invoke(String scriptText, String function, Object... objects) throws Exception {
        GroovyClassLoader classLoader = new GroovyClassLoader();
        Class groovyClass = classLoader.parseClass(scriptText);
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


    p
    public static <T> T invokeShell(String scriptText, String function, Object... objects) throws Exception {
        Script script = groovyShell.parse(scriptText);
        return (T) InvokerHelper.invokeMethod(script, function, objects);
    }
}
