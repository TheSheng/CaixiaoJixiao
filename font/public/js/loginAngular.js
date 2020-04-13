var myApp = angular.module('login', ['ngCookies'])

myApp.controller('loginController', ['$scope', '$http','$cookies','$cookieStore',function ($scope, $http,$cookies,$cookieStore) {
    var vm = $scope

    request=$http
    vm.username=""
    vm.password=""
    function tiao(url){
        window.location.href=url
    }
    $cookieStore.remove("user")

    vm.login=function () {
        console.log(vm.password)
        if(null==vm.username){
            swal("错误","请重新输入，建议关闭自动填充","error")
            return;
        }
        if(vm.username.length<6||vm.username.length>15){
            swal("错误","用户名长度要在6到15位之间","error")
            return
        }
        if(vm.password.length<6||vm.password.length>15){
            swal("错误","密码长度要在6到15位之间","error")
            return
        }
        data = {
            username: vm.username,
            password: vm.password,

        }
        request({
            method: 'POST',
            url: 'http://localhost:8080/user/login',
            data: data


        }).then(function (response) {
            // console.log(response.data)
            var rs=response.data
            console.log(response)
            if(rs.code=="200"){
                $cookieStore.put("user","ok")

                tiao("/")
            }else{
                swal("错误!", "用户名或密码错误", "error");
            }
            // console.log(response)
            // response.data.


        }, function (response) {

        });
    }


}])
