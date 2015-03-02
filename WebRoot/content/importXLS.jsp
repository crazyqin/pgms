
<title>数据导入</title>
<script src="/pgms/assets/js/fuelux/fuelux.wizard.js"></script>
<script src="/pgms/assets/js/bootbox.js"></script>
<script src="/pgms/js/ajaxfileupload.js"></script>

<script src="/pgms/assets/js/jquery.bootstrap-duallistbox.js"></script>
<link rel="stylesheet"
	href="/pgms/assets/css/bootstrap-duallistbox.css " />


<div class="page-header">
	<h1>
		数据导入 <small> <i class="ace-icon fa fa-angle-double-right"></i>
			从Excel导入
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
								<span class="title">选择Excel文件</span></li>

							<li data-step="2"><span class="step">2</span> <span
								class="title">选择工作簿</span></li>

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
										class="col-xs-12 col-sm-3 control-label no-padding-right">选择Excel文件：</label>

									<div class="col-xs-12 col-sm-5">
										<input type="file" name="xlsfile" id="my-file-input" /><span
											onclick="uploadXLS();" class="btn">上传文件</span> <br> <img
											src="/pgms/images/loading.gif" id="loading"
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
										class="col-xs-12 col-sm-3 control-label no-padding-right">选择工作簿：</label>

									<div class="col-xs-12 col-sm-5">
										<select id="sheet_selector" multiple="multiple" size="10"
											name="sheet_selector[]">

										</select>
									</div>
								</div>
							</form>
						</div>

						<div class="step-pane" data-step="3">
							<div class="center">
								<h3 id="importfinished" class="green hidden">导入成功！</h3>
								<h3 id="importing" class="red ">正在导入，请稍候。</h3>
								<h3 id="importerr" class="red hidden">导入失败，请重试。</h3>
								<h3 id="importtimeout" class="red hidden">超时！服务器失去响应，请重试。</h3>
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

					<button id="nextbtn" class="btn btn-success btn-next hidden"
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
	}).on('actionclicked.fu.wizard', function(e, info) {
		if (info.step == 2) {
			$.ajax({
				type : 'POST',
				url : "/pgms/json/xlsImport.action",
				data : {
					"processXlsFileName" : fn,
					"processSheets" : sheet_selector.val().toString()
				},
				success : function(data, status) {
					if (data.status == 1) {
						$("#importfinished").removeClass("hidden");
						$("#importing").addClass("hidden");
					} else {
						$("#importing").addClass("hidden");
						$("#importerr").removeClass("hidden");
					}
				},
				dataType : "json"
			});
		}
		;

		//info.step
		//info.direction

		//use e.preventDefault to cancel
	}).on('changed.fu.wizard', function() {
		//after step has changed
	}).on('finished.fu.wizard', function(e) {
		bootbox.dialog({
			message : "数据已经成功导入",
			buttons : {
				"success" : {
					"label" : "确定",
					"className" : "btn-sm btn-primary"
				}
			}
		});
	}).on('stepclick.fu.wizard', function(e) {
		//e.preventDefault();//this will prevent clicking and selecting steps
	});

	$('#my-file-input').ace_file_input({
		'allowExt' : [ 'xls', 'xlsx' ]
	});

	var sheet_selector = $('select[name="sheet_selector[]"]')
			.bootstrapDualListbox();
</script>
<script>
	function uploadXLS() {
		$
				.ajaxFileUpload({
					url : 'json/xls_upload.action',
					fileElementId : 'my-file-input',
					dataType : 'application/json',
					beforeSend : function() {

						$("#loading").show();
					},
					complete : function() {

						$("#loading").hide();
					},
					success : function(data, status) {
						data = JSON.parse(data);
						console.log(data);
						if (typeof (data.status) != 'undefined') {
							if (data.status != '1') {
								cont = 0;
								bootbox.confirm('文件上传失败，请重新上传！', function(
										result) {
									location.reload();
								})
							} else {
								fn = data.filename;
								console.log("成功");
								cont = 1;
								console.log();
								bootbox.confirm('文件上传成功！', function(result) {
								});
								$
										.ajax({
											type : 'POST',
											url : "/pgms/json/sheet_analysis.action",
											data : {
												"processXlsFileName" : data.filename
											},
											success : function(data, status) {
												console.log(data.sheets);
												strs = data.sheets.split(";");
												console.log(strs);
												var sheet_selector = $('select[name="sheet_selector[]"]');
												for (var i = 0; i < strs.length; i++) {
													sheet_selector
															.append('<option value='+strs[i]+'>'
																	+ strs[i]
																	+ '</option>');
												}
												sheet_selector
														.bootstrapDualListbox(
																'refresh', true);
											},
											dataType : "json"
										});
								$("#nextbtn").removeClass("hidden");
							}
						}

					}
				});
	}
</script>
