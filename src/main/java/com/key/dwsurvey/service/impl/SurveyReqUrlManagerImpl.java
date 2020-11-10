package com.key.dwsurvey.service.impl;

import com.key.common.utils.RandomUtils;
import com.key.dwsurvey.dao.SurveyReqUrlDao;
import com.key.dwsurvey.entity.SurveyReqUrl;
import com.key.dwsurvey.mapper.SurveyReqUrlMapper;
import com.key.dwsurvey.service.SurveyReqUrlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 短链地址
 */
@Service
public class SurveyReqUrlManagerImpl  implements SurveyReqUrlManager {
	
	@Autowired
	private SurveyReqUrlDao surveyReqUrlDao;
	@Autowired
	private SurveyReqUrlMapper surveyReqUrlMapper;
	@Transactional
	public void save(SurveyReqUrl entity){
		String sId=entity.getSid();
		if(sId==null || "".equals(sId)){
			sId= RandomUtils.randomStr(6, 12);
			entity.setSid(sId);
		}
		surveyReqUrlMapper.updateByPrimaryKey(entity);
//		surveyReqUrlDao.save(entity);
	}
	
	public void getByShortId(String shortId){
		
	}
	
}
