package com.example.springdemo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.springdemo.aop.CheckNull;
import com.example.springdemo.config.MyConfiguration;
import com.example.springdemo.pojo.Data;
import com.example.springdemo.service.DataService;
import io.netty.util.internal.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
@RefreshScope
@RestController
@RequestMapping("/test")
public class DataController {


    @Autowired
    DataService dataservice;

    @Value("${name}")
    private String name;

    @Resource
    ConfigurableApplicationContext context;

    @Value("${lws.config.name}")
    private String configName;

    @Autowired
    private MyConfiguration myConfiguration;

    @Autowired
    ApplicationContext applicationContext;

    RestTemplate restTemplate=new RestTemplate();

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

    @PostMapping("/getConfigName")
    public ResponseEntity<String> getConfigName(@RequestBody Map map){
         return restTemplate.exchange("http://otws19.zicp.vip:7062/cmdb/work/table!add"
                , HttpMethod.POST
                , new HttpEntity<>(JSONObject.toJSON(map))
                , String.class);
    }

    @PostMapping("/publishEnvironmentChange")
    public void publishEnvironmentChange(){
        Set set=new HashSet<String>();
        set.add("lws.config.name");
//        context.publishEvent(new ApplicationEnvironmentPreparedEvent());
    }



    @PostMapping("/readExcel")
    public Map readExcel(@RequestParam(value = "file") MultipartFile multipartFile){
        Workbook wb = null;
        Map map=new HashMap<>();
        //根据key构建sql

        Map res=new HashMap<>();
        int sucessCount=0;
        String str="";
        res.put("error",str);
        try {
            try {
                wb = new HSSFWorkbook(new POIFSFileSystem(multipartFile.getInputStream()));
            } catch (Exception e) {
                wb = new XSSFWorkbook(multipartFile.getInputStream());        //XSSF不能读取Excel2003以前（包括2003）的版本
            }

            Sheet sheet = wb.getSheetAt(0);

            if (sheet == null) {
                return res;
            }
            Map map1=new HashMap<>();
            map1.put("text","");
            map1.put("extend","");
            Map maphead=new HashMap<>();
            maphead.put("cmd",10000);
            maphead.put("ver","1.0");
            maphead.put("token","d7140cd0-693d-401c-ace6-763a80ebc42b");
            Map req=new HashMap<>();
            //获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            //获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();

            map.put("0d51a95d-7955-483d-8086-d84250fa5aea","");
            map.put("6af5144c-11b3-43f9-b13a-ea146bccdfce","");

            map.put("3213a33d-0f50-4166-ba86-cfd4aa04194b","");
            map.put("da3fe01d-ab0a-4379-89d9-c4bb76ef28d6","");
            map.put("7a889d2a-61a3-48cb-8f9b-74c590786222","");
            map.put("e6045e3b-1af5-4caa-92ed-d88c3511db74","");
            map.put("1d382ba4-040c-473c-b60c-643b91651fb9",map1);
            map.put("cf7be03b-e526-4e9b-9007-05755e62086e","");
            map.put("a43fa2d0-c744-48e4-a359-8599fbab0fe6",map1);
            map.put("9044b7f5-6fde-4926-85fe-c9ac22e09b5c",map1);
            map.put("fcd29de0-c9fc-48b6-8699-fa433db779bc",map1);
            map.put("b42197f1-26c6-4cd2-b4c0-dada5b191b03",map1);
            map.put("9eb8bb58-e11a-40da-9a9c-01996b9ed5b3",map1);
            map.put("14af773a-a07b-44f3-940a-6afd20a1cc44","");
            map.put("963e227e-a89f-43bf-a0c6-cd3b00735f06","");
            map.put("f4a29b58-2981-42ad-9005-1c78b2a36ca0",map1);
            map.put("4c523156-9d54-487e-bcc2-40ff31ea9596","");



            Map conMap=new HashMap<>();
            conMap.put("category_id",1255);
            req.put("con",conMap);
            req.put("head",maphead);

            //循环除了第一行的所有行
            for (int rowNum = firstRowNum+1; rowNum <= lastRowNum; rowNum++) {
                //获得当前行
                Row row = sheet.getRow(rowNum);
                if (row == null
                ) {
                    return res;
                }

                if(rowNum>=300){
                    map.put("9289d62e-b533-4c0d-9874-aae0fc8598c8", row.getCell(1).getStringCellValue());
                    map.put("effda073-7b7f-40fc-8b58-a28b0b71ccea","遗迹");
                    map.put("13dce3b2-6df7-417d-aa66-5b2fd702941a",row.getCell(3).getStringCellValue());
                    map.put("b7727a27-79ac-4157-a90f-e49109af0cac","");
                    map.put("c9502bcc-adb6-40ae-a86b-7b5ad5d0f18c","");
                    if( row.getCell(2).getStringCellValue().contains("s")){
                        map.put("c9502bcc-adb6-40ae-a86b-7b5ad5d0f18c",row.getCell(2).getStringCellValue());
                    }else if(row.getCell(2).getStringCellValue().contains("未知")){

                    }else{
                        map.put("b7727a27-79ac-4157-a90f-e49109af0cac",row.getCell(2).getStringCellValue());
                    }

                    map.put("597a50d6-54c9-4b13-978b-7ee52c31f343","中国");
                    map.put("764ae3e1-40ff-4e0c-b1a7-78d125792333","上海");
                    map.put("022f11d2-a099-4d43-9c0f-0cb432f546ec","上海");
                    map.put("b41a27e2-a88b-480f-9393-20d2251eceda",row.getCell(4).getStringCellValue());
                    map.put("f55d7f82-5906-4b19-80ba-161a0bcf833d",row.getCell(5).getStringCellValue().substring(6));
                    map.put("de50721c-0ff6-4e7e-9c25-70cc852a5028",row.getCell(5).getStringCellValue());

                    conMap.put("map",map);
                    ResponseEntity<String> exchange = restTemplate.exchange("http://otws19.zicp.vip:7062/cmdb/work/table!add"
                            , HttpMethod.POST
                            , new HttpEntity<>(JSONObject.toJSON(req))
                            , String.class);
                    if(exchange.getStatusCode().equals(HttpStatus.OK)){
                        sucessCount++;
                        res.put("count",sucessCount);
                    }else{
                        res.put("error",res.get("error")+""+row);
                    }
                }

            }
            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
                multipartFile.getInputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        String te="";
        System.out.println(te.length());
        String str="上海,上海人,上海男人,上海人民,上海区,上海市,上海市人民政府";
        str.substring(1,2);
        List a=new ArrayList<>();
        a.remove(a.size()-1);

          Trie trie=new Trie();
//          for(String s : str.split(",")){
//              trie.insert(s);
//          }
//
//        Scanner scanner=new Scanner(System.in);
//        System.out.println(trie.searchPre(scanner.nextLine()));


//        String key="上海市静安区光复路195号";
//
//
//
//        FunctionTest.choose.create(key);
//
//        String substring = key.substring(6);
//
//        System.out.println(substring);
//
//        String s="FEE_CODE";
//        System.out.println(s.toLowerCase());
//
//
//        SortedSet sortedSet=new TreeSet();
//        ListNode head=new ListNode(4);
//        ListNode node1=new ListNode(2);
//        ListNode node2=new ListNode(1);
//        ListNode node3=new ListNode(3);
//        ListNode node4=new ListNode(6);
//        head.next=node1;
//        node1.next=node2;
//        node2.next=node3;
//        node3.next=node4;
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
