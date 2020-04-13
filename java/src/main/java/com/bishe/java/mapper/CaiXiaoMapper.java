package com.bishe.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bishe.java.pojo.CaiXiao;
import com.bishe.java.pojo.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaiXiaoMapper extends BaseMapper<CaiXiao> {
   List<CaiXiao> selectNotCheck();
   void  updateCheckById(@Param("id") Integer id,@Param("check")String check);

}
