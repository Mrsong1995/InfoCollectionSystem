package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnOrderDTO;
import com.key.dwsurvey.dao.AnOrderDao;
import com.key.dwsurvey.entity.AnOrder;
import com.key.dwsurvey.entity.QuOrderby;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.mapper.AnOrderMapper;
import com.key.dwsurvey.service.AnOrderManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序题
 */
@Service
public class AnOrderManagerImpl extends BaseServiceImpl<AnOrder, String> implements AnOrderManager {

	@Autowired
	private AnOrderDao anOrderDao;
	@Autowired
	private AnOrderMapper anOrderMapper;
	@Override
	public void setBaseDao() {
		this.baseDao=anOrderDao;
	}

	@Override
	public List<AnOrder> findAnswer(String belongAnswerId, String quId) {//belongAnswerId quId
		QueryWrapper<AnOrder> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belong_answer_id", belongAnswerId);
		queryWrapper.eq("qu_id", quId);
		return anOrderMapper.selectList(queryWrapper);
		//		Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
		//		Criterion criterion2=Restrictions.eq("quId", quId);
		//		return anOrderDao.find(criterion1,criterion2);

	}

	@Override
	public void findGroupStats(Question question) {
//		anOrderDao.findGroupStats(question);

		List<AnOrderDTO> list=anOrderMapper.findGroupStats(question.getId());
		List<QuOrderby> quOrderbies=question.getQuOrderbys();

		/*
		int count=0;
		for (QuOrderby quOrderby : quOrderbies) {
			String quOrderById= quOrderby.getId();
			for (Object[] objects : list) {
				if(quOrderById.equals(objects[0].toString())){
					Float sumOrderyNum=Float.parseFloat(objects[1].toString());
					count+=sumOrderyNum;
					quOrderby.setAnOrderSum(sumOrderyNum.intValue());
					System.out.println("sumOrderyNum:"+sumOrderyNum);
					continue;
				}
			}
		}
		question.setAnCount(count);
		*/

		int count=0;
		List<QuOrderby> list2 = new ArrayList<QuOrderby>();
		for (AnOrderDTO item : list) {
			Float sumOrderyNum=item.getSumOrderNum();
			String quOrderById=item.getQuRowId();
			for (QuOrderby quOrderby : quOrderbies) {
				if(quOrderById.equals(quOrderby.getId())){
					quOrderby.setAnOrderSum(sumOrderyNum.intValue());
					list2.add(quOrderby);
				}
			}
		}
//		question.setAnCount(count);
		question.setQuOrderbys(list2);
	}
	
}
