package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnScoreDTO;
import com.key.dwsurvey.dao.AnScoreDao;
import com.key.dwsurvey.entity.AnScore;
import com.key.dwsurvey.entity.QuScore;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.mapper.AnScoreMapper;
import com.key.dwsurvey.service.AnScoreManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评分题
 */
@Service
public class AnScoreManagerImpl extends BaseServiceImpl<AnScore, String> implements AnScoreManager {

	@Autowired
	private AnScoreDao anScoreDao;
	@Autowired
	private AnScoreMapper anScoreMapper;

	@Override
	public void setBaseDao() {
		this.baseDao=anScoreDao;
	}

	@Override
	public List<AnScore> findAnswer(String belongAnswerId, String quId) {//belongAnswerId quId
		QueryWrapper<AnScore> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belong_answer_id", belongAnswerId);
		queryWrapper.eq("qu_id", quId);
		return anScoreMapper.selectList(queryWrapper);
		//		Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
		//		Criterion criterion2=Restrictions.eq("quId", quId);
		//		return anScoreDao.find(criterion1,criterion2);

	}

	@Override
	public void findGroupStats(Question question) {
//		anScoreDao.findGroupStats(question);

        List<AnScoreDTO> list=anScoreMapper.findGroupStats(question.getId());
        List<QuScore> quScores=question.getQuScores();

        int count=0;
        for (QuScore quScore : quScores) {

            String quScoreId=quScore.getId();
            for (AnScoreDTO item : list) {
                if(quScoreId.equals(item.getQuRowId())){
                    int anCount=item.getCount();
                    count+=anCount;
                    quScore.setAnCount(anCount);
                    quScore.setAvgScore(item.getAvgScore());;
                    break;
                }
            }
        }
        question.setAnCount(count);
	}
	
}
