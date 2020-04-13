package com.bishe.java.controller;


import com.bishe.java.mapper.CaiXiaoMapper;
import com.bishe.java.mapper.JiXIaoMapper;
import com.bishe.java.pojo.CaiXiao;
import com.bishe.java.pojo.JiXiao;
import com.bishe.java.service.JiXiaoService;
import com.bishe.java.util.PageInfo;
import com.bishe.java.util.ResponseError;
import com.bishe.java.util.ResponseOk;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2020-01-13
 */
@RestController
@RequestMapping("/jixiao")
@CrossOrigin(value = "*",maxAge = 3600)
public class JiXiaoController {
    @Autowired
    private JiXiaoService jiXiaoService;

    @PostMapping("/select")
    public ResponseEntity register(@RequestBody Map map){

        try {

            PageHelper.startPage(MapUtils.getInteger(map,"pageNum"),MapUtils.getInteger(map,"pageSize"));
            List<JiXiao> jiXiaos = jiXiaoService.selectByMaps(map);
            return ResponseOk.create(new PageInfo<>(jiXiaos));
        }catch (Exception e){
            return  ResponseError.create("查询失败："+e.getMessage());
        }
    }


}

