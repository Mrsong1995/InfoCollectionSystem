package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnAnswerDTO;
import com.key.dwsurvey.dao.AnAnswerDao;
import com.key.dwsurvey.entity.AnAnswer;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.mapper.AnAnswerMapper;
import com.key.dwsurvey.service.AnAnswerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author keyuan
 * keyuan258@gmail.com
 *
 */

@Service
public class AnAnswerManagerImpl implements AnAnswerManager {// extends BaseServiceImpl<AnAnswer, String> implements AnAnswerManager {

	@Autowired
	private AnAnswerDao anAnswerDao;
	@Autowired
	private AnAnswerMapper anAnswerMapper;
//	@Override
//	public void setBaseDao() {
//		this.baseDao=anAnswerDao;
//	}


	//根据exam_user信息查询答案
	@Override
	public AnAnswer findAnswer(String belongAnswerId, String quId){
		QueryWrapper<AnAnswer> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belong_answer_id", belongAnswerId);
		queryWrapper.eq("qu_id", quId);
		return anAnswerMapper.selectOne(queryWrapper);
		//begin delete  by jesse at 2020-07-15  for 优化
		//			Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
		//			Criterion criterion2=Restrictions.eq("quId", quId);
		//			return anAnswerDao.findUnique(criterion1,criterion2);
		//end delete by jesse at 2020-07-15

	}


    @Override
    public void findGroupStats(Question question) {
//			anAnswerDao.findGroupStats(question);
        AnAnswerDTO anAnswerDTO = anAnswerMapper.findGroupStats(question.getId());

        question.setRowContent(anAnswerDTO.getEmptyCount().toString());//未回答数
        question.setOptionContent(anAnswerDTO.getBlankCount().toString());//回答的项数
        question.setAnCount(Integer.parseInt(anAnswerDTO.getEmptyCount().toString()));
    }

    @Override
	public void save(AnAnswer anAnswer){
		if (anAnswer.getId() == null){
			anAnswerMapper.insertSelective(anAnswer);
		}else {
			anAnswerMapper.updateByPrimaryKey(anAnswer);
		}
	}

}
