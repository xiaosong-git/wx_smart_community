package com.company.project.dao;

import com.company.project.compose.TableList;
import com.company.project.core.Mapper;
import com.company.project.model.User;
import com.company.project.model.VisitorRecord;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface VisitorRecordMapper extends Mapper<VisitorRecord> {


    @Select("select * from "+ TableList.USER +" u left join "+ TableList.FAMILY +" f on f.user_id=u.id\n" +
            "left join "+TableList.HOURSE+" h on h.id=f.house_id left join "+TableList.BUILDING+" b on h.building_id=b.id\n" +
            "left join "+TableList.AREA +" a on b.area_id=a.id\n" +
            "where a.id = #{areaId} and u.phone = #{phone}")
    List<User> findByAreaIdPhone(Long areaId, String phone);


    List<Map<String,Object>> findApplying(Long userId) ;

    @Update("update " + TableList.VISITRECORD + " set cstatus = #{cstatus} where id = #{recordId}")
    int updateCstatus(Long recordId,String cstatus);

    List<Map<String,Object>> getUserInfo(Long visitorId);
}