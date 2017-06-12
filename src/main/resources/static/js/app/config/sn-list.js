(function ($) {
    var $table = $('.table'),
            $remove = $('#remove'),
            $export = $('#export'),
            $add = $('#add'),
            $save = $('#save'),
            $refresh = $('#refresh'),
            selections = ["device"];
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

                // TODO remove data from ajax api
                console.log("TODO 需要实现真实删除");
            });

	$table.bootstrapTable({
		'url': '/sn_config/list',
		'queryParams': function(params){
            var temp = {
               device: $('#device').val(),
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
                field: 'device',
                title: '设备',
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
                field: 'create_time',
                title: '创建时间',
                formatter: function (value, row, index) {
                     return moment(value).format('YYYY-MM-DD HH:mm:ss');
                }
            }

        ]
	});


    $add.click(function() {
        $("#packet-edit-modal").show();
    });

     $save.click(function() { 

        $("#snForm").ajaxSubmit({

               beforeSubmit:function(){

             },

              success:function(data) {
                    if(data.data != null) {
                        window.location.href = "/web/plan/sn";
                    }
              },
            error: function(data) {
                data = data.responseJSON;
                alert(data.code + ":" + data.msg);
            }
        });

     });

    $close.click(function() {
        $("#packet-edit-modal").hide();
    });

    $cross.click(function() {
        $("#packet-edit-modal").hide();
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
         var productLine = $('#productLine').val();
         var testResult = $('#testResult').val();
         window.location.href="/web/devices/export?factory=" + factory + "&productLine=" + productLine + "&testResult=" + testResult;

    });

})(jQuery);