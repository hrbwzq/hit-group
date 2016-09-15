package com.gsh.web.forum.controller;

import com.gsh.domain.Topic;
import com.gsh.domain.User;
import com.gsh.service.TopicReplyService;
import com.gsh.service.TopicService;
import com.gsh.web.consts.PageSize;
import com.gsh.web.forum.beans.TopicReplyFormBean;
import com.gsh.web.forum.beans.TopicReplyPageBean;
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
public class TopicController
{

	@Autowired
	private TopicService topicService;

	@Autowired
	private TopicReplyService topicReplyService;

	private Topic topic;

	private void fillPageContent(Model model, int topicId, int page)
	{
		//填充Topic数据
		this.topic = topicService.queryTopicById((long) topicId);
		model.addAttribute("topic", this.topic);

		//填充回复数据
		if(this.topic != null)
		{
			TopicReplyPageBean topicReplyPageBean = topicReplyService.getTopicReplyByTopicIdByPage((long) topicId, page, PageSize.FORUM_TOPIC_REPLY_PAGE_SIZE);
			model.addAttribute("topic_reply_page_bean", topicReplyPageBean);
		}

		//填充表单模型
		TopicReplyFormBean topicReplyFormBean = new TopicReplyFormBean();
		model.addAttribute("topicReplyFormBean", topicReplyFormBean);
	}

	//获取主题帖页面 /topic?topic_id=x&page=x
	@RequestMapping(method = RequestMethod.GET, value = "/topic")
	public String getTopicPage(@RequestParam("topic_id") int topicId,@RequestParam("page") int page, Model model)
	{
		this.fillPageContent(model, topicId, page);

		if(this.topic == null)
		{
			return "redirect:/forum?page=1";
		}
		else
		{
			return "topic";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topic")
	public String doPublishTopicReply(@Validated TopicReplyFormBean topicReplyFormBean,
	                                  @RequestParam(value = "topic_id", required = false) String topicId,
	                                  @RequestParam(value = "topic_reply_id", required = false) String topicReplyId,
	                                  BindingResult bindingResult,
	                                  Model model,
	                                  HttpSession httpSession)
	{
		if(bindingResult.hasErrors())
		{
			this.fillPageContent(model, Integer.parseInt(topicId), 1);
			return "topic";
		}
		else
		{
			User user = (User)httpSession.getAttribute("user");
			if(user == null)
			{
				return "redirect:/forum?page=1";
			}
			else
			{
				topicReplyFormBean.setContent(KeywordReplaceUtil.HTMLTageFilter(topicReplyFormBean.getContent()));
				if(!topicId.equals("") && topicReplyId.equals(""))
				{
					topicReplyService.publishTopicReply(topicReplyFormBean, Long.parseLong(topicId), null, user.getUserId());
				}
				else if(!topicId.equals("") && !topicReplyId.equals(""))
				{
					topicReplyService.publishTopicReply(topicReplyFormBean, Long.parseLong(topicId), Long.parseLong(topicReplyId), user.getUserId());
				}
				else
				{
					return "redirect:/forum?page=1";
				}
				return "redirect:/topic?topic_id=" + topicId + "&page=1";
			}
		}
	}
}
