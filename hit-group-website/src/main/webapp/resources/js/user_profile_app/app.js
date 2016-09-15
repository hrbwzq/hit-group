function check()
{
	var real_name = $("#realname");
	var age = $("#age");
	var major = $("#major");
	var address = $("#address");
	var qq = $("#qq");
	var phone = $("#phone");

	var edit_user_profile_error = $("#edit_user_profile_error");

	if(real_name.val().length > 10)
	{
		edit_user_profile_error.text("名字长度不能超过10个字");
		return false;
	}
	if(age.val() != "")
	{
		if(parseInt(age.val()) < 1 || parseInt(age.val()) > 99)
		{
			edit_user_profile_error.text("超出人类年龄范围");
			return false;
		}
	}
	if(major.val().length > 10)
	{
		edit_user_profile_error.text("专业名称不能超过十个字");
		return false;
	}
	if(address.val().length > 50)
	{
		edit_user_profile_error.text("地址不能超过50个字");
		return false;
	}
	if(qq.val().length > 10)
	{
		edit_user_profile_error.text("没有这么长的QQ号！");
		return false;
	}
	if(phone.val().length > 15)
	{
		edit_user_profile_error.text("没有这么长的手机号！");
		return false;
	}
		return true;
}

function click_this()
{
	console.log("(,,#ﾟДﾟ)发生了什么");
	$("#hidden_sex_radio").toggle(5000);
}