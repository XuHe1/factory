
    function isExist(username) {
            if (username == null) {
            }
            $.ajax({
                url: '/order/user/' + username,
                type: 'get',
                error: function(data) {
                    data = data.responseJSON;
                    alert(data.code + ":" + data.msg);
                }

            });
    }


(function ($) {
    var $table = $('.table'),
            $remove = $('#remove'),
            $export = $('#export'),
            $add = $('#add'),
            $refresh = $('#refresh'),
            selections = ["device"];
            $upload = $('#upload');
            $save = $('#save');
            $close = $('#close');
            $cross = $('#cross');

    // 获取选中的行
    function getIdSelections() {
            return $.map($table.bootstrapTable('getSelections'), function (row) {
                return row.id
            });
    }

    $remove.click(function () {
                var ids = getIdSelections();
                $table.bootstrapTable('remove', {
                    field: 'id',
                    values: ids
                });
                $remove.prop('disabled', true);

                console.log(ids)

                var params = {"state": 3, "id": ids};
                $.ajax({
                    url: '/order',
                    type: 'put',
                    data: params,
                    success:function(data) {
                        if(data.data != null) {
                            window.location.href = "/web/order";
                        }
                    },
                    error: function(data) {
                        data = data.responseJSON;
                        alert(data.code + ":" + data.msg);
                    }
                });

                // TODO remove data from ajax api
                console.log("TODO 需要实现真实删除");
            });


	$table.bootstrapTable({
		'url': '/order/list',
		'queryParams': function(params){
            var temp = {
               factory: $('#factory').val(),
               device: $('#device').val(),
               state: $('#state').val(),
               orderNo: $('#orderNo').val(),
               limit: params.limit, //页面大小
               offset: params.offset, //页码
              };
              return temp;
		 },
        'columns': [
//            {
//                checkbox: true
//            },
            {
                field: 'order_no_prefix',
                title: '订单号',
                formatter: function (value, row, index) {
                       return value + (Array(6).join(0) + row.id).slice(-6);
                }
            },
            {
                field: 'factory',
                title: '工厂',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'device',
                title: '设备',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'fw_version',
                title: '固件版本',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'fw_download',
                title: '固件下载地址',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'hw_version',
                title: '硬件版本',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'quantity',
                title: '数量',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'delivery_count',
                title: '分配sn数/次',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'start_sn',
                title: '起始sn',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'end_sn',
                title: '结束sn',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'username',
                title: '账号',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'password',
                title: '密码',
                formatter: function (value, row, index) {
                    return value;
                }
            },

            {
                field: 'schedule_begin',
                title: '预计开始',
                formatter: function (value, row, index) {
                    return moment(value).format('YYYY-MM-DD HH:mm:ss');
                }
            },
            {
                field: 'schedule_finish',
                title: '预计结束',
                formatter: function (value, row, index) {
                    return moment(value).format('YYYY-MM-DD HH:mm:ss');
                }
            },
            {
                field: 'create_time',
                title: '创建时间',
                formatter: function (value, row, index) {
                    return moment(value).format('YYYY-MM-DD HH:mm:ss');
                }
            },
            {
                field: 'update_time',
                title: '更新时间',
                formatter: function (value, row, index) {
                    return moment(value).format('YYYY-MM-DD HH:mm:ss');
                }
            },
            {
                field: 'attachment_id',
                title: 'BOM',
                formatter: function (value, row, index) {
                      if(value == null) {
                        return "";
                      }
                     return "<a href='/web/order/attachment/" + value+ "'>下载</a>"
                }
            },
            {
                field: 'state',
                title: '操作',
                formatter: function (value, row, index) {

                        var html = '';

                        if(value == 0) {
                            html = "<button type='button' id='modify' class='btn btn-default' value='" + row.id + "'>修改</button>";
                        } else if (value == 1 ) {
                            html = "<button type='button' id='stop' class='btn btn-default' value='" + row.id + "'>暂停</button>";
                        } else if (value == 2) {
                            html = "<button type='button' id='continue' class='btn btn-default' value='" + row.id + "'>继续</button>";
                        }
                        return html;
                }
            }

        ]
	});

    // 选中行后，设置删除按钮为可用
    $table.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        // save your data, here just save the current page
        //selections = getIdSelections();
        // push or splice the selections if you want to save all data selections
    });

    $export.click(function() {

         var factory = $('#factory').val();
         var device = $('#device').val();
         var state = $('#state').val();
         var orderNo = $('#orderNo').val();
         window.location.href="/web/order/export?factory=" + factory + "&device=" + device + "&state=" + state + "&orderNo=" + orderNo;

    });

    $add.click(function() {
        $('#id').val('');
        $('#orderNoPrefix').val('');
//        $('#form_device').val('');
        $('#project').val('');
        $('#fw_version').val('');
        $('#fw_download').val('');
        $('#hwVersion').val('');
        $('#quantity').val('');
//        $('#form_factory').val('');
        $('#username').val('');
        $('#password').val('');
        $('#deliveryCount').val('');
//        $('#scheduleBegin').val('');
//        $('#scheduleFinish').val('');
        $('#scheduleBegin').datetimepicker('setStartDate', new Date());
        $('#scheduleFinish').datetimepicker('setStartDate', new Date());
        $('#packet-edit-modal-label').html('新建订单');
        $("#packet-edit-modal").show();
    });

    $close.click(function() {
        $("#packet-edit-modal").hide();
    });

    $cross.click(function() {
        $("#packet-edit-modal").hide();
    });


     $upload.click(function() { 
        $("#fileForm").ajaxSubmit({

               beforeSubmit:function(){

             },

              success:function(data){
                    if(data.data != null) {
                         $('#attachmentId').val(data.data.id);
                         alert('上传成功')
                    }
              }
        });

       });

     $save.click(function() { 

//        var content = $("#scheduleBeginStr").val().replace('T', ' ');
//        $("#scheduleBegin").val(content);
//
//        var content = $("#scheduleFinishStr").val().replace('T', ' ');
//        $("#scheduleFinish").val(content);

        $("#orderForm").ajaxSubmit({

               beforeSubmit:function(){

             },

              success:function(data) {
                    if(data.data != null) {
                        window.location.href = "/web/order";
                    }
              },
            error: function(data) {
                data = data.responseJSON;
                alert(data.code + ":" + data.msg);
            }
        });

     });

    $(document).on('click', '#modify', function() {
        var id = this.value;
        $.ajax({
            url: '/order/' + id,
            type: 'get',
            success: function(data) {

                data = data.data;
                $('#id').val(data.id);
                $('#orderNoPrefix').val(data.order_no_prefix);
                $('#form_device').val(data.device);
                $('#project').val(data.project);
                $('#project').change();
                $('#fw_version').val(data.fw_version);
                $('#fw_download').val(data.fw_download);
                $('#hwVersion').val(data.hw_version);
                $('#quantity').val(data.quantity);
                $('#form_factory').val(data.factory);
                $('#username').val(data.username);
                $('#password').val(data.password);
                $('#deliveryCount').val(data.delivery_count);
                $('#scheduleBegin').datetimepicker('setStartDate', new Date());
                $('#scheduleFinish').datetimepicker('setStartDate', new Date());
                $('#scheduleBegin').val(moment(data.schedule_begin).format('YYYY-MM-DD HH:mm'));
                $('#scheduleFinish').val(moment(data.schedule_finish).format('YYYY-MM-DD HH:mm'));
                $('#packet-edit-modal-label').html('修改订单');
                $("#packet-edit-modal").show();



                console.log(data);
            }
        });

    });

    $(document).on('click', '#stop', function() {
        var id = new Array();
        id.push(this.value);
        var params = {"state": 2, "id": id};
        $.ajax({
            url: '/order',
            type: 'put',
            data: params,
            success:function(data) {
                if(data.data != null) {
                    window.location.href = "/web/order";
                }
            },
            error: function(data) {
                data = data.responseJSON;
                alert(data.code + ":" + data.msg);
            }
        });

    });

    $(document).on('click', '#continue', function() {
        var id = new Array();
        id.push(this.value);
        var params = {"state": 1, "id": id};
        $.ajax({
            url: '/order',
            type: 'put',
            data: params,
            success:function(data) {
                if(data.data != null) {
                    window.location.href = "/web/order";
                }
            },
            error: function(data) {
                data = data.responseJSON;
                alert(data.code + ":" + data.msg);
            }
        });

    });


    $('#scheduleBegin').datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        startDate: new Date(),
        autoclose: true,
        todayBtn: true
    }).on('changeDate', function(){
        $('#scheduleFinish').datetimepicker('setStartDate', this.value);
    });

    $('#scheduleFinish').datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        startDate: new Date(),
        autoclose: true,
        todayBtn: true
    });

    $('#project').change(function(){
         localStorage.clear();
          $.ajax({
              url: '/web/order/fm_versions/' + $(this).val(),
              type: 'get',
              async: false,
              success:function(data) {
                  var versionArray = data.data;
                  if(versionArray != null) {
                    var obj=document.getElementById('fw_version');
                    obj.options.length=0;
                    for(var item in versionArray) {
                        obj.add(new Option(versionArray[item].version, versionArray[item].version));
                        localStorage.setItem(versionArray[item].version, versionArray[item].path);
                      }

                     $('#fw_download').val(versionArray[0].path);
                  }
              },
              error: function(data) {
                  data = data.responseJSON;
                  alert(data.code + ":" + data.msg);
              }
          });
    });

        $('#fw_version').change(function(){
             var path = localStorage.getItem($(this).val());
            $('#fw_download').val(path);

        });



})(jQuery);