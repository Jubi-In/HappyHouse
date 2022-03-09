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
  var id = getParam("id");
  var user = localStorage.getItem(id);

  if (user) {
    var userText = JSON.parse(user);
    var id = userText.id;
    var pw = userText.pw;
    var name = userText.name;
    var addr = userText.addr;
    var tel = userText.tel;

    $("#id").text(id);
    $("#pw").text(pw);
    $("#name").text(name);
    $("#addr").text(addr);
    $("#tel").text(tel);
  }

  $(document).on("click", "#ok", function () {
    window.location.href = "index.html";
  });

  $(document).on("click", "#update", function () {
    window.location.href = "userUpdate.html?id=" + id;
  });

  $(document).on("click", "#delete", function () {
    localStorage.removeItem(id);
    window.location.href = "index.html";
  });
});
