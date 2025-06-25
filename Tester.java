package com.subodh.Exception;

class ParentClass {
    public void loadingClass(String className) throws ClassNotFoundException {
        Class.forName(className);
        System.out.println("Class loaded successfully: " + className);
    }
}

class ChildClass extends ParentClass {
    @Override
    public void loadingClass(String className) {
        if (className.isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be empty");
        }
        try {
            super.loadingClass(className);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading class in ChildClass. Converting to unchecked exception.");
            throw new RuntimeException("Unchecked Exception: RuntimeException from ChildClass");
        }
    }
}

public class Tester {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a class name.");
            return;
        }

        ChildClass obj = new ChildClass();
        try {
            obj.loadingClass(args[0]);
        } catch (RuntimeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
