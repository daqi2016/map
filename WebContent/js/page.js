/**
 * 分页条处理
 */
function dealPage(pageIndex,totalPage,el){
	if(totalPage == 0) return;
	/* var pageIndex = 14
	var totalPage = 15 */
	var pageDefautSize = 10;//默认显示页数
	var stepSize = 3;//歩长
	var pageStart = 1;//分页起始页
	var pageEnd = pageDefautSize;//分页结束页
	
	if(totalPage>pageDefautSize){
		var pageHalf = Math.ceil(pageDefautSize/2)
		if(pageIndex>pageHalf){
			var extLength = totalPage-pageIndex;
			if(extLength>=(pageDefautSize-1)){
				pageStart = pageIndex-stepSize;
			}else{
				pageStart = pageIndex-(pageDefautSize-extLength)+1;
			}
			pageEnd = pageStart+pageDefautSize-1;
		}
	}else{
		var pageStart = 1;
		var pageEnd = totalPage;
	}
	//上一页下一页 是否显示
	var hasPreFlag = false;
	var hasNextFlag = false;
	
	if(totalPage>pageDefautSize&&(totalPage-pageIndex)>0){
		hasNextFlag = true;
	}
	if(totalPage>pageDefautSize&&pageIndex>pageStart){
		hasPreFlag = true;
	}
	//首页 尾页 是否显示
	var hasStartFlag = false;
	var hasEndFlag = false;
	
	if(pageStart>1){
		hasStartFlag = true;
	}
	if(pageEnd<totalPage){
		hasEndFlag = true;
	}
	
	var htmlPage = "";
	if(hasStartFlag){
		htmlPage = htmlPage + "<li class=\"start\"><a href=\"javascript:void(0)\" onclick=\"javascript:jumpPage(1)\">首页</a></li>"
	}
	if(hasPreFlag){
		var prePageIndex = pageIndex - 1;
		htmlPage = htmlPage + "<li class=\"prev\"><a href=\"javascript:void(0)\" onclick=\"javascript:jumpPage("+prePageIndex+")\">上一页</a></li>";
	}
		
	for(var i = pageStart;i <= pageEnd; i++) {
		if(pageIndex==i){
			htmlPage = htmlPage + "<li class=\"active\"><a href=\"javascript:void(0)\" onclick=\"javascript:jumpPage("+i+")\">"+i+"</a></li>"
		}else{
			htmlPage = htmlPage + "<li><a href=\"javascript:void(0)\" onclick=\"javascript:jumpPage("+i+")\">"+i+"</a></li>"
		}
	}
	if(hasNextFlag){
		var nextPageIndex = pageIndex + 1;
		htmlPage = htmlPage + "<li class=\"next\"><a href=\"javascript:void(0)\" onclick=\"javascript:jumpPage("+nextPageIndex+")\">下一页</a></li>";
	}
	if(hasEndFlag){
		htmlPage = htmlPage + "<li class=\"end\"><a href=\"javascript:void(0)\" onclick=\"javascript:jumpPage("+totalPage+")\">尾页</a></li>"
	}
	$(el).html(htmlPage);
};

function jumpPage(pageNo) {
	$("#pageIndex").val(pageNo);
	$("#mainForm").submit();
}