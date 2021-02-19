<template>
  <div class="home">
    <el-menu class="el-menu-demo" router mode="horizontal" background-color="#209e85" text-color="#ffffff">
      <div class="title">单词学习网</div>
      <el-menu-item index="word">单词学习</el-menu-item>
      <el-menu-item index="book">单词书</el-menu-item>
      <el-menu-item index="3">生词本</el-menu-item>
      <el-menu-item index="4">扩展包</el-menu-item>
      <el-menu-item index="5">设置</el-menu-item>
      <el-dropdown @command="commandHandler" class="user_item">
          <span class="el-dropdown-link">
            {{user.name}}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
          <el-dropdown-item command="setting">设置</el-dropdown-item>
          <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-menu>
    <router-view/>
  </div>
</template>

<script>


export default {
  name: 'Home',
  data(){
    return{
      user: JSON.parse(window.sessionStorage.getItem('user'))
    }
  },
  methods:{
    commandHandler(cmd){
      if (cmd == 'logout') {
        this.$confirm('此操作将注销登录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.getRequest('/logout');
          window.sessionStorage.removeItem('user');
          this.$router.replace('/');
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
      }
    }
  }
}
</script>

<style>
  .el-menu-demo .title{
    font-size: 30px;
    font-family: 华文行楷;
    color: #ffffff;
    padding-right: 80px;
  }
  .el-menu-demo{
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .user_item{
    position: relative;
    left: 25%;
  }
  .user_item .el-dropdown-link{
    color: #ffffff;
  }
</style>
