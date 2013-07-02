
$(function () {    
    var getDate = function(string){
      var date = new Date(string);
        return date.getTime();
    };
   
        $('#container').highcharts({
            chart: {
                type: 'scatter',
                zoomType: 'xy'
            },
            title: {
                text: 'Timesheet Submitted Time'
            },
            subtitle: {
                text: 'ThoughtWorks'
            },
            xAxis: {
                 type: 'datetime',
                 dateTimeLabelFormats: {
                    day: '%e. %b'
                },
                title: {
                    enabled: true,
                    text: 'Day'
                },
                startOnTick: true,
                endOnTick: true,
                showLastLabel: true
            },
            yAxis: {
                type: 'datetime',           
                tickInterval:3600 * 1000 * 2,
                dateTimeLabelFormats : {
                    hour: '%H:00'
                },
                title: {
                    text: 'Time (Hour)'
                }
            },
            legend: {
                layout: 'vertical',
                align: 'left',
                verticalAlign: 'top',
                x: 100,
                y: 70,
                floating: true,
                backgroundColor: '#FFFFFF',
                borderWidth: 1
            },
            plotOptions: {
                series:{
                    pointStart:Date.UTC(2013,5,1),
                    pointInterval: 3600 * 1000
                },
                scatter: {
                    marker: {
                        radius: 5,
                        states: {
                            hover: {
                                enabled: true,
                                lineColor: 'rgb(100,100,100)'
                            }
                        }
                    },
                    states: {
                        hover: {
                            marker: {
                                enabled: false
                            }
                        }
                    }
                }
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%e. %b', this.x) +': '+                             Highcharts.dateFormat('%H:%M', this.y);
                }
            },
            series: [{
                name: "Xi'an",
                color: 'rgba(223, 83, 83, .5)',
                data: [[getDate('6/28/2013  4:49:11 AM'), getDate('6/28/2013  4:49:11 AM')],
                       [getDate('6/28/2013  2:33:14 AM'), getDate('6/28/2013  2:33:14 AM')],
                       [getDate('6/29/2013  6:42:13 AM'), getDate('6/29/2013  6:42:13 AM')],
                       [getDate('6/23/2013  11:54:37 PM'), getDate('6/23/2013  11:54:37 PM')]],
            pointStart: Date(2012, 0, 1),
            pointInterval: 24 * 3600 * 1000 // one day
            },{
                name: "Chengdu",
                color: 'rgba(223, 223, 83, .5)',
                data: [[getDate('6/23/2013  5:49:11 AM'), getDate('6/23/2013  5:49:11 AM')],
                       [getDate('6/22/2013  6:33:14 AM'), getDate('6/22/2013  6:33:14 AM')],
                       [getDate('6/27/2013  7:42:13 AM'), getDate('6/27/2013  7:42:13 AM')],
                       [getDate('6/25/2013  21:54:37 PM'), getDate('6/25/2013  21:54:37 PM')]],
            pointStart: Date(2012, 0, 1),
            pointInterval: 24 * 3600 * 1000 // one day
            },{
                name: "Beijing",
                color: 'rgba(83, 223, 83, .5)',
                data: [[getDate('6/24/2013  5:49:11 AM'), getDate('6/24/2013  5:49:11 AM')],
                       [getDate('6/25/2013  6:33:14 AM'), getDate('6/25/2013  6:33:14 AM')],
                       [getDate('6/24/2013  7:42:13 AM'), getDate('6/24/2013  7:42:13 AM')],
                       [getDate('6/27/2013  21:54:37 PM'), getDate('6/27/2013  21:54:37 PM')]],
            pointStart: Date(2012, 0, 1),
            pointInterval: 24 * 3600 * 1000 // one day
            },{
                name: "Shanghai",
                color: 'rgba(0, 0, 0, .5)',
                data: [[getDate('6/24/2013  5:49:11 AM'), getDate('6/24/2013  5:49:11 AM')],
                       [getDate('6/25/2013  6:33:14 AM'), getDate('6/25/2013  6:33:14 AM')],
                       [getDate('6/24/2013  7:42:13 AM'), getDate('6/24/2013  7:42:13 AM')],
                       [getDate('6/27/2013  21:54:37 PM'), getDate('6/27/2013  21:54:37 PM')]],
            pointStart: Date(2012, 0, 1),
            pointInterval: 24 * 3600 * 1000 // one day
            }]
        });
    });
