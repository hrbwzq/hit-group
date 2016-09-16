function make_add_friend_apply(to_user_id)
{
	$.get(
		"/message/make_friend_apply",
		{to_user_id : to_user_id},
		function(result)
	      {
		      alert(result);
	      }
	);
}

function watch_user(to_user_id)
{
	$.get(
		"/message/watch",
		{to_user_id : to_user_id},
	    function(result)
		{
			alert(result);
		}
	)
}