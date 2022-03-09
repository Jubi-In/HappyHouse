$(document).ready(function () {
  function getParam(sname) {
    var params = location.search.substr(location.search.indexOf("?") + 1);
    var sval = "";
    params = params.split("&");
    for (var i = 0; i < params.length; i++) {
      temp = params[i].split("=");
      if ([temp[0]] == sname) {
        sval = temp[1];
      }
    }
    return sval;
  }
  var user = localStorage.getItem(getParam("id"));

  if (user) {
    var userText = JSON.parse(user);
    var id = userText.id;
    var pw = userText.pw;
    var name = userText.name;
    var addr = userText.addr;
    var tel = userText.tel;
    console.log(id);
    $("#id").attr("value", id);
    $("#pw").attr("value", pw);
    $("#name").attr("value", name);
    $("#addr").attr("value", addr);
    $("#tel").attr("value", tel);
  }

  function register() {
    console.log(document.getElementById("id").value);
    var str = {
      id: document.getElementById("id").value,
      pw: document.getElementById("pw").value,
      name: document.getElementById("name").value,
      addr: document.getElementById("addr").value,
      tel: document.getElementById("tel").value,
    };

    localStorage.setItem(document.getElementById("id").value, JSON.stringify(str));
  }

  var id = document.getElementById("id").value;

  $(document).on("click", "#regbtn", function () {
    localStorage.removeItem(id);
    register();
    window.location.href = "info.html?id=" + id;
  });
});
