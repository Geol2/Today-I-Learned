package InstanceofDemo;
class Parent {}
class Child extends Parent implements MyInterface {}
interface MyInterface {}

public class InstanceofDemo {
    public static void main(String[] args) {

        Parent obj1 = new Parent();
        Parent obj2 = new Child();
        Child obj3 = new Child();
        // Child obj3 = new Parent(); error

        System.out.println("obj1 instanceof Parent: " + (obj1 instanceof Parent));
        System.out.println("obj1 instanceof Child: " + (obj1 instanceof Child));
        System.out.println("obj1 instanceof MyInterface: " + (obj1 instanceof MyInterface));

        System.out.println("obj2 instanceof Parent: " + (obj2 instanceof Parent));
        System.out.println("obj2 instanceof Child: " + (obj2 instanceof Child));
        System.out.println("obj2 instanceof MyInterface: " + (obj2 instanceof MyInterface));

        System.out.println("obj3 instanceof Parent: " + (obj3 instanceof Parent));
        System.out.println("obj3 instanceof Child: " + (obj3 instanceof Child));
        System.out.println("obj3 instanceof MyInterface: " + (obj3 instanceof MyInterface));
    }
}