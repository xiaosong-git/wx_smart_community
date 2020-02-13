package com.company.project.service;
import com.company.project.model.Params;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2020/02/12.
 */
public interface ParamsService extends Service<Params> {

    String findValueByName(String imageServerApiUrl);
}
