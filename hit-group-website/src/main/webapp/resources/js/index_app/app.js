$(document).ready(
	function ()
	{

		console.log("（╬￣皿￣）把控制台给我关上");

		$("#panel_1").show();
		$("#panel_2").hide();
		$("#panel_3").hide();
		$("#sub_nav_1").addClass("active");
	}
);

function show_panel_1()
{
	$("#panel_1").show();
	$("#panel_2").hide();
	$("#panel_3").hide();
	$("#sub_nav_1").addClass("active");
	$("#sub_nav_2").removeClass("active");
	$("#sub_nav_3").removeClass("active");
}
function show_panel_2()
{
	$("#panel_2").show();
	$("#panel_1").hide();
	$("#panel_3").hide();
	$("#sub_nav_2").addClass("active");
	$("#sub_nav_1").removeClass("active");
	$("#sub_nav_3").removeClass("active");
}
function show_panel_3()
{
	$("#panel_3").show();
	$("#panel_1").hide();
	$("#panel_2").hide();
	$("#sub_nav_3").addClass("active");
	$("#sub_nav_2").removeClass("active");
	$("#sub_nav_1").removeClass("active");
}