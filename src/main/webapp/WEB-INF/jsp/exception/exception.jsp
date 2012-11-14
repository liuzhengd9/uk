<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.log4j.Logger"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>异常</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body oncontextmenu="return false" ondragstart="return false" onselectstart ="return false" onselect="document.selection.empty()" oncopy="document.selection.empty()" onbeforecopy="return false">
		<div id="content">
			<h3>
				系统运行期错误:
			</h3>
			<script language="javascript">
        	function showDetail()
        	{
        	    if (detail_error_msg.style.display == 'none')
        	        detail_error_msg.style.display = '';
        	    else
        	        detail_error_msg.style.display = 'none';
        	}
    		</script>
			<button onclick="javascript:history.back();">
				返回
			</button>
			<p>
				<button onclick="showDetail();" class="button">
					开发人员点击此处获取详细错误信息
				</button>
			</p>
			<div id="detail_error_msg" style="display: none">
				<pre>
					<%
						Exception e = (Exception) request.getAttribute("exception");
						if (e != null) {
							Logger.getLogger(basePath).error(e.getMessage(), e);
							e.printStackTrace(new java.io.PrintWriter(out));
						}
					%>
				</pre>
			</div>
		</div>
	</body>
</html>
