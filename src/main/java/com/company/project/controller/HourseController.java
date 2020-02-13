package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Hourse;
import com.company.project.service.HourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/02/11.
*/
@RestController
@RequestMapping("/hourse")
public class HourseController {
    @Resource
    private HourseService hourseService;

    @PostMapping("/add")
    public Result add(Hourse hourse) {
        hourseService.save(hourse);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        hourseService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Hourse hourse) {
        hourseService.update(hourse);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Hourse hourse = hourseService.findById(id);
        return ResultGenerator.genSuccessResult(hourse);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Hourse> list = hourseService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    @PostMapping("/identityHouse")
    public Result identityHouse( @RequestParam() Long houseaddr, @RequestParam() String paltaddr,
    		@RequestParam() String name, @RequestParam() String idCard, @RequestParam() String phone) {
        List<Hourse> list = hourseService.findHouse(name, phone, idCard);
        boolean flag = false;
        if(list!=null) {
        	for(Hourse h:list) {
        		if(h.getBuildingId()==houseaddr&&h.getNum().equals(paltaddr)) {
        			flag=true;
        		}
        	}
        }
        if(flag==true) {
        	return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("认证失败");
    }
}
