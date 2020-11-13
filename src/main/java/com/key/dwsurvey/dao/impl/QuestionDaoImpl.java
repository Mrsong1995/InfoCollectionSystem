package com.key.dwsurvey.dao.impl;


import com.key.common.QuType;
import com.key.common.dao.BaseDaoImpl;
import com.key.common.plugs.page.Page;
import com.key.common.plugs.page.PropertyFilter;
import com.key.dwsurvey.dao.QuestionDao;
import com.key.dwsurvey.entity.*;
import com.key.dwsurvey.mapper.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 题基础 dao

 */

//@Repository("questionDao")
@Repository
public class QuestionDaoImpl extends BaseDaoImpl<Question, String> implements QuestionDao {

	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuRadioMapper quRadioMapper;
	@Autowired
	private QuCheckboxMapper quCheckboxMapper;

	@Autowired
	private QuScoreMapper quScoreMapper;

	@Autowired
	private QuOrderbyMapper quOrderbyMapper;
	@Autowired
	private QuMultiFillblankMapper quMultiFillblankMapper;
	@Autowired
	private QuChenRowMapper quChenRowMapper;
	@Autowired
	private QuChenColumnMapper quChenColumnMapper;
	@Autowired
	private QuChenOptionMapper quChenOptionMapper;

	@Autowired
	private QuestionLogicMapper questionLogicMapper;
	public List<Question> findByBelongTag(String qubankId,String tag){
		Page<Question> page=new Page<Question>();
		page.setOrderBy("orderById");
		page.setOrderDir("asc");
		
		
		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_belongId", qubankId));
		filters.add(new PropertyFilter("EQI_tag", tag));
		filters.add(new PropertyFilter("NEI_quTag", "3"));
		return findAll(page, filters);
	}
	
	public void update(Question entity ){
		super.save(entity);
	}
	/**
	 * 保存题目DAO入口
	 */
	@Override
	public void save(Question entity) {
		Session session=getSession();
		saveQuestion(entity, session);
	}
	private void saveQuestion(Question entity, Session session) {
		boolean isnew=false;
		String id=entity.getId();
		String belongId=entity.getBelongId();
		int orderById=entity.getOrderById();
		if(id==null || "".equals(id)){//如果是新增的题目，则根据已有的题来设置排序号
			isnew=true;
		}
		//保存题目的题干部分
		if (id == null){
			questionMapper.insertSelective(entity);
		}else{
			questionMapper.updateByPrimaryKeySelective(entity);
		}
		//session.saveOrUpdate(entity);

		//判断题目类型
		QuType quType=entity.getQuType();
		if(quType== QuType.RADIO || quType== QuType.COMPRADIO){
			saveRadio(entity, session);
		}else if(quType== QuType.CHECKBOX || quType== QuType.COMPCHECKBOX){
			saveCheckbox(entity, session);
		}else if(quType== QuType.MULTIFILLBLANK){
			saveMultiFillblank(entity, session);
		}else if(quType== QuType.BIGQU){
			saveQuBig(entity, session);
		}else if(quType== QuType.SCORE){
			saveQuScore(entity,session);
		}else if(quType== QuType.ORDERQU){
			saveQuOrderby(entity,session);
		}else if(quType== QuType.CHENRADIO){//矩阵单选
			saveQuChenRadio(entity,session,isnew);
		}else if(quType== QuType.CHENCHECKBOX){//矩阵多选
			saveQuChenCheckbox(entity,session,isnew);
		}else if(quType== QuType.CHENFBK){//矩阵填空
			saveQuChenFbk(entity,session,isnew);
		}else if(quType== QuType.CHENSCORE){//矩阵评分题
			saveQuChenScore(entity,session,isnew);
		}else if(quType== QuType.COMPCHENRADIO){//复合矩阵单选题
			saveQuCompChenRadio(entity,session,isnew);
		}
		//更新排序号--如果是新增
		List<QuestionLogic> questionLogics=entity.getQuestionLogics();
		if(questionLogics!=null){
			for (QuestionLogic questionLogic : questionLogics) {
				String qulogicId=questionLogic.getId();
				if("".equals(qulogicId)){
					questionLogic.setId(null);
				}
				questionLogic.setCkQuId(entity.getId());
				if (questionLogic.getId() == null){
					questionLogicMapper.insertSelective(questionLogic);
				}else {
					questionLogicMapper.updateByPrimaryKeySelective(questionLogic);
				}

//				session.saveOrUpdate(questionLogic);
			}
		}
		if(isnew){
			questionMapper.quOrderByIdAdd1(entity.getId(),belongId,orderById);
//			quOrderByIdAdd1(belongId, orderById);
		}
	}
	
