<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.jsp"%>

	<script  src="/js/dxTree/dhtmlXUtil.js"></script>
	<script charset="utf-8" src="http://map.soso.com/api/v2/main.js"></script>
	<script src="/js/CommonUtil.js"></script>
	<script src="/js/vehicleStatusUtil.js"></script>

	<script type="text/javascript" charset="utf-8">

	//var projectType = "<s:property value="#parameters.projectType"/>";
	//var role = "<s:property value="#session.sessionUserAccountRoleId"/>";

    function getDataList(){

    	$.extend( $.fn.dataTable.defaults, {
    		"oLanguage": {
    			"sUrl": "/js/jquery/datatables/language/search_zh_CN.txt"
    		},
    		"fnServerData": function ( sUrl, aoData, fnCallback, oSettings ) {
    			oSettings.jqXHR = $.ajax( {
    				"url":  sUrl,
    				"data": aoData,
    				"success": function (json) {
    					if ( json.sError ) {
    						oSettings.oApi._fnLog( oSettings, 0, json.sError );
    					}

    					$(oSettings.oInstance).trigger('xhr', [oSettings, json]);
    					var dtJsonData = {};
    					dtJsonData.iTotalDisplayRecords = json.iTotalDisplayRecords;
    					dtJsonData.aaData = json.aaData;
    					consoleAllPrpos(dtJsonData);
    					fnCallback( dtJsonData );
    				},
    				"dataType": "json",
    				"cache": false,
    				"type": oSettings.sServerMethod,
    				"error": function (xhr, error, thrown) {
    					if ( error == "parsererror" ) {
    						oSettings.oApi._fnLog( oSettings, 0, "DataTables warning: JSON data from "+
    							"server could not be parsed. This is caused by a JSON formatting error." );
    					}
    				}
    			} );
    		}
    	});
        var aoColumns =[    { "mData": "device" },
                                        { "mData": "factory" },
                                        { "mData": "productLine" },
                                        { "mData": "hwVersion" },
                                        { "mData": "swVersion" },
                                        { "mData": "chipId" },
                                        { "mData": "sn" },
                                        { "mData": "iccid" },
                                        { "mData": "gpsCount" },
                                        { "mData": "flash" },
                                        { "mData": "eeprom" },
                                        { "mData": "gprs" },
                                        { "mData": "batteryVoltage" },
                                        { "mData": "electricCurrent" },

                                        {
                                            "mData": "id",
                                            "mRender":function(data, type, full){
                                                return {id:full.id, testResult:full.testResult};
                                             }
                                        },
                                        { "mData": "receiveTime" }
                                        ];

                    var aoColumnDefs = [
                                            {
                                                "bSortable": false,
                                                "aTargets": [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
                                            },

                                            {
                                                "aTargets": [0],
                                                "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                    nTd.innerHTML =  sData;
                                                }
                                            },
                                            {
                                                "aTargets": [1],
                                                "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                    nTd.innerHTML =  sData;
                                                }
                                            },
                                               {
                                                   "aTargets": [2],
                                                   "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                       nTd.innerHTML =  sData;
                                                   }
                                               },
                                               {
                                                   "aTargets": [3],
                                                   "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                       nTd.innerHTML = sData;
                                                   }
                                               },
                                               {
                                                   "aTargets": [4],
                                                   "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                         nTd.innerHTML = sData;
                                                   }
                                               },
                                             {
                                                  "aTargets": [5],
                                                  "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                        nTd.innerHTML = sData;
                                                  }
                                              },
                                            {
                                                 "aTargets": [6],
                                                 "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                       nTd.innerHTML = sData;
                                                 }
                                            },
                                            {
                                                  "aTargets": [7],
                                                  "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                        nTd.innerHTML = sData;
                                                  }
                                          },
                                          {
                                                    "aTargets": [8],
                                                    "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                          nTd.innerHTML = sData;
                                                    }
                                            },
                                        {
                                              "aTargets": [9],
                                              "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                    nTd.innerHTML = sData;
                                              }
                                         },

                                          {
                                            "aTargets": [10],
                                            "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                  nTd.innerHTML = sData;
                                            }
                                         },

                                        {
                                              "aTargets": [11],
                                              "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                    nTd.innerHTML = sData;
                                              }
                                         },
                                          {
                                               "aTargets": [12],
                                               "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                     nTd.innerHTML = sData;
                                               }
                                          },

                                           {
                                             "aTargets": [13],
                                             "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                   nTd.innerHTML = sData;
                                             }
                                          },



                                            {
                                                "aTargets": [14],//操作
                                                "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                    var innerHTML = "";
                                                    if (sData.testResult == 0) {
                                                        innerHTML = "测试通过"
                                                    } else if (sData.testResult == 1) {
                                                       innerHTML = "测试未通过"
                                                    }
                                                    nTd.innerHTML = innerHTML;
                                                }
                                            },

                                            {
                                                   "aTargets": [15],
                                                   "fnCreatedCell": function (nTd, sData, parentNode, iRow, iCol) {
                                                         nTd.innerHTML = sData;
                                                   }
                                             }

                                        ];



    s_dataTablesObj = $('#dataTable0').dataTable({
                    "bLengthChange": false,
                    "bFilter": false,
                    "bDestroy": true,
                    "sAjaxSource": "/product/list.kyx",
                    "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
                    console.log(nRow);
                    console.log(aData);
                    console.log(iDisplayIndex);
                        $(nRow).bind("mouseover", function(){
                            $(this).children().each(function(i, td){
                                $(this).css("background", "#f5f5f5");
                            });
                        });
                        $(nRow).bind("mouseout", function(){
                            $(this).children().each(function(i, td){
                                $(this).css("background", "#ffffff");
                            });
                        });
                        //给除了第一列以外的所有列添加点击事件
                        $(nRow).children(":gt(0)").each(function(i, td){
                            //鼠标样式
                            $(this).css("cursor", "pointer");
                        });
                    },
                    "aoColumns": aoColumns,
                    "aoColumnDefs": aoColumnDefs
                    });

    }


