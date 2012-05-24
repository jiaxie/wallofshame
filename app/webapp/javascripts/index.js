function isFriday() {
  var now = new Date();
  return (now.getDay() === 5)
}

$(function() {
  $('#payrollSelect').change(onPayrollSelected);
  $('#officeSelect').change(onOfficeSelected);

  if (isFriday()) {
    $("#timesheetcall").show();
    $('marquee').hide();
  }
  else
  {
    $('#wallslider').isotope({
      layoutMode: 'masonryHorizontal',
      masonryHorizontal: {
        rowHeight: 200
      }
    });
    $('marquee').marquee();
  }
});

function onPayrollSelected() {
  var selectedPayroll = $('#payrollSelect').val();
  reload(selectedPayroll, 'All');
}

function onOfficeSelected() {
  var selectedPayroll = $('#payrollSelect').val();
  var selectedOffice = $('#officeSelect').val();
  reload(selectedPayroll, selectedOffice);
}

function reload(payroll, office) {
  window.location.href = 'index.html?payroll=' + payroll + '&office=' + office;
}