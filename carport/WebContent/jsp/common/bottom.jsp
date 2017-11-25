<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="/carport/js/jquery-1.11.1.js"></script>
<script src="/carport/js/jquery.flot.js"></script>
<script src="/carport/js/jquery.flot.resize.js"></script>	
<script src="/carport/js/bootstrap/js/bootstrap-2.0.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if (window.location.hash == null && window.location.hash == "") {
			return;
		}
		$('.nav.nav-list .' + window.location.hash.replace('#','')).addClass("active").siblings('li').removeClass('active');
	});
</script>