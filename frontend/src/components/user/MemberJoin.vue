<template>
  <div class="container text-center mt-3">
    <div class="col-lg-8 mx-auto">
      <h2 class="p-3 mb-3 shadow bg-light">
        <mark class="orange">회원가입</mark>
      </h2>
      <b-form class="text-left">
        <!-- 이름 입력칸 -->
        <b-form-group label="닉네임" label-for="user_name">
          <b-form-input
            id="user_name"
            v-model="user.user_name"
            placeholder="닉네임입력"
            @keyup.enter="confirm"
            trim
          ></b-form-input>
        </b-form-group>
        <!-- 아이디 입력칸 -->
        <b-form-group label="아이디" label-for="user_id">
          <b-form-input
            id="user_id"
            placeholder="아이디입력"
            v-model="user.user_id"
            :state="nameState"
            aria-describedby="input-live-help input-live-feedback"
            @input="checkid"
            @keyup.enter="confirm"
            trim
          ></b-form-input>
          <b-form-invalid-feedback
            id="input-live-feedback"
            v-show="isAlreadyUsedId"
          >
            이미 사용중인 아이디입니다.
          </b-form-invalid-feedback>
          <b-form-invalid-feedback id="input-live-feedback" v-show="!isValidId">
            영문 및 숫자를 합해서 2~14자리로만 입력 가능합니다.
          </b-form-invalid-feedback>
          <b-form-text
            id="input-live-help"
            v-show="!isAlreadyUsedId && user.user_id && isValidId"
          >
            사용 가능한 아이디입니다.
          </b-form-text>
          <b-form-text
            id="input-live-help"
            v-show="!isAlreadyUsedId && !user.user_id"
          >
            영문 및 숫자를 합해서 2~14자리로만 입력 가능합니다.
          </b-form-text>
        </b-form-group>
        <!-- 비밀번호 입력칸 버튼 -->
        <b-form-group label="비밀번호" label-for="user_pw">
          <b-form-input
            type="password"
            id="user_pw"
            v-model="user.user_pw"
            :state="pwdState"
            aria-describedby="input-live-help input-live-feedback"
            placeholder="비밀번호입력"
            @input="checkpwd"
            @keyup.enter="confirm"
            trim
          ></b-form-input>
          <b-form-invalid-feedback
            id="input-live-feedback"
            v-show="!isValidPwd"
          >
            영문 + 숫자 + 특수문자를 반드시 포함하고 6~16자리로만 입력
            가능합니다.
          </b-form-invalid-feedback>
          <b-form-text id="input-live-help" v-show="!this.user.user_pw">
            영문 + 숫자 + 특수문자를 반드시 포함하고 6~16자리로만 입력
            가능합니다.
          </b-form-text>
          <b-form-text
            id="input-live-help"
            v-show="isValidPwd && this.user.user_pw"
          >
            사용 가능한 비밀번호입니다.
          </b-form-text>
        </b-form-group>
        <!-- 비밀번호 재입력 입력칸 -->
        <b-form-group label="비밀번호 재입력" label-for="pwdcheck">
          <b-form-input
            type="password"
            id="pwdcheck"
            v-model="user.pwdcheck"
            placeholder="비밀번호재입력"
            @keyup.enter="confirm"
            trim
          ></b-form-input>
        </b-form-group>
        <!-- 이메일 입력칸 -->
        <label for="emailid">이메일</label><br />
        <div class="row">
          <div class="col">
            <b-form-input
              type="text"
              class="form-control"
              id="emailid"
              v-model="user.emailid"
              name="emailid"
              placeholder="이메일입력"
              size="25"
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

        <!-- 전화번호 입력칸 -->
        <label class="mt-2">전화번호</label>
        <div class="row">
          <div class="col">
            <b-form-input
              type="number"
              class="form-control"
              id="tel1"
              v-model="user.tel1"
              name="tel1"
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
              trim
            />
          </div>
        </div>
        <!-- 주소 입력칸 -->
        <b-form-group label="주소" label-for="address" class="mt-2">
          <b-form-input
            id="address"
            v-model="user.user_address"
            required
            placeholder="주소입력"
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form-group>
        <!-- 회원가입 버튼 -->
        <div class="form-group text-center">
          <b-button
            type="button"
            variant="outline-primary"
            class="m-1"
            @click="confirm"
          >
            회원가입</b-button
          >
          <!-- 초기화 버튼 -->
          <b-button type="reset" variant="outline-danger" class="m-1"
            >초기화</b-button
          >
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
// 2021.11.19 한길 수정
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";
import { checkIdValid } from "@/api/member";
export default {
  name: "MemberJoin",
  data() {
    return {
      user: {
        user_name: "",
        user_id: "",
        user_pw: "",
        pwdcheck: "",
        user_email: "",
        emailid: "",
        emaildomain: "ssafy.com",
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
      msg: "",
      isAlreadyUsedId: false,
      isValidId: false,
      isValidPwd: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["isRegister", "isRegisterError"]),
    nameState() {
      if (!this.user.user_id) return null;
      else if (this.isAlreadyUsedId || !this.isValidId) return false;
      else return true;
    },
    pwdState() {
      if (!this.user.user_pw) return null;
      else if (!this.isValidPwd) return false;
      else return true;
    },
  },
  methods: {
    ...mapActions(memberStore, ["register"]),
    async confirm() {
      if (!this.user.user_name) {
        alert("닉네임을 입력해주세요.");
      } else if (!this.user.user_id) {
        alert("아이디를 작성해주세요.");
      } else if (this.isAlreadyUsedId) {
        alert("이미 사용중인 아이디입니다.");
      } else if (!this.isValidId) {
        alert(
          "아이디는 영문 대소문자와 숫자를 합해서 2~14자리로 입력해야합니다.",
        );
      } else if (!this.user.user_pw) {
        alert("비밀번호를 입력해주세요.");
      } else if (!this.isValidPwd) {
        alert(
          "비밀번호는 영문 + 숫자 + 특수문자를 반드시 포함하고 6~16자리로 입력해야 합니다.",
        );
      } else if (this.user.user_pw != this.user.pwdcheck) {
        alert("재입력이 틀렸습니다. 비밀번호를 확인해주세요.");
      } else if (!this.user.emailid || !this.user.emaildomain) {
        alert("이메일을 입력해주세요.");
      } else if (!this.user.tel1 || !this.user.tel2 || !this.user.tel3) {
        alert("전화번호를 정확히 입력하였는지 확인해주세요.");
      } else if (!this.user.user_address) {
        alert("주소를 입력해주세요.");
      } else {
        this.user.user_email = this.user.emailid + "@" + this.user.emaildomain;
        this.user.user_tel =
          this.user.tel1 + "-" + this.user.tel2 + "-" + this.user.tel3;
        await this.register(this.user);
        if (this.isRegister) {
          alert("회원가입에 성공하였습니다.");
          this.$router.push({ name: "SignIn" });
        } else {
          alert("회원가입 실패");
        }
      }
    },
    async checkid(e) {
      if (this.user.user_id) {
        await checkIdValid(
          e,
          (response) => {
            if (response.data === "valid") {
              this.msg = "사용 가능한 아이디입니다.";
              this.isAlreadyUsedId = false;
            } else {
              this.msg = "이미 사용중인 아이디입니다.";
              this.isAlreadyUsedId = true;
            }
          },
          (error) => {
            console.log(error);
          },
        );
      }
      let idRegExp = /^[a-zA-Z0-9]{2,14}$/;
      if (!idRegExp.test(e)) {
        this.isValidId = false;
      } else {
        this.isValidId = true;
      }
    },
    async checkpwd(e) {
      let pwdRegExp =
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,16}$/;
      if (!pwdRegExp.test(e)) {
        this.isValidPwd = false;
      } else {
        this.isValidPwd = true;
      }
    },
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
