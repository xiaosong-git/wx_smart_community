package com.company.project.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Record;
import com.company.project.service.RecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2020/02/11.
*/
@RestController
@RequestMapping("/record")
public class RecordController {
    @Resource
    private RecordService recordService;


    /**
     * 传入用户id生成通行记录二维码
     * @param userId 用户id
     * @return 通行记录的id
     */
    @RequestMapping("/createRecord")
    @ResponseBody
    public Result createRecord(Long userId){
        try {
            return recordService.createRecord(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常","");
    }
    /**
     * 传入用户id生成通行记录二维码
     * @param record 记录
     * @return 通行记录的id
     */
    @RequestMapping("/scanning")
    @ResponseBody
    public Result scanning(Record record){
        try {
            return recordService.scanning(record);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("系统异常","");
    }


}
