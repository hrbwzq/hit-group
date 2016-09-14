function check()
{

	var topic_error = $("#topic_error");
	var title = $("#title");
	var content = $("#content");

	topic_error.text("");

	if(title.val() == "")
	{
		topic_error.text("标题不能为空");
		return false;
	}
	else if(content.val() == "")
	{
		topic_error.text("内容不能为空");
		return false;
	}
	else if(title.val().length > 20)
	{
		topic_error.text("标题不能超过20字");
		return false;
	}
	else if(content.val().length > 2500)
	{
		topic_error.text("帖子内容超出了字数限制");
		return false;
	}
	else
	{
		return true;
	}

}