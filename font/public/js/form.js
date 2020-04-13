var myApp = angular.module('form', ['ngCookies'])

myApp.controller('formController', ['$scope', '$http','$cookies','$cookieStore',function ($scope, $http,$cookies,$cookieStore) {
    var vm = $scope

    request=$http
    let user = $cookieStore.get("user");
    if(user==null){
        swal("错误","请先登录！","error")
        setTimeout(function () {
            window.location.href="/login"
        },3000)
    }
    vm.clear=function(){
        vm.score=null;
        vm.staffId=null;
        vm.type=null;
        vm.getInfo(1)
    }
    vm.pageNum=0
    vm.showStaff=function(staffId){
        request({
            method: 'GET',
            url: 'http://localhost:8080/staff/getOne?id='+staffId,
        }).then(function (response) {
            // console.log(response.data)
            if(response.data.code!=200){
                swal("查询错误",response.data.data,"error")
            }
            var staff= response.data.data
            var str="员工编号:"+staff.id+"<br>"+
                "员工姓名:"+staff.name+"<br>"+
                "员工性别:"+staff.sex+"<br>"+
                "员工级别:"+staff.type+"<br>"+
                "员工地址:"+staff.addr+"<br>"+
                "员工手机:"+staff.phone
            swal({
                title: "员工详情",
                text: str,

                html: true,
                timer: 5000,
                showConfirmButton: false
            });


        }, function (response) {

        });

    }
    vm.showDetail=function(detail){
        var str=""
          if(detail==null){
               str="尚未有得分记录,评分评级采用默认数据";
          }else {
              var split = detail.split(";");

              for (var i = 0; i < split.length; i++) {
                  if (split[i] != "") {
                      str = str + "第" + (i + 1) + "条:" + split[i] + "<br>"
                  }
              }
          }
            swal({
                title: "员工得分详情",
                text: str,

                html: true,
                timer: 5000,
                showConfirmButton: false
            });



    }
    vm.rsList=new Array();
    //页面加载完成是与后台数据交互，后台返回总页数（如果只有总条数的话就自己算。Math.ceil(总条数/每条多少页)=总页数）
    vm.getInfo=function(pageNum) {
        request({
            method: 'POST',
            url: 'http://localhost:8080/jixiao/select',
            data: {
                "pageNum": pageNum,
                "pageSize": 10,
                "type":vm.type,
                "staffId":vm.staffId,
                "score":vm.score
            }


        }).then(function (response) {
            // console.log(response.data)
            vm.rsList= response.data.data
            vm.pageCount=vm.rsList.totalPage
            var pageinit= {
                pageNum: vm.pageCount,
                current: pageNum,
            }
            zp.addhtml($(".zxf_pagediv"),pageinit)
            // console.log(response)
            // response.data.


        }, function (response) {

        });
    }
    vm.getInfoInit=function(pageNum,type) {
        request({
            method: 'POST',
            url: 'http://localhost:8080/jixiao/select',
            data: {
                "pageNum": pageNum,
                "pageSize": 10,
                "type":vm.type,
                "staffId":vm.staffId,
                "score":vm.score
            }


        }).then(function (response) {
            // console.log(response.data)
            vm.rsList= response.data.data
            vm.pageCount=vm.rsList.totalPage
            $.fn.createPage = function(options){
                var pageinit = $.extend({
                    pageNum : vm.pageCount,
                    current : 1,
                    backfun : function(){}
                },options);
                zp.init(this,pageinit);
            }

            $(".zxf_pagediv").createPage({
                pageNum: vm.pageCount,
                current: 1,
                backfun: function(e) {

                    vm.getInfo(e.current);//回调
                }
            });



            // console.log(response)
            // response.data.


        }, function (response) {

        });
    }
    vm.getInfoInit(vm.pageNum)





    vm.checkFunction=function (staffId,id,hasCheck) {
        request({
            method: 'GET',
            url: 'http://localhost:8080/caixiao/shenhe?id='+id+'&check='+hasCheck+'&staffId='+staffId,

        }).then(function (response) {
            if(response.data.code!=200){
                swal("错误",response.data.data,"error")
                return
            }
            swal("成功",response.data.data,"success")
            vm.getInfo(0)
        }, function (response) {

        });
    }




}])
