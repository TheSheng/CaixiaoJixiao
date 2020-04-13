package com.bishe.java.controller;


import com.bishe.java.pojo.Staff;
import com.bishe.java.pojo.User;
import com.bishe.java.service.StaffService;
import com.bishe.java.service.UserService;
import com.bishe.java.util.PageInfo;
import com.bishe.java.util.ResponseError;
import com.bishe.java.util.ResponseOk;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/staff")
@CrossOrigin(value = "*",maxAge = 3600)
public class StaffController {
    @Autowired
    private StaffService staffService;
    @GetMapping("/getOne")
    public  ResponseEntity selectOne(@RequestParam("id") Integer staffId){
        try {
            Staff byId = staffService.getById(staffId);
            return  ResponseOk.create(byId);
        }catch (Exception e){
            return  ResponseError.create(e.getMessage());
        }

    }

   @PostMapping("/add")
    public ResponseEntity addStaff(@RequestBody Staff staff){
       try {

           boolean save = staffService.add(staff);
           return save ? ResponseOk.create("新增员工成功") : ResponseError.create("新增员工失败");
       }catch (Exception e){
           return  ResponseError.create(e.getMessage());
       }
   }
    @PostMapping("/update")
    public ResponseEntity updateStaff(@RequestBody Staff staff){
        try {

            boolean save = staffService.updateById(staff);
            return save ? ResponseOk.create("修改员工信息成功") : ResponseError.create("新增员工失败");
        }catch (Exception e){
            return  ResponseError.create(e.getMessage());
        }
    }
    @GetMapping("/delete")
    public ResponseEntity deleteStaff(@RequestParam("id")Integer id){
       try {
            staffService.deleteall(id);
            return ResponseOk.create("删除成功");
       }catch (Exception e){
           return  ResponseError.create(e.getMessage());
       }

    }
    @PostMapping("/select")
    public ResponseEntity addStaff(@RequestBody Map map){
        try {
            PageHelper.startPage(MapUtils.getInteger(map,"pageNum"),MapUtils.getInteger(map,"pageSize"));
            List<Staff> list = staffService.selectByMap(map);
            return  ResponseOk.create(new PageInfo<>(list));
        }catch (Exception e){
            return  ResponseError.create(e.getMessage());
        }
    }


}