	private void saveQuChenScore(Question entity, Session session, boolean isnew) {
		//保存相关选项信息
		saveRows(entity,session);
		saveColumns(entity,session);
	}

	/**
	 * 复合矩阵单选题保存
	 * @param entity
	 * @param session
	 */
	private void saveQuCompChenRadio(Question entity, Session session,boolean isnew) {
		//保存相关选项信息
		saveRows(entity,session);
		saveColumns(entity,session);
		saveOptions(entity,session);
		
	}
	
	/**
	 * 矩阵填空题保存
	 * @param entity
	 * @param session
	 */
	private void saveQuChenFbk(Question entity, Session session,boolean isnew) {
		//保存相关选项信息
		saveRows(entity,session);
		saveColumns(entity,session);
	}
	/**
	 * 矩阵多选题保存
	 * @param entity
	 * @param session
	 */
	private void saveQuChenCheckbox(Question entity, Session session,boolean isnew) {
		//保存相关选项信息
		saveRows(entity,session);
		saveColumns(entity,session);		
	}
	/**
	 * 矩阵单选题保存
	 * @param entity
	 * @param session
	 */
	private void saveQuChenRadio(Question entity, Session session,boolean isnew) {
		//保存相关选项信息
		saveRows(entity,session);
		saveColumns(entity,session);		
	}
	
	/**
	 * delete row 
	 * @param entity
	 * @param session
	 */
	private void deleteRows(Question entity ,Session session){
		String delHql="delete from QuChenRow where quId=?";
		session.createQuery(delHql).setString(0, entity.getId()).executeUpdate();
	}
	private void deleteColumns(Question entity ,Session session){
		String delHql="delete from QuChenColumn where quId=?";
		session.createQuery(delHql).setString(0, entity.getId()).executeUpdate();
	}
	private void deleteOptions(Question entity ,Session session){
		String delHql="delete from QuChenOption where quId=?";
		session.createQuery(delHql).setString(0, entity.getId()).executeUpdate();
	}
	
	private void saveOptions(Question entity, Session session) {
		List<QuChenOption> options=entity.getOptions();
		String quId=entity.getId();
		for (QuChenOption quChenOption : options) {
			quChenOption.setQuId(quId);
			if (null == quChenOption.getId()){
				quChenOptionMapper.insertSelective(quChenOption);
			}else{
				quChenOptionMapper.updateByPrimaryKeySelective(quChenOption);
			}
			//session.saveOrUpdate(quChenOption);

		}
	}
	private void saveColumns(Question entity, Session session) {
		List<QuChenColumn> cols=entity.getColumns();
		String quId=entity.getId();
		for (QuChenColumn quChenColumn : cols) {
			quChenColumn.setQuId(quId);
			if (null == quChenColumn.getId()){
				quChenColumnMapper.insertSelective(quChenColumn);
			}else{
				quChenColumnMapper.updateByPrimaryKeySelective(quChenColumn);
			}
			//session.saveOrUpdate(quChenColumn);

		}
	}
	private void saveRows(Question entity, Session session) {
		List<QuChenRow> rows=entity.getRows();
		String quId=entity.getId();
		for (QuChenRow quChenRow : rows) {
			quChenRow.setQuId(quId);
			if (null == quChenRow.getId()){
				quChenRowMapper.insertSelective(quChenRow);
			}else{
				quChenRowMapper.updateByPrimaryKeySelective(quChenRow);
			}
			//session.saveOrUpdate(quChenRow);

		}
	}
	/**
	 * 保存评分题
	 * @param entity
	 * @param session
	 */
	private void saveQuScore(Question entity, Session session) {
		List<QuScore> quScores=entity.getQuScores();
		for (QuScore quScore : quScores) {
			quScore.setQuId(entity.getId());
			if (quScore.getId() == null){
				quScoreMapper.insertSelective(quScore);
			}else{
				quScoreMapper.updateByPrimaryKeySelective(quScore);
			}
//			session.saveOrUpdate(quScore);

		}
	}
	
