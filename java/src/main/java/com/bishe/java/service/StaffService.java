package com.bishe.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bishe.java.pojo.Staff;

import java.util.List;
import java.util.Map;

public interface StaffService extends IService<Staff> {
    void deleteall(Integer staffId);
    Boolean  add(Staff staff);
    List<Staff> selectByMap(Map map);
}
