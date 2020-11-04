package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.plugs.page.Page;
import com.key.common.service.BaseServiceImpl;
import com.key.dwsurvey.DTO.AnDfillblankDTO;
import com.key.dwsurvey.dao.AnDFillblankDao;
import com.key.dwsurvey.entity.AnDFillblank;
import com.key.dwsurvey.entity.QuMultiFillblank;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.mapper.AnDFillblankMapper;
import com.key.dwsurvey.service.AnDFillblankManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 多行填空题
 *
 * @author keyuan(keyuan258 @ gmail.com)
 * <p>
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */
@Service
public class AnDFillblankManagerImpl extends BaseServiceImpl<AnDFillblank, String> implements AnDFillblankManager {

    @Autowired
    private AnDFillblankDao anDFillblankDao;
    @Autowired
    private AnDFillblankMapper anDFillblankMapper;
	@Override
	public void setBaseDao() {
		this.baseDao=anDFillblankDao;
	}

	//根据exam_user信息查询答案
	public List<AnDFillblank> findAnswer(String belongAnswerId,String quId){
		//belongAnswerId quId
		QueryWrapper<AnDFillblank> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belong_answer_id", belongAnswerId);
		queryWrapper.eq("qu_id", quId);
		return anDFillblankMapper.selectList(queryWrapper);
		//begin delete  by jesse at 2020-07-15  for 优化
		//		Criterion criterion1=Restrictions.eq("belongAnswerId", belongAnswerId);
		//		Criterion criterion2=Restrictions.eq("quId", quId);
		//		return anDFillblankDao.find(criterion1,criterion2);
		//end delete by jesse at 2020-07-15

	}

    @Override
    public void findGroupStats(Question question) {
//        anDFillblankDao.findGroupStats(question);
        List<QuMultiFillblank> quMultiFillblanks = question.getQuMultiFillblanks();

        List<AnDfillblankDTO> list = anDFillblankMapper.findGroupStats(question.getId());
        for (QuMultiFillblank quMultiFillblank : quMultiFillblanks) {
            String quMultiFillblankId = quMultiFillblank.getId();
            for (AnDfillblankDTO item : list) {
                if (quMultiFillblankId.equals(item.getQuItemId().toString())) {
                    quMultiFillblank.setAnCount(item.getCount());
                    break;
                }
            }
        }
    }

    public Page<AnDFillblank> findPage(Page<AnDFillblank> page, String quItemId) {
        Criterion cri1 = Restrictions.eq("quItemId", quItemId);
        Criterion cri2 = Restrictions.eq("visibility", 1);
        return findPage(page, cri1, cri2);
    }

}
