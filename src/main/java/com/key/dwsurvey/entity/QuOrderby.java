package com.key.dwsurvey.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.key.common.base.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 评分题 行选项
 */
@Entity
@Table(name="t_qu_orderby")
@TableName("t_qu_orderby")
public class QuOrderby extends IdEntity {

		//所属题
		private String quId;
		//标识
		private String optionTitle;
		//选项内容
		private String optionName;
		//排序号
		private Integer orderById;
		//是否显示  0不显示 
		private Integer visibility=1;
		
		public String getQuId() {
			return quId;
		}
		public void setQuId(String quId) {
			this.quId = quId;
		}
		public String getOptionName() {
			return optionName;
		}
		public void setOptionName(String optionName) {
			this.optionName = optionName;
		}
		public Integer getOrderById() {
			return orderById;
		}
		public void setOrderById(Integer orderById) {
			this.orderById = orderById;
		}
		public String getOptionTitle() {
			return optionTitle;
		}
		public void setOptionTitle(String optionTitle) {
			this.optionTitle = optionTitle;
		}
		public Integer getVisibility() {
			return visibility;
		}
		public void setVisibility(Integer visibility) {
			this.visibility = visibility;
		}
		
		private int anOrderSum;
		@Transient
		public int getAnOrderSum() {
			return anOrderSum;
		}
		public void setAnOrderSum(int anOrderSum) {
			this.anOrderSum = anOrderSum;
		}
		
		
}
