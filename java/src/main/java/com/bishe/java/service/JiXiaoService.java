package com.bishe.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.java.pojo.CaiXiao;
import com.bishe.java.pojo.JiXiao;

import java.util.List;
import java.util.Map;

public interface JiXiaoService extends IService<JiXiao> {
    List<JiXiao> selectByMaps(Map map);
}
