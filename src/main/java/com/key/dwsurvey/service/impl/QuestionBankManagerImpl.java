package com.key.dwsurvey.service.impl;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import com.key.common.plugs.page.IPageToPage;
import com.key.common.plugs.page.Page;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.dao.QuestionBankDao;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.entity.QuestionBank;
import com.key.dwsurvey.mapper.QuestionBankMapper;
import com.key.dwsurvey.mapper.UserMapper;
import com.key.dwsurvey.service.QuestionBankManager;
import com.key.dwsurvey.service.QuestionManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 题库
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */
@Service
public class QuestionBankManagerImpl extends BaseServiceImpl<QuestionBank, String> implements QuestionBankManager {

	@Autowired
	private QuestionBankDao questionBankDao;
	@Autowired
	private AccountManager accountManager;
	@Autowired
	private QuestionManager questionManager;

	@Autowired
	private QuestionBankMapper questionBankMapper;
	@Autowired
	private UserMapper userMapper;
	@Override
	public void setBaseDao() {
		this.baseDao=questionBankDao;
	}
	
	@Override
	public void save(QuestionBank t) {
		User user = accountManager.getCurUser();
		if(user!=null){
			t.setUserId(user.getId());
			super.save(t);
		}
	}
	/**
	 * 得到当前目录所在的目录位置
	 */
	public List<QuestionBank> findPath(QuestionBank questionBank) {
		List<QuestionBank> resultList=new ArrayList<QuestionBank>();
		if(questionBank!=null){
			List<QuestionBank> dirPathList=new ArrayList<QuestionBank>();
			dirPathList.add(questionBank);
			String parentUuid=questionBank.getParentId();
			while(parentUuid!=null && !"".equals(parentUuid)){
				QuestionBank questionBank2=get(parentUuid);
				parentUuid=questionBank2.getParentId();
				dirPathList.add(questionBank2);
			}
			for (int i = dirPathList.size()-1; i >=0; i--) {
				resultList.add(dirPathList.get(i));
			}
		}
		return resultList;
	}

	@Override
	public QuestionBank getBank(String id) {
		QuestionBank questionBank = questionBankMapper.selectByPrimaryKey(id);
		//begin delete  by jesse at 2020-07-16  for 优化
		//		QuestionBank questionBank=get(id);
		//end delete by jesse at 2020-07-16

		return questionBank;
	}
	@Override
	public QuestionBank findByNameUn(String id, String parentId, String bankName) {
		Map<String,String> map = new HashMap<>();
		map.put("bankName", bankName);
		map.put("parentId", parentId);
		//begin delete  by jesse at 2020-07-16  for 优化
		//		List<Criterion> criterions=new ArrayList<Criterion>();
		//		Criterion eqName=Restrictions.eq("bankName", bankName);
		//		Criterion eqParentId=Restrictions.eq("parentId", parentId);
		//		criterions.add(eqName);
		//		criterions.add(eqParentId);
		//end delete by jesse at 2020-07-16

		
		if(id!=null && !"".equals(id)){
			map.put("id", id);
			//begin delete  by jesse at 2020-07-16  for 优化
			//			Criterion eqId=Restrictions.ne("id", id);
			//			criterions.add(eqId);
			//end delete by jesse at 2020-07-16
		}
		return questionBankMapper.findFirst(map);
//		return questionBankDao.findFirst(criterions);
	}
	
	@Override
	public Page<QuestionBank> findPage(Page<QuestionBank> page, QuestionBank entity) {
		com.baomidou.mybatisplus.extension.plugins.pagination.Page<QuestionBank> page1 = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page.getPageNo(), page.getPageSize());
		Map<String,Object> map = new HashMap<>();
		IPageToPage<QuestionBank> iPageToPage = new IPageToPage<>();
		iPageToPage.setOrderDir("desc");
		iPageToPage.setOrderBy("createDate");

//		page.setOrderBy("createDate");
//		page.setOrderDir("desc");


//		List<Criterion> criterions=new ArrayList<Criterion>();
		map.put("visibility", 1);
		map.put("dirType", 2);
		map.put("bankState", 1);
//		criterions.add(Restrictions.eq("visibility", 1));
//		criterions.add(Restrictions.eq("dirType", 2));
//		criterions.add(Restrictions.eq("bankState", 1));
		
