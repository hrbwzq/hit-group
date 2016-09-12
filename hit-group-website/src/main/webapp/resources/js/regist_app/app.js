function check()
{
	var user_help = $("#username_help").text("");
	var pass_help = $("#password_help").text("");
	var repass_help = $("#repassword_help").text("");
	var email_help = $("#email_help").text("");

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
	else if($("#repassword").val() == "")
	{
		repass_help.text("重复密码不能为空");
		return false;
	}
	else if($("#email").val() == "")
	{
		email_help.text("邮箱不能为空");
		return false;
	}
	else if($("#password").val() != $("#repassword").val())
	{
		repass_help.text("两次密码输入不一致");
		return false;
	}
	else
	{
		return true;
	}
}