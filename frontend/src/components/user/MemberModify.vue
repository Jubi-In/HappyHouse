<template>
  <!-- 2021.11.21 한길 추가 -->
  <div class="container text-center mt-3">
    <div class="col-lg-8 mx-auto">
      <h2 class="p-3 mb-5 shadow bg-light">
        <mark style="background-color: #ebe9e9">회원정보수정</mark>
      </h2>

      <!-- 메세지 출력창 -->
      <b-alert show variant="danger" v-show="isPwdChangeError">
        {{ errorMsg }}
      </b-alert>
      <b-alert show variant="danger" v-show="isInfoChangeError">
        {{ errorMsg }}
      </b-alert>

      <table class="usermodify-table table">
        <tbody>
          <tr id="tr-top">
            <th>아이디</th>
            <td>
              <!-- 비밀번호수정일때 -->
              <strong v-if="isPwdChange" class="usermodify-current-id">{{
                userInfo.user_id
              }}</strong>
              <!-- 회원정보수정일때 -->
              <div v-if="!isPwdChange" class="usermodify">
                <b-form-input
                  id="user_id"
                  v-model="user.user_id"
                  disabled
                ></b-form-input>
              </div>
            </td>
          </tr>
          <tr>
            <th>닉네임</th>
            <td>
              <!-- 비밀번호수정일때 -->
              <strong v-if="isPwdChange" class="usermodify-current-name">{{
                userInfo.user_name
              }}</strong>
              <!-- 회원정보수정일때 -->
              <div v-if="!isPwdChange" class="usermodify">
                <b-form-input
                  id="user_name"
                  v-model="user.user_name"
                  placeholder="닉네임입력"
                  :state="nameState"
                  trim
                ></b-form-input>
              </div>
            </td>
          </tr>
          <tr v-if="isPwdChange">
            <th>비밀번호</th>
            <td>
              <!-- 비밀번호수정일때 -->
              <div v-if="isPwdChange">
                <div class="usermodify">
                  <!-- 현재비밀번호 입력 -->
                  <b-form-input
                    type="password"
                    id="putNowPwd"
                    v-model="newPwd.nowPwd"
                    placeholder="현재비밀번호 입력"
                    trim
                  ></b-form-input>
                  <!-- 새비밀번호 입력 -->
                  <b-form-input
                    type="password"
                    id="putNewPwd"
                    v-model="newPwd.newPwd"
                    placeholder="새 비밀번호 입력"
                    trim
                  ></b-form-input>
                  <!-- 새비밀번호 재입력 -->
                  <b-form-input
                    type="password"
                    id="putNewPwdCheck"
                    v-model="newPwd.newPwdCheck"
                    placeholder="새 비밀번호 재입력"
                    trim
                  ></b-form-input>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>
              <!-- 비밀번호수정일때 -->
              <div v-if="isPwdChange" class="usermodify">
                <strong class="usermodify-current-email">{{
                  userInfo.user_email
                }}</strong>
              </div>
              <!-- 회원정보수정일때 -->
              <div v-if="!isPwdChange" class="usermodify">
                <div class="row">
                  <div class="col">
                    <b-form-input
                      type="text"
                      class="form-control"
                      id="emailid"
                      v-model="user.emailid"
                      name="emailid"
                      placeholder="이메일입력"
                      :state="emailState"
                      trim
                    />
                  </div>
                  @
                  <div class="col">
                    <b-form-select
                      class="form-control"
                      v-model="user.emaildomain"
                      :options="emailDomainOptions"
                    >
                    </b-form-select>
                  </div>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <th>전화번호</th>
            <td>
              <!-- 비밀번호수정일때 -->
              <div v-if="isPwdChange" class="usermodify">
                <strong class="usermodify-current-tel">{{
                  userInfo.user_tel
                }}</strong>
              </div>
              <!-- 회원정보수정일때 -->
              <div v-if="!isPwdChange" class="usermodify">
                <div class="row">
                  <div class="col">
                    <b-form-input
                      type="number"
                      class="form-control"
                      id="tel1"
                      v-model="user.tel1"
                      name="tel1"
                      :state="tel1State"
                      trim
                    />
                  </div>
                  _
                  <div class="col">
                    <b-form-input
                      type="number"
                      class="form-control"
                      id="tel2"
                      v-model="user.tel2"
                      name="tel2"
                      :state="tel2State"
                      trim
                    />
                  </div>
                  _
                  <div class="col">
                    <b-form-input
                      type="number"
                      class="form-control"
                      id="tel3"
                      v-model="user.tel3"
                      name="tel3"
                      :state="tel3State"
                      trim
                    />
                  </div>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <th>주소</th>
            <td>
              <!-- 비밀번호수정일때 -->
              <div v-if="isPwdChange" class="usermodify">
                <strong class="usermodify-current-address">{{
                  userInfo.user_address
                }}</strong>
              </div>
              <!-- 회원정보수정일때 -->
              <div v-if="!isPwdChange" class="usermodify">
                <b-form-input
                  id="user_address"
                  v-model="user.user_address"
                  placeholder="주소 입력"
                  :state="addressState"
                ></b-form-input>
              </div>
            </td>
          </tr>
        </tbody>
      </table>

      <div class="form-group mt-4">
        <!-- 정보수정 버튼 -->
        <b-button
          type="button"
          variant="secondary"
          class="m-1"
          @click="toMyPage"
        >
          작성취소</b-button
        >
        <!-- 회원탈퇴 버튼 -->
        <b-button type="button" variant="primary" class="m-1" @click="doModify">
          수정완료</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";
