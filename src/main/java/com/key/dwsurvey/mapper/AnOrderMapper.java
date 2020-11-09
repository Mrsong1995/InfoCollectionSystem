package com.key.dwsurvey.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.key.dwsurvey.DTO.AnOrderDTO;
import com.key.dwsurvey.entity.AnOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnOrderMapper extends BaseMapper<AnOrder> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_an_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_an_order
     *
     * @mbggenerated
     */
    int insert(AnOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_an_order
     *
     * @mbggenerated
     */
    int insertSelective(AnOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_an_order
     *
     * @mbggenerated
     */
    AnOrder selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_an_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AnOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_an_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AnOrder record);


    /**
     * @param
     * @return
     * @description
     * @author suewong
     * @date 2020/7/16 10:48
     */
    List<AnOrderDTO> findGroupStats(@Param("quId") String quId);
}