$().ready(function() {
    getDataList();
    $("#s_testResult").change(function(e){
        //列表页面车辆搜索
        s_dataTablesObj.fnPageChange('first');
    });

    $("#s_factory").change(function(e){
        //列表页面车辆搜索
        s_dataTablesObj.fnPageChange('first');
    });

    $("#s_productLine").change(function(e){
        //列表页面车辆搜索
        s_dataTablesObj.fnPageChange('first');
    });



    $("#downloadAsExcel").click(function(e){
      var switchState = $("#s_switchState").val();
      var projectType = "<s:property value="#parameters.projectType"/>";
      window.location.href="/fleetOS/project/downloadProject.kyx?switchState=" + switchState + "&projectType=" + projectType;
    });

} );
//-----------列表结束----------
function switchJob(obj){
    var orgId = obj.value;
    if (obj.checked == true) {
       jobSwitch = 1;
       var selectAll = true;
       $("input[name='orgIds']").each(function (i,val) {
             if($(val).attr("checked") != "checked"){
                 selectAll = false;
             }
       });
       if(selectAll){
            $("#selectAll").attr("checked", true);
       }

    } else {
       jobSwitch = 0;
       $("#selectAll").attr("checked", false);
    }
    var data = {'org.id': orgId , 'org.jobSwitch':jobSwitch};
    $.ajax({
        url:"/fleetOS/orgManager/switchOrgJob.kyx",
        type:"post",
        data: data,
        success:function(resultData ){
            if (resultData) {
                if (resultData[0] && resultData[0].code != 1) {
                    alert(resultData[0].message);
                }
             }
        }

    });
}



function checkAll(checkbox) {
	if (checkbox.checked == true) {
        $("#jobSwitch").val(1);
		$("#dataTable0 :checkbox").each(function (i,val) {
			$(val).attr("checked", true);
		});
		var data = $("#myPageForm").serialize();
        $.ajax({
            url:"/fleetOS/orgManager/batchSwitchJobs.kyx",
            type:"post",
            data: data,
            success:function(resultData ){
                if (resultData) {
                    if (resultData[0] && resultData[0].code != 1) {
                        alert(resultData[0].message);
                    }
                 }
            }

        });

	} else {
	    $("#jobSwitch").val(0);
	    var data = $("#myPageForm").serialize();
        $.ajax({
            url:"/fleetOS/orgManager/batchSwitchJobs.kyx",
            type:"post",
            data: data,
            success:function(resultData ){
                if (resultData) {
                    if (resultData[0] && resultData[0].code != 1) {
                        alert(resultData[0].message);
                    }else{
                    		$("#dataTable0 :checkbox").each(function (i,val) {
                    			$(val).attr("checked", false);
                    		});
                    }
                 }
            }

        });

	}



}