	/**
	 * 保存排序题
	 * @param entity
	 * @param session
	 */
	private void saveQuOrderby(Question entity, Session session) {
		List<QuOrderby> quOrderbys=entity.getQuOrderbys();
		for (QuOrderby quOrderby : quOrderbys) {
			quOrderby.setQuId(entity.getId());
			if (quOrderby.getId() == null){
				quOrderbyMapper.insertSelective(quOrderby);
			}else{
				quOrderbyMapper.updateByPrimaryKeySelective(quOrderby);
			}
			//session.saveOrUpdate(quOrderby);

		}
	}
	/**
	 * 保存大题
	 * @param entity
	 * @param session
	 */
	private void saveQuBig(Question entity, Session session) {
		List<Question> questions=entity.getQuestions();
		if (entity.getId() == null){
			questionMapper.insertSelective(entity);
		}else{
			questionMapper.updateByPrimaryKeySelective(entity);
		}
//		session.save(entity);
		for (Question question : questions) {
			question.setParentQuId(entity.getId());

			saveQuestion(question,session);
		}
	}
	/**
	 * 保存单选题的单选项
	 * @param entity
	 * @param session
	 */
	private void saveRadio(Question entity,Session session){
		List<QuRadio> quRadios=entity.getQuRadios();
		
		for (QuRadio quRadio : quRadios) {
			String quRadioId=quRadio.getId();
			if(quRadioId!=null && "0".equals(quRadioId)){
				quRadio.setId(null);
			}
			quRadio.setQuId(entity.getId());
			if (quRadio.getId() == null){
				quRadioMapper.insertSelective(quRadio);
			}else{
				quRadioMapper.updateByPrimaryKeySelective(quRadio);
			}
			//session.saveOrUpdate(quRadio);

		}
	}

	/**
	 * 保存多选题选项
	 * @param entity
	 * @param session
	 */
	private void saveCheckbox(Question entity,Session session){
		List<QuCheckbox> quCheckboxs=entity.getQuCheckboxs();
		
		for (QuCheckbox quCheckbox : quCheckboxs) {
			String quRadioId=quCheckbox.getId();
			if(quRadioId!=null && "0".equals(quRadioId)){
				quCheckbox.setId(null);
			}
			quCheckbox.setQuId(entity.getId());
			if (quCheckbox.getId() == null){
				quCheckboxMapper.insertSelective(quCheckbox);
			}else{
				quCheckboxMapper.updateByPrimaryKeySelective(quCheckbox);
			}
//			session.saveOrUpdate(quCheckbox);

		}
		
	}
	
	/**
	 * 保存多项填空题选项
	 * @param entity
	 * @param session
	 */
	private void saveMultiFillblank(Question entity,Session session){
		List<QuMultiFillblank> quMultiFillblanks=entity.getQuMultiFillblanks();
		
		for (QuMultiFillblank quMultiFillblank : quMultiFillblanks) {
			quMultiFillblank.setQuId(entity.getId());
			if (quMultiFillblank.getId() == null){
				quMultiFillblankMapper.insertSelective(quMultiFillblank);
			}else{
				quMultiFillblankMapper.updateByPrimaryKeySelective(quMultiFillblank);
			}
			//session.saveOrUpdate(quMultiFillblank);

		}
		// 执行要删除的选项
		String[] removeOptionUuIds=entity.getRemoveOptionUuIds();
		if(removeOptionUuIds!=null){
			for (String optionUuId : removeOptionUuIds) {
				if(optionUuId!=null && !"".equals(optionUuId)){
					QuMultiFillblank quMultiFillblank=new QuMultiFillblank();
					quMultiFillblank.setId(optionUuId);

					quMultiFillblankMapper.deleteByPrimaryKey(optionUuId);
					//	session.delete(quMultiFillblank);//删除

					
				}
			}
		}
	}
	
	/**
	 * 更新orderbyId
	 * 属性 belongId所有题目，只要大于等于orderById+1
	 * @param belongId
	 * @param orderById
	 */
	private void quOrderByIdAdd1(String belongId,Integer orderById){
		if(belongId!=null && !"".equals(belongId)){
			String sql="update t_question set order_by_id=order_by_id+1 where belong_id=? and order_by_id>=?";
			//更新排序号
			SQLQuery query=this.getSession().createSQLQuery(sql);
			query.setString(0, belongId);
			query.setInteger(1, orderById);
			query.executeUpdate();
		}
	}
	
	public void quOrderByIdDel1(String belongId,Integer orderById){
		if(belongId!=null && !"".equals(belongId)){
			String sql="update t_question set order_by_id=order_by_id-1 where belong_id=? and order_by_id>=?";
			//更新排序号
			SQLQuery query=this.getSession().createSQLQuery(sql);
			query.setString(0, belongId);
			query.setInteger(1, orderById);
			query.executeUpdate();
		}
	}
}
