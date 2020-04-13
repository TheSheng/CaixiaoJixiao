package com.bishe.java.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.java.mapper.CaiXiaoMapper;
import com.bishe.java.mapper.StaffMapper;
import com.bishe.java.pojo.CaiXiao;
import com.bishe.java.pojo.Staff;
import com.bishe.java.service.CaiXiaoService;
import com.bishe.java.service.StaffService;
import org.springframework.stereotype.Service;

/**
 * @ClassName： StaffServiceImpl
 * @description:
 * @author: lisheng
 * @create: 2020-02-09 12:54
 **/
@Service
public class CaiXiaoServiceImpl extends ServiceImpl<CaiXiaoMapper, CaiXiao> implements CaiXiaoService {
}
