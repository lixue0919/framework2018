package com.syfri.digitalplan.dao.basicinfo.equipmentsource;

import com.syfri.baseapi.dao.BaseDAO;
import com.syfri.digitalplan.model.basicinfo.equipmentsource.EquipmentVO;

import java.util.List;

public interface EquipmentsourceDAO extends BaseDAO<EquipmentVO>{

    /*--根据条件查询记录.--*/
    List<EquipmentVO> doSearchByVO(EquipmentVO equipmentVO);

    /*--根据装备id获取装备详情.--*/
    List<EquipmentVO> doFindDetailById(String id);

}