function switchPush(obj){
    var orgId = obj.value;
    if (obj.checked == true) {
       mqSwitch = 1;
       var selectAll = true;
       $("input[name='pushSwitch']").each(function (i,val) {
             if($(val).attr("checked") != "checked"){
                 selectAll = false;
             }
       });
       if(selectAll){
            $("#allMqSwitch").attr("checked", true);
       }

    } else {
       mqSwitch = 0;
       $("#allMqSwitch").attr("checked", false);
    }
    var data = {'org.id': orgId , 'org.mqSwitch':mqSwitch};
    $.ajax({
        url:"/fleetOS/orgManager/switchOrgMq.kyx",
        type:"post",
        data: data,
        success:function(resultData ){
            if (resultData) {
                if (resultData[0] && resultData[0].code != 1) {
                    alert(resultData[0].message);
                }
             }
        }

    });
}



function switchAllPush(checkbox) {
	if (checkbox.checked == true) {
        $("#mqSwitch").val(1);
		$("input[name='pushSwitch']").each(function (i,val) {
			$(val).attr("checked", true);
		});
		var data = $("#myPageForm").serialize();
        $.ajax({
            url:"/fleetOS/orgManager/batchSwitchMqs.kyx",
            type:"post",
            data: data,
            success:function(resultData ){
                if (resultData) {
                    if (resultData[0] && resultData[0].code != 1) {
                        alert(resultData[0].message);
                    }
                 }
            }

        });

	} else {
	    $("#mqSwitch").val(0);
        $("input[name='pushSwitch']").each(function (i,val) {
            $(val).attr("checked", false);
        });
	    var data = $("#myPageForm").serialize();
        $.ajax({
            url:"/fleetOS/orgManager/batchSwitchMqs.kyx",
            type:"post",
            data: data,
            success:function(resultData ){
                if (resultData) {
                    if (resultData[0] && resultData[0].code != 1) {
                        alert(resultData[0].message);
                    }
                 }
            }

        });

	}



}



</script>

</head>
<body>
<div class="wrapper">
	<!-- topbar starts -->
    <!--头部-->
	<!-- topbar ends -->

		<div class="container-fluid">
		<div class="row-fluid">

			<!-- left menu starts -->
			<%@ include file="/include/leftMenu.jsp"%><!--菜单-->
			<!-- left menu ends -->

			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">告警!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>

			<div id="content" class="span10">
			<!-- content starts -->

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-th"></i> Product List</h2>
						<div class="box-icon">
							<a href="#" class="btn-minimize btn-small"><i class="icon-chevron-up"></i></a>
						</div>
					</div>
					<div class="box-content">

                  	<div class="row-fluid">

                        <form method="post" action="" id="myPageForm">
							<div class="tab_container">
								<div class="tab_content">

                                    <div class="dataTable_btnBox" style=" margin-bottom:5px;">
                                    </div>


                                    <div class="control-group">
                                    测试结果：
                                            <select id="s_testResult" name="testResult">

                                                <option value="-1">所有</option>
                                                <option value="0">通过</option>
                                                <option value="1">未通过</option>

                                            </select>
                                           工厂： <input type="text" id="s_factory" name="factory"/>
                                           生产线： <input type="text" id="s_productLine" name="productLine"/>
                                    <a href="javascript:void(0);" id="downloadAsExcel" class="btn btn-small">导出为excel</a>

                                    </div>

									<table cellpadding="0" cellspacing="0" border="0" class="display dataTable" id="dataTable0">
										<thead>
											<tr>

                                                  <th> device</th>
                                                  <th> factory </th>
                                                  <th>productLine</th>
                                                  <th>hwVersion</th>
                                                  <th>swVersion</th>
                                                  <th>chipId</th>
                                                  <th>sn</th>
                                                  <th>iccid</th>
                                                  <th>gpsCount</th>
                                                  <th>flash</th>
                                                  <th>eeprom</th>
                                                  <th>gprs</th>
                                                  <th>batteryVoltage</th>
                                                  <th>electricCurrent</th>
                                                  <th>testResult</th>
                                                  <th>receiveTime</th>


											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div><!-- end of #tab_content -->
							</div><!-- end of .tab_container -->


						</form>
                    </div>
                  </div>
				</div><!--/span-->
			</div><!--/row-->

					<!-- content ends -->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
	</div><!--/.fluid-container-->

    <%@ include file="/include/footer.jsp"%>
</div>
</body>
</html>