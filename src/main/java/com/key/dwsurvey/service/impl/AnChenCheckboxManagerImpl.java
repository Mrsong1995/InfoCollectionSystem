package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnChenCheckboxDTO;
import com.key.dwsurvey.dao.AnChenCheckboxDao;
import com.key.dwsurvey.entity.AnChenCheckbox;
import com.key.dwsurvey.entity.QuChenColumn;
import com.key.dwsurvey.entity.QuChenRow;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.mapper.AnChenCheckboxMapper;
import com.key.dwsurvey.service.AnChenCheckboxManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 矩陈多选题
 */
@Service
public class AnChenCheckboxManagerImpl extends BaseServiceImpl<AnChenCheckbox, String> implements AnChenCheckboxManager {

    @Autowired
    private AnChenCheckboxDao anChenCheckboxDao;
    @Autowired
    private AnChenCheckboxMapper anChenCheckboxMapper;

    @Override
    public void setBaseDao() {
        this.baseDao = anChenCheckboxDao;
    }

    @Override
    public List<AnChenCheckbox> findAnswer(String belongAnswerId, String quId) {//belongAnswerId quId
        QueryWrapper<AnChenCheckbox> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_answer_id", belongAnswerId);
        queryWrapper.eq("qu_id", quId);
        return anChenCheckboxMapper.selectList(queryWrapper);
        //		Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
        //		Criterion criterion2=Restrictions.eq("quId", quId);
        //		return anChenCheckboxDao.find(criterion1,criterion2);
    }

    @Override
    public void findGroupStats(Question question) {
//		anChenCheckboxDao.findGroupStats(question);


        List<AnChenCheckboxDTO> list = anChenCheckboxMapper.findGroupStats(question.getId());

        List<QuChenRow> quChenRows = question.getRows();
        List<QuChenColumn> quChenColumns = question.getColumns();

        int count = 0;
        for (QuChenRow quChenRow : quChenRows) {

            String quChenRowId = quChenRow.getId();
//			for (QuChenColumn quChenColumn : quChenColumns) {
//
//				String quChenColId=quChenColumn.getId();
//				for (Object[] objects : list) {
//					if(quChenRowId.equals(objects[0].toString()) && quChenColId.equals(objects[1].toString())){
//						int anCount=Integer.parseInt(objects[2].toString());
//						count+=anCount;
//						quChenRow.setAnCount(quChenRow.getAnCount()+anCount);
//						//quChenColumn.setAnCount(anCount);;
//						continue;
//					}
//				}
//
//			}

            for (AnChenCheckboxDTO item : list) {
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


        List<AnChenCheckbox> anChenCheckboxs = new ArrayList<AnChenCheckbox>();

        for (AnChenCheckboxDTO item : list) {
            AnChenCheckbox anChenCheckbox = new AnChenCheckbox();
            //anChenRadio.setBelongId(belongId);
            anChenCheckbox.setQuRowId(item.getQuRowId());
            anChenCheckbox.setQuColId(item.getQuColId());
            int anCount = item.getCount();
            anChenCheckbox.setAnCount(anCount);
            anChenCheckboxs.add(anChenCheckbox);
        }

        question.setAnChenCheckboxs(anChenCheckboxs);
    }
}
