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
            url: "/Celler/CellerData.do",

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
            {"data": "入窖时间"},
            {"data": "出窖时间"},
            {"data": "周期"},
            {"data": "组号"},
            {"data": "罐号"}

        ],
        "columnDefs" : [ {
            "targets" : 6,//操作按钮目标列
            "data" : null,
            "render" : function(data, type,row) {
                da={
                    time:row.时间,
                    intime:row.入窖时间,
                    outtime:row.出窖时间,
                    jarid:row.罐号,
                    groupid:row.组号,
                    period:row.周期,
                }

                var html = "<a href='javascript:void(0);'   onclick='deleteCeller("+ JSON.stringify(da) + ")'  class='down btn btn-default btn-xs'> 删除</a>"
                +"<a href='javascript:void(0);'   onclick='editCeller("+ JSON.stringify(da)+ ")'  class='down btn btn-default btn-xs'> 修改</a>"


                return html;
            }
        } ],

    })

})
var deletedata;
function remove() {

    $.ajax({
        url:'/Celler/RemoveCeller',
        data:deletedata,
        type:'POST',
        success:function(str){	//成功回调函数
            if(str=="删除成功")
            {
                alert("删除成功");

                $('#delcfmModel').modal("hide");

            }
            else{

                alert("删除失败");
            }

        },
        error:function (err){	//失败回调函数

            alert("删除失败");
        }
    });

}
function deleteCeller(da) {

    deletedata=da;
    $('#delcfmModel').modal("show");




}


function reloadTable() {


    table.settings()[0].ajax.data =getSearchData() ;
    table.ajax.reload();
    
}
function AddCeller() {

    $("#time_add").val("2020-05-02");
    $("#intime_add").val("2020-05-02");
    $("#outtime_add").val("2020-05-02");
    $("#jarid_add").val(1);
    $("#groupid_add").val(2);
    $("#period_add").val(5);

    $('#Modal_add').modal("show");

}
function Add() {

    $('#Modal_add').modal("show");

    $.ajax({
        url:'/Celler/AddCeller',
        data:{
            time:$("#time_add").val()+" 00:00:00.000",
            intime:$("#intime_add").val()+" 00:00:00.000",
            outtime:$("#outtime_add").val()+" 00:00:00.000",
            jarid: $("#jarid_add").val(),
            groupid :$("#groupid_add").val(),
            period:$("#period_add").val()
        },
        type:'POST',
        success:function(str){	//成功回调函数
            if(str=="录入成功")
            {
                alert("录入成功");

                $('#Modal_add').modal("hide");

            }
            else{

                alert("录入失败");
            }

        },
        error:function (err){	//失败回调函数

            alert("录入失败");
        }
    });

    
}


function getSearchData() {

    if($("#groupid").val()=="")
    {
        group=0;
    }
    else
    {
        group=$("#groupid").val();
    }

    if($("#jarid").val()=="")
    {
        jar=0;
    }
    else
    {
        jar=$("#jarid").val();
    }

    if($("#period").val()=="")
    {
        period=-1;
    }
    else
    {
        period=$("#period").val();
    }

    Data={
        "intime":$("#WHC_PayDate").val(),
        "outtime":$("#WHC_PayDate2").val(),
        "groupid":group,
        "jarid":jar,
        "period":period
    }
    return Data;

}
var olddata;
function getEditCeller() {

    var data = new Array();

    oldata={
        time:new Date(olddata.time).getTime(),
        intime:new Date(olddata.intime).getTime(),
        outtime:new Date(olddata.outtime).getTime(),
        jarid: olddata.jarid,
        groupid :olddata.groupid,
        period:olddata.period
    }

    newdata={
        time:new Date($("#time").val()).getTime(),
        intime:new Date($("#intime").val()).getTime(),
        outtime:new Date($("#outtime").val()).getTime(),
        jarid: $("#jarid_edit").val(),
        groupid :$("#groupid_edit").val(),
        period:$("#period_edit").val()
    }

    data.push(oldata);
    data.push(newdata);


    return data

}
function EditCeller() {

    console.log(getEditCeller());
    $.ajax({
        url:'/Celler/EditCeller',
        contentType:"application/json",
        data:JSON.stringify(getEditCeller()),
        type:'POST',
        success:function(str){	//成功回调函数
            if(str=="编辑成功")
            {
                alert("编辑成功");

                $('#Modal_edit').modal("hide");

            }
            else{

                alert("编辑失败");
            }

        },
        error:function (err){	//失败回调函数

            alert("编辑失败");
        }
    });


}
function editCeller(da) {

    olddata=da;

    $('#Modal_edit').modal("show");

    $("#time").val(da.time);
    $("#intime").val(da.intime);
    $("#outtime").val(da.outtime);
    $("#jarid_edit").val(da.jarid);
    $("#groupid_edit").val(da.groupid);
    $("#period_edit").val(da.period);

}