export default {
  name: "MemberModify",
  data() {
    return {
      isPwdChangeError: false,
      isInfoChangeError: false,
      errorMsg: "",
      user: {
        user_name: "",
        user_id: "",
        user_pw: "",
        pwdcheck: "",
        user_email: "",
        emailid: "",
        emaildomain: "",
        user_tel: "",
        tel1: "010",
        tel2: "",
        tel3: "",
        user_address: "",
      },
      emailDomainOptions: [
        { value: "ssafy.com", text: "ssafy.com" },
        { value: "naver.com", text: "naver.com" },
        { value: "kakao.com", text: "kakao.com" },
        { value: "gmail.com", text: "gmail.com" },
      ],
      newPwd: {
        nowPwd: "",
        newPwd: "",
        newPwdCheck: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, [
      "isLogin",
      "isLoginError",
      "userInfo",
      "isPwdChange",
    ]),
    nameState() {
      if (this.user.user_name == this.userInfo.user_name) return null;
      else if (!this.user.user_name) return false;
      else return true;
    },
    emailState() {
      let emailPart = this.userInfo.user_email.split("@");
      if (this.user.emailid == emailPart[0]) return null;
      else if (!this.user.emailid) return false;
      else return true;
    },
    tel1State() {
      let telPart = this.userInfo.user_tel.split("-");
      if (this.user.tel1 == telPart[0]) return null;
      else if (!this.user.tel1) return false;
      else return true;
    },
    tel2State() {
      let telPart = this.userInfo.user_tel.split("-");
      if (this.user.tel2 == telPart[1]) return null;
      else if (!this.user.tel2) return false;
      else return true;
    },
    tel3State() {
      let telPart = this.userInfo.user_tel.split("-");
      if (this.user.tel3 == telPart[2]) return null;
      else if (!this.user.tel3) return false;
      else return true;
    },
    addressState() {
      if (this.user.user_address == this.userInfo.user_address) return null;
      else if (!this.user.user_address) return false;
      else return true;
    },
  },
  methods: {
    ...mapActions(memberStore, ["userUpdate"]),
    toMyPage() {
      this.$router.push({ name: "MemberMyPage" });
    },
    async doModify() {
      this.user.user_email = this.user.emailid + "@" + this.user.emaildomain;
      this.user.user_tel =
        this.user.tel1 + "-" + this.user.tel2 + "-" + this.user.tel3;
      if (this.isPwdChange) {
        // 비밀번호 수정
        this.isPwdChangeError = true;
        this.isInfoChangeError = false;
        if (!this.newPwd.nowPwd) {
          this.errorMsg = "현재 비밀번호를 입력해주세요.";
        } else if (!this.newPwd.newPwd) {
          this.errorMsg = "새 비밀번호를 입력해주세요.";
        } else if (this.newPwd.newPwd != this.newPwd.newPwdCheck) {
          this.errorMsg = "새 비밀번호가 일치하지 않습니다.";
        } else if (!this.newPwd.newPwd) {
          this.errorMsg = "새 비밀번호를 입력해주세요.";
        } else if (this.newPwd.nowPwd != this.userInfo.user_pw) {
          this.errorMsg = "현재 비밀번호를 확인해주세요.";
        } else {
          await this.userUpdate({
            user_id: this.user.user_id,
            user_pw: this.newPwd.newPwd,
            user_name: this.user.user_name,
            user_address: this.user.user_address,
            user_email: this.user.user_email,
            user_tel: this.user.user_tel,
          });
          this.$router.push({ name: "MemberMyPage" });
        }
      } else {
        // 회원정보 수정
        this.isPwdChangeError = false;
        this.isInfoChangeError = true;
        if (!this.user.user_name) {
          this.errorMsg = "닉네임을 입력해주세요.";
        } else if (!this.user.emailid) {
          this.errorMsg = "이메일을 입력해주세요.";
        } else if (!this.user.tel1 || !this.user.tel2 || !this.user.tel3) {
          this.errorMsg = "전화번호를 정확히 입력해주세요.";
        } else if (!this.user.user_address) {
          this.errorMsg = "주소를 입력해주세요.";
        } else {
          await this.userUpdate({
            user_id: this.user.user_id,
            user_pw: this.userInfo.user_pw,
            user_name: this.user.user_name,
            user_address: this.user.user_address,
            user_email: this.user.user_email,
            user_tel: this.user.user_tel,
          });
          this.$router.push({ name: "MemberMyPage" });
        }
      }
    },
  },
  created() {
    this.user.user_name = this.userInfo.user_name;
    this.user.user_id = this.userInfo.user_id;
    let emailPart = this.userInfo.user_email.split("@");
    this.user.emailid = emailPart[0];
    this.user.emaildomain = emailPart[1];
    let telPart = this.userInfo.user_tel.split("-");
    this.user.tel1 = telPart[0];
    this.user.tel2 = telPart[1];
    this.user.tel3 = telPart[2];
    this.user.user_address = this.userInfo.user_address;
  },
};
</script>

<style>
/* 전화번호 입력 input type 이 number 라 화살표가 있었는데, 그거 없애는거 */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>
