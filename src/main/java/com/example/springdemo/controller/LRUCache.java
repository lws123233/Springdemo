package com.example.springdemo.controller;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    int capacity=0;
    Map<Integer,Node> map =new HashMap<Integer,Node>();
    Node head=new Node();
    Node tail=new Node();

     public class Node {
        int key;
        int value;
        Node pre;
        Node last;
        public Node(){
        }
        public Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity=capacity;
        head.last=tail;
        tail.pre=head;
    }

    public int get(int key) {
        Node r=map.get(key);
        //不为null
        if(r!=null){
            move2Head(head,r,tail);
            //返回值
            return r.value;
        }
        //为null返回
        return -1;
    }

    public void put(int key, int value) {
        Node t=map.get(key);
        //更新值
        if(t!=null){
            t.value=value;
            move2Head(head,t,tail);
            return;
        }
        Node n=new Node();
        //没有则是添加值,创建新节点并加在队头后面
        n.key=key;
        n.value=value;
        //n的前驱指向head
        n.pre=head;
        //n的后驱指向head的后驱节点
        n.last=head.last;
        //head的后驱节点的前驱指向n
        head.last.pre=n;
        //head指向n
        head.last=n;
        map.put(key,n);
        //如果超出容量，移除队尾节点
        capacity--;
        if(capacity<0){
            map.remove(tail.pre.key);
            //移除队尾的前驱节点的后驱指针
            tail.pre.last=null;
            //队尾的前驱节点的前驱节点的后驱指向队尾
            tail.pre.pre.last=tail;
            //队尾前驱指向前驱节点的前驱
            tail.pre=tail.pre.pre;
            capacity++;
        }


    }

    private void move2Head(Node head,Node r,Node tail){
         if(tail.pre.pre!=head){
             //如果该节点为队尾节点，让队尾指向r的前驱节点
             tail=tail.pre;
         }
        //将该节点移到双向链表表头
        //r的前驱节点指向r的后驱节点
        r.pre.last=r.last;
        //r的后驱节点指向r的前驱节点
        r.last.pre=r.pre;
        //r指向head的后驱节点
        r.last=head.last;
        //head的后驱节点指向r
        head.last.pre=r;
        //head的后驱指向r
        head.last=r;
        //r的前驱指向head
        r.pre=head;
    }
}

