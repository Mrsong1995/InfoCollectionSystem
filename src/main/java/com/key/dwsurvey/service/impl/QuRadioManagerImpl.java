package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.dao.QuRadioDao;
import com.key.dwsurvey.entity.QuRadio;
import com.key.dwsurvey.mapper.QuRadioMapper;
import com.key.dwsurvey.service.QuRadioManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 单选题
 */
@Service("quRadioManager")
public class QuRadioManagerImpl extends BaseServiceImpl<QuRadio, String> implements QuRadioManager {

	@Autowired
	private QuRadioDao quRadioDao;
	@Autowired
	private QuRadioMapper radioMapper;

	@Override
	public void setBaseDao() {
		this.baseDao=quRadioDao;
	}
	/*******************************************************************8
	 * 查询操作
	 */

	/**
	 * 得到某一题的选项
	 */
	public List<QuRadio> findByQuId(String quId){
//		Page<QuRadio> page=new Page<QuRadio>();
//		page.setOrderBy("orderById");
//		page.setOrderDir("asc");
//
//		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
//		filters.add(new PropertyFilter("EQS_quId", quId));
//		filters.add(new PropertyFilter("EQI_visibility", "1"));
		return radioMapper.selectByVvisibilityAndQuId(Integer.valueOf(1),quId);
	}
	
	public int getOrderById(String quId){
		QuRadio quRadio = radioMapper.findFirstByOrderByIdDesc(quId);
//		Criterion criterion=Restrictions.eq("quId", quId);
//		QuRadio quRadio=quRadioDao.findFirst("orderById", false, criterion);
		if(quRadio!=null){
			return quRadio.getOrderById();
		}
		return 0;
	}
	
	
	
	/*******************************************************************8
	 * 更新操作
	 */
	
	@Override
	@Transactional
	public QuRadio upOptionName(String quId,String quItemId, String optionName) {
		if(quItemId!=null && !"".equals(quItemId)){
			QuRadio quRadio = radioMapper.selectByPrimaryKey(quItemId);
//			QuRadio quRadio=quRadioDao.get(quItemId);
			quRadio.setOptionName(optionName);
			radioMapper.updateByPrimaryKeySelective(quRadio);
//			quRadioDao.save(quRadio);
			return quRadio;
		}else{
			//取orderById
			int orderById=getOrderById(quId);
			//新加选项
			QuRadio quRadio=new QuRadio();
			quRadio.setQuId(quId);
			quRadio.setOptionName(optionName);
			//title
			quRadio.setOrderById(++orderById);
			quRadio.setOptionTitle(orderById+"");
			radioMapper.insertSelective(quRadio);
//			quRadioDao.save(quRadio);
			return quRadio;
		}
	}
	
	@Override
	@Transactional
	public List<QuRadio> saveManyOptions(String quId,List<QuRadio> quRadios) {
		//取orderById
		int orderById=getOrderById(quId);
		for (QuRadio quRadio : quRadios) {
			//新加选项
			quRadio.setOrderById(++orderById);
			quRadio.setOptionTitle(orderById+"");
			radioMapper.updateByPrimaryKeySelective(quRadio);
//			quRadioDao.save(quRadio);
		}
		return quRadios;
	}
	
	@Override
	@Transactional
	public void ajaxDelete(String quItemId) {
		QuRadio quRadio = radioMapper.selectByPrimaryKey(quItemId);
//		QuRadio quRadio=get(quItemId);
		String quId=quRadio.getQuId();
		int orderById=quRadio.getOrderById();
		radioMapper.deleteByPrimaryKey(quItemId);
//		quRadioDao.delete(quItemId);
		//修改排序号
		radioMapper.quOrderByIdDel1(quId,orderById);
//		quRadioDao.quOrderByIdDel1(quId, orderById);
	}
	
	@Override
	@Transactional
	public void saveAttr(String quItemId, String isNote) {
		QuRadio quRadio = radioMapper.selectByPrimaryKey(quItemId);
//		QuRadio quRadio=get(quItemId);
		if(isNote!=null && "1".equals(isNote)){
			quRadio.setIsNote(1);
		}else{
			quRadio.setIsNote(0);
		}
		radioMapper.updateByPrimaryKeySelective(quRadio);
//		quRadioDao.save(quRadio);
	}
}
