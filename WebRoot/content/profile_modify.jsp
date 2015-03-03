<title>修改信息</title>

<!-- ajax layout which only needs content area -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="tabbable">
			<ul class="nav nav-tabs padding-12 tab-color-blue background-blue"
				id="myTab4">
				<li class="active"><a data-toggle="tab" href="#profile">修个个人资料</a>
				</li>

				<li><a data-toggle="tab" href="#password">修改密码</a></li>
			</ul>

			<div class="tab-content">
				<div id="profile" class="tab-pane in active">
					<p>Nothing</p>
				</div>

				<div id="password" class="tab-pane">
					<div>
						<p>
							原密码：<input type="password" id="oldpassword" name="oldpassword" />
						</p>
						<p>
							设置密码：<input type="password" id="newpassword" name="newpassword" />
						</p>
						<p>
							确认密码：<input type="password" confirmpassword" name="confirmpassword" />
						</p>
						<p>
							<span class="btn btn-primary" onclick="savePwd();">修改密码</span>
						</p>

					</div>
				</div>
			</div>
		</div>
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<!-- page specific plugin scripts -->

<script type="text/javascript">
	var scripts = [ null, null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		//inline scripts related to this page

	});
</script>
<script>
	function savePwd() {
		a = $("#oldpassword").val();
		b = $("#newpassword").val();
		c = $("#confirmpassword").val();
		if (b == c) {
			$.ajax({
				type : "post",
				dataType : "json",
				url : "/pgms/json/changePwd.action",
				data : {
					id : ${session.userid},
					oldpassword : a,
					password : b
				},
				success : function(data) {
					alert(data.msg);
					if (data.status == 1) {
						window.location.href = "/pgms/logout.action";
					}

				}
			});
		} else {
			alert("两次密码不匹配！");
		}
	}
</script>