package com.syfri.digitalplan.dao.basicinfo.organization;

import com.syfri.baseapi.dao.BaseDAO;
import com.syfri.digitalplan.model.basicinfo.organization.OrganizationVO;

import java.util.List;

public interface OrganizationDAO extends BaseDAO<OrganizationVO>{
    /**
     * @Description:
     * @Param:
     * @Return:
     * @Author: zhaijianchen
     * @Modified By:
     * @Date: 2018/4/24 15:26
     */
/*--根据条件查询记录.--*/
    List<OrganizationVO> doSearchByVO(OrganizationVO organizationVO);

    /*--根据机构id获取机构详情.--*/
    List<OrganizationVO> doFindDetailById(String organizationId);

    /*--获取全部机构.--*/
    List<OrganizationVO> doFindAllOrganization();

}