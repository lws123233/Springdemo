package com.example.springdemo.controller;


import com.example.springdemo.aop.CheckNull;
import com.example.springdemo.config.MyConfiguration;
import com.example.springdemo.pojo.Data;
import com.example.springdemo.service.DataService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
@RefreshScope
@RestController
@RequestMapping("/datarequest")
public class DataController {


    @Autowired
    DataService dataservice;

    @Value("${name}")
    private String name;

    @Autowired
    private MyConfiguration myConfiguration;

    @Autowired
    ApplicationContext applicationContext;

    @CheckNull
    @PostMapping("/getDatabyid")
    public Data getDataByid(@RequestBody Data date1, HttpServletRequest request) throws InterruptedException {

        List<String> list=new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        String name = applicationContext.getEnvironment().getProperty("name");
        Integer i=-1;

        applicationContext.getEnvironment().getProperty("interceptor.excludeUrl");
        List<String> collect = list.stream().filter(s -> null != s && !s.equals("")).collect(Collectors.toList());
        request.getMethod();
        Data data=dataservice.getData(date1.getId());
        System.out.println(data.getId()+"  "+
                           data.getName()+"  "+
                           data.getPrice());
        Thread.sleep(5000);
        return data;
    }


    public static void main(String[] args) {

        Data data1 =FunctionTest.functionTest1.create("1");
        Data data2 =FunctionTest.functionTest2.create("1");



        String s="FEE_CODE";
        System.out.println(s.toLowerCase());


        SortedSet sortedSet=new TreeSet();
        ListNode head=new ListNode(4);
        ListNode node1=new ListNode(2);
        ListNode node2=new ListNode(1);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(6);
        head.next=node1;
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        //LRU
        //doTest6();

        //链表排序
        //doTest5(head);

        //缺失的第一个正整数
        //doTest4();
        //螺旋矩阵
        //doTest3();
        //矩阵置0
        //doTest2();
        //缺失的第一个正数
        //doTest();

    }



    private static void test7(String... str){
        System.out.println(str);
    }

    private static void doTest6() {
        LRUCache lruCache=new LRUCache(2);
        lruCache.put(1,0);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    private static ListNode doTest5(ListNode head) {

        if(head==null || head .next==null){
            return  head;
        }
        ListNode temp=head;
        int length=0;
        while(temp!=null){
            length++;
            temp=temp.next;
        }

        ListNode res=new ListNode();
        res.next=head;
        for(int i=1;i<length;i=i*2){
            ListNode cur=res.next;
            ListNode pre=res;
            while(cur!=null){
                ListNode h1=cur;
                for(int j=1;j<i;j++){
                    if(cur.next!=null){
                        cur=cur.next;
                    }else{
                        pre.next=h1;
                        break;
                    }
                }

                ListNode h2=cur.next;
                if(h2==null){
                    pre.next=h1;
                    break;
                }
                cur.next=null;
                cur=h2;
                for(int j=1;j<i;j++){
                    if(cur.next!=null){
                        cur=cur.next;
                    }
                }
                ListNode n=cur.next;
                cur.next=null;
                cur=n;
                pre.next=merge(h1,h2);
                while(pre.next!=null){
                    pre=pre.next;
                }
            }
        }
        return  res.next;
    }

    // 下面的排序过程就不解析了，很简单
    public static ListNode merge(ListNode h1, ListNode h2) {
        ListNode front = new ListNode(0);
        ListNode mark = front, mark1 = h1, mark2 = h2;
        while (mark1 != null && mark2 != null) {
            if (mark1.val > mark2.val) {
                mark.next = mark2;
                mark2 = mark2.next;
            } else {
                mark.next = mark1;
                mark1 = mark1.next;
            }
            mark = mark.next;
        }
        if (mark1 != null) mark.next = mark1;
        else if (mark2 != null) mark.next = mark2;
        return front.next;
    }


    private static int doTest4() {
        int[] nums={-1,4,2,1,9,10};
        int temp=0;
        for(int i=0;i<nums.length;i++){
            while(nums[i]>0 && nums[i] !=i+1 && nums[i]<nums.length){
                temp=nums[nums[i]-1];
                nums[nums[i]-1]=nums[i];
                nums[i]=temp;
            }
        }

        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;

    }

    private static void doTest3() {
        int[][] matrix={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> res=new ArrayList<>();
        //行一次能走几格
        int h=matrix[0].length-1;
        //列一次能走几格
        int l=matrix.length-1;
        //初始从零行开始走
        int m=0;
        int n=h*l;
        while(true){
            //从左往右
            for(int i=m;i<=h;i++){
                res.add(matrix[m][i]);
            }
            if(res.size()==n){
                break;
            }
            m++;
            for(int i=m;i<=l;i++){
                res.add(matrix[i][h]);
            }
            if(res.size()==n){
                break;
            }
            h--;
            for(int i=h;i>=m-1;i--){
                res.add(matrix[l][i]);
            }
            if(res.size()==n){
                break;
            }
            l--;
            for(int i=l;i>=m;i--){
                res.add(matrix[i][m-1]);
            }
            if(res.size()==n){
                break;
            }
        }

    }

    private static void doTest2() {
        int[][] matrix={{1,1,1},{1,0,1},{1,1,1}};
        Set<Integer> h=new HashSet<>();
        Set<Integer> l=new HashSet<>();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]==0){
                    h.add(i);
                    l.add(j);
                }
            }
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(h.contains(i) || l.contains(j)){
                    matrix[i][j]=0;
                }
            }
        }
        System.out.println("asd");

    }

    private static int doTest() {
        int[] nums={1,2,0};
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(i==nums.length-1){
                return nums[i]+1;
            }
            if(nums[i]>0&& nums[i]!=1){
                return 1;
            }else if(nums[i]<=0 || nums[i]==nums[i+1] || nums[i]+1==nums[i+1]){
                continue;
            }else {
                return nums[i]+1;
            }
        }
        return  0;
    }

}
