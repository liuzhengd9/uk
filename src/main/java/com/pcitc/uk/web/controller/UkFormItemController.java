package com.pcitc.uk.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcitc.uk.common.PageDataBean;
import com.pcitc.uk.model.UkFormItem;
import com.pcitc.uk.service.UkFormItemService;
import com.pcitc.uk.util.JsonUtils;

@Controller
public class UkFormItemController {

	private final Logger logger = Logger.getLogger(getClass());

	@Resource(name = "ukFormItemService", type = UkFormItemService.class)
	private UkFormItemService ukFormItemService;

	@RequestMapping(value = "/ukFormItem/index.html")
	public String index() {

		return "uk/ukFormItem";
	}

	@RequestMapping(value = "/ukFormItem/list.html")
	@ResponseBody
	public PageDataBean<UkFormItem> list(HttpServletRequest request) {

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 20);
		String applicant = ServletRequestUtils.getStringParameter(request, "applicant", "");
		String[] sort = JsonUtils.getSort(ServletRequestUtils.getStringParameter(request, "sort", ""), new String[] { "id", "asc" });
		logger.info("start:" + start);
		logger.info("limit:" + limit);
		logger.info("applicant:" + applicant);
		Map<String, Object[]> params = new HashMap<String, Object[]>();
		if (StringUtils.hasText(applicant)) {
			params.put("and applicant like ?", new String[] { "%" + applicant + "%" });
		}
		return ukFormItemService.findPageDataBean("UkFormItem", params, start, limit, sort[0], sort[1]);
	}

	@RequestMapping(value = "/ukFormItem/delete.html")
	@ResponseBody
	public ModelMap delete(@RequestParam(value = "id") Integer[] ids) {

		ModelMap model = new ModelMap();
		logger.info("id:" + ids);
		ukFormItemService.delete(ids);
		model.addAttribute("success", true);
		model.addAttribute("msg", "删除成功");
		return model;
	}

	@RequestMapping(value = "/ukFormItem/persist.html")
	@ResponseBody
	public ModelMap persist(HttpServletRequest request) throws ServletException {

		ModelMap model = new ModelMap();
		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		int appFormId = ServletRequestUtils.getRequiredIntParameter(request, "appFormId");
		String applicant = ServletRequestUtils.getRequiredStringParameter(request, "applicant");
		boolean isUk = ServletRequestUtils.getRequiredBooleanParameter(request, "isUk");
		String adName = ServletRequestUtils.getRequiredStringParameter(request, "adName");
		boolean isTms = ServletRequestUtils.getRequiredBooleanParameter(request, "isTms");
		String tmsAccount = ServletRequestUtils.getStringParameter(request, "tmsAccount", "");
		boolean isNew = ServletRequestUtils.getRequiredBooleanParameter(request, "isNew");
		boolean isSpecial = ServletRequestUtils.getRequiredBooleanParameter(request, "isSpecial");
		String threeLevelDept = ServletRequestUtils.getStringParameter(request, "threeLevelDept", "");
		String fourLevelDept = ServletRequestUtils.getStringParameter(request, "fourLevelDept", "");
		String postalAddress = ServletRequestUtils.getStringParameter(request, "postalAddress", "");
		String postcode = ServletRequestUtils.getStringParameter(request, "postcode", "");
		String position = ServletRequestUtils.getStringParameter(request, "position", "");
		String identityCardNo = ServletRequestUtils.getRequiredStringParameter(request, "identityCardNo");
		String phone = ServletRequestUtils.getRequiredStringParameter(request, "phone");
		int loginMode = ServletRequestUtils.getRequiredIntParameter(request, "loginMode");
		String remark = ServletRequestUtils.getStringParameter(request, "remark", "");
		logger.info("appFormId:" + appFormId);
		logger.info("applicant:" + applicant);
		logger.info("isUk:" + isUk);
		logger.info("adName:" + adName);
		logger.info("isTms:" + isTms);
		logger.info("tmsAccount:" + tmsAccount);
		logger.info("isNew:" + isNew);
		logger.info("isSpecial:" + isSpecial);
		logger.info("threeLevelDept:" + threeLevelDept);
		logger.info("fourLevelDept:" + threeLevelDept);
		logger.info("postalAddress:" + postalAddress);
		logger.info("postcode:" + postcode);
		logger.info("position:" + position);
		logger.info("idendtityCardNo:" + identityCardNo);
		logger.info("phone:" + phone);
		logger.info("loginMode:" + loginMode);
		logger.info("remark:" + remark);
		UkFormItem item = new UkFormItem();
		if (id > 0) {
			item.setId(id);
		}
		item.setAppFormId(appFormId);
		item.setApplicant(applicant);
		item.setIsUk(isUk);
		item.setAdName(adName);
		item.setIsTms(isTms);
		item.setTmsAccount(tmsAccount);
		item.setIsNew(isNew);
		item.setIsSpecial(isSpecial);
		item.setThreeLevelDept(threeLevelDept);
		item.setFourLevelDept(fourLevelDept);
		item.setPostalAddress(postalAddress);
		item.setPostcode(postcode);
		item.setPosition(position);
		item.setIdentityCardNo(identityCardNo);
		item.setPhone(phone);
		item.setLoginMode(loginMode);
		item.setRemark(remark);
		ukFormItemService.saveOrUpdate(item);
		model.addAttribute("success", true);
		model.addAttribute("msg", "添加成功");
		return model;
	}

	@RequestMapping(value = "/ukFormItem/load.html")
	@ResponseBody
	public ModelMap load(@RequestParam(value = "id") Integer id) {

		ModelMap model = new ModelMap();
		model.addAttribute("success", true);
		model.addAttribute("data", ukFormItemService.get(id));
		return model;
	}
}
