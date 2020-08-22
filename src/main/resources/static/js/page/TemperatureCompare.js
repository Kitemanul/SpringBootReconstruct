
var chart;
$(function () {
    chart = Highcharts.chart('containerchart', {
        title: {
            text: '温度变化折线图'
        },
        tooltip:{

            formatter: function() {

                if (this.series.name =='第一组')
                {return '时间：第' + Math.ceil(this.x)+'天<br/>'+'温度(℃)：' +this.y;}
                else if(this.series.name =='第二组')
                {return '时间：第' + Math.ceil(this.x)+'天<br/>'+'温度(℃)：' + this.y;}
                else if(this.series.name =='第一组变化率')
                {return '时间：第' + Math.ceil(this.x)+'天<br/>'+'变化率(℃)：' + this.y;}
                else if(this.series.name =='第二组变化率')
                {return '时间：第' + Math.ceil(this.x)+'天<br/>'+'变化率(℃)：' + this.y;}
            }
        },
        //
        xAxis: {
            title: {
                text: '时间/天'
            }
        },
        //
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },
        //
        plotOptions: {
            series: {
                pointInterval:1/48
            }
        },
        //
        series: [{
            name:'第一组',
            data:[]

        },
            {
                name:'第一组变化率',
                data:[]

            },
            {
                name:'第二组',
                data:[]

            },
            {
                name:'第二组变化率',
                data:[]

            }]
    });

});


/**
 * Name 数据载入
 */

function getCompareData() {

    var customerArray = new Array();
    customerArray.push(
        {
            "period": $("#period").val(),
            "groupid": $("#groupid").val(),
            "jarid": $("#jarid").val(),
            "rate": $("#rate").val()
        }
    );
    customerArray.push(
        {   "period":$("#period2").val(),
            "groupid":$("#groupid2").val(),
            "jarid":$("#jarid2").val(),
            "rate":$("#rate2").val()
        }
    );
    return customerArray;

}

//ajax传递搜索数据 作图
function draw()
{
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
        beforeSend: function (xhr) {
            if(header && token ){
                xhr.setRequestHeader(header, token);
            }
        }});

    console.log(JSON.stringify(getCompareData()));
    $.ajax({
        type:"post",
        url:"/Temperature/Compare",
        data:JSON.stringify(getComparedata()),
        contentType:"application/json",
        dataType:"json",
        async:true,
        success:function(Data)
        {
            var data1=new Array();
            var data2=new Array();
            var rate1=new Array();
            var rate2=new Array();
            var m=0;
            var n=0;
            for(var i in Data)
            { if(Data[i].分类=='1组')
            {data1[m]=Data[i].温度;
                rate1[m++]=Data[i].变化率;
            }
            else if(Data[i].分类=='2组')
            { data2[n]=Data[i].温度;
                rate2[n++]=Data[i].变化率;
            }
            };

            //Highchart作图
            chart.series[0].setData(data1);
            chart.series[1].setData(rate1);
            chart.series[2].setData(data2);
            chart.series[3].setData(rate2);

            //
        },
        error:function()
        {    alert("错误");}
    });

}


function getComparedata() {
    var arr=new Array();

    data1={
        groupid:$("#groupid").val(),
        jarid:$("#jarid").val(),
        period:$("#period").val(),
        rate:$("#rate").val()
    }
    data2={
        groupid:$("#groupid2").val(),
        jarid:$("#jarid2").val(),
        period:$("#period2").val(),
        rate:$("#rate2").val()
    }
    arr.push(data1)
    arr.push(data2)

    return arr;

}