var table;

$(function () {

    $('#WHC_PayDate').datepicker({
        format : 'yyyy-mm-dd',
        todayBtn: true,
        autoclose : true
    });


    $('#WHC_PayDate2').datepicker({
        format : 'yyyy-mm-dd',
        autoclose : true
    });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
        beforeSend: function (xhr) {
            if(header && token ){
                xhr.setRequestHeader(header, token);
            }
        }});


    table= $("#example1").DataTable({
        "sScrollX":1200,
        "language" :{
            url: "/js/jquery.dataTables.chinese.json"
        },
        "processing": true,
        "serverSide": false,
        "ajax": {
            type:'POST',
            url: "/Temperature/SearchTemperature",
            data:{
                jarid: $("#jarid").val(),
                groupid :$("#groupid").val(),
                period:$("#period").val(),
                rate:$("#rate").val()
            }

        },
        "autoWidth": true,
        "lengthChange": true,
        "DisplayChange": 10,
        "searching": false,
        "filter": true,
        "ordering": true,
        "pagingType": "full_numbers",
        "cursor":"pointer",
        "columns": [
            {"data": "时间"},
            {"data": "组号"},
            {"data": "罐号"},
            {"data": "周期"},
            {"data": "温度"},
            {"data": "变化率"}

        ],


    })

})



function reloadTable() {

    data={
        jarid: $("#jarid").val(),
        groupid :$("#groupid").val(),
        period:$("#period").val(),
        rate:$("#rate").val()
    }

    console.log(data);
    table.settings()[0].ajax.data =data ;

    table.ajax.reload();

}