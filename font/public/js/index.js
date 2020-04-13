var myApp = angular.module('index', ['ngCookies'])

myApp.controller('indexController', ['$scope', '$http','$cookies','$cookieStore','$interval',function ($scope, $http,$cookies,$cookieStore,$interval) {
    var vm = $scope

    request=$http
    let user = $cookieStore.get("user");
    if(user==null){
        swal("错误","请先登录！","error")
        setTimeout(function () {
            window.location.href="/login"

        },3000)
    }

    var violet = '#DF99CA',
        red = '#F0404C',
        green = '#7CF29C',
        blue = '#4680ff';
    //员工性别占比
    //查询后台男女个数
    vm.getSex=function() {
        request({
            method: 'GEt',
            url: 'http://localhost:8080/charts/getSex'
        }).then(function (response) {
            var PIECHART = $('#pieChart1');
            var myPieChart = new Chart(PIECHART, {
                type: 'doughnut',
                options: {
                    cutoutPercentage: 90,
                    legend: {
                        display: true
                    }
                },
                data: {
                    labels: [
                        "女",
                        "男",
                    ],
                    datasets: [
                        {
                            data: response.data.data,
                            borderWidth: [0, 0],
                            backgroundColor: [
                                green,
                                blue,
                            ],
                            hoverBackgroundColor: [
                                green,
                                blue,
                            ]
                        }]
                }
            });


        }, function (response) {

        });
    }
    vm.getSex();
    //员工等级占比
    vm.getType=function() {
        request({
            method: 'GEt',
            url: 'http://localhost:8080/charts/getSex'
        }).then(function (response) {
            var PIECHART = $('#pieChart2');
            var myPieChart = new Chart(PIECHART, {
                type: 'doughnut',
                options: {
                    cutoutPercentage: 90,
                    legend: {
                        display: true
                    }
                },
                data: {
                    labels: [
                        "普通员工",
                        "管理层员工"
                    ],
                    datasets: [
                        {
                            data: response.data.data,
                            borderWidth: [0, 0],
                            backgroundColor: [
                                violet,
                                red
                            ],
                            hoverBackgroundColor: [
                                violet,
                                red
                            ]
                        }]
                }
            });


        }, function (response) {

        });
    }
    vm.getType();


    //员工绩效占比


    var DOUGHNUTCHARTEXMPLE  = $('#doughnutChartExample');
    var pieChartExample1 = new Chart(DOUGHNUTCHARTEXMPLE, {
        type: 'doughnut',
        options: {
            cutoutPercentage: 80,
        },
        data: {
            labels: ['正在加载数据'],
            datasets: [
                {
                    data: ['10'],
                    borderWidth: 0,
                    backgroundColor: [
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red
                    ],
                    hoverBackgroundColor: [
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red
                    ]
                }]
        }
    });

    pieChartExample1.options = {
        responsive: true
    };

    vm.getJiXiaoZHanBi=function() {
        request({
            method: 'GEt',
            url: 'http://localhost:8080/charts/getJiXIaoZhanBi'
        }).then(function (response) {
            pieChartExample1.data.labels=response.data.data[0]
            pieChartExample1.data.datasets[0].data=response.data.data[1]
            pieChartExample1.update()


        }, function (response) {

        });
    }

    vm.getJiXiaoZHanBi();
    $interval(vm.getJiXiaoZHanBi,5000)

    //待处理审核占比
    vm.shenhedata=new Array()
    vm.shenhedata[0]=["正在初始化"]
    vm.shenhedata[1]=[10]

    var PIECHARTEXMPLE    = $('#pieChartExample');
    var pieChartExample = new Chart(PIECHARTEXMPLE, {
        type: 'pie',
        data: {
            labels: vm.shenhedata[0],
            datasets: [
                {
                    data: vm.shenhedata[1],
                    borderWidth: 0,
                    backgroundColor: [
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red
                    ],
                    hoverBackgroundColor: [
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red
                    ]
                }]
        }
    });

    pieChartExample.options = {
        responsive: true
    };
    vm.getShenhe=function() {
        request({
            method: 'GET',
            url: 'http://localhost:8080/charts/getShenHe'
        }).then(function (response) {
            vm.shenhedata=response.data.data
            pieChartExample.data.labels=vm.shenhedata[0]
            pieChartExample.data.datasets[0].data=vm.shenhedata[1]
            pieChartExample.update()



        }, function (response) {

        });
    }

    vm.getShenhe();
    $interval(vm.getShenhe,5000)

  //各地区分数比较


    var BARCHARTEXMPLE    = $('#barChartExample1');
    var barChartExample = new Chart(BARCHARTEXMPLE, {
        type: 'bar',
        options: {
            responsive: true,
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        color: '#fff'
                    }
                }],
                yAxes: [{
                    display: true,

                    gridLines: {
                        color: '#fff'
                    }
                }]
            },
            legend: false
        },
        data: {
            labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"],
            datasets: [
                {
                    label: "各地员工实时采销质量监控",
                    backgroundColor: [
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red,
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red,
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red
                    ],
                    hoverBackgroundColor: [
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red,
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red,
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red
                    ],
                    borderColor: [
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red,
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red,
                        violet,
                        green,
                        blue,
                        "#817e2e",
                        red
                    ],
                    borderWidth: 1,
                    data: [65, 59, 80, 81, 56, 55, 40, 30, 45, 80, 44, 36, 66, 58],
                }
            ]
        }
    });
    vm.getAddr=function() {
        request({
            method: 'GET',
            url: 'http://localhost:8080/charts/getAddr'
        }).then(function (response) {
            barChartExample.data.labels=response.data.data[0]
            barChartExample.data.datasets[0].data=response.data.data[1]
            barChartExample.update()



        }, function (response) {

        });
    }
    vm.getAddr();
    $interval(vm.getAddr,5000)

}])
