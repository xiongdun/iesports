/**
 * 
 */
package com.iesports.test.carport.bean;

/**
 * 描述：组织机构分配车位结果
 * 
 * @author zhangyijie
 * @created 2016年12月6日 上午10:36:33
 * @since
 */
public class OrgCarportResult {

	private String org_id;// 组织机构ID
	private String org_name;// 组织机构名
	private String status;// 状态
	private String apartment_id;// 部门Id
	private Integer need_carportNum;// 需要的车位数
	private Double count_ratio;// 计算车位比例值

	public Double getCount_ratio() {
		return count_ratio;
	}

	public void setCount_ratio(Double count_ratio) {
		this.count_ratio = count_ratio;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApartment_id() {
		return apartment_id;
	}

	public void setApartment_id(String apartment_id) {
		this.apartment_id = apartment_id;
	}

	public Integer getNeed_carportNum() {
		return need_carportNum;
	}

	public void setNeed_carportNum(Integer need_carportNum) {
		this.need_carportNum = need_carportNum;
	}
}
