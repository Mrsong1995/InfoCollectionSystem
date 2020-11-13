package com.key.dwsurvey.service.impl;

import com.key.common.plugs.page.Page;
import com.key.common.plugs.page.PropertyFilter;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.dao.QuChenOptionDao;
import com.key.dwsurvey.entity.QuChenOption;
import com.key.dwsurvey.mapper.QuChenOptionMapper;
import com.key.dwsurvey.service.QuChenOptionManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 矩陈题选项
 */
@Service
public class QuChenOptionManagerImpl extends BaseServiceImpl<QuChenOption, String> implements QuChenOptionManager {

	@Autowired
	private QuChenOptionDao quChenOptionDao;
	@Autowired
	private QuChenOptionMapper quChenOptionMapper;
	
	@Override
	public void setBaseDao() {
		this.baseDao=quChenOptionDao;
	}

	@Override
	public List<QuChenOption> findByQuId(String quId) {
//		Page<QuChenOption> page=new Page<QuChenOption>();
//		page.setOrderBy("orderById");
//		page.setOrderDir("asc");
//
//		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
//		filters.add(new PropertyFilter("EQS_quId", quId));
//		return findAll(page, filters);
		return quChenOptionMapper.selectByVisibilityAndQuId(quId,Integer.valueOf(1));
	}

	@Override
	public String getContentByQuId(String quId) {
		String content="";
		if(quId!=null && !"".equals(quId)){
			List<QuChenOption> options=findByQuId(quId);
			for (QuChenOption quChenOption : options) {
				content+=quChenOption.getOptionName()+"\r\n";
			}
		}
		return content;
	}
	
	public int getOrderById(String quId){
		QuChenOption quChenOption = quChenOptionMapper.findFirstByOrderByIdDesc(quId);
		//	Criterion criterion=Restrictions.eq("quId", quId);
		//		QuChenOption quChenOption=quChenOptionDao.findFirst("orderById", false, criterion);

		if(quChenOption!=null){
			return quChenOption.getOrderById();
		}
		return 0;
	}
	
	@Override
	@Transactional
	public QuChenOption upOptionName(String quId, String quItemId, String optionName) {
		if(quItemId!=null && !"".equals(quItemId)){
			QuChenOption quChenOption = quChenOptionMapper.selectByPrimaryKey(quItemId);
			//			QuChenOption quChenOption=quChenOptionDao.get(quItemId);

			quChenOption.setOptionName(optionName);
			quChenOptionMapper.updateByPrimaryKeySelective(quChenOption);
			//			quChenOptionDao.save(quChenOption);

			return quChenOption;
		}else{
			//取orderById
			int orderById=getOrderById(quId);
			//新加选项
			QuChenOption quChenOption=new QuChenOption();
			quChenOption.setQuId(quId);
			quChenOption.setOptionName(optionName);
			//title
			quChenOption.setOrderById(++orderById);
			quChenOptionMapper.insertSelective(quChenOption);
			//			quChenOptionDao.save(quChenOption);
			return quChenOption;
		}
	}
}
