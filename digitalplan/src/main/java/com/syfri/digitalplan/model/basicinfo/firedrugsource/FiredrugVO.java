package com.syfri.digitalplan.model.basicinfo.firedrugsource;

import java.io.Serializable;

import com.syfri.baseapi.model.ValueObject;

public class FiredrugVO extends ValueObject implements Serializable{

	private static final long serialVersionUID = 1L;

	private String uuid;	//主键
	private String yjbm;	//药剂编码
	private String yjmc;	//药剂名称
	private String ssdz;	//所属队站
	private String ssdzmc;	//所属队站名称
	private String xzqy;	//行政区划CODE（到区县）
	private String yjlx;	//药剂类型YJLX
	private String sccj;	//生产厂家
	private String pc;	//批次
	private String cbl;	//储备量（t）
	private String czl;	//车载量（t）
	private String cjrid;	//创建人ID
	private String cjrmc;	//创建人名称
	private String cjsj;	//创建时间
	private String xgrid;	//修改人ID
	private String xgrmc;	//修改人姓名
	private String xgsj;	//修改时间
	private String deleteFlag;	//删除标志
	private String datasource;	//数据来源[100000一体化]
	private String bz;	//备注
	private String jdh;	//节点号
	private String sjc;	//时间戳
	private String reserve1;	//备用字段1
	private String reserve2;	//备用字段2
	private String reserve3;	//备用字段3
	private String reserve4;	//备用字段4

	public String getUuid(){
		return uuid;
	}
	public void setUuid(String uuid){
		this.uuid = uuid;
	}
	public String getYjbm(){
		return yjbm;
	}
	public void setYjbm(String yjbm){
		this.yjbm = yjbm;
	}
	public String getYjmc(){
		return yjmc;
	}
	public void setYjmc(String yjmc){
		this.yjmc = yjmc;
	}
	public String getSsdz(){
		return ssdz;
	}
	public void setSsdz(String ssdz){
		this.ssdz = ssdz;
	}
	public String getSsdzmc(){
		return ssdzmc;
	}
	public void setSsdzmc(String ssdzmc){
		this.ssdzmc = ssdzmc;
	}
	public String getXzqy(){
		return xzqy;
	}
	public void setXzqy(String xzqy){
		this.xzqy = xzqy;
	}
	public String getYjlx(){
		return yjlx;
	}
	public void setYjlx(String yjlx){
		this.yjlx = yjlx;
	}
	public String getSccj(){
		return sccj;
	}
	public void setSccj(String sccj){
		this.sccj = sccj;
	}
	public String getPc(){
		return pc;
	}
	public void setPc(String pc){
		this.pc = pc;
	}
	public String getCbl(){
		return cbl;
	}
	public void setCbl(String cbl){
		this.cbl = cbl;
	}
	public String getCzl(){
		return czl;
	}
	public void setCzl(String czl){
		this.czl = czl;
	}
	public String getCjrid(){
		return cjrid;
	}
	public void setCjrid(String cjrid){
		this.cjrid = cjrid;
	}
	public String getCjrmc(){
		return cjrmc;
	}
	public void setCjrmc(String cjrmc){
		this.cjrmc = cjrmc;
	}
	public String getCjsj(){
		return cjsj;
	}
	public void setCjsj(String cjsj){
		this.cjsj = cjsj;
	}
	public String getXgrid(){
		return xgrid;
	}
	public void setXgrid(String xgrid){
		this.xgrid = xgrid;
	}
	public String getXgrmc(){
		return xgrmc;
	}
	public void setXgrmc(String xgrmc){
		this.xgrmc = xgrmc;
	}
	public String getXgsj(){
		return xgsj;
	}
	public void setXgsj(String xgsj){
		this.xgsj = xgsj;
	}
	public String getDeleteFlag(){
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag){
		this.deleteFlag = deleteFlag;
	}
	public String getDatasource(){
		return datasource;
	}
	public void setDatasource(String datasource){
		this.datasource = datasource;
	}
	public String getBz(){
		return bz;
	}
	public void setBz(String bz){
		this.bz = bz;
	}
	public String getJdh(){
		return jdh;
	}
	public void setJdh(String jdh){
		this.jdh = jdh;
	}
	public String getSjc(){
		return sjc;
	}
	public void setSjc(String sjc){
		this.sjc = sjc;
	}
	public String getReserve1(){
		return reserve1;
	}
	public void setReserve1(String reserve1){
		this.reserve1 = reserve1;
	}
	public String getReserve2(){
		return reserve2;
	}
	public void setReserve2(String reserve2){
		this.reserve2 = reserve2;
	}
	public String getReserve3(){
		return reserve3;
	}
	public void setReserve3(String reserve3){
		this.reserve3 = reserve3;
	}
	public String getReserve4(){
		return reserve4;
	}
	public void setReserve4(String reserve4){
		this.reserve4 = reserve4;
	}

	private String cbl_min;	//储备量（t）
	public String getCbl_min(){
		return cbl_min;
	}
	public void setCbl_min(String cbl_min){
		this.cbl_min = cbl_min;
	}

	private String cbl_max;
	public String getCbl_max(){
		return cbl_max;
	}
	public void setCbl_max(String cbl_max){
		this.cbl_max = cbl_max;
	}

	private String czl_min;	//车载量（t）
	public String getCzl_min(){
		return czl_min;
	}
	public void setCzl_min(String czl_min){
		this.czl_min = czl_min;
	}

	private String czl_max;
	public String getCzl_max(){
		return czl_max;
	}
	public void setCzl_max(String czl_max){
		this.czl_max = czl_max;
	}

	private String dzmc;	//队站名称
	private String yjlxmc;	//药剂类型名称
	public String getDzmc(){
		return dzmc;
	}
	public void setDzmc(String dzmc){
		this.dzmc = dzmc;
	}
	public String getYjlxmc(){
		return yjlxmc;
	}
	public void setYjlxmc(String yjlxmc){
		this.yjlxmc = yjlxmc;
	}
}