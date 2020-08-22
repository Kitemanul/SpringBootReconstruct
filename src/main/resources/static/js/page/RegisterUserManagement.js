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
            url: "/user/registeringUserdata",
            data:getSearchData()

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
            {"data": "用户名"},
            {"data": "权限"},
            {"data": "审核状态"},

        ],
        "columnDefs" : [ {
            "targets" : 3,//操作按钮目标列
            "data" : null,
            "render" : function(data, type,row) {
                da={
                    username:row.用户名,


                }

                var html = "<a href='javascript:void(0);'   onclick='editCeller("+ JSON.stringify(da)+ ")'  class='down btn btn-default btn-xs'> 审核</a>"


                return html;
            }
        } ],

    })

})
var deletedata;
function remove() {

    $.ajax({
        url:'/user/RemoveUser',
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
function AddUser() {


    $('#Modal_add').modal("show");

}
function Add() {

    $.ajax({
        url:'/user/AddUser',
        data:{
            username:$("#username_add").val(),
            mm:$("#mm_add").val(),
            permission:$("#permission_add").val(),
        },
        type:'POST',
        success:function(str){	//成功回调函数
            if(str=="添加成功")
            {
                alert("添加成功");

                $('#Modal_add').modal("hide");

            }
            else{

                alert("添加失败");
            }

        },
        error:function (err){	//失败回调函数

            alert("录入失败");
        }
    });


}


function getSearchData() {

    if($("#userid").val()=="")
    {
        username="";
    }
    else
    {
        username=$("#userid").val();
    }

    if($("#permission").val()=="")
    {
        permission =0;
    }
    else
    {
        permission=$("#permission").val();
    }

    Data={
        "username":username,
        "permission":permission,

    }
    return Data;

}
var olddata;

function EditUser() {

    $.ajax({
        url:'/user/userpass',
        data:olddata,
        type:'POST',
        success:function(str){	//成功回调函数
            if(str=="审核通过")
            {
                alert("审核通过");

                $('#Modal_edit').modal("hide");

            }
            else{

                alert("审核失败");
            }

        },
        error:function (err){	//失败回调函数

            alert("审核失败");
        }
    });


}
function editCeller(da) {

    olddata=da;

    $("#username_edit").val(da.username)


    $('#Modal_edit').modal("show");



}
