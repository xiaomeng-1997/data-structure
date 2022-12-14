package com.study.stack;

import java.util.Scanner;

/**
 * @author meng
 */
public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack(5);
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
                    Node newNode = new Node(scanner.nextInt());
                    stack.push(newNode);
                    break;
                case "pop":
                    try {
                        System.out.printf("取出来的是：%d\n", stack.pop().getNo());
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

class LinkedStack {
    public int maxSize;

    public int top = -1;

    private final Node head = new Node(-1);

    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return head.getNext() == null;
    }

    public void push(Node newNode) {
        if (isFull()) {
            System.out.println("栈满！！！");
            return;
        }
        if (head.getNext() == null) {
            head.setNext(newNode);
            top++;
            return;
        }
        newNode.setNext(head.getNext());
        head.setNext(newNode);
        top++;
    }

    public Node pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        Node first = head.getNext();
        head.setNext(head.getNext().getNext());
        top--;
        return first;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        Node fist = head.getNext();
        while (fist != null) {
            System.out.printf("数据：%d\n", fist.getNo());
            fist = fist.getNext();
        }
    }
}

class Node {

    private int no;

    private Node next;

    public int getNo() {
        return no;
    }

    public Node(int no) {
        this.no = no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }

}