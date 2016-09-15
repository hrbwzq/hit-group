function check_internal(floor)
{
	var textarea_pattern = "#content_" + floor;
	var textarea = $(textarea_pattern);

	var help_block_pattern = "#reply_help_" + floor;
	var help_block = $(help_block_pattern);

	if(textarea.val() == "")
	{
		help_block.text("输入内容不能为空");
		return false;
	}
	if(textarea.val().length > 200)
	{
		help_block.text("输入内容不能超过200字");
		return false;
	}
	return true;
}

function check()
{
	var textarea = $("#content");
	var help_block = $("#reply_help");
	if(textarea.val() == "")
	{
		help_block.text("输入内容不能为空");
		return false;
	}
	if(textarea.val().length > 2000)
	{
		help_block.text("输入内容不能超过2000字");
		return false;
	}
	return true;
}

function show_reply_textarea(floor)
{
	var pattern = "#reply_textarea_" + floor;
	$(pattern).toggle(1000);
}