		Integer bankTag = entity.getBankTag();
		if(bankTag==null){
			bankTag=0;
		}
		map.put("bankTag", bankTag);
//		criterions.add(Restrictions.eq("bankTag", bankTag));
		String groupId1 = entity.getGroupId1();
		String groupId2 = entity.getGroupId2();
		if(groupId1!=null && !"".equals(groupId1)){
			map.put("groupId1", groupId1);
		//			criterions.add(Restrictions.eq("groupId1", groupId1));
		}
		if(groupId2!=null && !"".equals(groupId2)){
			map.put("groupId2", groupId2);
		//			criterions.add(Restrictions.eq("groupId2", groupId2));
		}
		List<QuestionBank> questionBanks = questionBankMapper.selectPage(page1,map);
		//导入用户名
		for(int i = 0; i < questionBanks.size(); i++){
			questionBanks.get(i).setUserName(userMapper.selectByPrimaryKey(questionBanks.get(i).getUserId()).getName());
		}
		iPageToPage.setRecords(questionBanks);
		page = new Page<>(iPageToPage);
		page.setTotalItems(page1.getTotal());
		return page;
		//TODO 需要验证
		//		return questionBankDao.findPageList(page, criterions);
	}
	
	@Override
	@Transactional
	public void delete(String id) {
		//设为不可见

		QuestionBank questionBank = questionBankMapper.selectByPrimaryKey(id);
		//		QuestionBank questionBank=get(id);
		questionBank.setVisibility(0);
		try {
			questionBankMapper.updateByPrimaryKey(questionBank);
		}catch (Exception e){
			e.printStackTrace();
		}
		//		questionBankDao.save(questionBank);
		List<QuestionBank> banks = new ArrayList<>();
		try {
			banks = questionBankMapper.findListByParentId(questionBank.getId());
		}catch (Exception e){
			e.printStackTrace();
		}
		//		Criterion criterion=Restrictions.eq("parentId", questionBank.getId());
		//		List<QuestionBank> banks=findList(criterion);
		if(banks!=null){
			for (QuestionBank questionBank2 : banks) {
				delete(questionBank2);
			}
		}
	}
	@Transactional
	public void delete(QuestionBank questionBank) {
		String id=questionBank.getId();
		//目录ID，为1的为系统默认注册用户目录不能删除
		if(!"1".equals(id)){
			//设为不可见
			questionBank.setVisibility(0);
			//TODO 后加需要验证，因为它虽然设置了不可见但是没有存入数据库。
			questionBankMapper.updateByPrimaryKey(questionBank);
			List<QuestionBank> banks = questionBankMapper.findListByParentId(questionBank.getId());
//			Criterion criterion=Restrictions.eq("parentId", questionBank.getId());
//			List<QuestionBank> banks=findList(criterion);
			if(banks!=null){
				for (QuestionBank questionBank2 : banks) {
					delete(questionBank2);
				}
			}
		}
	}
	
	@Override
	@Transactional
	public void executeBank(String id) {

		QuestionBank questionBank=getBank(id);
		questionBank.setBankState(1);
		//更新下题目数
		List<Question> questions=questionManager.find(id, "1");
		if(questions!=null){
			questionBank.setQuNum(questions.size());
		}
		questionBankMapper.updateByPrimaryKey(questionBank);
		//begin delete  by jesse at 2020-07-16  for 优化
		//		super.save(questionBank);
		//end delete by jesse at 2020-07-16

	}
	
	@Override
	@Transactional
	public void closeBank(String id) {
		QuestionBank questionBank=getBank(id);
		questionBank.setBankState(0);
		questionBankMapper.updateByPrimaryKey(questionBank);
		//begin delete  by jesse at 2020-07-16  for 优化
		//		super.save(questionBank);
		//end delete by jesse at 2020-07-16
	}
	
	public List<QuestionBank> newQuestionBank() {
		List<QuestionBank> result=new ArrayList<QuestionBank>();
		try{
			QuestionBank entity=new QuestionBank();
			Page<QuestionBank> page=new Page<QuestionBank>();
			page.setPageSize(15);
			page=findPage(page,entity);
			result=page.getResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
