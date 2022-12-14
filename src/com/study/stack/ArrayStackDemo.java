package com.study.stack;

import java.util.Scanner;

/**
 * @author meng
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        // 测试栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("show：显示所有数据");
            System.out.println("push：存放数据");
            System.out.println("pop：取出数据");
            System.out.println("exit：退出程序");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.printf("取出来的是：%d\n", stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    scanner.close();
                    break;
            }
        }
        System.out.println("程序结束 ");

    }
}

/**
 * 用数组模拟栈
 */
class ArrayStack {
    /**
     * 栈的容量
     */
    public int maxSize;
    /**
     * 栈的数据
     */
    public int[] stack;
    /**
     * 栈顶
     */
    public int top = -1;

    /**
     * 初始化栈
     *
     * @param maxSize 栈的大小
     */
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈满
     *
     * @return true -- 满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     *
     * @return true -- 空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法添加！！");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     *
     * @return value
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，无法取出数据！！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无法取出数据！！");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

}
