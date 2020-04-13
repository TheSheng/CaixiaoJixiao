var myApp = angular.module('table', ['ngCookies'])

myApp.controller('tableController', ['$scope', '$http','$cookies','$cookieStore',function ($scope, $http,$cookies,$cookieStore) {
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
        vm.mapname=null;
        vm.mapphone=null;
        vm.mapaddr=null;
        vm.getInfo(1)
    }

    vm.pageNum=1


    vm.rsList=new Array();
    //页面加载完成是与后台数据交互，后台返回总页数（如果只有总条数的话就自己算。Math.ceil(总条数/每条多少页)=总页数）
    vm.getInfo=function(pageNum) {
        request({
            method: 'POST',
            url: 'http://localhost:8080/staff/select',
            data: {
                "pageNum": pageNum,
                "pageSize": 5,
                "name":vm.mapname,
                "phone":vm.mapphone,
                "addr":vm.mapaddr
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
            url: 'http://localhost:8080/staff/select',
            data: {
                "pageNum": pageNum,
                "pageSize": 5,
                "name":vm.mapname,
                "phone":vm.mapphone,
                "addr":vm.mapaddr
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








    vm.submitStaff=function(){
        thisdata={
            "name":vm.name,
            "sex":vm.sex,
            "type":vm.type,
            "addr":vm.addr,
            "phone":vm.phone
        }
        request({
            method: 'POST',
            url: 'http://localhost:8080/staff/add',
            data: thisdata

        }).then(function (response) {
            if(response.data.code!=200){
                swal("错误",response.data.data,"error")
                return
            }
            swal("成功",response.data.data,"success")
            vm.showTable=true;
            vm.showAdd=false;
            vm.getInfo(1)

            // console.log(response)
            // response.data.


        }, function (response) {

        });
    }
    vm.submitUpdateStaff=function(){
        thisdata={
            "id":vm.staffid,
            "name":vm.oldname,
            "sex":vm.oldsex,
            "type":vm.oldtype,
            "addr":vm.oldaddr,
            "phone":vm.oldphone
        }
        request({
            method: 'POST',
            url: 'http://localhost:8080/staff/update',
            data: thisdata

        }).then(function (response) {
            if(response.data.code!=200){
                swal("错误",response.data.data,"error")
                return
            }
            swal("成功",response.data.data,"success")
            vm.showTable=true;
            vm.showUpdate=false;
            vm.getInfo(1)

            // console.log(response)
            // response.data.


        }, function (response) {

        });
    }
   vm.showAdd=false;
   vm.showTable=true;
   vm.showUpdate=false;
   vm.showAddFunction=function () {
        vm.showAdd=true;
        vm.showTable=false;
   }
    vm.showUpdateFunction=function (i) {
       vm.oldname=i.name;
       vm.oldtype=i.type;
       vm.oldsex=i.sex;
       vm.oldphone=i.phone;
       vm.staffid=i.id;
       vm.oldaddr=i.id;
        vm.showUpdate=true;
        vm.showTable=false;
    }
   vm.del=function (id) {
       request({
           method: 'GET',
           url: 'http://localhost:8080/staff/delete?id='+id,

       }).then(function (response) {
           if(response.data.code!=200){
               swal("错误",response.data.data,"error")
               return
           }
           swal("成功",response.data.data,"success")
           vm.getInfo(1)
       }, function (response) {

       });
   }



}])
