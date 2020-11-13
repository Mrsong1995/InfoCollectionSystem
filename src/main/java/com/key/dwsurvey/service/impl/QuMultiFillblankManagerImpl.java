package com.key.dwsurvey.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.dao.QuMultiFillblankDao;
import com.key.dwsurvey.entity.QuMultiFillblank;
import com.key.dwsurvey.mapper.QuMultiFillblankMapper;
import com.key.dwsurvey.service.QuMultiFillblankManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 多项填空题
 */
@Service("quMultiFillblankManager")
public class QuMultiFillblankManagerImpl extends BaseServiceImpl<QuMultiFillblank, String> implements QuMultiFillblankManager {
    @Autowired
    private QuMultiFillblankDao quMultiFillblankDao;
    @Autowired
    private QuMultiFillblankMapper quMultiFillblankMapper;

    @Override
    public void setBaseDao() {
        this.baseDao = quMultiFillblankDao;
    }

    public List<QuMultiFillblank> findByQuId(String quId) {
//		Page<QuMultiFillblank> page=new Page<QuMultiFillblank>();
//		page.setOrderBy("orderById");
//		page.setOrderDir("asc");
//
//		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
//		filters.add(new PropertyFilter("EQS_quId", quId));
//		filters.add(new PropertyFilter("EQI_visibility", "1"));
//		return findAll(page, filters);
        return quMultiFillblankMapper.selectByVisibilityAndQuId( Integer.valueOf(1), quId);
    }

    public int getOrderById(String quId) {
        QuMultiFillblank quMultiFillblank =  quMultiFillblankMapper.findFirstByOrderByIdDesc(quId);
//        Criterion criterion = Restrictions.eq("quId", quId);
//        QuMultiFillblank quMultiFillblank = quMultiFillblankDao.findFirst("orderById", false, criterion);
        if (quMultiFillblank != null) {
            return quMultiFillblank.getOrderById();
        }
        return 0;
    }


    /*******************************************************************8
     * 更新操作
     */

    @Override
    @Transactional
    public QuMultiFillblank upOptionName(String quId, String quItemId, String optionName) {
        if (quItemId != null && !"".equals(quItemId)) {
            QuMultiFillblank quMultiFillblank = quMultiFillblankMapper.selectByPrimaryKey(quItemId);
//            QuMultiFillblank quMultiFillblank = quMultiFillblankDao.get(quItemId);
            quMultiFillblank.setOptionName(optionName);
            quMultiFillblankMapper.updateByPrimaryKeySelective(quMultiFillblank);
//            quMultiFillblankDao.save(quMultiFillblank);
            return quMultiFillblank;
        } else {
            //取orderById
            int orderById = getOrderById(quId);
            //新加选项
            QuMultiFillblank quMultiFillblank = new QuMultiFillblank();
            quMultiFillblank.setQuId(quId);
            quMultiFillblank.setOptionName(optionName);
            //title
            quMultiFillblank.setOrderById(++orderById);
            quMultiFillblank.setOptionTitle(orderById + "");
            quMultiFillblankMapper.insertSelective(quMultiFillblank);
//            quMultiFillblankDao.save(quMultiFillblank);
            return quMultiFillblank;
        }
    }

    @Override
    @Transactional
    public List<QuMultiFillblank> saveManyOptions(String quId, List<QuMultiFillblank> quMultiFillblanks) {
        //取orderById
        int orderById = getOrderById(quId);
        for (QuMultiFillblank quMultiFillblank : quMultiFillblanks) {
            //新加选项
            quMultiFillblank.setOrderById(++orderById);
            quMultiFillblank.setOptionTitle(orderById + "");
            quMultiFillblankMapper.updateByPrimaryKeySelective(quMultiFillblank);
//            quMultiFillblankDao.save(quMultiFillblank);
        }
        return quMultiFillblanks;
    }

    @Override
    @Transactional
    public void ajaxDelete(String quItemId) {
        QuMultiFillblank quMultiFillblank = quMultiFillblankMapper.selectByPrimaryKey(quItemId);
//        QuMultiFillblank quMultiFillblank = get(quItemId);
        quMultiFillblank.setVisibility(0);
        quMultiFillblankMapper.updateByPrimaryKeySelective(quMultiFillblank);
//        quMultiFillblankDao.save(quMultiFillblank);
    }

    @Override
    @Transactional
    public void saveAttr(String quItemId) {
        QuMultiFillblank quMultiFillblank = quMultiFillblankMapper.selectByPrimaryKey(quItemId);
//        QuMultiFillblank quMultiFillblank = get(quItemId);
        quMultiFillblankMapper.updateByPrimaryKeySelective(quMultiFillblank);
//        quMultiFillblankDao.save(quMultiFillblank);
    }

}
