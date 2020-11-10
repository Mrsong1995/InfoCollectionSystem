package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.plugs.page.IPageToPage;
import com.key.common.plugs.page.Page;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnFillblankDTO;
import com.key.dwsurvey.dao.AnFillblankDao;
import com.key.dwsurvey.entity.AnFillblank;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.mapper.AnFillblankMapper;
import com.key.dwsurvey.service.AnFillblankManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 填空题
 */
@Service
public class AnFillblankManagerImpl extends BaseServiceImpl<AnFillblank, String> implements AnFillblankManager {

	@Autowired
	private AnFillblankDao anFillblankDao;

	@Autowired
	private AnFillblankMapper anFillblankMapper;
	@Override
	public void setBaseDao() {
		this.baseDao=anFillblankDao;
	}

	//根据exam_user信息查询答案
	public AnFillblank findAnswer(String belongAnswerId, String quId){
		//belongAnswerId quId
		QueryWrapper<AnFillblank> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belong_answer_id", belongAnswerId);
		queryWrapper.eq("qu_id", quId);
		return anFillblankMapper.selectOne(queryWrapper);
		//begin delete  by jesse at 2020-07-15  for 优化
		//		Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
		//		Criterion criterion2=Restrictions.eq("quId", quId);
		//		return anFillblankDao.findUnique(criterion1,criterion2);
		//end delete by jesse at 2020-07-15
	}

    @Override
    public void findGroupStats(Question question) {
//		anFillblankDao.findGroupStats(question);
        AnFillblankDTO anFillblankDTO = anFillblankMapper.findGroupStats(question.getId());
        question.setRowContent(anFillblankDTO.getEmptyCount().toString());//未回答数
        question.setOptionContent(anFillblankDTO.getBlankCount().toString());//回答的项数
        question.setAnCount(Integer.parseInt(anFillblankDTO.getBlankCount().toString()));
    }


	@Override
	public Page<AnFillblank> findPage(Page<AnFillblank> page, String quId) {
		com.baomidou.mybatisplus.extension.plugins.pagination.Page<AnFillblank> page1 = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
		Map<String,Object> map = new HashMap<>();
		map.put("quId",quId);
		map.put("visibility",1);
		List<AnFillblank> anFillblanks = anFillblankMapper.selectPage(page1,map);
		IPageToPage<AnFillblank> iPageToPage = new IPageToPage<>();
		iPageToPage.setCurrent(page.getPageNo());
		iPageToPage.setSize(page.getPageSize());
		iPageToPage.setRecords(anFillblanks);
		page = new Page<>(iPageToPage);
		page.setTotalItems(page1.getTotal());
		return page;
		//TODO 需要验证
		//begin delete  by jesse at 2020-07-15  for 优化
		//		Criterion cri1 = Restrictions.eq("quId",quId);
		//		Criterion cri2 = Restrictions.eq("visibility",1);
		//		return findPage(page,cri1,cri2);
		//end delete by jesse at 2020-07-15

	}
}
