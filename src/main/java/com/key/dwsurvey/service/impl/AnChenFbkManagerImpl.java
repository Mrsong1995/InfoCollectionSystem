package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnChenFbkDTO;
import com.key.dwsurvey.dao.AnChenFbkDao;
import com.key.dwsurvey.entity.AnChenFbk;
import com.key.dwsurvey.entity.QuChenColumn;
import com.key.dwsurvey.entity.QuChenRow;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.mapper.AnChenFbkMapper;
import com.key.dwsurvey.service.AnChenFbkManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 矩陈填空题
 */
@Service
public class AnChenFbkManagerImpl extends BaseServiceImpl<AnChenFbk, String> implements AnChenFbkManager {

    @Autowired
    private AnChenFbkDao anChenFbkDao;
    @Autowired
    private AnChenFbkMapper anChenFbkMapper;

    @Override
    public void setBaseDao() {
        this.baseDao = anChenFbkDao;
    }

    @Override
    public List<AnChenFbk> findAnswer(String belongAnswerId, String quId) {//belongAnswerId quId
        QueryWrapper<AnChenFbk> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_answer_id", belongAnswerId);
        queryWrapper.eq("qu_id", quId);
        return anChenFbkMapper.selectList(queryWrapper);
        //Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
        //Criterion criterion2=Restrictions.eq("quId", quId);
        //return anChenFbkDao.find(criterion1,criterion2);

    }

    @Override
    public void findGroupStats(Question question) {
//		anChenFbkDao.findGroupStats(question);


        List<AnChenFbkDTO> list = anChenFbkMapper.findGroupStats(question.getId());

        List<QuChenRow> quChenRows = question.getRows();
        List<QuChenColumn> quChenColumns = question.getColumns();

        int count = 0;
        for (QuChenRow quChenRow : quChenRows) {

            String quChenRowId = quChenRow.getId();

            for (AnChenFbkDTO item : list) {
                if (quChenRowId.equals(item.getQuRowId())) {
                    int anCount = item.getCount();
                    count += anCount;
                    quChenRow.setAnCount(quChenRow.getAnCount() + anCount);
                    //quChenColumn.setAnCount(anCount);;
                    break;
                }
            }

        }
        question.setAnCount(count);


        List<AnChenFbk> anChenFbks = new ArrayList<AnChenFbk>();

        for (AnChenFbkDTO item : list) {
            AnChenFbk anChenFbk = new AnChenFbk();
            //anChenRadio.setBelongId(belongId);
            anChenFbk.setQuRowId(item.getQuRowId());
            anChenFbk.setQuColId(item.getQuColId());
            int anCount = item.getCount();
            anChenFbk.setAnCount(anCount);
            anChenFbks.add(anChenFbk);
        }

        question.setAnChenFbks(anChenFbks);
    }
}
