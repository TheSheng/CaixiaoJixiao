package com.bishe.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bishe.java.pojo.CaiXiao;
import com.bishe.java.pojo.JiXiao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JiXIaoMapper extends BaseMapper<JiXiao> {
    void deleteByStaffId(@Param("staffId") Integer staffId);
    JiXiao selectByStaffId(@Param("staffId")Integer staffId);
    List<JiXiao> selectAll();
    List<JiXiao> selectByMaps(@Param("map") Map map);


}
