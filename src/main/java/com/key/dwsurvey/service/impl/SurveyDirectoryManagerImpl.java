package com.key.dwsurvey.service.impl;

import java.io.File;
import java.util.*;

import com.alibaba.druid.pool.WrapperAdapter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.key.common.plugs.page.IPageToPage;
import com.key.common.utils.IdUtils;
import com.key.dwsurvey.dao.SurveyDirectoryDao;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.entity.SurveyDetail;
import com.key.dwsurvey.entity.SurveyStats;
import com.key.dwsurvey.mapper.SurveyDetailMapper;
import com.key.dwsurvey.mapper.SurveyDirectoryMapper;
import com.key.dwsurvey.mapper.SurveyStatsMapper;
import com.key.dwsurvey.mapper.UserMapper;
import com.key.dwsurvey.service.SurveyDirectoryManager;
import com.key.dwsurvey.service.SurveyStatsManager;
import com.key.dwsurvey.service.UserManager;
import com.key.dwsurvey.entity.SurveyDirectory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import com.key.common.plugs.page.Page;
import com.key.common.service.BaseServiceImpl;
import com.key.common.utils.RandomUtils;
import com.key.common.QuType;
import com.key.dwsurvey.service.QuestionBankManager;
import com.key.dwsurvey.service.QuestionManager;
import com.key.dwsurvey.service.SurveyAnswerManager;
import com.key.dwsurvey.service.SurveyDetailManager;


/**
 * 问卷
 */
@Slf4j
@Service("surveyDirectoryManager")
public class SurveyDirectoryManagerImpl extends BaseServiceImpl<SurveyDirectory, String> implements SurveyDirectoryManager {

    @Autowired
    private SurveyDirectoryDao surveyDirectoryDao;


    @Autowired
    private SurveyDetailManager surveyDetailManager;
    @Autowired
    private QuestionManager questionManager;
    @Autowired
    private SurveyStatsManager surveyStatsManager;
    @Autowired
    private SurveyAnswerManager surveyAnswerManager;
    @Autowired
    private AccountManager accountManager;
    @Autowired
    private QuestionBankManager questionBankManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private SurveyDetailMapper detailMapper;
    @Autowired
    private SurveyDirectoryMapper surveyDirectoryMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SurveyStatsMapper surveyStatsMapper;
    @Override
    public void setBaseDao() {
        this.baseDao = surveyDirectoryDao;
    }

    @Transactional
    @Override
    public void save(SurveyDirectory surveyDirectory) {
        User user = accountManager.getCurUser();
        String userId = surveyDirectory.getUserId();
        String id = surveyDirectory.getId();
        if (id == null) {
            surveyDirectory.setUserId(user.getId());
            userId = surveyDirectory.getUserId();
        }
        if (userId != null && userId.equals(user.getId())) {
            String sId = surveyDirectory.getSid();
            if (sId == null || "".equals(sId)) {
                sId = RandomUtils.randomStr(6, 12);
                surveyDirectory.setSid(sId);
            }
            surveyDirectoryMapper.updateByPrimaryKey(surveyDirectory);
//			surveyDirectoryDao.save(t);
            //保存SurveyDirectory
            if (surveyDirectory.getDirType() == 2) {
                SurveyDetail surveyDetailTemp = surveyDirectory.getSurveyDetail();

                SurveyDetail surveyDetail = surveyDetailManager.getBySurveyId(id);
                if (surveyDetail != null) {
                    if (surveyDetailTemp != null) {
                        surveyDetail.setSurveyNote(surveyDetailTemp.getSurveyNote());
                    }
                } else {
                    surveyDetail = new SurveyDetail();
                    surveyDetail.setSurveyNote("非常感谢您的参与！如有涉及个人信息，我们将严格保密。");
                }
                surveyDetail.setDirId(surveyDirectory.getId());
                if (surveyDetail.getId() != null) {
                    detailMapper.updateByPrimaryKey(surveyDetail);
                } else {
                    detailMapper.insertSelective(surveyDetail);
                }
//                surveyDetailManager.save(surveyDetail);
            }
        }
    }

    @Transactional
    public void saveByAdmin(SurveyDirectory surveyDirectory) {
        String sId = surveyDirectory.getSid();
        if (sId == null || "".equals(sId)) {
            sId = RandomUtils.randomStr(6, 12);
            surveyDirectory.setSid(sId);
        }
        surveyDirectoryMapper.updateByPrimaryKeySelective(surveyDirectory);
//        surveyDirectoryDao.save(surveyDirectory);
    }

