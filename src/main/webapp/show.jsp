<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ include file="/common/taglibs.jsp" %>

<c:set var="pageTitle" value="电影列表" scope="page"></c:set>
<!DOCTYPE html>
<html>
<head>
    <title>你是怎么回家的？？</title>
    <%@ include file="/common/head-inner.jsp" %>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0">
    <link href="/static/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="/static/css/app.css" rel="stylesheet">
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
                <img src="/static/images/global_DE42B97A-1B81-A336-A344-55775E49AEE2_src.jpg"/>
            </p>

            <p>
                这个测验其实挺有趣的：有一幅图画，有一个英挺、身穿战甲的战士正骑着马，在一望无际的原野中急速向前奔腾。请问如果要你在这幅画加个东西，你会最想加上什么物品呢？
            </p>
        </div>

        <div class="vote-content">
            <form action="">
                <table>
                    <tr>
                        <td>
                            <label class="radio" for="radio-0"><input name="radio-select" id="radio-0" type="radio" value="0"/>A
                                一支锐利的长矛</label>
                        </td>
                    </tr>
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td>
                                <label class="radio" for="radio-${item.id}"><input name="radio-select" id="radio-${item.id}" type="radio" value="1"/>${item.name}</label>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>

</body>
<%@ include file="/common/import-js.jsp" %>
</html>
