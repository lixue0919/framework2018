package com.syfri.digitalplan.controller.yafjxz;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syfri.baseapi.model.ResultVO;

import com.syfri.digitalplan.config.properties.YafjxzProperties;
import com.syfri.digitalplan.model.digitalplan.DigitalplanlistVO;
import com.syfri.digitalplan.model.yafjxz.YafjxzVO;
import com.syfri.digitalplan.service.digitalplan.DigitalplanlistService;
import com.syfri.digitalplan.service.yafjxz.YafjxzService;
import com.syfri.digitalplan.utils.DownloadUtil;
import com.syfri.digitalplan.utils.StringUtils;
import com.syfri.digitalplan.utils.ZipCompressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author lixiaoyang
 * @Title: 预案附件下载
 * @date 2018/4/2 09:30
 */
@Controller
@RequestMapping("yafjxz")
public class YafjxzController {

	@Autowired
	private YafjxzService yafjxzService;
	@Autowired
	private DigitalplanlistService digitalplanlistService;
	@Autowired
	private YafjxzProperties yafjxzProperties;

	/**
	 * @Description:上传文件
	 * @Param: [request, response, yafjxzVO]
	 * @Return: void
	 * @Author: lixiaoayng
	 * @Modified By:
	 * @Date: 2018/4/20 15:49
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public void upload(HttpServletRequest request, HttpServletResponse response
			,YafjxzVO yafjxzVO) {
		System.out.println("id:"+yafjxzVO.getYaid());
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartRequest.getFileNames();

		while (iterator.hasNext()) {
			MultipartFile multipartFile = multipartRequest.getFile(iterator.next());

			// 获取文件名
			String fileName = multipartFile.getOriginalFilename();

			if("".equals(multipartFile.getOriginalFilename())) throw new RuntimeException("文件为空");
			InputStream is = null;
			try {
				is = multipartFile.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 文件上传固定的路径
			StringBuffer relativePath=new StringBuffer(yafjxzProperties.getSavePath());
			//新建的文件夹名称（预案基本信息创建时间+预案基本信息PKID）
			//123456为行政代码

			DigitalplanlistVO digitalplanlist=digitalplanlistService.doFindById(yafjxzVO.getYaid());
			if(StringUtils.isBlank(digitalplanlist.getUuid())){
				throw new RuntimeException("未查到预案基本信息！");
			}

			StringBuffer new_folder=new StringBuffer("123456/").append(digitalplanlist.getCjsj())
					.append("/").append(digitalplanlist.getUuid()).append("/");

			String folderName=relativePath.append(new_folder).toString();
			//创建文件夹
			File dir = new File(folderName);

			if (!dir.exists()) {
				dir.mkdirs();
			}

			// 获取文件的后缀名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			//数据库要存的数据
			String dbPath=new_folder.append(fileName).toString();
			System.out.println(dbPath);

			//文件全路径
			StringBuffer allPath=new StringBuffer(folderName).append(fileName);

			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(allPath.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			byte[] b = new byte[1024];
			try {
				while((is.read(b)) != -1){
					fos.write(b);
				}
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				fos.close();
				is.close();
				//插入数据
				YafjxzVO yafjxz =new YafjxzVO();
				yafjxz.setId(StringUtils.getUUId());
				yafjxz.setYaid(yafjxzVO.getYaid());
				yafjxz.setDir(dbPath);
				yafjxz.setExtension(suffixName);
				yafjxz.setRelname(fileName);
				yafjxzService.doInsertByVO(yafjxz);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @Description:逻辑删除
	 * @Param: [pkid]
	 * @Return: com.syfri.baseapi.model.ResultVO
	 * @Author: lixiaoayng
	 * @Modified By:
	 * @Date: 2018/4/20 15:48
	 */
	@RequestMapping(value = "/deleteById")
	public @ResponseBody ResultVO deleteById(@RequestBody YafjxzVO yafjxz) {
		ResultVO resultVO = ResultVO.build();

		//注释的内容为先压缩在删除原文件
		/*
		if(StringUtils.isBlank(yaid)&&StringUtils.isBlank(pkid)){
			return null;
		}
		if(StringUtils.isNotBlank(yaid)){
			YafjxzVO seyafjxz =new YafjxzVO();
			seyafjxz.setYaid(yaid);
			yafjxz=yafjxzService.doFindByVO(seyafjxz);
		}
		if(StringUtils.isNotBlank(pkid)){
			yafjxz=yafjxzService.doFindById(pkid);
		}*/
	/*	DigitalplanlistVO dl=new DigitalplanlistVO();
		if(null!=yafjxz){
			dl=digitalplanlistService.doFindById(yafjxz.getYaid());
		}
		if(null==dl){
			return null;
		}*/
		//逻辑删除
		int dr=yafjxzService.doDeleteByVO(yafjxz);
		if(dr>0){
			resultVO.setMsg("删除成功");
			StringBuffer sb=new StringBuffer(yafjxzProperties.getSavePath()).append(yafjxz.getDir());
			File file=new File(sb.toString());
			if(file.exists()){
				boolean rf=file.delete();
				if(rf){
					resultVO.setMsg("删除成功");
				}
			}
		}
		return resultVO;
	}
	/**
	 * @Description:下载预案，
	 * 例子http://localhost:8005/yafjxz/download?yaid=67833B5F91EE2169E053B077770AE803
	 * @Param: [response, yafjxzVO]
	 * @Return: void
	 * @Author: lixiaoayng
	 * @Modified By:
	 * @Date: 2018/4/20 13:47
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request,HttpServletResponse response,YafjxzVO yafjxzVO) {
		//判断预案id是否为空
		if(null==yafjxzVO||StringUtils.isBlank(yafjxzVO.getYaid())){
			return;
		}
		List<YafjxzVO> yafjxzs=yafjxzService.doSearchListByVO(yafjxzVO);
		YafjxzVO yafjxz=new YafjxzVO();
		//以为只获取文件夹所以只需要第一条数据
		if(yafjxzs.size()>0){
			yafjxz=yafjxzs.get(0);
		}else{
			return;
		}
		DigitalplanlistVO digitalplanlist=digitalplanlistService.doFindById(yafjxzVO.getYaid());
		//判断预案基本信息是否为空
		if(null==digitalplanlist){
			return;
		}
		//新生成的文件名（浏览器下载显示的文件名）
		String newFileName=new StringBuffer(digitalplanlist.getYamc()).append(".zip").toString();
		//下载文件的源路径
		String sZipFileName=yafjxz.getDir().replace(yafjxz.getRelname().trim(),"");
		//生成的zip文件路径
		String zipfilename=new StringBuffer(yafjxzProperties.getZipPath())
				.append(newFileName).toString();
		//压缩文件夹
		ZipCompressUtil zipCompressUtil=new ZipCompressUtil(zipfilename,sZipFileName);
		try {
			zipCompressUtil.zip();
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(new File(zipfilename)));
			//返回浏览器输出流
			DownloadUtil.zip(bis, request, response,  newFileName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * @Description:显示预案基本信息列表
	 * @Param: [yafjxzVO]
	 * @Return: com.syfri.baseapi.model.ResultVO
	 * @Author: lixiaoayng
	 * @Modified By:
	 * @Date: 2018/4/20 15:47
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResultVO list(@RequestBody com.syfri.digitalplan.model.yafjxz.YafjxzVO yafjxzVO) {
		ResultVO resultVO = ResultVO.build();
		List<com.syfri.digitalplan.model.yafjxz.YafjxzVO> yafjxzVOs=yafjxzService.doSearchListByVO(yafjxzVO);
		resultVO.setResult(yafjxzVOs);
		return resultVO;
	}
}