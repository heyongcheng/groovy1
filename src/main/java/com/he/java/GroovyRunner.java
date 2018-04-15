package com.he.java;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author heyc
 * @date 2018/4/12 17:31
 */
public class GroovyRunner {

    private static String NEW_LINE = System.getProperty("line.separator");

    public static void main(String[] args) {
        invoke();
    }


    public static void invoke() {
        try {
            GroovyClassLoader gcl = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
            String groovyScript = read("E:\\groovy\\Groovy1\\src\\main\\java\\com\\he\\groovy\\StepFun.groovy");
            System.out.println("script:" + groovyScript);
            System.out.println("**************************************************");
            final Class groovyClass = gcl.parseClass(groovyScript, "StepFun");
            final GroovyObject groovyObject = (GroovyObject)groovyClass.newInstance();
            final Object result1 = groovyObject.invokeMethod("step", 4);
            System.out.println(result1);
            final Object result2 = groovyObject.invokeMethod("step", 10);
            System.out.println(result2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     * @param path
     * @return
     */
    public static String read(String path) {
        try {
            StringBuffer sb = new StringBuffer();
            FileReader fr = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(NEW_LINE);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
