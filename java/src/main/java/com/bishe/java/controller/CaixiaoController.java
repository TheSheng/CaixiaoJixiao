package com.bishe.java.controller;


import com.bishe.java.mapper.CaiXiaoMapper;
import com.bishe.java.mapper.JiXIaoMapper;
import com.bishe.java.mapper.StaffMapper;
import com.bishe.java.pojo.CaiXiao;
import com.bishe.java.pojo.JiXiao;
import com.bishe.java.pojo.Staff;
import com.bishe.java.pojo.User;
import com.bishe.java.service.UserService;
import com.bishe.java.util.PageInfo;
import com.bishe.java.util.ResponseError;
import com.bishe.java.util.ResponseOk;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
@RequestMapping("/caixiao")
@CrossOrigin(value = "*",maxAge = 3600)
public class CaixiaoController {
    @Autowired
    private CaiXiaoMapper caiXiaoMapper;
    @Autowired
    private JiXIaoMapper jiXIaoMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/select")
    public ResponseEntity register(@RequestBody Map map){

        try {

            PageHelper.startPage(MapUtils.getInteger(map,"pageNum"),MapUtils.getInteger(map,"pageSize"));
            List<CaiXiao> caiXiaos = caiXiaoMapper.selectNotCheck();
            return ResponseOk.create(new PageInfo<>(caiXiaos));
        }catch (Exception e){
            return  ResponseError.create("查询失败："+e.getMessage());
        }
    }
    @GetMapping("/shenhe")
    public  ResponseEntity check(@RequestParam("staffId")Integer staffId,@RequestParam("id")Integer id,@RequestParam("check")Boolean check){
        try {
            String hasCheck=check?"审核通过":"审核未通过";
            caiXiaoMapper.updateCheckById(id,hasCheck);
            CaiXiao caiXiao = caiXiaoMapper.selectById(id);
            JiXiao jiXiao = jiXIaoMapper.selectByStaffId(staffId);

            Double score = Double.valueOf(jiXiao.getScore());
            String detail = jiXiao.getDetail();
            String type = jiXiao.getType();
            if(check){
                score+=0.5d;
            }else{
                score-=0.3d;
            }
            jiXiao.setScore(score.toString());
            if(score<2d){
                type="D";
            }else if(score<3d){
                type="C";
            }
            else if(score<4d){
                type="B";
            }else if(score<5d){
                type="A";
            }else{
                type="A+";
            }
            jiXiao.setType(type);
            detail=(detail==null?"":detail)+caiXiao.getText()+","+hasCheck+(check?"得0.5分":"负0.3分")+";";
            jiXiao.setDetail(detail);
            jiXIaoMapper.updateById(jiXiao);

            Staff staff = staffMapper.selectById(staffId);
            redisTemplate.opsForZSet().incrementScore("caixiaoScore",staff.getAddr(),check?0.5:-0.3);

            return  ResponseOk.create("审核成功");
        }catch (Exception e){
            return ResponseError.create(e.getMessage());
        }


    }



}

