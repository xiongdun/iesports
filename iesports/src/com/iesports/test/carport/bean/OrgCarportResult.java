/**
 * 
 */
package com.iesports.test.carport.bean;

/**
 * ��������֯�������䳵λ���
 * 
 * @author zhangyijie
 * @created 2016��12��6�� ����10:36:33
 * @since
 */
public class OrgCarportResult {

	private String org_id;// ��֯����ID
	private String org_name;// ��֯������
	private String status;// ״̬
	private String apartment_id;// ����Id
	private Integer need_carportNum;// ��Ҫ�ĳ�λ��
	private Double count_ratio;// ���㳵λ����ֵ

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
