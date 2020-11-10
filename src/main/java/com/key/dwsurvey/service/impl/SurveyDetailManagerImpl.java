package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.common.utils.ReflectionUtils;
import com.key.dwsurvey.dao.SurveyDetailDao;
import com.key.dwsurvey.entity.SurveyDetail;
import com.key.dwsurvey.mapper.SurveyDetailMapper;
import com.key.dwsurvey.service.SurveyDetailManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 问卷详情
 */
@Service
public class SurveyDetailManagerImpl extends BaseServiceImpl<SurveyDetail, String> implements SurveyDetailManager{
	
	@Autowired
	private SurveyDetailDao surveyDetailDao;
	@Autowired
	private SurveyDetailMapper surveyDetailMapper;
	@Override
	public void setBaseDao() {
		this.baseDao=surveyDetailDao;
	}
	
	@Transactional
	@Override
	public void save(SurveyDetail t) {
		//判断有无，有则更新
		SurveyDetail surveyDetail=findUn(t.getDirId());
		if(surveyDetail==null){
			surveyDetail=new SurveyDetail();
		}
		ReflectionUtils.copyAttr(t,surveyDetail);
		SurveyDetail result = surveyDetailMapper.selectByPrimaryKey(surveyDetail.getId());
		if (result == null){
			surveyDetailMapper.insert(surveyDetail);
		}else {
			surveyDetailMapper.updateByPrimaryKey(surveyDetail);
		}
//		super.save(surveyDetail);

	}
	
	private SurveyDetail findUn(String dirId){
		QueryWrapper<SurveyDetail> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("dir_id", dirId);
		List<SurveyDetail> details = surveyDetailMapper.selectList(queryWrapper);
//		Criterion criterion=Restrictions.eq("dirId", dirId);
//		List<SurveyDetail> details=surveyDetailDao.find(criterion);

		 if(details!=null && details.size()>0){
			 return details.get(0);
		 }
		 return null;
	}

	@Override
	public SurveyDetail getBySurveyId(String surveyId) {
		 return findUn(surveyId);
	}
	
}
