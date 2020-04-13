package com.bishe.java.util;

import com.bishe.java.mapper.CaiXiaoMapper;
import com.bishe.java.mapper.JiXIaoMapper;
import com.bishe.java.mapper.StaffMapper;
import com.bishe.java.mapper.UserMapper;
import com.bishe.java.pojo.CaiXiao;
import com.bishe.java.pojo.JiXiao;
import com.bishe.java.pojo.Staff;
import com.bishe.java.pojo.User;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName： OrderGenerate
 * @description: 订单生成器
 * @create: 2020-01-21 15:40
 **/
@Component
@EnableScheduling
public class Generate {
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private CaiXiaoMapper caiXiaoMapper;
    @Autowired
    private JiXIaoMapper jiXIaoMapper;
    private  final  ExecutorService executorService = Executors.newCachedThreadPool();
    @Autowired
    private StringRedisTemplate template;
   // @PostConstruct
    public void generateUser(){
        for(int i=0;i<100;i++) {
            executorService.execute(() -> {
                String name = RandomUtil.getChineseName();
                String city = RandomUtil.getAddr();
                Staff staff = new Staff();
                staff.setAddr(city);
                staff.setName(name);
                staff.setPhone(RandomUtil.getTel());
                staff.setSex(RandomUtil.getSex());
                staff.setType(RandomUtil.getType());
                staffMapper.insert(staff);
            });
        }
    }
   // @PostConstruct
    public void  generatePingZheng(){
        for(int i=0;i<100;i++) {
            executorService.execute(()->{
            Staff staff = staffMapper.getRandomOne();
            CaiXiao caiXiao = new CaiXiao();
            caiXiao.setStaffId(staff.getId());
            caiXiao.setTime(RandomUtil.getTime());
            Map good = staffMapper.getRandomGood();
            String text = "于" + caiXiao.getTime() + "，" + staff.getName() + RandomUtil.getCaiXiaoType() + "了" + MapUtils.getString(good, "title") + ",单价为:" + MapUtils.getString(good, "price") + "元";
            caiXiao.setText(text);
            caiXiaoMapper.insert(caiXiao);
            });
        }


    }
//    @PostConstruct
    @Scheduled(cron = "0/10 * * * * *")
    public void  generateJiXIao(){

            executorService.execute(()->{
                Staff staff = staffMapper.getRandomOne();
                CaiXiao caiXiao = new CaiXiao();
                caiXiao.setStaffId(staff.getId());
                caiXiao.setTime(RandomUtil.getTime());
                Map good = staffMapper.getRandomGood();
                String text = "于" + caiXiao.getTime() + "，" + staff.getName() + RandomUtil.getCaiXiaoType() + "了" + MapUtils.getString(good, "title") + ",单价为:" + MapUtils.getString(good, "price") + "元";
                caiXiao.setText(text);
                caiXiaoMapper.insert(caiXiao);
                JiXiao jiXiao = jiXIaoMapper.selectByStaffId(staff.getId());
                Double score = Double.valueOf(jiXiao.getScore());
                String detail = jiXiao.getDetail();
                String type = jiXiao.getType();
                boolean check=RandomUtil.getCheck();
                String hasCheck=check?"审核通过":"审核未通过";
                caiXiaoMapper.updateCheckById(caiXiao.getId(),hasCheck);
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
                template.opsForZSet().incrementScore("caixiaoScore",staff.getAddr(),check?0.5d:-0.3);
            });




    }


}
