function check()
{
	var user_help = $("#reply_help").text("");
	var text_area = $("#content");

	if(text_area.val() == "")
	{
		user_help.text("内容不能为空");
		return false;
	}
	else if(text_area.val().length > 500)
	{
		user_help.text("评论字数不能超过500字");
		return false;
	}
	else
	{
		return true;
	}
}