    /**
     * 得到当前目录所在的目录位置
     */
    public List<SurveyDirectory> findPath(SurveyDirectory surveyDirectory) {
        List<SurveyDirectory> resultList = new ArrayList<SurveyDirectory>();
        if (surveyDirectory != null) {
            List<SurveyDirectory> dirPathList = new ArrayList<SurveyDirectory>();
            dirPathList.add(surveyDirectory);
            String parentUuid = surveyDirectory.getParentId();
            while (parentUuid != null && !"".equals(parentUuid)) {
                SurveyDirectory surveyDirectory2 = surveyDirectoryMapper.selectByPrimaryKey(parentUuid);
//                SurveyDirectory surveyDirectory2 = get(parentUuid);
                parentUuid = surveyDirectory2.getParentId();
                dirPathList.add(surveyDirectory2);
            }
            for (int i = dirPathList.size() - 1; i >= 0; i--) {
                resultList.add(dirPathList.get(i));
            }
        }
        return resultList;
    }

    @Override
    public SurveyDirectory getSurveyBySid(String sid) {
//        SurveyDirectory surveyDirectory = surveyDirectoryMapper.selectOne(new QueryWrapper<SurveyDirectory>().eq("sid", sid));
//        Criterion criterion = Restrictions.eq("sid", sid);
//        SurveyDirectory surveyDirectory = surveyDirectoryDao.findUnique(criterion);
        SurveyDirectory surveyDirectory = surveyDirectoryMapper.selectBySid(sid);
        getSurveyDetail(surveyDirectory.getId(), surveyDirectory);
        return surveyDirectory;
    }

    @Override
    public SurveyDirectory getSurvey(String id) {
        if (id == null || "".equals(id)) {
            return new SurveyDirectory();
        }
        SurveyDirectory surveyDirectory = surveyDirectoryMapper.selectByPrimaryKey(id);
//		SurveyDirectory directory=get(id);
//		getSurveyDetail(id,directory);
        surveyDirectory = getSurveyDetail(id, surveyDirectory);
//		return directory;
        return surveyDirectory;
    }

    @Transactional
    public SurveyDirectory getSurveyByUser(String id, String userId) {
        SurveyDirectory directory = surveyDirectoryMapper.selectByPrimaryKey(id);
        SurveyDetail surveyDetail = detailMapper.queryByDirId(id);
        if (userId.equals(directory.getUserId())) {
            if (surveyDetail != null) {
                directory.setSurveyDetailId(surveyDetail.getId());
            }
            //	getSurveyDetail(id,directory);
            directory.setSurveyDetail(surveyDetail);
            return directory;
        }
        return null;
    }

    public SurveyDirectory getSurveyDetail(String id, SurveyDirectory directory) {

        //TODO 修改成mapper
        String surveyDetailId = directory.getSurveyDetailId();
        SurveyDetail surveyDetail = null;
        if (surveyDetailId != null) {
            surveyDetail = detailMapper.selectByPrimaryKey(surveyDetailId);
            //			surveyDetail=surveyDetailManager.get(surveyDetailId);

        }
        if (surveyDetail == null) {
            QueryWrapper<SurveyDetail> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("dir_id", id);
            List<SurveyDetail> surveyDetails = detailMapper.selectList(queryWrapper);
            if (surveyDetails != null && surveyDetails.size() > 0) {
                surveyDetail = surveyDetails.get(0);
            }
//			surveyDetail=surveyDetailManager.getBySurveyId(id);
        }
        if (surveyDetail == null) {
            surveyDetail = new SurveyDetail();
        }
        directory.setSurveyDetail(surveyDetail);
        return directory;
    }

    @Override
    public void upSurveyData(SurveyDirectory entity) {
        List<Question> questions = questionManager.findDetails(entity.getId(), "2");
        if (questions != null) {
            int anItemLeastNum = 0;
            for (Question question : questions) {
                QuType quType = question.getQuType();
                if (quType == QuType.ENUMQU) {//枚举
                    anItemLeastNum += question.getParamInt01();
                } else if (quType == QuType.MULTIFILLBLANK) {//组合填空
                    anItemLeastNum += question.getQuMultiFillblanks().size();
                } else if (quType == QuType.SCORE) {//评分
                    anItemLeastNum += question.getQuScores().size();
                } else if (quType == QuType.CHENRADIO || quType == QuType.CHENCHECKBOX) {//矩阵单选 矩阵多选
                    anItemLeastNum += question.getRows().size();
                } else if (quType == QuType.CHENFBK || quType == QuType.COMPCHENRADIO) {//矩阵填空 复合矩阵单选
                    anItemLeastNum += question.getRows().size() * question.getColumns().size();
                } else {
                    anItemLeastNum++;
                }
            }
            entity.setSurveyQuNum(questions.size());
            entity.setAnItemLeastNum(anItemLeastNum);
        }
    }

