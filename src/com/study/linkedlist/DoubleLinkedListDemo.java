package com.study.linkedlist;

import java.time.temporal.Temporal;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 node3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 node4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(node1);
//        doubleLinkedList.add(node2);
//        doubleLinkedList.add(node3);
//        doubleLinkedList.add(node4);
        doubleLinkedList.addByOrder(node2);
        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node4);
        doubleLinkedList.addByOrder(node3);

        doubleLinkedList.list();

        HeroNode2 node5 = new HeroNode2(3, "入云龙", "公孙胜");
        doubleLinkedList.update(node5);
        System.out.println("修改后的数据：");
        doubleLinkedList.list();

        doubleLinkedList.del(2);
        System.out.println("删除后的数据：");
        doubleLinkedList.list();
    }
}


class DoubleLinkedList {

    /**
     * 初始化头节点
     */
    private final HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 添加节点
     *
     * @param heroNode 节点
     */
    public void add(HeroNode2 heroNode) {

        HeroNode2 temp = head;
        while (temp.next != null) {
            // 找到最后一个节点
            // 没找到, 后移一下
            temp = temp.next;
        }
        // 最后一个节点指向新加入的节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 顺序添加，并且不能已存在编号不能覆盖
     */
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        // 添加一个标识flag,编号是否存在
        boolean flag = Boolean.FALSE;

        // 找插入节点位置
        while (temp.next != null) {
            // 位置找到了
            if (temp.next.no > heroNode.no) {
                break;
                // 编号已存在
            } else if (temp.next.no == heroNode.no) {
                flag = Boolean.TRUE;
                break;
            }
            // 后移
            temp = temp.next;
        }

        // 插入节点
        if (flag) {
            System.out.printf("编号%d已存在，不能再添加\n", heroNode.no);
        } else {
            // next
            heroNode.next = temp.next;
            temp.next = heroNode;

            // pre
            heroNode.pre = temp;
            if (heroNode.next != null) {
                heroNode.next.pre = heroNode;
            }

        }


    }


    /**
     * 更新节点信息
     *
     * @param newHeroNode 节点
     */
    public void update(HeroNode2 newHeroNode) {
        HeroNode2 temp = head;
        // 判断是否可以修改节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == newHeroNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }
        if (flag) {
            temp.next.name = newHeroNode.name;
            temp.next.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("更新节点 %d 不存在\n", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     *
     * @param no
     */
    public void del(int no) {
        if (head.next == null) {
            return;
        }
        HeroNode2 temp = head.next;
        // 判断节点是否可以删除
        boolean flag = false;
        while (temp != null) {
            // 判断节点是否存在
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 可以删除
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("删除节点 %d 失败\n", no);
        }
    }
    /**
     * 遍历链表
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        // 头节点的下一个节点，即第一个节点
        HeroNode2 temp = head.next;
        while (temp != null) {
            // 判断到节点是不是空的
            System.out.println(temp);
            // 将next后移
            temp = temp.next;
        }
    }

}

class HeroNode2 {
    public int no;

    public String name;

    public String nickName;

    /**
     * 指向下一个节点
     */
    public HeroNode2 next;

    /**
     * 指向上一个节点
     */
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}