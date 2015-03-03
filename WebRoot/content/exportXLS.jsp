
<title>数据导出</title>
<script src="/pgms/assets/js/fuelux/fuelux.wizard.js"></script>
<script src="assets/js/chosen.jquery.js"></script>
<link rel="stylesheet" href="/pgms/assets/css/chosen.css" />


<div class="page-header">
	<h1>
		数据导出 <small> <i class="ace-icon fa fa-angle-double-right"></i>
			导出到Excel
		</small>
	</h1>
</div>
<!-- /.page-header -->

<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="widget-body">
			<div class="widget-main">
				<!-- #section:plugins/fuelux.wizard -->
				<div id="my-wizard">
					<div>
						<!-- #section:plugins/fuelux.wizard.steps -->
						<ul class="steps">
							<li data-step="1" class="active"><span class="step">1</span>
								<span class="title">选择入学年份</span></li>

							<li data-step="2"><span class="step">2</span> <span
								class="title">选择导出类别</span></li>

							<li data-step="3"><span class="step">3</span> <span
								class="title">完成</span></li>

						</ul>

						<!-- /section:plugins/fuelux.wizard.steps -->
					</div>

					<hr />

					<!-- #section:plugins/fuelux.wizard.container -->
					<div class="step-content pos-rel">
						<div class="step-pane active" data-step="1">
							<form class="form-horizontal" id="uploadExcel">
								<!-- #section:elements.form.input-state -->
								<div>
									<label
										class="col-xs-12 col-sm-3 control-label no-padding-right">选择入学年份</label>

									<div class="col-xs-12 col-sm-5">
										<select multiple id="yearselect" name="export_years"
											class="chosen-select tag-input-style form-control">
										</select> <br> <img src="/pgms/images/loading.gif" id="loading"
											style="display:none">
									</div>
								</div>
							</form>
						</div>

						<div class="step-pane" data-step="2">
							<form class="form-horizontal" id="sheetForm">
								<!-- #section:elements.form.input-state -->
								<div>
									<label
										class="col-xs-12 col-sm-3 control-label no-padding-right">选择导出类别：</label>

									<div class="col-xs-12 col-sm-5">
										<label> <input value="按学院" type="checkbox"
											name="export_sortby" class="ace " /> <span "按学院" class="lbl">按学院</span>
										</label><br> <label> <input value="按学位类别" type="checkbox"
											name="export_sortby" class="ace " /> <span class="lbl">按学位类别</span>
										</label><br> <label> <input value="按领域" type="checkbox"
											name="export_sortby" class="ace" /> <span class="lbl">按领域</span>
										</label>
									</div>
								</div>
							</form>
						</div>

						<div class="step-pane" data-step="3">
							<div class="center">
								<h3 id="exportfinished" class="green hidden">导出成功！<a id="download_url" >点击下载</a></h3>
								<h3 id="exporting" class="red ">正在导出，请稍候。</h3>
								<h3 id="exporterr" class="red hidden">导出失败，请重试。</h3>
								<h3 id="exporttimeout" class="red hidden">超时！服务器失去响应，请重试。</h3>

							</div>
						</div>
					</div>

					<!-- /section:plugins/fuelux.wizard.container -->
				</div>

				<hr />
				<div class="wizard-actions">
					<!-- #section:plugins/fuelux.wizard.buttons -->
					<button class="btn btn-prev">
						<i class="ace-icon fa fa-arrow-left"></i> 上一步
					</button>

					<button id="nextbtn" class="btn btn-success btn-next"
						data-last="完成">
						下一步 <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
					</button>

					<!-- /section:plugins/fuelux.wizard.buttons -->
				</div>

				<!-- /section:plugins/fuelux.wizard -->
			</div>
			<!-- /.widget-main -->
		</div>
		<!-- /.widget-body -->
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
	var fn;
	$('#my-wizard').ace_wizard({
	//step: 2 //optional argument. wizard will jump to step "2" at first
	//buttons: '.my-action-buttons' //which is possibly located somewhere else and is not a sibling of wizard
	}).on(
			'actionclicked.fu.wizard',
			function(e, info) {
				if (info.step == 2) {
					var export_years;
					for (var i = 0; i < $("#yearselect").val().length; i++) {
						if (export_years == undefined) {
							export_years = $("#yearselect").val()[i];
						} else {
							export_years = export_years + ","
									+ $("#yearselect").val()[i];
						}

					}
					var chk_value = [];
					var str_sortby;
					$('input[name="export_sortby"]:checked').each(function() {
						chk_value.push($(this).val());
					});

					for (var k = 0; k < chk_value.length; k++) {
						if (str_sortby == undefined) {
							str_sortby = chk_value[k];
						} else {
							str_sortby = str_sortby + "," + chk_value[k];
						}

					}

					$.ajax({
						type : 'POST',
						url : "/pgms/json/xlsExport.action",
						data : {
							"export_sortby" : str_sortby,
							"export_years" : export_years
						},
						success : function(data, status) {
							if (data.exportStatus == 1) {
								$("#exportfinished").removeClass("hidden");
								$("#exporting").addClass("hidden");
								console.log(encodeURI("download.action?fileName="+export_years.replace(/,/g,"_")+str_sortby.replace(/,/g,"_")+".xls"));
								$("#download_url").attr("href",encodeURI("download.action?fileName="+export_years.replace(/,/g,"_")+str_sortby.replace(/,/g,"_")+".xls"));
							} else {
								$("#exporting").addClass("hidden");
								$("#exporterr").removeClass("hidden");
							}
						},
						dataType : "json"
					});

				}

				//info.step
				//info.direction

				//use e.preventDefault to cancel
			}).on('changed.fu.wizard', function() {
		//after step has changed
	}).on('finished.fu.wizard', function(e) {

	}).on('stepclick.fu.wizard', function(e) {
		//e.preventDefault();//this will prevent clicking and selecting steps
	});
</script>
<script>
	console.log(new Date().getFullYear());
	for (var k = 2010, kk = 10; k < new Date().getFullYear() + 1; k++, kk++) {
		$("#yearselect").append("<option value='"+kk+"'>" + k + "年<option>");

	}
	$(".chosen-select").chosen({
		max_selected_options : 3
	});
</script>