package com.company.project.service;

import com.company.project.core.Service;
import com.company.project.model.Params;


/**
 * Created by CodeGenerator on 2020/02/12.
 */
public interface ParamsService extends Service<Params> {

    String findValueByName(String imageServerApiUrl);
}