    @Override
    @Transactional
    public void executeSurvey(SurveyDirectory entity) {
        entity.setSurveyState(1);
        //计算可以回答的题量
        upSurveyData(entity);
        surveyDirectoryMapper.updateByPrimaryKeySelective(entity);
//        super.save(entity);
        //生成全局统计结果记录表

        SurveyStats surveyStats = new SurveyStats();
        surveyStats.setSurveyId(entity.getId());
        surveyStatsMapper.updateByPrimaryKey(surveyStats);
//        surveyStatsManager.save(surveyStats);
    }

    @Override
    @Transactional
    public void closeSurvey(SurveyDirectory entity) {
        entity.setSurveyState(2);
        //计算可以回答的题量
        surveyDirectoryMapper.updateByPrimaryKeySelective(entity);
//        super.save(entity);
        //生成全局统计结果记录表
    }

    @Override
    @Transactional
    public void delete(String id) {
        //设为不可见
        SurveyDirectory parentDirectory = surveyDirectoryMapper.selectByPrimaryKey(id);
//        SurveyDirectory parentDirectory = get(id);
        parentDirectory.setVisibility(0);
        surveyDirectoryMapper.updateByPrimaryKeySelective(parentDirectory);
//        surveyDirectoryDao.save(parentDirectory);
        List<SurveyDirectory> directories = surveyDirectoryMapper.selectList(new QueryWrapper<SurveyDirectory>().eq("parent_id", parentDirectory.getId()));
//        Criterion criterion = Restrictions.eq("parentId", parentDirectory.getId());
//        List<SurveyDirectory> directories = findList(criterion);
        if (directories != null) {
            for (SurveyDirectory surveyDirectory : directories) {
                delete(surveyDirectory);
            }
        }
    }

    @Transactional
    public void delete(SurveyDirectory parentDirectory) {
        String id = parentDirectory.getId();
        //目录ID，为1的为系统默认注册用户目录不能删除
        if (!"1".equals(id)) {
            //设为不可见
            parentDirectory.setVisibility(0);
            List<SurveyDirectory> directories = surveyDirectoryMapper.selectList(new QueryWrapper<SurveyDirectory>().eq("parent_id", parentDirectory.getId()));
//            Criterion criterion = Restrictions.eq("parentId", parentDirectory.getId());
//            List<SurveyDirectory> directories = findList(criterion);
            if (directories != null) {
                for (SurveyDirectory surveyDirectory : directories) {
                    delete(surveyDirectory);
                }
            }
        }
    }

    @Override
    public SurveyDirectory findByNameUn(String id, String parentId, String surveyName) {
        Map<String, String> map = new HashMap<>();
        map.put("surveyName", surveyName);
        map.put("parentId", parentId);
//		List<Criterion> criterions=new ArrayList<Criterion>();
//		Criterion eqName=Restrictions.eq("surveyName", surveyName);
//		Criterion eqParentId=Restrictions.eq("parentId", parentId);
//		criterions.add(eqName);
//		criterions.add(eqParentId);


        if (id != null && !"".equals(id)) {
            map.put("id", id);
//			Criterion eqId=Restrictions.ne("id", id);
//			criterions.add(eqId);

        }

        SurveyDirectory surveyDirectory = surveyDirectoryMapper.findFirst(map);
        User user = userMapper.selectByPrimaryKey(surveyDirectory.getUserId());
        surveyDirectory.setUserName(user.getName());
//		SurveyDirectory surveyDirectory1 = surveyDirectoryDao.findFirst(criterions);
//		return surveyDirectoryDao.findFirst(criterions);
        return surveyDirectory;
    }

