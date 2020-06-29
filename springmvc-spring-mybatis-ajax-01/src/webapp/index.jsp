<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>计算器</title>
    <script type="text/javascript" src="js/jquery-3.5.0.js"></script>
</head>
<body>
<script type="text/javascript">
    //页面加载之后执行
    $(function () {
        //发送ajax请求，使用JQuery，实际上是创建了XMLHttpRequest
        $.ajax({
            //链接地址（就是连接通道）
            url: "pay.do",
            //请求方式，get
            type: "get",
            //响应数据，json,text
            dataType: "json",
            //是否全局异步
            async: true,
            //失败，弹出错误信息
            error: function (status, error) {
                alert("错误信息：" + error + status);
            },
            //成功执行方法
            success: function (result) {//result为请求结果，JQuery会把返回的json字符串变成json对象
                if (result != null) {
                    //alert(result);
                    //清空原来数据
                    $("#repayment").empty();
                    //添加HTML代码
                    $("#repayment").append(
                        "<option value=''>还款方式</option>"
                    )
                    //遍历结果
                    $.each(result, function (i, n) {
                        $("#repayment").append(
                            "<option value='" + n.payNum + "'>" + n.payMethod + "</option>"
                        )
                    })
                }
            }
        })

        //贷款方式
        $.ajax({
            url: "mor.do",
            type: "get",
            dataType: "json",
            async: true,
            error: function (status, error) {
                alert("错误信息：" + error + status);
            },
            success: function (result) {
                if (result != null) {
                    $("#loanMethod").empty();
                    $("#loanMethod").append(
                        "<option value=''>贷款方式</option>"
                    )
                    $.each(result, function (i, n) {
                        $("#loanMethod").append(
                            "<option value='" + n.morNum + "'>" + n.mortgageType + "</option>"
                        )
                    })
                }
            }
        })

        //期数
        $.ajax({
            url: "per.do",
            type: "get",
            dataType: "json",
            async: true,
            error: function (status, error) {
                alert("错误信息：" + error + status);
            },
            success: function (result) {
                if (result != null) {
                    $("#period").empty();
                    $("#period").append(
                        "<option value=''>分期数</option>"
                    )
                    $.each(result, function (i, n) {
                        $("#period").append(
                            "<option value='" + n.perNum + "'>" + n.periodNum + "</option>"
                        )
                    })
                }
            }
        })

        //支付租金方式
        $("#rent").empty();
        $("#rent").append("<option value= '1'>整交</option>");
        $("#rent").append("<option value= '2'>每月</option>");

        //当发生单击事件时执行检查，检查用户是否填写正确
        $("#compute").click(function () {
            //每单击一次，清除所有内容；
            $("#save").empty();
            //获取每个选项的值
            //还款方式
            var pay = $("#repayment > option:selected").val();
            //判断是否为空
            if (pay == "") {
                $("#payError").empty();
                $("#payError").append(
                    "<font color='red'>&nbsp;&nbsp;&nbsp;*请选择还款方式</font>"
                )
            } else {
                $("#payError").empty();
            }

            //贷款方式
            var loan = $("#loanMethod > option:selected").val();
            if (loan == "") {
                $("#loanError").empty();
                $("#loanError").append(
                    "<font color='red'>&nbsp;&nbsp;&nbsp;*请选择贷款方式</font>"
                )
            } else {
                $("#loanError").empty();
            }

            //输入金额
            var borrow = $("#borrow").val();
            if (borrow == 0) {
                $("#borrowError").empty();
                $("#borrowError").append(
                    "<font color='red'>&nbsp;&nbsp;&nbsp;*请输入金额</font>"
                )
            } else {
                $("#borrowError").empty();
            }

            //期数
            var period = $("#period > option:selected").val();
            if (period == "") {
                $("#periodError").empty();
                $("#periodError").append(
                    "<font color='red'>&nbsp;&nbsp;&nbsp;*请选择分期数</font>"
                )
            } else {
                $("#periodError").empty();
            }

            //利率
            var interestRate = $("#interestRate").val();
            if (interestRate == 0) {
                $("#intError").empty();
                $("#intError").append(
                    "<font color='red'>&nbsp;&nbsp;&nbsp;*请输入利率</font>"
                )
            } else {
                $("#intError").empty();
            }

            //交租金方式
            var rent = $("#rent").val();
            //alert("租金："+rent)
            //金额
            var cost = $("#cost").val();
            if (cost == 0) {
                //判断抵押方式
                if (loan == "1") {
                    $("#costError").empty();
                    $("#costError").append(
                        "<font color='red'>&nbsp;&nbsp;&nbsp;*请输入停车费</font>"
                    )
                } else {
                    $("#costError").empty();
                    $("#costError").append(
                        "<font color='red'>&nbsp;&nbsp;&nbsp;*请输入GBS月租费用</font>"
                    )
                }
            } else {
                $("#costError").empty();
            }

            //全部赋值了，就可以发送ajax请求，处理数据，接收处理之后的结果
            if (pay != "" && loan != "" && borrow != 0 && period != "" && interestRate != 0 && rent != "" && cost != 0) {
                //检查结果信息
                //alert("pay="+pay+"loan="+loan+"borrow="+borrow+"period="+period+"interestRate="+interestRate+"rent="+rent+"cost="+cost);
                //拿到数据之后进行post请求，携带参数,返回结果数据为json格式，并执行callback方法
                $.get("compute.do", {
                    pay: pay,
                    loan: loan,
                    borrow: borrow,
                    period: period,
                    interestRate: interestRate,
                    rent: rent,
                    cost: cost
                }, callback, "json");
            }
        })

        function callback(result) {//JQuery将结果转变成json对象
            //清空原来信息
            $("#result").empty();
            //遍历集合
            $.each(result, function (i, n) {
                $("#result").append(
                    "<tr>"
                    + "<td>" + n.periodNumber + "</td>" +
                    "<td>" + n.repaymentDate + "</td>"
                    + "<td>" + n.monthlyPrincipal + "</td>" +
                    "<td>" + n.monthlyInterest + "</td>"
                    + "<td>" + n.monthlyRent + "</td>" +
                    "<td>" + n.totalA + "</td>" +
                    "</tr>"
                )
            });
            $("#save").append(
                "<button style='width: 100%'>保存</button>"
            )
        }

        //当发生单击事件时执行
        $("#save").click(function(){
            //发生单击事件后，发送ajax请求，告诉服务器存储数据
            var save = "yes";
            $.post("save.do",{save:save});
        })
    })

