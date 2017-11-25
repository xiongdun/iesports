<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript" src="/iesports/js/framework/jquery.js"></script>
<script type="text/javascript" src="/iesports/js/framework/echarts.min.js"></script>
<style type="text/css">
	
</style>
</head>
<body>
	<center>
		<div id="main" style="width: 600px;height:400px;">
		</div>
	</center>
	
</body>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
	    var myChart = echarts.init(document.getElementById('main'));
	
	    // 指定图表的配置项和数据
	    var option = {
	        title: {
	            text: 'ECharts 入门示例'
	        },
	        tooltip: {},
	        legend: {
	            data:['销量']
	        },
	        xAxis: {
	            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
	        },
	        yAxis: {},
	        series: [{
	            name: '销量',
	            type: 'bar',
	            data: [5, 20, 36, 15, 10, 20]
	        }]
	    };
	
	    // 使用刚指定的配置项和数据显示图表。
	    myChart.setOption(option);
	</script>
</html>