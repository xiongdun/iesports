<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/common/top.jsp"></jsp:include>
</head>
<body>
	<div class="navbar navbar-fixed-top">
      	<jsp:include page="/jsp/common/nav.jsp"></jsp:include>
	</div>
    <div class="container-fluid">
      	<div class="row-fluid">
        	<div class="span3">
          		<jsp:include page="/jsp/common/sidenav.jsp"></jsp:include>
        	</div>
        	<div class="span9">
		  		<div class="row-fluid">
					<div class="page-header">
						<h1>抢车位历史统计 <small>Carport history statistics...</small></h1>
					</div>
					<div id="placeholder" style="width:80%;height:300px;"></div>
		  		</div>
        	</div>
      	</div>
      	<hr>
      	<jsp:include page="/jsp/common/copyright.jsp"></jsp:include>
    </div>
    <jsp:include page="/jsp/common/bottom.jsp"></jsp:include>
	<script>
		$(function () {
			var data = [
			{
				label: 'Example 2',
				data: [[0, 4454], [1, 1245], [2, 7876], [3, 5211], [4, 6787]]
			}];
			var options = {
				legend: {
					show: true,
					margin: 10,
					backgroundOpacity: 0.5
				},
				bars: {
					show: true,
					barWidth: 0.5,
					align: 'center'
				},
				grid: {
					borderWidth:1,
					hoverable: true
				},
				xaxis: {
					axisLabel: 'Month',
					ticks: [[0, 'Jan'], [1, 'Feb'], [2, 'Mar'], [3, 'Apr'], [4, 'May'], [5, 'Jun'], [6, 'Jul'], [7, 'Aug'], [8, 'Sep'], [9, 'Oct'], [10, 'Nov'], [11, 'Dec']],
					tickDecimals: 0,
				},
				yaxis: {
					tickSize:2000,
					tickDecimals: 0
				}
			};
			function showTooltip(x, y, contents) {
				$('<div id="tooltip">' + contents + '</div>').css( {
					position: 'absolute',
					display: 'none',
					top: y + 5,
					left: x + 5,
					border: '1px solid #D6E9C6',
					padding: '2px',
					'background-color': '#DFF0D8',
					opacity: 0.80
				}).appendTo("body").fadeIn(200);
			}
			var previousPoint = null;
			$("#placeholder").bind("plothover", function (event, pos, item) {
				if (item) {
					if (previousPoint != item.dataIndex) {
						previousPoint = item.dataIndex;
	
						$("#tooltip").remove();
						showTooltip(item.pageX, item.pageY, item.series.label + ": " + item.datapoint[1]);
					}
				}
				else {
					$("#tooltip").remove();
					previousPoint = null;            
				}
			});
			$.plot( $("#placeholder") , data, options );
		});
	</script>
</body>
</html>