    @Override
    public SurveyDirectory findByNameUserUn(String id, String surveyName) {
        User user = accountManager.getCurUser();
        Map<String, String> map = new HashMap<>();
        if (user != null) {
            map.put("surveyName", surveyName);
            map.put("userId", user.getId());
//            List<Criterion> criterions = new ArrayList<Criterion>();
//            Criterion eqName = Restrictions.eq("surveyName", surveyName);
//            Criterion eqUserId = Restrictions.eq("userId", user.getId());
//            criterions.add(eqName);
//            criterions.add(eqUserId);

            if (id != null && !"".equals(id)) {
                map.put("id", id);
//                Criterion eqId = Restrictions.ne("id", id);
//                criterions.add(eqId);
            }
            SurveyDirectory surveyDirectory = surveyDirectoryMapper.findFirst(map);
            surveyDirectory.setUserName(user.getName());
            return surveyDirectory;
//            return surveyDirectoryDao.findFirst(criterions);
        }
        return null;
    }

    @Override
    @Transactional
    public void backDesign(SurveyDirectory entity) {
        entity.setSurveyState(0);
        //计算可以回答的题量
        surveyDirectoryMapper.updateByPrimaryKeySelective(entity);
//        super.save(entity);
    }

    @Transactional
    public void checkUp(SurveyDirectory entity) {
        //计算可以回答的题量
        if (entity.getId() == null) {
            surveyDirectoryMapper.insertSelective(entity);
        } else {
            surveyDirectoryMapper.updateByPrimaryKeySelective(entity);
        }
//        super.save(entity);
    }


    @Transactional
    public void upSuveyText(SurveyDirectory surveyDirectory) {
        String id = surveyDirectory.getId();
        if (id != null && id.length() > 0) {
            surveyDirectoryMapper.updateByPrimaryKeySelective(surveyDirectory);
//            super.save(surveyDirectory);
            //保存SurveyDirectory
            if (surveyDirectory.getDirType() == 2) {
                SurveyDetail surveyDetail = surveyDirectory.getSurveyDetail();
                surveyDetail.setDirId(surveyDirectory.getId());
                detailMapper.insertSelective(surveyDetail);
//                surveyDetailManager.save(surveyDetail);
            }
        }
    }

    @Transactional
    public void saveUser(SurveyDirectory surveyDirectory) {
        if (surveyDirectory.getId() == null) {
            surveyDirectoryMapper.insertSelective(surveyDirectory);
        } else {
            surveyDirectoryMapper.updateByPrimaryKeySelective(surveyDirectory);
        }
//        super.save(surveyDirectory);
        //保存SurveyDirectory
        if (surveyDirectory.getDirType() == 2) {
            SurveyDetail surveyDetail = surveyDirectory.getSurveyDetail();
            surveyDetail.setDirId(surveyDirectory.getId());
            detailMapper.insertSelective(surveyDetail);
//            surveyDetailManager.save(surveyDetail);
        }
    }

    public void saveUserSurvey(SurveyDirectory entity) {
        User user = accountManager.getCurUser();
        if (user != null) {
            String enId = entity.getId();
            String userId = user.getId();
            if (enId == null || "".equals(enId)) {
                //根据用户名得到目录
                String loginName = user.getLoginName();
                SurveyDirectory surveyDirUser = findByNameUn(null, "1", loginName);
                if (surveyDirUser == null) {
                    //没有此目录则创建此目录，你ID=1
                    surveyDirUser = new SurveyDirectory();
                    surveyDirUser.setSurveyName(loginName);
                    surveyDirUser.setDirType(1);
                    surveyDirUser.setUserId(user.getId());
                    surveyDirUser.setParentId("1");
                    saveUser(surveyDirUser);
                }
                entity.setParentId(surveyDirUser.getId());

                entity.setUserId(userId);
                saveUser(entity);
            } else {
                //判断当前人有无权限修改
                String enUserId = entity.getUserId();
                if (userId.equals(enUserId)) {
                    entity.setUserId(userId);
                    saveUser(entity);
                }
            }
        }
    }

