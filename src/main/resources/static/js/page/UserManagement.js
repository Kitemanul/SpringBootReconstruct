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
            url: "/user/Userdata",
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
                    permission:row.权限,

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
function getEditCeller() {

    var data = {
        _username:olddata.username,
        username:$("#username_edit").val(),
        mm:$("#mm_edit").val(),
        permission:$("#permission_edit").val(),
    };



    return data

}
function EditUser() {

    console.log(getEditCeller());
    $.ajax({
        url:'/user/EditUser',
        data:getEditCeller(),
        type:'POST',
        success:function(str){	//成功回调函数
            if(str=="修改成功")
            {
                alert("修改成功");

                $('#Modal_edit').modal("hide");

            }
            else{

                alert("修改失败");
            }

        },
        error:function (err){	//失败回调函数

            alert("编辑失败");
        }
    });


}
function editCeller(da) {

    olddata=da;

    $("#username_edit").val(da.username)
    $('#permission_edit').val(da.permission);

    $('#Modal_edit').modal("show");



}
