package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.dao.AnEnumquDao;
import com.key.dwsurvey.entity.AnEnumqu;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.mapper.AnEnumquMapper;
import com.key.dwsurvey.service.AnEnumquManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 枚举题
 *
 * @author keyuan(keyuan258 @ gmail.com)
 * <p>
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */
@Service
public class AnEnumquManagerImpl extends BaseServiceImpl<AnEnumqu, String> implements AnEnumquManager {

	@Autowired
	private AnEnumquDao anEnumquDao;
	@Autowired
	private AnEnumquMapper anEnumquMapper;
	@Override
	public void setBaseDao() {
		this.baseDao=anEnumquDao;
	}

	//根据exam_user信息查询答案
	public List<AnEnumqu> findAnswer(String belongAnswerId,String quId){
		//belongAnswerId quId
		QueryWrapper<AnEnumqu> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belong_answer_id", belongAnswerId);
		queryWrapper.eq("qu_id", quId);
		return anEnumquMapper.selectList(queryWrapper);
		//begin delete  by jesse at 2020-07-15  for 优化
		//		Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
		//		Criterion criterion2=Restrictions.eq("quId", quId);
		//		return anEnumquDao.find(criterion1,criterion2);
		//end delete by jesse at 2020-07-15

	}

    @Override
    public void findGroupStats(Question question) {
//        anEnumquDao.findGroupStats(question);
        question.setAnCount(anEnumquMapper.findGroupStats(question.getId()).size());
    }
}
