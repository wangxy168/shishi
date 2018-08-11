<%--
  Created by IntelliJ IDEA.
  User: 79856
  Date: 2018/8/11
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Word Count View Page</title>
    <script type="application/javascript" src="/js/jquery-1.8.3.min.js"></script>
    <script type="application/javascript" src="/js/echarts.js"></script>
</head>
<body>
<div id="main" style="height: 100%"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'Word Count'
        },
        tooltip: {//鼠标悬浮弹窗提示
            trigger: 'item',
            show: true,
            showDelay: 5,
            hideDelay: 2,
            transitionDuration: 0,
            formatter: function (params, ticket, callback) {
                // console.log(params);
                var res = "次数：" + params.value;
                return res;
            }
        },
        xAxis: {
            data: [],
            type: 'category',
            axisLabel: {
                interval: 0
            }
        },
        yAxis: {},
        series: [{
            name: '数量',
            type: 'bar',
            data: [],
            itemStyle: {
                color: '#2AAAE3'
            }
        }, {
            name: '折线',
            type: 'line',
            itemStyle: {
                color: '#FF3300'
            },
            data: []
        }
        ]

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.showLoading();

    function show() {
        // 异步加载数据
        $.get('/data', function (data) {
            var words = [];
            var counts = [];
            var counts2 = [];
            for (var d in data) {
                words.push(d);
                counts.push(data[d]);
                counts2.push(eval(data[d]) + 50);
            }
            myChart.hideLoading();
            // 填入数据
            myChart.setOption({
                xAxis: {
                    data: words
                },
                series: [{
                    name: '数量',
                    data: counts
                }, {
                    name: '折线',
                    data: counts2
                }]
            });
        });
    }

    // 设置定时刷新
    setInterval("show()", 500);

</script>

</body>
</html>