package com.example.springdemo.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo.pojo.Data;
import com.example.springdemo.service.DataService;
import com.example.springdemo.util.ExecutorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RefreshScope
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    @Value("${name}")
    private String name;

    @Autowired
    @Value("${testname}")
    private String testname;
    @Autowired
    DataService dataservice;

    @Autowired
    BaseMapper<Data> baseMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/testRefresh")
    public void testRefresh() throws InterruptedException {

           log.info("测试1"+name);
           log.info("测试2"+testname);
           Thread.sleep(5000);

    }


    @GetMapping("/tesMybatisInterceptor")
    public void tesMybatisInterceptor() throws InterruptedException {
        Data data = dataservice.getData("2");
        System.out.println(data);
    }

    @GetMapping("/testExecutor")
    public void testExecutor() {
        log.info("testExecutor-----------------start");
        new ExecutorUtil("executorTest");
        log.info("testExecutor-----------------End");
    }

    @GetMapping("/interceptor/testInterceptor")
    public void testInterceptor() {
        log.info("testInterceptor-----------------start");
    }

    @PostMapping("/readExcel1")
    public Map readExcel1(@RequestParam(value = "file") MultipartFile multipartFile){

        RestTemplate restTemplate=new RestTemplate();
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

            map.put("3f919b38-e9fa-4817-b0c6-550218a3b6b7","");
            map.put("9818905d-6ed9-444a-9802-8d1458780690","");

            map.put("050b0032-0811-46be-a4e4-70aae13dbf6d","");
            map.put("88a7ad35-c281-4e68-814d-753f20a507e8","");
            map.put("b7860fb0-b175-43ce-86e2-42a6fd495db0","");
            map.put("19b46abe-bf3e-4b6a-b46a-70322a476d57",map1);
            map.put("5ae05d56-700f-47f1-ab7b-456ffb111ea8","");
            map.put("0b9ca036-a24f-4a41-bfcb-3e5910158c9b",map1);
            map.put("9326d6fc-c5b8-4ef6-ac5a-38d9c779f20b","");
            map.put("5a82ff0c-e5ea-4230-a8c9-8e39ae42c13c","");
            map.put("d3bea31c-04a0-4222-864e-2bfc14977d33","");
            map.put("0cd3ab3c-4bbb-451d-b9f9-cfa8b3f09f6b","");
            map.put("c02ff02a-f94a-4197-913d-22882bf5ab13","");
            map.put("9ecb212a-2016-4c09-8197-bfe07d3aeb3b","");
            map.put("ddf7381b-98ec-4c84-9f35-70f4d0539ca0","");
            map.put("63a578c0-ff99-4936-906c-006166a256ed","");
            map.put("f2add351-9632-4fbe-8e0d-e5ba6b75d9bb","");
            map.put("36ef431e-c2b9-4386-b2b5-699f81109ee7","");
            map.put("76de4d47-12b5-4306-9d86-936b1f3d6a9c","");
            map.put("fb2139bd-0d8b-4986-8037-7a137a119c39","");
            map.put("7fa95144-3c0f-4480-b12a-726f41e97f7f","");
            map.put("cbcfe187-39a2-4421-be8f-4889c2418f53","");
            map.put("003c62dc-5c5f-4b53-afd4-b0cf419e21b3","");
            map.put("bf4388a1-f116-4010-8d60-2896a958ab1b",map1);
            map.put("9d254cea-2ec8-4102-b0df-dc176fb8e58f","");
            map.put("8a4a4ee2-81b9-4584-9fb7-9c3797e4f9fc",map1);
            map.put("3888e7af-bde8-479d-ac62-e4b9721afccc",map1);
            map.put("ab1f9a80-f4bc-4a09-9c2a-2bb49163a465",map1);
            map.put("62c89184-7a9f-47cb-a8b1-6dc7e325cc9f",map1);
            map.put("b3449804-60ca-4f4a-8114-302d506ead95",map1);
            map.put("08cf5c5b-4841-4304-8743-626393254474","");
            map.put("fa3c1432-5865-4d36-a2b1-28ba6bc7d3f0","");
            map.put("ed55b581-84da-4eb2-9e45-1b1054bfcb9a",map1);
            map.put("301b94db-4ff5-4077-bcda-eac7b36b8daa","");

            Map conMap=new HashMap<>();
            conMap.put("category_id",1249);
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

                if( (rowNum>=310)){
                    map.put("01aef358-4146-4f10-8d2a-acf41bf7f356", row.getCell(1).getStringCellValue());//名称
                    //map.put("effda073-7b7f-40fc-8b58-a28b0b71ccea","遗迹");
                    //map.put("13dce3b2-6df7-417d-aa66-5b2fd702941a",row.getCell(3).getStringCellValue());
                    map.put("1daf110c-3379-42b8-b65e-e1787148b554","");
                    map.put("96aa71ed-c460-49f2-be59-d3937c43d8c0","");
                    if( row.getCell(2).getStringCellValue().contains("s")){
                        map.put("1daf110c-3379-42b8-b65e-e1787148b554",row.getCell(2).getStringCellValue());
                    }else if( row.getCell(2).getStringCellValue().contains("未知")){
                    }else{
                        map.put("96aa71ed-c460-49f2-be59-d3937c43d8c0",row.getCell(2).getStringCellValue());
                    }

                    map.put("165bf0b2-129b-4fda-9ba4-c6b26d60f63c","中国");
                    map.put("c5a35da7-b8c2-4f44-bfda-f786c3646526","上海");
                    map.put("90c6c11f-63bc-478f-83ea-fec81d23be9a","上海");
                    map.put("4e71dadd-eea8-48c8-a154-75539ab497c2",row.getCell(4).getStringCellValue());
                    map.put("3fb1ae17-f561-4f66-852d-d792cd64eb14",row.getCell(5).getStringCellValue().substring(6));
                    map.put("86ec9d7b-dc86-4c44-9870-b9506a7fb0b2",row.getCell(5).getStringCellValue());

                    conMap.put("map",map);
                    ResponseEntity<String> exchange = restTemplate.exchange("http://otws19.zicp.vip:7062/cmdb/work/table!add"
                            , HttpMethod.POST
                            , new HttpEntity<>(JSONObject.toJSON(req))
                            , String.class);
                    if(exchange.getStatusCode().equals(HttpStatus.OK)){
                        sucessCount++;
                    }else{
                        res.put("error",res.get("error")+""+row);
                    }
                    res.put("count",sucessCount);


                    map.put("1daf110c-3379-42b8-b65e-e1787148b554","");
                    map.put("96aa71ed-c460-49f2-be59-d3937c43d8c0","");
                    map.put("01aef358-4146-4f10-8d2a-acf41bf7f356", row.getCell(3).getStringCellValue());//名称
                    conMap.put("map",map);
                    ResponseEntity<String> exchange2 = restTemplate.exchange("http://otws19.zicp.vip:7062/cmdb/work/table!add"
                            , HttpMethod.POST
                            , new HttpEntity<>(JSONObject.toJSON(req))
                            , String.class);
                    if(exchange2.getStatusCode().equals(HttpStatus.OK)){
                        sucessCount++;
                    }else{
                        res.put("error",res.get("error")+""+row);
                    }
                    res.put("count",sucessCount);
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
}
