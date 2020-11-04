package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnCheckboxDTO;
import com.key.dwsurvey.DTO.AnRadioDTO;
import com.key.dwsurvey.dao.AnCheckboxDao;
import com.key.dwsurvey.entity.*;
import com.key.dwsurvey.mapper.AnCheckboxMapper;
import com.key.dwsurvey.mapper.AnRadioMapper;
import com.key.dwsurvey.service.AnCheckboxManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 多选题
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */

@Service
public class AnCheckboxManagerImpl implements AnCheckboxManager {//extends BaseServiceImpl<AnCheckbox, String> implements AnCheckboxManager {

	@Autowired
	private AnCheckboxDao anCheckboxDao;
	@Autowired
	private AnCheckboxMapper anCheckboxMapper;
	@Autowired
    private AnRadioMapper anRadioMapper;
//	@Override
//	public void setBaseDao() {
//		this.baseDao=anCheckboxDao;
//	}

	//根据exam_user信息查询答案
		public List<AnCheckbox> findAnswer(String belongAnswerId,String quId){
			//belongAnswerId quId
			QueryWrapper<AnCheckbox> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("belong_answer_id", belongAnswerId);
			queryWrapper.eq("qu_id", quId);
			return anCheckboxMapper.selectList(queryWrapper);
			//begin delete  by jesse at 2020-07-15  for
			//			Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
			//			Criterion criterion2=Restrictions.eq("quId", quId);
			//			return anCheckboxDao.find(criterion1,criterion2);
			//end delete by jesse at 2020-07-15

		}

    @Override
    public void findGroupStats(Question question) {
//        anCheckboxDao.findGroupStats(question);

        List<AnCheckboxDTO> list=anCheckboxMapper.findStatsDataChart(question.getId());
        List<QuCheckbox> quCheckboxs=question.getQuCheckboxs();

        int count=0;
        for (QuCheckbox quCheckbox : quCheckboxs) {

            String quCheckboxId=quCheckbox.getId();
            for (AnCheckboxDTO item : list) {
                if(quCheckboxId.equals(item.getQuItemId())){
                    int anCount=item.getCount();
                    count+=anCount;
                    quCheckbox.setAnCount(anCount);
                    break;
                }
            }
        }
        question.setAnCount(count);
    }

    @Override
    public List<DataCross> findStatsDataCross(Question rowQuestion,
                                              Question colQuestion) {
		    return null;
//        return anCheckboxDao.findStatsDataCross(rowQuestion, colQuestion);
    }

    @Override
    public List<DataCross> findStatsDataChart(Question question) {
//        return anCheckboxDao.findStatsDataChart(question);
        List<DataCross> crosses=new ArrayList<DataCross>();

        List<AnCheckboxDTO> list=anCheckboxMapper.findStatsDataChart(question.getId());

        List<QuCheckbox> quCheckboxs=question.getQuCheckboxs();
        for (QuCheckbox quCheckbox : quCheckboxs) {
            String quItemId=quCheckbox.getId();
            String optionName=quCheckbox.getOptionName();
            DataCross dataCross=new DataCross();
            dataCross.setOptionName(optionName);
            for (AnCheckboxDTO item : list) {
                String anQuItemId=item.getQuItemId();
                int count=item.getCount();
                if(quItemId.equals(anQuItemId)){
                    dataCross.setCount(count);
                    break;
                }
            }
            crosses.add(dataCross);
        }
        return crosses;
    }

    @Override
    public void save(AnCheckbox anCheckbox) {
        if (anCheckbox.getId() == null){
            anCheckboxMapper.insertSelective(anCheckbox);
        }else{
            anCheckboxMapper.updateByPrimaryKey(anCheckbox);
        }
    }
}
