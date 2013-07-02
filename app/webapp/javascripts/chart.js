
$(function () {

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
                categories: ['07-01 (Monday)', '07-02 (Tuesday)', '07-03 (Wednesday)', '07-04 (Thursday)', '07-05 (Friday)', '07-06 (Saturday)', '07-07 (Sunday)'],
                title: {
                    enabled: true,
                    text: 'Date (2013)'
                },
                startOnTick: true,
                endOnTick: true,
                showLastLabel: true
            },
            yAxis: {
                categories: [0,1,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24],
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
                    return '<b>'+ this.series.name +'</b><br/>'+ this.x +': '+ this.y + ':00';
                }
            },
            series: [{
                name: "Xi'an",
                color: 'rgba(223, 83, 83, .5)',
                data: [['07-01', 10],
                       ['07-02', 22],
                       ['07-03', 13],
                       ['07-04', 12],
                       ['07-05', 12],
                       ['07-06', 19]]
            }, {
                name: "Chengdu",
                color: 'rgba(0, 0, 225, .5)',
                data: [['07-01', 5],
                       ['07-02', 3],
                       ['07-03', 11],
                       ['07-04', 23],
                       ['07-05', 23],
                       ['07-06', 23]]
            }, {
                name: "Beijing",
                color: 'rgba(0, 0, 0, .5)',
                data: [['07-01', 23],
                       ['07-05', 23],
                       ['07-05', 23],
                       ['07-05', 23],
                       ['07-05', 23],
                       ['07-05', 23]]
            },{
                name: "Shanghai",
                color: 'rgba(0, 225, 0, .5)',
                data: [['7-01', 23],
                       ['7-02', 21],
                       ['7-03', 13],
                       ['7-04', 19],
                       ['7-05', 23],
                       ['7-06', 23]]
            }]
        });
    });
