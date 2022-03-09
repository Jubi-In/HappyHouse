$(document).ready(function () {
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
    register();
    window.location.href = "info.html?id=" + id;
  });
});
