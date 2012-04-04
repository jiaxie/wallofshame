function setSB(v, el) {
  var ie5 = (document.all && document.getElementsByTagName);
  if (ie5 || document.readyState == "complete") {
    filterEl = el.children[0];
    valueEl = el.children[1];
    filterEl.style.width = v * 5 + "px";
    valueEl.innerText = v + "%";
  }
}
function fakeProgress(v, el, redirect_path) {
  if (v > 100)
    location.href = redirect_path;
  else {
    setSB(v, el);
    window.setTimeout("fakeProgress(" + (++v) + ", document.all['" + el.id + "'], '" + redirect_path + "')", 50);
  }
}
