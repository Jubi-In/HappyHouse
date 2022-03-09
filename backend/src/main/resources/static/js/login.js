$(document).ready(function () {
  $(document).on("click", "#loginbtn", function () {
    var id = document.getElementById("id").value;
    var pw = document.getElementById("pw").value;
    var user = localStorage.getItem(id);
    var userText = JSON.parse(user);

    if (userText.pw === pw) {
      document.querySelector(".logout").style.display = "none";
      document.querySelector(".login").style.display = "block";
      window.location.href = "index.html?login=" + "ok";
    } else {
      alert("아이디 비밀번호를 확인하세요.");
    }
  });
});
