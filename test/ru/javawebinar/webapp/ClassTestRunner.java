package ru.javawebinar.webapp;


import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class ClassTestRunner {
    private Class testClass;
    private List<Method> beforeMethods;
    private List<Method> testMethods;

    public ClassTestRunner(String className) throws Exception {
        testClass = Class.forName(className);
        beforeMethods = getMethodsByAnnotation(Before.class);
        testMethods = getMethodsByAnnotation(Test.class);
    }

    public static void main(String[] args) throws Exception {
        new ClassTestRunner("ru.javawebinar.webapp.storage.ArrayStorageTest").runAll();
        new ClassTestRunner("ru.javawebinar.webapp.storage.SortedArrayStorageTest").runAll();
    }

    public void runAll() {
        int testsFailed = 0;
        int testsNumber = 0;
        System.out.println("-------------------------------------------");
        System.out.println("RUNNING TESTS FOR " + testClass.toString());
        for (Method testMethod : testMethods) {
            testsNumber++;
            if (!runTest(testMethod)) {
                testsFailed++;
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println(String.format("Failed %d from %d tests", testsFailed, testsNumber));
        System.out.println("-------------------------------------------");
    }

    public boolean runTest(Method testMethod) {
        Object instance;
        try {
            instance = testClass.newInstance();
            for (Method method : beforeMethods) {
                method.invoke(instance);
            }
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new IllegalStateException("Wrong test syntax");
        }

        try {
            testMethod.invoke(instance);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Wrong test syntax");
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            Class<? extends Throwable> expectedClass = testMethod.getAnnotation(Test.class).expected();
//          e instanceof expectedClass
            return targetException.getClass().isAssignableFrom(expectedClass);
        }
        return true;
    }

    private List<Method> getMethodsByAnnotation(Class annotationClass) {
        List<Method> annotatedMethods = new LinkedList<>();
        for (Method method : testClass.getMethods()) {
            if (method.getAnnotation(annotationClass) != null) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}
