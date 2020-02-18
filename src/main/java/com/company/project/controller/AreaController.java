package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Area;
import com.company.project.service.AreaService;
import com.company.project.service.UserService;
import com.company.project.util.DESUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2020/02/11.
*/
@RestController
@RequestMapping("/area")
public class AreaController {
    @Resource
    private AreaService areaService;
    @Resource
    private UserService userservice;

    @PostMapping("/add")
    public Result add(Area area) {
        areaService.save(area);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        areaService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Area area) {
        areaService.update(area);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Area area = areaService.findById(id);
        return ResultGenerator.genSuccessResult(area);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Area> list = areaService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    @PostMapping("/areaList")
    public Result areaList(@RequestParam() String areaCode) {
        List<Area> list = areaService.areaList(areaCode);
        return ResultGenerator.genSuccessResult(list);
    }
    /*
     * 查看用户下的所有小区
     * */
    @PostMapping("/getAreaById")
    public Result getAreaById(@RequestParam() Long userId) {
        List<Area> list = areaService.findAreaById(userId);
        return ResultGenerator.genSuccessResult(list);
    }
    /*
     * 查看用户在某一个小区下的所有楼层信息
     * */
    @PostMapping("/findByAreaId")
    public Result findByAreaId(@RequestParam() Long userId, @RequestParam() Long areaId) {
        List<Area> list = areaService.findByAreaId(userId, areaId);
        return ResultGenerator.genSuccessResult(list);
    }
	/* 
	 * 某小区下人员通行报表 
	 * 
	 * */
    @GetMapping("/reports")
    public Result reports(@RequestParam() Long userId, @RequestParam() Long areaId,
    		HttpServletRequest req, HttpServletResponse resp) {
    	String userName = userservice.findById(userId).getName();
        List<Area> list = areaService.reports(areaId, userName);
        if(list!=null) {
        	try {
			 //1 创建工作簿对象
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        //2 创建工作表对象
	        XSSFSheet xssfSheet = workbook.createSheet("人员进出报表");
	        xssfSheet.setColumnWidth(0,20*256);//设置列宽度
	        xssfSheet.setColumnWidth(1,20*256);
	        xssfSheet.setColumnWidth(2,20*256);
	        xssfSheet.setColumnWidth(3,20*256);
	        xssfSheet.setColumnWidth(4,20*256);
	        //3 创建行
	        XSSFRow row0 = xssfSheet.createRow(0);
	        row0.setHeightInPoints(28);
	        //合并列
	        row0.createCell(0).setCellValue(list.get(0).getProvince()+"   "+list.get(0).getCity()+"   "+list.get(0).getArea()+"   "+list.get(0).getExt2());
	        CellRangeAddress region=new CellRangeAddress(0,0,0,4);
	        xssfSheet.addMergedRegion(region);
	        
	        XSSFRow row1 = xssfSheet.createRow(1);
	        row1.setHeightInPoints(26);
	        row1.createCell(0).setCellValue("小区名称 "+list.get(0).getAreaName());
	        CellRangeAddress region1=new CellRangeAddress(1,1,0,1);
	        xssfSheet.addMergedRegion(region1);
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //制定输出格式
			String date = sdf.format( new Date()); //将日期转换为字符串且格式按照之前制定的
	        
	        row1.createCell(2).setCellValue("日期："+date);
	        CellRangeAddress region2=new CellRangeAddress(1,1,2,3);
	        xssfSheet.addMergedRegion(region2);
	        
	        row1.createCell(4).setCellValue("操作员 "+userName);
	        XSSFRow row2 = xssfSheet.createRow(2);
	        row2.createCell(0).setCellValue("人员姓名");
	        row2.createCell(1).setCellValue("身份证号");
	        row2.createCell(2).setCellValue("人员属性");
	        row2.createCell(3).setCellValue("通行时间");
	        row2.createCell(4).setCellValue("列别（进或出）");
	        
	        for(int i=3;i<list.size()+3;i++) {
	        	int j = i-3;
	        	 XSSFRow row = xssfSheet.createRow(i);
	        	 row.createCell(0).setCellValue(list.get(j).getUser().getName());
	        	 row.createCell(1).setCellValue(encryptIdCard(list.get(j).getUser().getIdNo()));
			        if(list.get(j).getUser()!=null) {
			        	String managerType = list.get(j).getUser().getIsManager();
			        	switch (managerType) {
						case "0":
							managerType = "管理员";
							break;
						case "10" :
							managerType = "工作人员";
							break;
						case "20" :
							managerType = "普通用户";
							break;
						default:
							break;
						}
			        	row.createCell(2).setCellValue(managerType);
			        }else {
			        	row.createCell(2).setCellValue("");
			        }
			        row.createCell(3).setCellValue(list.get(j).getRecord().getPassTime());
			        
			        if(list.get(j).getRecord()!=null) {
			        	String type = list.get(j).getRecord().getType();
			        	switch (type) {
						case "in":
							type = "进";
							break;
						case "out" :
							type = "出";
							break;
						default:
							break;
						}
			        	 row.createCell(4).setCellValue(type);
			        }else {
			        	row.createCell(4).setCellValue("");
			        }
			        
			       
	        }
	      //通过流输出进行文件下载
            ServletOutputStream out = resp.getOutputStream();
            resp.setContentType("applicatioon/vnd.ms-excel");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setHeader("content-Disposition"
                    , "attachment;filename=人员进出报表.xlsx");
            workbook.write(out);
            out.flush();
            out.close();
			workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return ResultGenerator.genFailResult("获取报表失败");
			}
	}
        return null;
    }
    
  //身份证号码：显示前六后四，范例：110601********2015
  		public static String encryptIdCard(String idCard) {
  			 String workKey = "iB4drRzSrC";//生产的des密码
  	         // update by cwf  2019/10/15 10:36 Reason:暂时修改为后端加密
  	         idCard = DESUtil.decode(workKey, idCard);
  			if (idCard == null) {
  				return "";
  			}
  			return replaceBetween(idCard, 6, idCard.length() - 4, null);
  		}
      
  	/**
  	 * 将字符串开始位置到结束位置之间的字符用指定字符替换
  	 * @param sourceStr 待处理字符串
  	 * @param begin	开始位置
  	 * @param end	结束位置
  	 * @param replacement 替换字符
  	 * @return 
  	 */
  	private static String replaceBetween(String sourceStr, int begin, int end, String replacement) {
  		if (sourceStr == null) {
  			return "";
  		}
  		if (replacement == null) {
  			replacement = "*";
  		}
  		int replaceLength = end - begin;
  		if (StringUtils.isNotBlank(sourceStr) && replaceLength > 0) {
  			StringBuilder sb = new StringBuilder(sourceStr);
  			sb.replace(begin, end, StringUtils.repeat(replacement, replaceLength));
  			return sb.toString();
  		} else {
  			return sourceStr;
  		}
  	}
}
