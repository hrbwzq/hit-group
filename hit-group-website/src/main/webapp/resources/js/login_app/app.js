function change_vercode_img()
{
	var img = $("#vercode_img");
	var timestamp = new Date().getTime();
	img.attr('src',img.attr('src') + '?' +timestamp);
}

function check()
{
	var user_help = $("#username_help").text("");
	var pass_help = $("#password_help").text("");
	var vercode_help = $("#vercode_help").text("");

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
	else if($("#vercode").val() == "")
	{
		vercode_help.text("验证码不能为空");
		return false;
	}
	else if($("#vercode").val().length != 4)
	{
		vercode_help.text("验证码为4位");
		return false;
	}
	else
	{
		return true;
	}
}