</script>
<div style="width:500px;margin-left: 500px">
    <fieldset>
        <legend>计算器</legend>
        <table>
            <tr>
                <td><font color="red">*</font>选择还款方式</td>
            </tr>
            <td>
                <select id="repayment">
                </select><span id="payError"></span>
            </td>
            </tr>
            <tr></tr>
            <tr>
                <td><font color="red">*</font>选择贷款方式</td>
            </tr>
            <tr>
                <td>
                    <select id="loanMethod">
                    </select><span id="loanError"></span>
                </td>
            </tr>
            <tr></tr>
            <tr>
                <td>
                    <font color="red">*</font>设置借款金额
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" id="borrow" placeholder="请输入借款金额"/><span id="borrowError"></span>
                </td>
            </tr>
            <tr></tr>
            <tr>
                <td><font color="red">*</font>选择期数</td>
            </tr>
            <tr>
                <td>
                    <select id="period">
                    </select><span id="periodError"></span>
                </td>
            </tr>
            <tr></tr>
            <tr>
                <td><font color="red">*</font>输入利率</td>
            </tr>
            <tr>
                <td><input type="text" id="interestRate" placeholder="请输入利率"/>%<span id="intError"></span>
                </td>
            </tr>
            <tr>
                <td><span id="costError"></span></td>
            </tr>
            <tr>
                <td>
                    <select id="rent">
                    </select>&nbsp;<input type="text" id="cost">元
                </td>
            </tr>
            <tr>
                <td>
                    <button id="compute"> 计算</button>
                </td>
            </tr>
        </table>
    </fieldset>
</div>
<div style="width: 600px;margin-left: 400px;">
    <fieldset>
        <legend>明细</legend>
        <table border="1" width="100%" id="result">
        </table>
        <span id="save"></span>
    </fieldset>
</div>
</body>
</html>
