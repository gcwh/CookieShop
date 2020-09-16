<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>购物车</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>
</head>
<body>
<!--header-->
<jsp:include page="header.jsp">
	<jsp:param name="flag" value="7"></jsp:param>
</jsp:include>
<!--//header-->


<!--cart-items-->
<div class="cart-items">
	<div class="container">
		<h2>我的购物车</h2>
		<div class="car-headers-menu">
			<div class="row">
				<div class="col-md-3 car-menu">商品信息</div>
				<div class="col-md-2 car-menu">商品参数</div>
				<div class="col-md-1 car-menu">单价</div>
				<div class="col-md-2 car-menu">数量</div>
				<div class="col-md-2 car-menu">金额</div>
				<div class="col-md-2 car-menu">操作</div>
			</div>
		</div>
		<c:forEach items="${order.itemMap}" var="item">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3 good-info">
							<a href="/goods_detail?id=${item.key}">
								<img src="${pageContext.request.contextPath }${item.value.goods.cover}" class="img-responsive" style="max-width: 60%">
							</a>
						</div>
						<div class="col-md-2 good-info">
							<a href="/goods_detail?id=${item.key}">${item.value.goods.name}</a>
						</div>
						<div class="col-md-1 good-info">
							<span>¥ ${item.value.price}</span>
						</div>
						<div class="col-md-2 btn-group good-info">
							<a class="btn btn-default" href="javascript:buy(${item.key});" >+</a>
							<input type="text" value="${item.value.amount}" class="btn btn-default " style="width: 34px;height: 34px" readonly="readonly"/>
							<a class="btn btn-default" href="javascript:lessen(${item.key});" >-</a>
						</div>
						<div class="col-md-2 good-info">
							<span>¥ ${(item.value.price)*(item.value.amount)}</span>
						</div>
						<div class="col-md-2 good-info">
							<a class="btn btn-danger" href="javascript:deletes(${item.key});">删除</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>


		<div class="cart-header col-md-12">
			<hr>
			<h3>订单总金额: ¥ ${order.total}</h3>
			<a class="btn btn-success btn-lg" style="margin-left:74%" href="/order_submit">提交订单</a>
		</div>



	</div>
</div>
<!--//cart-items-->






<!--footer-->
<jsp:include page="footer.jsp"></jsp:include>
<!--//footer-->


</body>
</html>
