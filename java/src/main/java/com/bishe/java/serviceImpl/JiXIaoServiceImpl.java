package com.bishe.java.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.java.mapper.JiXIaoMapper;
import com.bishe.java.mapper.StaffMapper;
import com.bishe.java.pojo.JiXiao;
import com.bishe.java.pojo.Staff;
import com.bishe.java.service.JiXiaoService;
import com.bishe.java.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName： StaffServiceImpl
 * @description:
 * @author: lisheng
 * @create: 2020-02-09 12:54
 **/
@Service
public class JiXIaoServiceImpl extends ServiceImpl<JiXIaoMapper, JiXiao> implements JiXiaoService {
    @Autowired
    JiXIaoMapper jiXIaoMapper;
    @Override
    public List<JiXiao> selectByMaps(Map map) {
        List<JiXiao> jiXiaos = jiXIaoMapper.selectByMaps(map);
        return  jiXiaos;
    }
}
