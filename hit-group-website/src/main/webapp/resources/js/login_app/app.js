function check()
{
	var user_help = $("#username_help").text("");
	var pass_help = $("#password_help").text("");

	if($("#username").val() == "")
	{
		user_help.text("用户名不能为空");
		return false;
	}
	else if($("#password").val() == "")
	{
		pass_help.text("密码不能为空");
		return false;
	}
	else
	{
		return true;
	}
}