    @Override
    public Page<SurveyDirectory> findPage(Page<SurveyDirectory> page,
                                          SurveyDirectory entity) {
//        page.setOrderBy("createDate");
//        page.setOrderDir("desc");

        IPageToPage<SurveyDirectory> iPageToPage = new IPageToPage<>();
        iPageToPage.setOrderBy("createDate");
        iPageToPage.setOrderDir("desc");

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<SurveyDirectory> page1 =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<SurveyDirectory>(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = new HashMap<>();

        map.put("createDate", "create_date");
        map.put("desc", "desc");
        map.put("visibility", 1);
        map.put("surveyState", 1);
        map.put("dirType", 2);
        map.put("surveyModel", 1);
//
//        List<Criterion> criterions = new ArrayList<Criterion>();
//        criterions.add(Restrictions.eq("visibility", 1));
//        criterions.add(Restrictions.eq("surveyState", 1));
//
//        criterions.add(Restrictions.eq("dirType", 2));
//        criterions.add(Restrictions.eq("surveyModel", 1));


        Integer share = entity.getIsShare();
        if (share != null && share == 1) {
            map.put("isShare", share);
//            criterions.add(Restrictions.eq("isShare", share));
        }
        List<SurveyDirectory> surveyDirectories = surveyDirectoryMapper.selectPage(page1, map);
        iPageToPage.setRecords(surveyDirectories);
        page = new Page<>(iPageToPage);
        page.setResult(surveyDirectories);
        page.setTotalItems(page1.getTotal());
        return page;
//        return surveyDirectoryDao.findPageList(page, criterions);
    }


    public List<SurveyDirectory> newSurveyList() {
        List<SurveyDirectory> result = new ArrayList<SurveyDirectory>();
        try {
            SurveyDirectory entity = new SurveyDirectory();
            Page<SurveyDirectory> page = new Page<SurveyDirectory>();
            page.setPageSize(25);
            page = findPage(page, entity);
            result = page.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Transactional
    public void saveAll(SurveyDirectory directory) {
        List<Question> questions = directory.getQuestions();
        directory.setDirType(2);
        directory.setParentId("402880e5428a2dca01428a2f1f290000");
        directory.setSurveyTag(0);
        directory.setSurveyQuNum(questions.size());
        if (directory.getId() == null) {
            surveyDirectoryMapper.insertSelective(directory);
        } else {
            surveyDirectoryMapper.updateByPrimaryKeySelective(directory);
        }
//        surveyDirectoryDao.save(directory);
        String surveyId = directory.getId();
        //详细信息
        SurveyDetail detail = directory.getSurveyDetail();
        detail.setDirId(surveyId);
        if (detail.getId() == null) {
            detailMapper.insertSelective(detail);
        } else {
            detailMapper.updateByPrimaryKey(detail);
        }
//        surveyDetailManager.save(detail);
//		directory.setSurveyDetailId(detail.getId());
        //题目列表
        for (Question question : questions) {
            if (question != null) {
                question.setBelongId(surveyId);
                question.setCreateDate(directory.getCreateDate());
                question.setTag(2);
                questionManager.save(question);
            }
        }
    }

    @Override
    public SurveyDirectory findNext(SurveyDirectory directory) {

        Date date = directory.getCreateDate();
//        Criterion criterion = Restrictions.gt("createDate", date);

        Map<String, Object> map = new HashMap<>();
        map.put("createDate", date);
        return surveyDirectoryMapper.findFirst(map);
//        return surveyDirectoryDao.findFirst(criterion);
    }

    @Override
    public Page<SurveyDirectory> findByUser1(Page<SurveyDirectory> page,
                                            SurveyDirectory entity) {
        IPageToPage<SurveyDirectory> surveyDirectoryIPageToPage = new IPageToPage<>();
        surveyDirectoryIPageToPage.setCurrent(page.getPageNo());
        surveyDirectoryIPageToPage.setSize(page.getPageSize());
        Page<SurveyDirectory> page2 = new Page<>();
        User user = accountManager.getCurUser();
        if (user != null) {
            List<Criterion> criterions = new ArrayList<Criterion>();
            QueryWrapper<SurveyDirectory> queryWrapper = new QueryWrapper<>();
            Map<String, Object> map = new HashMap<>();

            map.put("userId", user.getId());
            map.put("visibility", 1);
            map.put("dirType", 2);
            map.put("surveyModel", 1);

            queryWrapper.eq("user_id", user.getId());
            queryWrapper.eq("visibility", 1);
            queryWrapper.eq("dir_type", 2);
            queryWrapper.eq("survey_model", 1);


            criterions.add(Restrictions.eq("userId", user.getId()));
            criterions.add(Restrictions.eq("visibility", 1));
            criterions.add(Restrictions.eq("dirType", 2));
            criterions.add(Restrictions.eq("surveyModel", 1));

            if (entity != null) {
                Integer surveyState = entity.getSurveyState();
                if (surveyState != null && !"".equals(surveyState)) {
                    map.put("surveyState", surveyState);
                    queryWrapper.eq("survey_state", surveyState);
                    criterions.add(Restrictions.eq("surveyState", surveyState));
                }
                String surveyName = entity.getSurveyName();
                if (surveyName != null && !"".equals(surveyName)) {
                    queryWrapper.like("survey_name", surveyName);
                    map.put("surveyName", "%" + surveyName + "%");
                    criterions.add(Restrictions.like("surveyName", "%" + surveyName + "%"));
                }
            }

            page.setOrderBy("createDate");
            page.setOrderDir("desc");
            map.put("orderBy", "create_date");
            map.put("orderDir", "desc");
            surveyDirectoryIPageToPage.setOrderBy("createDate");
            surveyDirectoryIPageToPage.setOrderDir("desc");

//			surveyDirectoryMapper
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<SurveyDirectory> page1 =
                    new com.baomidou.mybatisplus.extension.plugins.pagination.Page<SurveyDirectory>(page.getPageNo(), page.getPageSize());
            List<SurveyDirectory> surveyDirectories = surveyDirectoryMapper.selectPage(page1, map);
            List<SurveyDirectory> surveyDirectories1 = surveyDirectoryMapper.selectPage1(page1, queryWrapper);
            surveyDirectoryIPageToPage.setRecords(surveyDirectories);
            page2 = new Page<>(surveyDirectoryIPageToPage);

            page2.setResult(surveyDirectories);
            QueryWrapper<SurveyDirectory> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("user_id", user.getId());
            queryWrapper1.eq("visibility", 1);
            queryWrapper1.eq("dir_type", 2);
            queryWrapper1.eq("survey_model", 1);
            Integer count = surveyDirectoryMapper.selectCount(queryWrapper);
            page2.setTotalItems(count);


            page = surveyDirectoryDao.findPageList(page, criterions);
            log.info("page2=" + page2);
        }
        return page;
    }

    @Override
    public Page<SurveyDirectory> findByUser(Page<SurveyDirectory> page,
                                             SurveyDirectory entity) {
        IPageToPage<SurveyDirectory> surveyDirectoryIPageToPage = new IPageToPage<>();
        surveyDirectoryIPageToPage.setCurrent(page.getPageNo());
        surveyDirectoryIPageToPage.setSize(page.getPageSize());
        User user = accountManager.getCurUser();
        if (user != null) {
            QueryWrapper<SurveyDirectory> queryWrapper = new QueryWrapper<>();
            Map<String, Object> map = new HashMap<>();
            map.put("userId", user.getId());
            map.put("visibility", 1);
            map.put("dirType", 2);
            map.put("surveyModel", 1);

            queryWrapper.eq("user_id", user.getId());
            queryWrapper.eq("visibility", 1);
            queryWrapper.eq("dir_type", 2);
            queryWrapper.eq("survey_model", 1);
            if (entity != null) {
                Integer surveyState = entity.getSurveyState();
                if (surveyState != null && !"".equals(surveyState)) {
                    map.put("surveyState", surveyState);
                    queryWrapper.eq("survey_state", surveyState);
                }
                String surveyName = entity.getSurveyName();
                if (surveyName != null && !"".equals(surveyName)) {
                    queryWrapper.like("survey_name", surveyName);
                    map.put("surveyName", surveyName);
                }
            }

            page.setOrderBy("createDate");
            page.setOrderDir("desc");
            map.put("orderBy", "create_date");
            map.put("orderDir", "desc");
            surveyDirectoryIPageToPage.setOrderBy("createDate");
            surveyDirectoryIPageToPage.setOrderDir("desc");

//			surveyDirectoryMapper
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<SurveyDirectory> page1 =
                    new com.baomidou.mybatisplus.extension.plugins.pagination.Page<SurveyDirectory>(page.getPageNo(), page.getPageSize());
            List<SurveyDirectory> surveyDirectories = surveyDirectoryMapper.selectPage(page1, map);
            surveyDirectories.forEach(
                    surveyDirectory1 -> surveyDirectory1.setUserName(userMapper.selectByPrimaryKey(surveyDirectory1.getUserId()).getName()));
            surveyDirectoryIPageToPage.setRecords(surveyDirectories);
            page = new Page<>(surveyDirectoryIPageToPage);
            page.setResult(surveyDirectories);
            page.setTotalItems(page1.getTotal());

            log.info("page2=" + page);
        }
        return page;
    }

    public Page<SurveyDirectory> findByGroup(String groupId1, String groupId2, Page<SurveyDirectory> page) {

        IPageToPage<SurveyDirectory> iPageToPage = new IPageToPage<>();
        iPageToPage.setOrderBy("createDate");
        iPageToPage.setOrderDir("desc");

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<SurveyDirectory> page1 =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<SurveyDirectory>(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = new HashMap<>();

        map.put("createDate", "create_date");
        map.put("desc", "desc");


        //TODO 有问题 surveyDirectory 中没有 groupId1和groupId2
//        List<Criterion> criterions = new ArrayList<Criterion>();
        if (groupId1 != null && !"".equals(groupId1)) {
            map.put("groupId1", groupId1);
//            Criterion cri1 = Restrictions.eq("groupId1", groupId1);
//            criterions.add(cri1);
        }
        if (groupId2 != null && !"".equals(groupId2)) {
            map.put("groupId2", groupId2);
//            Criterion cri1_2 = Restrictions.eq("groupId2", groupId2);
//            criterions.add(cri1_2);
        }
        map.put("visibility", 1);
        map.put("surveyModel", 4);
        List<SurveyDirectory> surveyDirectories = surveyDirectoryMapper.selectPage(page1, map);
        iPageToPage.setRecords(surveyDirectories);
        page = new Page<>(iPageToPage);
        page.setResult(surveyDirectories);
        page.setTotalItems(page1.getTotal());

        log.info("page=" + page);
        return page;
//        Criterion cri2 = Restrictions.eq("visibility", 1);
//        Criterion cri4 = Restrictions.eq("surveyModel", 4);
//
//        criterions.add(cri2);
//        criterions.add(cri4);
//        page.setOrderBy("createDate");
//        page.setOrderDir("desc");
//
//        return surveyDirectoryDao.findPage(page, criterions.toArray(new Criterion[criterions.size()]));
    }


    @Override
    public Page<SurveyDirectory> findModel(Page<SurveyDirectory> page,
                                           SurveyDirectory entity) {


        IPageToPage<SurveyDirectory> iPageToPage = new IPageToPage<>();
        iPageToPage.setOrderBy("createDate");
        iPageToPage.setOrderDir("desc");

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<SurveyDirectory> page1 =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<SurveyDirectory>(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = new HashMap<>();

        map.put("createDate", "create_date");
        map.put("desc", "desc");


        Integer surveyState = entity.getSurveyState();
        String surveyName = entity.getSurveyName();
//        List<Criterion> criterions = new ArrayList<Criterion>();

        if (surveyState != null && surveyState.intValue() != 100) {
            map.put("surveyState", surveyState);
//            Criterion cri1 = Restrictions.eq("surveyState", surveyState);
//            criterions.add(cri1);
        }
        if (surveyName != null && !"".equals(surveyName)) {
            map.put("surveyName", surveyName);
//            Criterion cri1 = Restrictions.like("surveyName", "%" + surveyName + "%");
//            criterions.add(cri1);
        }
        map.put("visibility", 1);
        map.put("surveyModel", 4);
        List<SurveyDirectory> surveyDirectories = surveyDirectoryMapper.selectPage(page1, map);
        iPageToPage.setRecords(surveyDirectories);
        page = new Page<>(iPageToPage);
        page.setResult(surveyDirectories);
        page.setTotalItems(page1.getTotal());

        log.info("page=" + page);
        return page;
//        Criterion cri2 = Restrictions.eq("visibility", 1);
//        criterions.add(cri2);
//        Criterion cri4 = Restrictions.eq("surveyModel", 4);
//        criterions.add(cri4);
//        page.setOrderBy("createDate");
//        page.setOrderDir("desc");
//        return surveyDirectoryDao.findPageList(page, criterions);
    }

    @Override
    public List<SurveyDirectory> findByIndex() {
        QueryWrapper<SurveyDirectory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("visibility", 1);
        queryWrapper.eq("parent_id", "402880e5428a2dca01428a2f1f290000");
        queryWrapper.eq("survey_tag", 1);
        queryWrapper.isNull("sid");
        queryWrapper.orderByDesc("create_date");
        List<SurveyDirectory> surveyDirectories = surveyDirectoryMapper.selectList(queryWrapper).subList(0, 10);
        return surveyDirectories;
//        Criterion cri1 = Restrictions.eq("visibility", 1);
//        Criterion cri2 = Restrictions.eq("parentId", "402880e5428a2dca01428a2f1f290000");
//        Criterion cri3 = Restrictions.eq("surveyTag", 1);
//        Criterion cri4 = Restrictions.isNull("sid");
//        Page<SurveyDirectory> page = new Page<SurveyDirectory>();
//        page.setOrderBy("createDate");
//        page.setOrderDir("desc");
//        page.setPageSize(10);
//        List<SurveyDirectory> surveys = surveyDirectoryDao.findPage(page, cri1, cri2, cri3, cri4).getResult();
//        return surveys;
    }

    @Override
    public List<SurveyDirectory> findByT1() {
        QueryWrapper<SurveyDirectory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("visibility", 1);
        queryWrapper.eq("parent_id", "402880e5428a2dca01428a2f1f290000");
        queryWrapper.eq("survey_tag", 1);
        queryWrapper.isNull("sid");
        queryWrapper.orderByDesc("create_date");
        List<SurveyDirectory> surveyDirectories = surveyDirectoryMapper.selectList(queryWrapper).subList(0, 10);
        return surveyDirectories;
//        Criterion cri1 = Restrictions.eq("visibility", 1);
//        Criterion cri2 = Restrictions.eq("parentId", "402880e5428a2dca01428a2f1f290000");
//        Criterion cri3 = Restrictions.eq("surveyTag", 1);
//        Criterion cri4 = Restrictions.isNull("sid");
//        Page<SurveyDirectory> page = new Page<SurveyDirectory>();
//        page.setOrderBy("createDate");
//        page.setOrderDir("desc");
//        page.setPageSize(10);
//        List<SurveyDirectory> surveys = surveyDirectoryDao.findPage(page, cri1, cri2, cri3, cri4).getResult();
//        return surveys;
    }

    @Override
    public SurveyDirectory createBySurvey(String fromBankId, String surveyName,
                                          String tag) {//new
        SurveyDirectory surveyDirectory = buildCopyObj(fromBankId, surveyName,
                tag);

        saveUserSurvey(surveyDirectory);
        String belongId = surveyDirectory.getId();
        List<Question> questions = questionManager.find(fromBankId, tag);
        questionManager.saveBySurvey(belongId, 2, questions);
        return surveyDirectory;
    }

    private SurveyDirectory buildCopyObj(String fromBankId, String surveyName, String tag) {
        SurveyDirectory surveyDirectory = new SurveyDirectory();
        surveyDirectory.setSurveyName(surveyName);
        surveyDirectory.setDirType(2);
        surveyDirectory.setSurveyDetail(new SurveyDetail());
        SurveyDirectory directory = getSurvey(fromBankId);
        directory.setExcerptNum(directory.getExcerptNum() + 1);
        surveyDirectoryMapper.updateByPrimaryKey(directory);
//        super.save(directory);
        surveyDirectory.setSurveyQuNum(directory.getSurveyQuNum());
        surveyDirectory.getSurveyDetail().setSurveyNote(surveyDirectory.getSurveyDetail().getSurveyNote());
        return surveyDirectory;
    }


    @Override
    public boolean insertOrUpdate(SurveyDirectory surveyDirectory) {
        User user = accountManager.getCurUser();
        String userId = surveyDirectory.getUserId();
        String id = surveyDirectory.getId();
        if (id == null) {
            surveyDirectory.setUserId(user.getId());
            userId = surveyDirectory.getUserId();
        }
        if (userId != null && userId.equals(user.getId())) {
            String sId = surveyDirectory.getSid();
            if (sId == null || "".equals(sId)) {
                sId = RandomUtils.randomStr(6, 12);
                surveyDirectory.setSid(sId);
            }

            if (surveyDirectory.getId() == null) {
                surveyDirectory.setId(new IdUtils().getId());
                surveyDirectoryMapper.insertSelective(surveyDirectory);
            } else {
                surveyDirectoryMapper.updateByPrimaryKeySelective(surveyDirectory);
                detailMapper.updateByPrimaryKeySelective(surveyDirectory.getSurveyDetail());
            }
            //保存SurveyDirectory
            if (surveyDirectory.getDirType() == 2) {
                SurveyDetail surveyDetailTemp = surveyDirectory.getSurveyDetail();

                SurveyDetail surveyDetail = surveyDetailManager.getBySurveyId(id);
                if (surveyDetail != null) {
                    if (surveyDetailTemp != null) {
                        surveyDetail.setSurveyNote(surveyDetailTemp.getSurveyNote());
                    }
                } else {
                    surveyDetail = new SurveyDetail();
                    surveyDetail.setSurveyNote("非常感谢您的参与！如有涉及个人信息，我们将严格保密。");
                }
                surveyDetail.setDirId(surveyDirectory.getId());

                if (surveyDetail.getId() == null) {
                    surveyDetail.setId(new IdUtils().getId());
                    detailMapper.insertSelective(surveyDetail);
                } else {
                    detailMapper.updateByPrimaryKeySelective(surveyDetail);
                }
            }
        }
        return true;
    }
}
