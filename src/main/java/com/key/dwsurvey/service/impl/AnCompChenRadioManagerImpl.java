package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.dao.AnCompChenRadioDao;
import com.key.dwsurvey.entity.AnCompChenRadio;
import com.key.dwsurvey.mapper.AnCompChenRadioMapper;
import com.key.dwsurvey.service.AnCompChenRadioManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 复合矩陈单选题
 */
@Service
public class AnCompChenRadioManagerImpl extends BaseServiceImpl<AnCompChenRadio, String> implements AnCompChenRadioManager {

	@Autowired
	private AnCompChenRadioDao anCompChenRadioDao;
	@Autowired
	private AnCompChenRadioMapper anCompChenRadioMapper;
	@Override
	public void setBaseDao() {
		this.baseDao=anCompChenRadioDao;
	}
	
	@Override
	public List<AnCompChenRadio> findAnswer(String belongAnswerId, String quId) {//belongAnswerId quId
		QueryWrapper<AnCompChenRadio> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belong_answer_id", belongAnswerId);
		queryWrapper.eq("qu_id", quId);
		return anCompChenRadioMapper.selectList(queryWrapper);
		//		Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
		//		Criterion criterion2=Restrictions.eq("quId", quId);
		//		return anCompChenRadioDao.find(criterion1,criterion2);
	}
}
