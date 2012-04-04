jQuery(function() {
  setUpBrickSlider();
  setUpLastUpdateTimeCounter();
  jQuery('#payrollSelect').change(onPayrollSelected);
  jQuery('#officeSelect').change(onOfficeSelected);
});

function setUpLastUpdateTimeCounter() {
  var startDateStr = jQuery('#lastUpdateTime').text();
  var startDate = new Date(startDateStr);
  var startMills = startDate.getTime();
  setInterval(function() {
    var now = new Date();
    var nowMills = now.getTime();
    var mins = Math.floor((nowMills - startMills) / (1000 * 60)) % 60;
    var hours = Math.floor((nowMills - startMills) / (1000 * 60 * 60));
    jQuery('#lastUpdateTime').text(hours + ' hours ' + mins + ' minutes ago');

  }, 500);
}

function interval() {
  return 3000;
}

function setUpBrickSlider() {
  setInterval(function() {

    if (isAllBricksOnWall())
      return;

    slideBricks();

  }, interval());
}

function slideBricks() {

  $('#wallslider > li:first').fadeOut(1000, function() {
    $('#wallslider > li:first').appendTo('#wallslider');
    $('#wallslider > li:last').show();
  });
}
function isAllBricksOnWall() {
  var viewportHeight = $(window).height();
  if ($('#wallslider >li:last').offset() == null)
    return true;
  var sliderHeight = $('#wallslider > li:last').offset().top + $('#wallslider > li:last').outerHeight();
  return viewportHeight >= sliderHeight;
}


function onPayrollSelected() {
  var selectedPayroll = jQuery('#payrollSelect').val();
  reload(selectedPayroll, 'All');
}

function onOfficeSelected() {
  var selectedPayroll = jQuery('#payrollSelect').val();
  var selectedOffice = jQuery('#officeSelect').val();
  reload(selectedPayroll, selectedOffice);
}

function reload(payroll, office) {
  window.location.href = 'index.html?payroll=' + payroll + '&office=' + office;
}