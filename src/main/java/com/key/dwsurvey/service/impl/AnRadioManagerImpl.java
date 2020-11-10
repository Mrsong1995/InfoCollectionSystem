package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnRadioDTO;
import com.key.dwsurvey.dao.AnRadioDao;
import com.key.dwsurvey.entity.AnRadio;
import com.key.dwsurvey.entity.DataCross;
import com.key.dwsurvey.entity.QuRadio;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.mapper.AnRadioMapper;
import com.key.dwsurvey.service.AnRadioManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 单选题
 *
 */
@Service
public class AnRadioManagerImpl extends BaseServiceImpl<AnRadio, String> implements AnRadioManager {

    @Autowired
    private AnRadioDao anRadioDao;
    @Autowired
    private AnRadioMapper anRadioMapper;

    @Override
    public void setBaseDao() {
        this.baseDao = anRadioDao;
    }

    //根据exam_user信息查询答案
    public AnRadio findAnswer(String belongAnswerId, String quId) {
        //belongAnswerId quId
        QueryWrapper<AnRadio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_answer_id", belongAnswerId);
        queryWrapper.eq("qu_id", quId);
        return anRadioMapper.selectOne(queryWrapper);
        //		Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
        //		Criterion criterion2=Restrictions.eq("quId", quId);
        //		return anRadioDao.findUnique(criterion1,criterion2);

    }

    @Override
    public void findGroupStats(Question question) {
//		anRadioDao.findGroupStats(question);

        List<AnRadioDTO> list = anRadioMapper.findGroupStats(question.getId());
        List<QuRadio> quRadios = question.getQuRadios();

        int count = 0;
        for (QuRadio quRadio : quRadios) {
            String quRadioId = quRadio.getId();
            for (AnRadioDTO item : list) {
                if (quRadioId.equals(item.getQuItemId())) {
                    int anCount = item.getCount();
                    count += anCount;
                    quRadio.setAnCount(anCount);
                    break;
                }
            }
        }
        question.setAnCount(count);
    }

    @Override
    public List<DataCross> findStatsDataCross(Question rowQuestion,
                                              Question colQuestion) {
        return anRadioDao.findStatsDataCross(rowQuestion, colQuestion);
    }

    @Override
    public List<DataCross> findStatsDataChart(Question question) {
//		return anRadioDao.findStatsDataChart(question);

        List<DataCross> crosses = new ArrayList<DataCross>();

        List<AnRadioDTO> list = anRadioMapper.findStatsDataChart(question.getId());

        List<QuRadio> quRadios = question.getQuRadios();
        for (QuRadio quRadio : quRadios) {
            String quItemId = quRadio.getId();
            String optionName = quRadio.getOptionName();
            DataCross dataCross = new DataCross();
            dataCross.setOptionName(optionName);
            for (AnRadioDTO item : list) {
                String anQuItemId = item.getQuItemId();
                int count = item.getCount();
                if (quItemId.equals(anQuItemId)) {
                    dataCross.setCount(count);
                    break;
                }
            }
            crosses.add(dataCross);
        }
        return crosses;
    }
}
