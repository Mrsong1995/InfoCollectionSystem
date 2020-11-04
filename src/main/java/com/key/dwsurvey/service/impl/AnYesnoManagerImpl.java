package com.key.dwsurvey.service.impl;

import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnyesnoAnswerDTO;
import com.key.dwsurvey.dao.AnYesnoDao;
import com.key.dwsurvey.entity.AnYesno;
import com.key.dwsurvey.entity.DataCross;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.mapper.AnYesnoMapper;
import com.key.dwsurvey.service.AnYesnoManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举题
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */
@Service
public class AnYesnoManagerImpl extends BaseServiceImpl<AnYesno, String> implements AnYesnoManager {

    @Autowired
    private AnYesnoDao anYesnoDao;
    @Autowired
    private AnYesnoMapper anYesnoMapper;

    @Override
    public void setBaseDao() {
        this.baseDao = anYesnoDao;
    }

    //根据exam_user信息查询答案
    public AnYesno findAnswer(String belongAnswerId, String quId) {
        //belongAnswerId quId
        Criterion criterion1 = Restrictions.eq("belongAnswerId", belongAnswerId);
        Criterion criterion2 = Restrictions.eq("quId", quId);
        return anYesnoDao.findUnique(criterion1, criterion2);
    }

    @Override
    public void findGroupStats(Question question) {
//		anYesnoDao.findGroupStats(question);
        List<AnyesnoAnswerDTO> list = anYesnoMapper.findGroupStats(question.getId());

        String tranValue = question.getYesnoOption().getTrueValue();
        String falseValue = question.getYesnoOption().getFalseValue();

        question.setParamInt01(0);
        question.setParamInt02(0);
        int count = 0;

        for (AnyesnoAnswerDTO item : list) {
            if (tranValue.equals(item.getYesnoAnswer().toString())) {
//				quRadio.setAnCount(Integer.parseInt(objects[1].toString()));
                int anCount = item.getCount();
                count += anCount;
                question.setParamInt01(anCount);
                continue;
            } else if (falseValue.equals(item.getYesnoAnswer())) {
                int anCount = item.getCount();
                count += anCount;
                question.setParamInt02(anCount);
                continue;
            }
        }
        question.setAnCount(count);
    }

	@Override
	public List<DataCross> findStatsDataCross(Question rowQuestion, Question colQuestion) {
		return anYesnoDao.findStatsDataCross(rowQuestion,colQuestion);
	}

	@Override
	public List<DataCross> findStatsDataChart(Question question) {
//		return anYesnoDao.findStatsDataChart(question);

        List<AnyesnoAnswerDTO> list=anYesnoMapper.findStatsDataChart(question.getId());
        List<DataCross> crosses=new ArrayList<DataCross>();
        DataCross dataCross0=new DataCross();
        dataCross0.setCount(0);
        dataCross0.setOptionName(question.getYesnoOption().getFalseValue());
        DataCross dataCross1=new DataCross();
        dataCross1.setCount(0);
        dataCross1.setOptionName(question.getYesnoOption().getTrueValue());
        crosses.add(dataCross0);
        crosses.add(dataCross1);

        for (AnyesnoAnswerDTO item : list) {
            String optionName=item.getYesnoAnswer();
            int count=item.getCount();
            for (DataCross dataCross : crosses) {
                if(dataCross.getOptionName().equals(optionName)){
                    dataCross.setCount(count);
                }
            }
        }
        return crosses;
	}
	
}
