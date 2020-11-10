package com.key.dwsurvey.service.impl;

import com.key.dwsurvey.dao.QuestionLogicDao;
import com.key.dwsurvey.entity.QuestionLogic;
import com.key.dwsurvey.mapper.QuestionLogicMapper;
import com.key.dwsurvey.service.QuestionLogicManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 题逻辑
 */
@Service
public class QuestionLogicManagerImpl implements QuestionLogicManager {
    @Autowired
    private QuestionLogicDao questionLogicDao;
    @Autowired
    private QuestionLogicMapper questionLogicMapper;

    @Override
    public List<QuestionLogic> findByCkQuId(String ckQuId) {
//			Criterion cri1=Restrictions.eq("ckQuId", ckQuId);
//			Criterion cri2=Restrictions.eq("visibility", 1);
//			return questionLogicDao.find(cri1,cri2);
        return questionLogicMapper.selectByQuIdAndVisibility(ckQuId, Integer.valueOf(1));

    }
}
