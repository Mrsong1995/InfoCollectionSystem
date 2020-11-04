package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnChenRadioDTO;
import com.key.dwsurvey.dao.AnChenRadioDao;
import com.key.dwsurvey.entity.*;
import com.key.dwsurvey.mapper.AnChenRadioMapper;
import com.key.dwsurvey.service.AnChenRadioManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 矩陈单选题
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */
@Service
public class AnChenRadioManagerImpl extends BaseServiceImpl<AnChenRadio, String> implements AnChenRadioManager {

	@Autowired
	private AnChenRadioDao anChenRadioDao;

	@Autowired
	private AnChenRadioMapper anChenRadioMapper;
	@Override
	public void setBaseDao() {
		this.baseDao=anChenRadioDao;
	}

	@Override
	public List<AnChenRadio> findAnswer(String belongAnswerId, String quId) {//belongAnswerId quId
		QueryWrapper<AnChenRadio> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belong_answer_id", belongAnswerId);
		queryWrapper.eq("qu_id", quId);
		return anChenRadioMapper.selectList(queryWrapper);
		//begin delete  by jesse at 2020-07-15  for 优化
		//		Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
		//		Criterion criterion2=Restrictions.eq("quId", quId);
		//		return anChenRadioDao.find(criterion1,criterion2);
		//end delete by jesse at 2020-07-15
	}

	@Override
	public void findGroupStats(Question question) {
//		anChenRadioDao.findGroupStats(question);


		List<AnChenRadioDTO> list=anChenRadioMapper.findGroupStats(question.getId());

		List<QuChenRow> quChenRows=question.getRows();
		List<QuChenColumn> quChenColumns=question.getColumns();

		int count=0;
		for (QuChenRow quChenRow : quChenRows) {

			String quChenRowId=quChenRow.getId();
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

			for (AnChenRadioDTO item : list) {
				if(quChenRowId.equals(item.getQuRowId())){
					int anCount=item.getCount();
					count+=anCount;
					quChenRow.setAnCount(quChenRow.getAnCount()+anCount);
					//quChenColumn.setAnCount(anCount);;
					break;
				}
			}

		}
		question.setAnCount(count);


		List<AnChenRadio> anChenRadios=new ArrayList<AnChenRadio>();

		for (AnChenRadioDTO item : list) {
			AnChenRadio anChenRadio=new AnChenRadio();
			//anChenRadio.setBelongId(belongId);
			anChenRadio.setQuRowId(item.getQuRowId());
			anChenRadio.setQuColId(item.getQuColId());
			int anCount=item.getCount();
			anChenRadio.setAnCount(anCount);
			anChenRadios.add(anChenRadio);
		}

		question.setAnChenRadios(anChenRadios);

	}
	
	@Override
	public List<DataCross> findStatsDataChart(Question question) {
		// TODO Auto-generated method stub
		return null;
	}
}
