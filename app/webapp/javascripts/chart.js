
$(function () {
       var dateList = {
                "TCX": [
                   [Date.UTC(2013, 0, 7), 10],
                   [Date.UTC(2013, 0, 2), 22],
                   [Date.UTC(2013, 0, 5), 13],
                   [Date.UTC(2013, 0, 4), 12],
                   [Date.UTC(2013, 0, 5), 12],
                   [Date.UTC(2013, 0, 2), 12]
                ],
                "TCC": [
                    [Date.UTC(2013, 0, 1), 11],
                    [Date.UTC(2013, 0, 2), 23],
                    [Date.UTC(2013, 0, 3), 10],
                    [Date.UTC(2013, 0, 4), 3]
                ],
                "TBS": [
                    [Date.UTC(2013, 0, 1), 13],
                    [Date.UTC(2013, 0, 2), 0],
                    [Date.UTC(2013, 0, 3), 12],
                    [Date.UTC(2013, 0, 4), 15]
                ],
                "Shanghai": [
                    [Date.UTC(2013, 0, 1), 10],
                    [Date.UTC(2013, 0, 2), 4],
                    [Date.UTC(2013, 0, 3), 13],
                    [Date.UTC(2013, 0, 4), 19]
                ]
        };

//       var dateList = {};

       var drawChart = function(){
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
                minTickInterval: 24*3600*1000,
                title: {
                    enabled: true,
                    text: 'Day'
                },
                startOnTick: false,
                endOnTick: false
            },
            yAxis: {
                categories: [0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24],
                tickInterval: 2,
                title: {
                    text: 'Submit Time (Hour)',
                },
                labels: {
                    format: '{value}:00'
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
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                    Highcharts.dateFormat('%e. %b', this.x) +': '+ this.y + ':00';
                }
            },
            series: [{
                name: "Xi'an",
                color: 'rgba(223, 83, 83, .5)',
                data: dateList["TCX"]
            },{
                name: "Chengdu",
                color: 'rgba(0, 0, 225, .5)',
                data: dateList['TCC']
            },
            {
                name: "Shanghai",
                color: 'rgba(0, 0, 0, .5)',
                data: dateList['Shanghai']
            },{
                name: "Beijing",
                color: 'rgba(0, 225, 0, .5)',
                data: dateList['TBS']
            }]
        });
       };


          $.ajax({
            url:"chartData",
            contentType: "application/json; charset=utf-8",
            type: 'GET',
            dataType: 'json',
            success: function(data){
                console.log(data);
                console.log(data['TCX'][0][1]);
                console.log('************');
//               dataList = data;
               drawChart();
            }
          });
//       drawChart();
    });
