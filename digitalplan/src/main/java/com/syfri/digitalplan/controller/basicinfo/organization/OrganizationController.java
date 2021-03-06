package com.syfri.digitalplan.controller.basicinfo.organization;
import com.syfri.baseapi.controller.BaseController;
import com.syfri.baseapi.model.ResultVO;
import com.syfri.baseapi.utils.EConstants;
import com.syfri.digitalplan.model.basicinfo.organization.OrganizationVO;
import com.syfri.digitalplan.model.basicinfo.organization.OrganizationTree;
import com.syfri.digitalplan.service.basicinfo.organization.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "组织机构",tags = "组织机构",description = "组织机构")
@RestController
@RequestMapping("organization")
public class OrganizationController extends BaseController<OrganizationVO>{

	private static final Logger logger  = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	protected Environment environment;

	@Autowired
	private OrganizationService organizationService;

	@Override
	public OrganizationService getBaseService() {
		/**
		 * @Description:
		 * @Param: []
		 * @Return: com.syfri.digitalplan.service.digitalplan.OrganizationService
		 * @Author: zhaijianchen
		 * @Modified By:
		 * @Date: 2018/4/24 14:53
		 */
		return this.organizationService;

	}

	@ModelAttribute
	public void Model(Model model){
		if (environment.containsProperty("server.context-path")) {
			model.addAttribute("contextPath", environment.getProperty("server.context-path"));
		}else{
			model.addAttribute("contextPath", "/");
		}
	}

	@GetMapping("")
	public String getUser(Model model, @RequestParam(value="index") String index){
		model.addAttribute("index", index);
		return "basicinfo/organization_list";
	}

	/**
	 * 根据id获取预案信息
	 */
	@ApiOperation(value="根据id获取预案信息",notes="列表信息")
	@GetMapping("/doFindById/{pkid}")
	public @ResponseBody ResultVO doFindDetailById(@PathVariable String pkid){
		ResultVO resultVO = ResultVO.build();
		try{
			resultVO.setResult(organizationService.doFindDetailById(pkid));
		}catch(Exception e){
			logger.error("{}",e.getMessage());
			resultVO.setCode(EConstants.CODE.FAILURE);
		}
		return resultVO;
	}

	/**
	 * 获取全部机构树
	 */
	@ApiOperation(value="获取全部机构树",notes="查询")
	@PostMapping("/getOrganizationtree")
	public @ResponseBody ResultVO getOrganizationtree(){
		ResultVO resultVO = ResultVO.build();
		try{
			List<OrganizationTree> result = organizationService.doFindAllOrganization();
			resultVO.setResult(result);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
			resultVO.setCode(EConstants.CODE.FAILURE);
		}
		return resultVO;
	}


}
