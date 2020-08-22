var table;

$(function () {

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
            url: "/Temperature/ErrorTemperatureSearch",
            data:{
                jarid:$("#jarid").val(),
                groupid:$("#groupid").val()
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
            {"data": "组号"},
            {"data": "罐号"},
            {"data": "温度"},
            {"data": "时间"}


        ],

    })

})

function reloadTable() {



    table.settings()[0].ajax.data ={
        jarid:$("#jarid").val(),
        groupid:$("#groupid").val()

    } ;

    table.ajax.reload();

}