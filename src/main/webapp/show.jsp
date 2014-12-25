<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>你是怎么回家的？？</title>
    <%@ include file="/common/head-inner.jsp" %>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0">
    <link href="${ctx}/static/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="${ctx}/static/css/app.css" rel="stylesheet">
    <style>
        #category li {
            width: 24%;
        }
    </style>
</head>
<body>
<body>
<%@ include file="/common/nav.jsp" %>
<div id="content-wrapper">
    <%--<%@ include file="/common/search.jsp"%>--%>
    <div class="container bodycontainer">
        <div class="vote-tips">
            <h3 class="title">标题</h3>
            <p>xxxx 2014-12-24 12:21</p>
        </div>

        <div class="vote-desc">
            <p>
                <img src="${ctx}/static/images/global_DE42B97A-1B81-A336-A344-55775E49AEE2_src.jpg"/>
            </p>

            <p>
                这个测验其实挺有趣的：有一幅图画，有一个英挺、身穿战甲的战士正骑着马，在一望无际的原野中急速向前奔腾。请问如果要你在这幅画加个东西，你会最想加上什么物品呢？
            </p>
        </div>

        <div class="vote-content">
            <form action="${ctx}/show" method="post">
                <div class="row">
                    <hr>
                    <c:set var="clazz" value="${fn:split('progress-bar-success,progress-bar-info,progress-bar-warning,progress-bar-danger,progress-bar-success', ',')}"/>
                    <c:forEach var="item" items="${items}">
                        <div class="col-lg-12">
                                <div class="input-group">
                                      <span class="input-group-addon">
                                        <input type="checkbox" id="checkbox-${item.id}" name="voteChecks" value="${item.id}"/>
                                      </span>
                                    <label class="form-control" for="checkbox-${item.id}">${item.name}</label>
                                </div>
                            <c:if test="${return}" >
                            <div class="progress" style="height: 25px;">
                                <div class="progress-bar ${clazz[item.id]}" role="progressbar"
                                     aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                                     style='width: <fmt:formatNumber value="${item.records/totalCount}" type="percent"></fmt:formatNumber> ;'>
                                </div> <span style="text-align: center; margin-left: 10px;"> ${item.records}票</span> <a href="${item.link}" style="margin-left: 5px; text-align: right">下载</a>
                            </div>
                            <p>${item.funnyMsg}</p>
                           </c:if>
                        </div>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${return}">
                            <span>谢谢您的参与</span>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </c:otherwise>
                    </c:choose>

                </div>

            </form>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>

</body>
<%@ include file="/common/import-js.jsp" %>
</html>
