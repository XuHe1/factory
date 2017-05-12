(function ($) {
    var $table = $('.table'),
            $remove = $('#remove'),
            $export = $('#export'),
            $save = $('#save'),
            $refresh = $('#refresh'),
            selections = ["device"];

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
		'url': '/product/list',
		'queryParams': function(params){
            var temp = {
               factory: $('#factory').val(),
               productLine: $('#productLine').val(),
               testResult: $('#testResult').val(),
               limit: params.limit, //页面大小
               offset: params.offset, //页码
              };
              return temp;
		 },
        'columns': [
            {
                checkbox: true
            },
            {
                field: 'device',
                title: '设备',
                formatter: function (value, row, index) {
                    return value;
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
                field: 'product_line',
                title: '生产线',
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
                field: 'sw_version',
                title: '软件版本',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'chip_id',
                title: '芯片号',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'sn',
                title: '设备号',
                formatter: function (value, row, index) {
                    return value == null? 'Z994499' : value;
                }
            },
            {
                field: 'iccid',
                title: 'ICCID',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'gps_count',
                title: 'GPS',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'flash',
                title: 'FLASH',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'eeprom',
                title: 'eeprom',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                field: 'gprs',
                title: 'GPRS',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                 field: 'battery_voltage',
                 title: '电压',
                 formatter: function (value, row, index) {
                     return value;
                 }
            },
            {
                  field: 'electric_current',
                  title: '电流',
                  formatter: function (value, row, index) {
                      return value;
                  }
            },
            {
                field: 'test_result',
                title: '测试结果',
                formatter: function (value, row, index) {
                    return value == 0? "通过": "未通过";
                }
            },

            {
                field: 'receive_time',
                title: '接收时间',
                formatter: function (value, row, index) {
                     return moment(value).format('YYYY-MM-DD HH:mm:ss');
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
         var productLine = $('#productLine').val();
         var testResult = $('#testResult').val();
         window.location.href="/web/devices/export?factory=" + factory + "&productLine=" + productLine + "&testResult=" + testResult;

    });

    $save.click(function() {
        // TODO
    });
})(jQuery);