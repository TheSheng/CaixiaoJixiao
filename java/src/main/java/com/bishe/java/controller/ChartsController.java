package com.bishe.java.controller;

import com.bishe.java.mapper.StaffMapper;
import com.bishe.java.util.ResponseOk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName： ChartsController
 * @description: 主页图标求数据
 * @author: lisheng
 * @create: 2020-02-18 15:09
 **/
@RestController
@RequestMapping("/charts")
@CrossOrigin(value = "*",maxAge = 3600)
public class ChartsController {
    @Autowired
    StaffMapper staffMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    @GetMapping("/getSex")
    public ResponseEntity getSex(){
        List<Integer> sex = staffMapper.getSex();
        return ResponseOk.create(sex);

    }
    @GetMapping("/getType")
    public ResponseEntity getType(){
        List<Integer> type = staffMapper.getType();
        return ResponseOk.create(type);

    }
    @GetMapping("/getJiXIaoZhanBi")
    public ResponseEntity getJiXiaoZhanBi(){
        List<Map> type = staffMapper.getJiXIaoZhanBi();
        List<List> rs=new ArrayList<>();
        List<String> key=new ArrayList<>();
        List<Integer> value=new ArrayList<>();

        type.forEach(x->{
            key.add(x.get("type").toString());
            value.add(Integer.parseInt(x.get("value").toString()));
        });
        rs.add(key);
        rs.add(value);
        return ResponseOk.create(rs);

    }
    @GetMapping("/getShenHe")
    public ResponseEntity getShenHe(){
        List<Map> type = staffMapper.getShenhe();
        List<List> rs=new ArrayList<>();
        List<String> key=new ArrayList<>();
        List<Integer> value=new ArrayList<>();

        type.forEach(x->{
            key.add(x.get("type").toString());
            value.add(Integer.parseInt(x.get("value").toString()));
        });
        rs.add(key);
        rs.add(value);
        return ResponseOk.create(rs);

    }
    @GetMapping("/getAddr")
    public  ResponseEntity getAddr(){
        Set<ZSetOperations.TypedTuple<String>> caixiaoScore = redisTemplate.opsForZSet().reverseRangeWithScores("caixiaoScore", 0, 11);
        List<List> rs=new ArrayList<>();
        List<String> key=new ArrayList<>();
        List<Double> value=new ArrayList<>();
        caixiaoScore.forEach(x->{
            key.add(x.getValue());
            value.add(x.getScore());
        });
        rs.add(key);
        rs.add(value);
        return  ResponseOk.create(rs);
    }


}
