var myApp = angular.module('shenhe', ['ngCookies'])

myApp.controller('shenheController', ['$scope', '$http','$cookies','$cookieStore',function ($scope, $http,$cookies,$cookieStore) {
    var vm = $scope

    request=$http
    let user = $cookieStore.get("user");
    if(user==null){
        swal("错误","请先登录！","error")
        setTimeout(function () {
            window.location.href="/login"
        },3000)
    }

    vm.pageNum=0
    vm.rsList=new Array();
    //页面加载完成是与后台数据交互，后台返回总页数（如果只有总条数的话就自己算。Math.ceil(总条数/每条多少页)=总页数）
    vm.getInfo=function(pageNum) {
        request({
            method: 'POST',
            url: 'http://localhost:8080/caixiao/select',
            data: {
                "pageNum": pageNum,
                "pageSize": 10
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

                    vm.getInfoNotInit(e.current);//回调
                }
            });
            // console.log(response)
            // response.data.


        }, function (response) {

        });
    }
    vm.getInfoNotInit=function(pageNum,type) {
        request({
            method: 'POST',
            url: 'http://localhost:8080/caixiao/select',
            data: {
                "pageNum": pageNum,
                "pageSize": 10
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
    vm.getInfo(vm.pageNum)


    //点击分页按钮触发



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
