<%@page import="com.qxinli.server.promotion.model.StatisticsData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html style="font-size: 54px">
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <!-- <meta name="viewport" content="width=device-width,target-densitydpi=high-dpi, user-scalable=no"/> -->
    <title>测测你的家庭氛围几颗星？【Q心理】</title>
    <style>
    a:focus, a:hover {
    color: #fff;
    text-decoration: none;
}
    .main{
     width:90%;
     margin:0 auto;
     padding: 0;
    }
    #header{
            width:100%;
            /* margin: 1% 5%;*/
            font-size: 1em;
            line-height:28px;
    }
    #header p{
            margin: 0.1% 0.5%;
            line-height: 20px
    }
    .inputComment{
    height: 0.648rem;
    font-size: 0.277rem;
    line-height: 0.6rem;
    color:#fff;
    }
    </style>
</head>
<body style="background-color:#f4f6f8;font-size: 12px;margin:0;padding:0" >
  <div id="header" style="height:1.1rem;background-color: #eaeaea;position: fixed;top: 0px; ">
        <div style="float: left">
            <div style="float:left">
                <img src="${ctx}/imgs/wapLogo.png" style="width: 0.7rem;margin-top: 0.18rem; margin-left: 0.37rem;" id="imgsrc" >
            </div>
            <div style="float:left;width: 145px;margin-top:0.6em;margin-left:0.8em">
                <span style="color:#666;font-size: 0.259rem;font-family: 'Microsoft YaHei'">专注青少年心理成长</span>
                <p style="font-size: 0.203rem;color: #a3a3a3;line-height: 0.203rem;">Q心理</p>
            </div>
        </div>
        <div style="float: right">
            <div style="padding-right:0.277rem;padding-top:0.3rem">
                <a style=" padding: 0.14rem 0.25rem;font-size: 0.203rem;color: #45d0cb;border:1px solid #45d0cb;border-radius:5px" href="http://www.qxinli.com/download.php">立即下载</a>
            </div>
        </div>
   </div>
    <div style="clear:both"></div>
  <div class="main">
    <div style="margin-top:1.111rem">
       <div style="padding-top: 0.37rem;font-size: 0.314rem;color: #333;font-weight: bold;line-height: 0.314rem;">测测你的家庭氛围几颗星？</div>
       <div style="margin-top: 0.15rem;font-size:0.23rem;color: #a0a0a0;margin-bottom: 0.277rem;line-height: 0.23rem;">已有${visits }人测过</div> 
       <div>
         <img src="${ctx}/imgs/weixinStartTesting.png" class="img-responsive">
       </div>
       <div style="margin-top: 0.277rem;font-size: 0.277rem;color: #333;">如果说孩子是一颗种子，那么家庭就是土壤，家庭氛围便是空气和水分。<br/>你的家庭氛围能打几颗星？测测看↓↓↓</div>
       <div style="margin-top:0.16rem;font-size:0.23rem;color:#ff8814">测试题： 8题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;测试时间： 1分钟</div>
       <%-- <div style="width: 100%;text-align: center;margin-top:0.555rem;margin-bottom:1.85rem">
         <a href="${ctx }/promotion/p_begin.action" class="weui_btn weui_btn_primary inputComment" >
            <span style="margin-left:0.231rem">开始测试</span>
         </a>
       </div> --%>
       <div style="width: 100%;text-align: center;margin-top:0.555rem;margin-bottom:1.85rem">
         <a href="${ctx}/promotion/p_begin.action" class="weui_btn weui_btn_primary inputComment" style="padding: 0.25em 1em;font-size:0.25rem" > 开始测试
         </a>
       </div>
    </div>
  </div>
</body>
</html>   