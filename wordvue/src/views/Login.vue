<template>
  <div class="login_main">
    <el-form ref="loginFrom" :rules="rules" :model="loginForm" class="loginContainer" v-show="flag">
      <h3 style="text-align: center">登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="请输入用户密码"></el-input>
      </el-form-item>
      <div style="text-align: right; margin: 0 0 15px 0" @click="flag=false"><a href="#">注册账号</a></div>
      <el-form-item>
        <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>
      </el-form-item>
    </el-form>

    <el-form ref="loginFrom" :rules="rules" :model="loginForm" class="loginContainer" v-show="!flag">
      <h3 style="text-align: center">注册</h3>
      <el-form-item prop="name">
        <el-input type="text" v-model="loginForm.name" auto-complete="off" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="请输入用户密码"></el-input>
      </el-form-item>
      <div style="text-align: right; margin: 0 0 15px 0" @click="flag=true"><a href="#">已有账号,立即登录</a></div>
      <el-form-item>
        <el-button type="primary" style="width: 100%" @click="register">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "Login",
    data() {
      return{
        loginForm: {
          name: '',
          username: '',
          password: ''
        },
        rules: {
          username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
          password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
        },
        flag: true
      }
    },
    methods: {
      submitLogin(){
        this.$refs.loginFrom.validate((valid) => {
          if (valid) {
            this.postRequest('/login',this.loginForm).then(resp => {
              if (resp) {
                window.sessionStorage.setItem('user',JSON.stringify(resp.obj));
                let path = this.$route.query.redirect;
                this.$router.replace((path=='/'||path==undefined)?'/word':path);
              }
            })
          } else {
            this.$message.error('请输入用户名和密码');
            return false;
          }
        });
      },
      register(){
        this.$refs.loginFrom.validate((valid) => {
          if (valid) {
            this.postRequest('/register',this.loginForm).then(resp => {
              if (resp) {
                this.flag = true;
              }
            })
          } else {
            this.$message.error('请输入完整信息');
            return false;
          }
        });
      }
    }
  }
</script>

<style>
  .loginContainer {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 15px 35px 15px 35px;
    background: #fff;
    border: 1px solid #aaaaaa;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_main{
    filter: brightness(0.6);
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: -1;
    background: url(https://cn-south-227-storage-hitokoto-19627663.oss.dogecdn.com/pic/qf3cu.jpg) center no-repeat;
    background-size: cover;
  }
</style>