function check()
{
	var textarea = $("#content");
	var message = $("#notice_check");

	if(textarea.val() == "")
	{
		message.text("内容不能为空");
		return false;
	}
	if(textarea.val().length > 50)
	{
		message.text("公告不能超过50字");
		return false;
	}
	return true;
}