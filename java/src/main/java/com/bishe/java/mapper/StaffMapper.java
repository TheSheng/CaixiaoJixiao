package com.bishe.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bishe.java.pojo.Staff;
import com.bishe.java.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StaffMapper extends BaseMapper<Staff> {
    Staff getRandomOne();
    Map getRandomGood();
    List<Integer> selectId();
    List<Staff> selectByMaps(@Param("map") Map map);
    List<Integer> getSex();
    List<Integer> getType();
    List<Map> getShenhe();
    List<Map> getJiXIaoZhanBi();
    void deleteByStaffId(@Param("staffId")Integer StaffId);


}
