package com.key.dwsurvey.service.impl;

import com.key.common.plugs.page.Page;
import com.key.common.plugs.page.PropertyFilter;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.dao.QuScoreDao;
import com.key.dwsurvey.entity.QuScore;
import com.key.dwsurvey.mapper.QuScoreMapper;
import com.key.dwsurvey.service.QuScoreManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 评分题
 */
@Service
public class QuScoreManagerImpl extends BaseServiceImpl<QuScore, String> implements QuScoreManager {

    @Autowired
    private QuScoreDao quScoreDao;
    @Autowired
    private QuScoreMapper scoreMapper;

    @Override
    public void setBaseDao() {
        this.baseDao = quScoreDao;
    }

    public List<QuScore> findByQuId(String quId) {
//		Page<QuScore> page=new Page<QuScore>();
//		page.setOrderBy("orderById");
//		page.setOrderDir("asc");
//
//		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
//		filters.add(new PropertyFilter("EQS_quId", quId));
//		filters.add(new PropertyFilter("EQI_visibility", "1"));
//		return findAll(page, filters);
        return scoreMapper.selectByQuIdAndvisibility(quId, Integer.valueOf(1));
    }

    public int getOrderById(String quId) {
        QuScore quScore = scoreMapper.findFirstByOrderByIdDesc(quId);
//        Criterion criterion = Restrictions.eq("quId", quId);
//        QuScore quRadio = quScoreDao.findFirst("orderById", false, criterion);
        if (quScore != null) {
            return quScore.getOrderById();
        }
        return 0;
    }


    /*******************************************************************8
     * 更新操作
     */

    @Override
    @Transactional
    public QuScore upOptionName(String quId, String quItemId, String optionName) {
        if (quItemId != null && !"".equals(quItemId)) {
            QuScore quScore = scoreMapper.selectByPrimaryKey(quItemId);
//            QuScore quScore = quScoreDao.get(quItemId);
            quScore.setOptionName(optionName);
            scoreMapper.updateByPrimaryKey(quScore);
//            quScoreDao.save(quScore);
            return quScore;
        } else {
            //取orderById
            int orderById = getOrderById(quId);
            //新加选项
            QuScore quScore = new QuScore();
            quScore.setQuId(quId);
            quScore.setOptionName(optionName);
            //title
            quScore.setOrderById(++orderById);
            quScore.setOptionTitle(orderById + "");
            scoreMapper.insertSelective(quScore);
//            quScoreDao.save(quScore);
            return quScore;
        }
    }

    @Override
    @Transactional
    public List<QuScore> saveManyOptions(String quId, List<QuScore> quScores) {
        //取orderById
        int orderById = getOrderById(quId);
        for (QuScore quScore : quScores) {
            //新加选项
            quScore.setOrderById(++orderById);
            quScore.setOptionTitle(orderById + "");
            scoreMapper.updateByPrimaryKey(quScore);
//            quScoreDao.save(quScore);
        }
        return quScores;
    }

    @Override
    @Transactional
    public void ajaxDelete(String quItemId) {
        QuScore quScore = scoreMapper.selectByPrimaryKey(quItemId);
//        QuScore quScore = get(quItemId);
        quScore.setVisibility(0);
        scoreMapper.updateByPrimaryKey(quScore);
//        quScoreDao.save(quScore);
    }

    @Override
    @Transactional
    public void saveAttr(String quItemId) {
        QuScore quScore = scoreMapper.selectByPrimaryKey(quItemId);
//        QuScore quScore = get(quItemId);
        scoreMapper.updateByPrimaryKey(quScore);
//        quScoreDao.save(quScore);
    }
}
