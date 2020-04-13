package com.bishe.java.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.java.mapper.JiXIaoMapper;
import com.bishe.java.mapper.StaffMapper;
import com.bishe.java.pojo.JiXiao;
import com.bishe.java.pojo.Staff;
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
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {
    @Autowired
    StaffMapper staffMapper;
    @Autowired
    JiXIaoMapper jiXIaoMapper;
    @Override
    public void deleteall(Integer staffId) {
        this.removeById(staffId);
        staffMapper.deleteByStaffId(staffId);
        jiXIaoMapper.deleteByStaffId(staffId);


    }

    @Override
    public Boolean add(Staff staff) {
        boolean save = this.save(staff);
        JiXiao jiXiao=new JiXiao();
        jiXiao.setStaffId(staff.getId());
        jiXIaoMapper.insert(jiXiao);
        return  save;

    }

    @Override
    public List<Staff> selectByMap(Map map) {
        List list = staffMapper.selectByMaps(map);
        return list;
    }
}
