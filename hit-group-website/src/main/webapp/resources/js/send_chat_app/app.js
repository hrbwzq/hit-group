function sendChat(to_user_id)
{
	var textarea = $("#content");

	if(check(textarea.val()))
	{
		$.post(
			"/message/send_chat",
			{to_user_id : to_user_id, content : textarea.val()}
		);
	}
}

function check(post_text)
{
	var alert_success = $("#alert_success");
	var alert_error = $("#alert_error");
	var error_message = $("#error_message");

	if(post_text.length == 0)
	{
		alert_error.toggle();
		error_message.text("发送内容不能为空");
		return false;
	}
	if(post_text.length > 50)
	{
		alert_error.toggle();
		error_message.text("发送内容不能超过50字");
		return false;
	}
	alert_success.toggle();
	return true;
}