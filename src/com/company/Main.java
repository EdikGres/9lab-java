package com.company;

public class Main {

    public static void main(String[] args) {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();
        resourceA.resourceB = resourceB;
        resourceB.resourceA = resourceA;
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        myThread1.resourceA = resourceA;
        myThread2.resourceB = resourceB;
        myThread1.start();
        myThread2.start();
    }
}

class MyThread1 extends Thread{
    ResourceA resourceA;

    @Override
    public void run() {
        System.out.println(resourceA.getI());
    }
}
class MyThread2 extends Thread{
    ResourceB resourceB;

    @Override
    public void run() {
        System.out.println(resourceB.getI());
    }
}


class ResourceA{
    ResourceB resourceB;
    public synchronized int getI(){
        //удивительно, что компилятор жабы не оптимизирует цикл)0))0))
//        for (int i = 0; i < 10000; i++){
////            System.out.println(i);
//        }
        return resourceB.returnI();
    }
    public synchronized int returnI(){

        return 100;
    }

}

class ResourceB{
    ResourceA resourceA;
    public synchronized int getI(){
//        for (int i = 0; i < 10000; i++){
////            System.out.println(i);
//        }
        return resourceA.returnI();
    }
    public synchronized int returnI(){

        return 200;
    }

}
