package com.gsh.web.forum.controller;

import com.gsh.domain.Group;
import com.gsh.domain.Notice;
import com.gsh.domain.User;
import com.gsh.service.GroupService;
import com.gsh.service.NoticeService;
import com.gsh.service.TopicService;
import com.gsh.web.consts.PageSize;
import com.gsh.web.forum.beans.TopicFormBean;
import com.gsh.web.forum.beans.TopicPageBean;
import com.gsh.web.util.KeywordReplaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class ForumIndexController
{
	@Autowired
	private TopicService topicService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private NoticeService noticeService;

	private void fillPageContent(Model model, int pageNum, int groupId)
	{
		//查询当前所属圈子
		Group group = this.groupService.getGroupById((long)groupId);
		model.addAttribute("group", group);

		//查询当前已激活的公告
		Notice notice = noticeService.getCurrentNotice();
		model.addAttribute("notice", notice);

		//分页查询讨论区内所有主题
		TopicPageBean topicPageBean = topicService.getLimitTopicByGroupId(pageNum, PageSize.FORUM_TOPIC_PAGE_SIZE, (long) groupId);
		model.addAttribute("topicPageBean", topicPageBean);

		//传入当前页码
		model.addAttribute("pageNum", pageNum);

		//填充表单模型
		model.addAttribute("topicFormBean", new TopicFormBean());
	}

	//分页查询讨论区页面 /forum?page=x&group_id=x
	@RequestMapping(value = "/forum", method = RequestMethod.GET)
	public String getForumIndexPage(@RequestParam("page") int pageNum, @RequestParam("group_id") int groupId, Model model)
	{
		this.fillPageContent(model, pageNum, groupId);
		return "forum_index";
	}

	//发布主题,提交表单
	@RequestMapping(value = "/forum", method = RequestMethod.POST)
	public String publishTopic(@Validated TopicFormBean topicFormBean, @RequestParam int pageNum,@RequestParam("group_id") int groupId, BindingResult bindingResult, Model model, HttpSession httpSession)
	{
		//表单校验出错
		if(bindingResult.hasErrors())
		{
			this.fillPageContent(model, pageNum, groupId);
			return "forum_index";
		}
		//表单校验成功
		else
		{
			//登陆session校验
			User user = (User)httpSession.getAttribute("user");
			//Session校验失败
			if(user == null)
			{
				this.fillPageContent(model, pageNum, groupId);
				model.addAttribute("topicError", "请先登陆");
				return "forum_index";
			}
			//登陆校验成功
			else
			{
				topicFormBean.setTitle(KeywordReplaceUtil.HTMLTageFilter(topicFormBean.getTitle()));
				topicFormBean.setContent(KeywordReplaceUtil.HTMLTageFilter(topicFormBean.getContent()));
				topicService.publishTopic(topicFormBean, user.getUserId(), (long)groupId);

				String resultStr = "redirect:/forum?page=1&group_id=" + groupId;

				return resultStr;
			}
		}
	}
}
